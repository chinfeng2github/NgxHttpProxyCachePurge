package net.chinfeng.ngx.extensions.httpproxycache.cache;

/**
 * 缓存信息
 * 
 * @author god
 *
 */
public class Cache {
	/**
	 * 缓存文件名
	 */
	private String fileName;
	/**
	 * 缓存路径
	 */
	private String cacheFilePath;

	/**
	 * 缓存key
	 */
	private String cacheKey;

	/**
	 * 缓存的路径中URL的path
	 */
	//private String urlPath;
	/**
	 * 缓存的域
	 */
	//private String domain;

	public String getFileName() {
		return fileName;
	}

	public String getCacheFilePath() {
		return cacheFilePath;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	void setFileName(String fileName) {
		this.fileName = fileName;
	}

	void setCacheFilePath(String cacheFilePath) {
		this.cacheFilePath = cacheFilePath;
	}

	void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

//	public String getUrlPath() {
//		return urlPath;
//	}
//
//	void setUrlPath(String urlPath) {
//		this.urlPath = urlPath;
//	}
//
//	public String getDomain() {
//		return domain;
//	}
//
//	void setDomain(String domain) {
//		this.domain = domain;
//	}

}
