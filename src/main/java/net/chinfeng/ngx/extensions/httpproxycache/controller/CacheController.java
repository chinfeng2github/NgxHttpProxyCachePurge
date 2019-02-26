package net.chinfeng.ngx.extensions.httpproxycache.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.chinfeng.ngx.extensions.httpproxycache.cache.Cache;
import net.chinfeng.ngx.extensions.httpproxycache.service.CacheService;

@Controller
@RequestMapping("cache")
public class CacheController {
	@Resource
	private CacheService cacheService;

	@RequestMapping("purge")
	@ResponseBody
	public List<Cache> purge(String pattern) {
		return cacheService.purgeMatchedCaches(null, pattern);
	}

	@RequestMapping("list")
	@ResponseBody
	public List<Cache> list() {
		return cacheService.getCaches();
	}

}
