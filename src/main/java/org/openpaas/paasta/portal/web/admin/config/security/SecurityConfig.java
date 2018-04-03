package org.openpaas.paasta.portal.web.admin.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure global.
	 *
	 * @param authenticationMgr the authentication mgr
	 * @throws Exception the exception
	 */

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {

		authenticationMgr.authenticationProvider(customAuthenticationProvider());
	}

	@Bean
	AuthenticationProvider customAuthenticationProvider() {

		CustomAuthenticationProvider impl = new CustomAuthenticationProvider();
		return impl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/*/*").access("hasRole('ROLE_ADMIN')")
//				.and()
//				.formLogin().loginPage("/index")
//				.defaultSuccessUrl("/main")
//				.failureUrl("/index?error")
//				.usernameParameter("id").passwordParameter("password")
//				.and()
//				.logout().logoutSuccessUrl("/index?logout");

        http
				.csrf().disable()
                .authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/dashboard").access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin().loginPage("/index")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/index?error")
				.usernameParameter("id").passwordParameter("password");



	}

}
