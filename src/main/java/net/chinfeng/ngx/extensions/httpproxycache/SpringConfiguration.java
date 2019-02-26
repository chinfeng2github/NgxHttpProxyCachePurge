//package net.chinfeng.ngx.extensions.httpproxycache;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configurable
//public class SpringConfiguration {
//
//	/**
//	 * spring mvc 配置
//	 */
//	@Configuration
//	@EnableWebMvc
//	@ComponentScan({ "net.chinfeng.ngx.extensions.httpproxycache.controller" })
//	public class SpringConfigurer_WebMvc implements WebMvcConfigurer {
//		// public class SpringConfigurer_WebMvc extends WebMvcConfigurerAdapter {
//		// @Resource
//		// private SecurityInterceptor securityInterceptor;
//
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//		}
//
//	}
//
//}
