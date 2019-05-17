package org.openpaas.paasta.portal.web.admin.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

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

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/external/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/index")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/index?error")
                .usernameParameter("id").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/index?logout");


    }


    private CorsConfigurationSource corsConfiguration(){
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.addAllowedOrigin("*");
                config.setAllowCredentials(true);
                return config;
            }
        };
    }


}
//Spring boot Admin 정보 접근 URL -  시작

//                        .antMatchers("/info**").permitAll()
//                        .antMatchers("/env**").permitAll()
//                        .antMatchers("/metrics**").permitAll()
//                        .antMatchers("/trace**").permitAll()
//                        .antMatchers("/dump**").permitAll()
//                        .antMatchers("/jolokia**").permitAll()
//                        .antMatchers("/configprops**").permitAll()
//                        .antMatchers("/logfile**").permitAll()
//                        .antMatchers("/logging**").permitAll()
//                        .antMatchers("/refresh**").permitAll()
//                        .antMatchers("/flyway**").permitAll()
//                        .antMatchers("/liquibase**").permitAll()
//                        .antMatchers("/httptrace**").permitAll()
//                        .antMatchers("/threaddump**").permitAll()
//                        .antMatchers("/heapdump**").permitAll()
//                        .antMatchers("/loggers**").permitAll()
//                        .antMatchers("/auditevents**").permitAll()
//                        .antMatchers("/hystrix.stream**").permitAll()
//                        .antMatchers("/docs**").permitAll()
//                        .antMatchers("/jmx**").permitAll()
//                        .antMatchers("/management/**").permitAll()
//                        .antMatchers("/applications/**").permitAll()
//                        .antMatchers("/applications/**/**").permitAll()
//                        .antMatchers("/applications/**/**/**").permitAll()
//                        .antMatchers("/health**").permitAll()
//                        .antMatchers("/health/**").permitAll()
//Spring boot Admin 정보 접근 URL - 끝