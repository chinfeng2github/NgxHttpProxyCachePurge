package net.chinfeng.ngx.extensions.httpproxycache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(SpringConfiguration.class)
public class NgxHttpProxyCachePurgeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(NgxHttpProxyCachePurgeApplication.class, args);

//		CacheService cacheService = configurableApplicationContext.getBean(CacheService.class);
//
//		cacheService.purgeMatchedCaches("", "/game-resources/**/*.*").forEach(cache -> {
//			// System.out.println(cache.getCacheKey() + "->" + cache.getFileName());
//		});
	}

}
