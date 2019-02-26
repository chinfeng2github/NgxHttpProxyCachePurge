package net.chinfeng.ngx.extensions.httpproxycache.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.chinfeng.ngx.extensions.httpproxycache.cache.Cache;
import net.chinfeng.ngx.extensions.httpproxycache.cache.CacheReader;
import net.chinfeng.ngx.extensions.httpproxycache.service.CacheService;

//同时多人下载同一个回源时....
//[root@engine 62]# ll
//total 116996
//-rw------- 1 root root 51633850 Feb 16 13:37 ad562e7498d663bf014d60fae59d6295
//-rw------- 1 root root 41418752 Feb 16 13:38 ad562e7498d663bf014d60fae59d6295.0000000011
//-rw------- 1 root root 26738688 Feb 16 13:38 ad562e7498d663bf014d60fae59d6295.0000000012

@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Value("${ngx.proxyCachePath}")
	String proxyCachePath;

	@Value("${ngx.proxyPurgeApi}")
	String proxyPurgeApi;

	@Override
	public List<String> getCachedUrls() {
		List<String> list = new ArrayList<String>();

		getCaches().forEach(cache -> {
			list.add(cache.getCacheKey());
		});

		return list;
	}

	@Override
	public List<Cache> getCaches() {
		File cacheDirectory = new File(proxyCachePath);
		List<Cache> caches = new ArrayList<Cache>();

		Collection<File> cachedFiles = FileUtils.listFiles(cacheDirectory, FileFilterUtils.fileFileFilter(),
				FileFilterUtils.directoryFileFilter());
		cachedFiles.forEach(file -> {
			CacheReader cacheReader = new CacheReader(file.getAbsolutePath());
			Cache cache = cacheReader.read();
			caches.add(cache);
		});

		return caches;
	}

	@Override
	public List<Cache> getMatchedCaches(String pattern) {
		return getMatchedCaches(null, pattern);
	}

	@Override
	public List<Cache> getMatchedCaches(String doamin, String pattern) {
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		List<Cache> caches = new ArrayList<Cache>();

		getCaches().forEach(cache -> {
			// TODO 域名处理
			if (antPathMatcher.match(pattern, cache.getCacheKey())) {
				caches.add(cache);
			}
		});

		return caches;
	}

	@Override
	public List<Cache> purgeMatchedCaches(String doamin, String pattern) {
		List<Cache> caches = getMatchedCaches(doamin, pattern);
		caches.forEach(cache -> {
			RestTemplate restTemplate = new RestTemplate();

			String url = proxyPurgeApi + cache.getCacheKey();

			LOGGER.info("cache purge:" + url);

			int statusCodeValue = 0;
			try {
				ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
				String body = responseEntity.getBody();
				// HttpStatus statusCode = responseEntity.getStatusCode();
				statusCodeValue = responseEntity.getStatusCodeValue();
				StringBuffer result = new StringBuffer();
				result.append("responseEntity.getBody()：").append(body);
				LOGGER.debug("result:" + result.toString());
			} catch (HttpClientErrorException httpClientErrorException) {
				statusCodeValue = httpClientErrorException.getStatusCode().value();
			}

			LOGGER.info("cache purge statusCodeValue:" + statusCodeValue);
		});

		return caches;
	}

	public static void main(String[] args) {
		new CacheServiceImpl().purgeMatchedCaches("", "/game-resources/**/*.*").forEach(cache -> {
			// System.out.println(cache.getCacheKey() + "->" + cache.getFileName());
		});
	}
}
