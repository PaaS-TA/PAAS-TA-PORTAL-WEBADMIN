package org.openpaas.paasta.portal.web.admin.config.security.userdetail;

import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.common.UserList;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mg on 2016-05-11.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private ConfigService configService;

    @Autowired
    ConfigRepository configRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List role = new ArrayList();

        role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User user = new User("admin", "admin", role);
        return user;
    }


    public UserList loginByUsernameAndPassword(String username, String password) throws Exception {

        Map<String, Object> resBody = new HashMap();

        resBody.put("id", username);
        resBody.put("password", password);


        List users = new ArrayList();
        int apiLoginCount = 0;
        int adminYN = 0;
        for (ConfigEntity configEntity : configService.getConfigs()) {

            LOGGER.info("> configEntity Name: " + configEntity.getName());
            LOGGER.info("> configEntity ApiUri: " + configEntity.getApiUri());
            LOGGER.info("> configEntity UaaUri: " + configEntity.getUaaUri());


            /*
             * CF_API  로그인 요청
             */
            Map result = null;
            try {
                result = commonService.procCfApiRestTemplate(configEntity.getApiUri(), "/login", configEntity.getAuthorization(), HttpMethod.POST, resBody, "");
                apiLoginCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*
             * DB에 사용자 정보 요청
             */
            Map info = null;
            Map userInfo = null;
            try {
                info = commonService.procCommonApiRestTemplate(configEntity.getApiUri(), "/v2/user/" + username, configEntity.getAuthorization(), HttpMethod.GET, null, "");
                userInfo = (Map) info.get("User");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String token = null;
            Long expireDate = null;
            if (result != null) {
                token = (String) result.get("token");
                expireDate = (Long) result.get("expireDate");
            }

            String name = null;
            String adminYn = null;
            String imgPath = null;

            if (userInfo != null) {
                adminYn = (String) userInfo.get("adminYn");
                imgPath = (String) userInfo.get("imgPath");
                name = (String) userInfo.get("name");
            }


            String roleName;
            if (adminYn != null && adminYn.equals("Y")) {
                roleName = "ROLE_ADMIN";
                adminYN++;
            } else {
                roleName = "ROLE_USER";
                token = null;
            }


            List role = new ArrayList();
            role.add(new SimpleGrantedAuthority(roleName));

            User user = new User(username, password, role);
            user.setName(name);
            user.setToken(token);
            user.setExpireDate(expireDate);
            user.setImgPath(imgPath);
            user.setInfra_name(configEntity.getName());
            user.setAuthorization(configEntity.getAuthorization());
            user.setApiUri(configEntity.getApiUri());
            user.setUaaUri(configEntity.getUaaUri());
            user.setKey(configEntity.getKey());
            LOGGER.info("End Login :: " + configEntity.getApiUri() + " /// " + user.toString());
            users.add(user);
        }

        LOGGER.info("LOGIN :: " + apiLoginCount);
        if (apiLoginCount == 0) {
            throw new BadCredentialsException("Incorrect login information or incorrect contact address.");
        }
        LOGGER.info("adminYN :: " + apiLoginCount);
        if (adminYN == 0) {
            throw new BadCredentialsException("You do not have access..");
        }

        List role = new ArrayList();
        role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserList userList = new UserList(username, password, role);
        userList.setUsers(users);
        return userList;
    }
}
