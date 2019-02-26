package net.chinfeng.ngx.extensions.httpproxycache.service;

import java.util.List;

import net.chinfeng.ngx.extensions.httpproxycache.cache.Cache;

public interface CacheService {

	/**
	 * 得到缓存的url
	 * 
	 * @return
	 */
	List<String> getCachedUrls();

	/**
	 * 得到缓存
	 * 
	 * @return
	 */
	List<Cache> getCaches();

	/**
	 * 得到匹配的缓存
	 * 
	 * @param pattern antmatcher风格匹配模式
	 * @return
	 */
	List<Cache> getMatchedCaches(String pattern);

	/**
	 * 得到匹配的缓存
	 * 
	 * @param doamin
	 * @param pattern
	 * @return
	 */
	List<Cache> getMatchedCaches(String doamin, String pattern);

	/**
	 * 清除匹配的缓存
	 * 
	 * @param doamin
	 * @param pattern
	 * @return
	 */
	List<Cache> purgeMatchedCaches(String doamin, String pattern);

}
