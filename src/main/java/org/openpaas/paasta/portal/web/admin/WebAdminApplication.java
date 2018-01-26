package org.openpaas.paasta.portal.web.admin;

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
		SpringApplication.run(WebAdminApplication.class, args);
	}
}
