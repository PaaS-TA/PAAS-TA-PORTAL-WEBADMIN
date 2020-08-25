package org.openpaas.paasta.portal.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
//@EnableDiscoveryClient
public class WebAdminApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }

}
