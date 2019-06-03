package org.openpaas.paasta.portal.web.admin.config.security;

/**
 * Created by mg on 2016-05-12.
 */

import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.common.UserList;
import org.openpaas.paasta.portal.web.admin.config.security.userdetail.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    /*
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;
    */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Collection<? extends GrantedAuthority> authorities = null;

        UserList users = null;

        try {
            users = customUserDetailsService.loginByUsernameAndPassword(username, password);
        } catch (UsernameNotFoundException e) {
            LOGGER.info(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        } catch (BadCredentialsException e) {
            LOGGER.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new BadCredentialsException("");
        }


        List role = new ArrayList();
        role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities = role;
        return new UsernamePasswordAuthenticationToken(users, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}