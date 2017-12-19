package org.openpaas.paasta.portal.web.admin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Use  web-server.
 *
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.openpaas.paasta.portal.web.admin"})
public class WebAdminApplication {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 *
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
//		System.setProperty("spring.config.name", "web-admin-server");
		SpringApplication.run(WebAdminApplication.class, args);
	}
}
