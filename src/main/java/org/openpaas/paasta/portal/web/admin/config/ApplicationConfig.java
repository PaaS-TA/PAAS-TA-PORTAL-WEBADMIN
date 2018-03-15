package org.openpaas.paasta.portal.web.admin.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * The type Application config.
 */
@EnableWebMvc
@Configuration
//@ComponentScan({ "com.openpaas.portal.web.admin.*" })
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
	/**
	 * View resolver internal resource view resolver.
	 *
	 * @return the internal resource view resolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/messages/message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}

	/**
	 * Mapping jackson 2 http message converter mapping jackson 2 http message converter.
	 *
	 * @return the mapping jackson 2 http message converter
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		return converter;
	}



	@Bean
	public static ConfigureRedisAction configureRedisAction() {return ConfigureRedisAction.NO_OP;}

	@Value("${spring.redis.host}")
	String REDISURL;
	@Value("${spring.redis.port}")
	String REDISPORT;

	@Bean
	public boolean printRedis(){
		LOGGER.info("##########################################");
		LOGGER.info("##########################################");
		LOGGER.info("#########Redis Connection info############");
		LOGGER.info("Connection Url : " + REDISURL);
		LOGGER.info("Connection Port: " + REDISPORT);
		LOGGER.info("##########################################");
		LOGGER.info("##########################################");
		return true;
	}

}
