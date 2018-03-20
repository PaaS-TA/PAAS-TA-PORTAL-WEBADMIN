package org.openpaas.paasta.portal.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Use  web-server.
 *
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WebAdminApplication extends WebMvcConfigurerAdapter {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 *
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebAdminApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
