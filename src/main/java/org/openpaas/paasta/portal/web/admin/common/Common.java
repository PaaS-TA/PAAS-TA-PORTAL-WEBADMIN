package org.openpaas.paasta.portal.web.admin.common;

import org.openpaas.paasta.portal.web.admin.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

/**
 * Common Class
 *
 * @author nawkm
 * @version 1.0
 * @since 2016.5.24
 */
public class Common {

    private static final Logger LOG = LoggerFactory.getLogger(Common.class);

    @Autowired
    public CommonService commonService;

    @Autowired
    public CatalogService catalogService;

    @Autowired
    public ConfigInfoService configInfoService;

    @Autowired
    public CommonCodeService commonCodeService;


    @Autowired
    public OrgSpaceListService orgSpaceListService;

    @Autowired
    public UserManagementService userManagementService;

    @Autowired
    public AuthorityManagementService authorityManagementService;

    @Autowired
    public DomainService domainService;
    /**
     * Get Token
     *
     * @return string string
     */


    @Autowired
    public MessageSource messageSource;


    public String getToken() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ("".equals(auth.getCredentials())) return ((String) auth.getPrincipal().toString());

        final Object authPrincipal = auth.getPrincipal();
        if (authPrincipal instanceof String) return ((String) authPrincipal);

        User user = (User) authPrincipal;

        //token 만료 시간 비교
        if (user.getExpireDate() <= System.currentTimeMillis()) {

            try {
                Map<String, Object> resBody = new HashMap();
                resBody.put("id", user.getUsername());
                resBody.put("password", user.getPassword());

                Map result;

                result = commonService.procCfApiRestTemplate("/login", HttpMethod.POST, resBody, null);

                user.setToken((String) result.get("token"));
                user.setExpireDate((Long) result.get("expireDate"));

                // session에 적용
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                LOG.info("new token : " + user.getToken());

            } catch (Exception e) {
                throw new BadCredentialsException(e.getMessage());
            }
        }

        LOG.info("############################# Expires In : " + (user.getExpireDate() - System.currentTimeMillis()) / 1000 + " sec");

        String token = user.getToken();

        return token;
    }


    public User updateToken(User user) {
        try {
            Map<String, Object> resBody = new HashMap();
            resBody.put("id", user.getUsername());
            resBody.put("password", user.getPassword());

            Map result;

            result = commonService.procCfApiRestTemplate(user.getApiUri(), "/login", user.getAuthorization(), HttpMethod.POST, resBody, null);

            user.setToken((String) result.get("token"));
            user.setExpireDate((Long) result.get("expireDate"));


        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
        return user;
    }


    /*
     * 1. Token이 만료되면, 만료된 토큰을 업데이트한다.
     * 2. 업데이트 한 토큰을 다시 세션에 저장한다.
     * 3. Token 만료되지 않은 경우, 업데이트는 하지 않는다.
     */

    public Map getServerInfo(int key) {
        Map map = new HashMap();
        try {
            String password = "";
            int tokenExpired = 0;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            final Object authPrincipal = auth.getPrincipal();
            UserList userList = (UserList) authPrincipal;
            List<User> users = userList.getUsers();
            List<User> refreshUsers = new ArrayList<>();
            for (Object obj : users) {
                User user = (User) obj;
                if (user.getExpireDate() <= System.currentTimeMillis()) {
                    password = user.getPassword();
                    user = updateToken(user);
                    tokenExpired++;
                }

                if (user.getKey() == key) {
                    map.put("token", user.getToken());
                    map.put("apiuri", user.getApiUri());
                    map.put("uaauri", user.getUaaUri());
                    map.put("authorization", user.getAuthorization());
                }
                refreshUsers.add(user);
            }

            if (tokenExpired > 0) {
                Collection<? extends GrantedAuthority> authorities = null;
                List role = new ArrayList();
                role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities = role;
                userList.setUsers(refreshUsers);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userList, password, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public List getServerInfos() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            final Object authPrincipal = auth.getPrincipal();
            UserList userList = (UserList) authPrincipal;
            List users = userList.getUsers();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

