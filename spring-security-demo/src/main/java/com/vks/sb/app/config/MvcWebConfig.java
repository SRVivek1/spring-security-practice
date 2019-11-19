/**
 * 
 */
package com.vks.sb.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author vivek
 *
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class MvcWebConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		final InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver(
				"/WEB-INF/pages/jsp/", ".jsp");
		internalResourceViewResolver.setViewClass(JstlView.class);
		
		registry.viewResolver(internalResourceViewResolver);
		//WebMvcConfigurer.super.configureViewResolvers(registry);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("index");
	}
}
