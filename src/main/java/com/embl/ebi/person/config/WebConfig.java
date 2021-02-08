package com.embl.ebi.person.config;

import com.embl.ebi.person.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web client configuration
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired // Logging interceptor
	private LogInterceptor logInterceptor;

	/**
	 * Add logging interceptor to the registry
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}

}
