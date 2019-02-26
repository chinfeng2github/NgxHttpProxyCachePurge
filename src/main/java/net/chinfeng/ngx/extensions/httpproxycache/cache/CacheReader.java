package net.chinfeng.ngx.extensions.httpproxycache.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * nginx cache reader, read nginx cache file and anlaysis
 * 
 * @author god
 *
 */
public class CacheReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheReader.class);
	private String file;

	public CacheReader(String file) {
		this.file = file;
	}

	public Cache read() {
		Cache cache = new Cache();
		File cachedFile = new File(file);
		cache.setFileName(cachedFile.getAbsolutePath());
		cache.setCacheKey(getKeyFromCacheFile(cachedFile));
//		try {
//			URL url = new URL(cache.getCacheKey());
//			cache.setDomain(url.getHost());
//			cache.setUrlPath(url.getPath());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		return cache;
	}

	private String getKeyFromCacheFile(File cachedFile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(cachedFile));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains("KEY: ")) {
					return keyValueExcatFromLine(tempString).getValue();
				}
			}

		} catch (Exception e) {
			LOGGER.warn("getKeyFromCacheFile" + cachedFile.getAbsolutePath() + " err", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return "";
	}

	/**
	 * exact the line in cache file which indicate nginx cache key
	 * 
	 * @param line
	 * @return
	 */
	private KeyValue keyValueExcatFromLine(String line) {
		KeyValue keyValue = new KeyValue();
		String[] stringArr = line.split(": ");
		keyValue.setKey(StringUtils.trimAllWhitespace(stringArr[0]));
		keyValue.setValue(StringUtils.trimAllWhitespace(stringArr[1]));
		return keyValue;
	}

	public class KeyValue {
		String key;
		String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

}
