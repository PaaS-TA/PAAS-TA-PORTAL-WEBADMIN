package org.openpaas.paasta.portal.web.admin.config.security.userdetail;

import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.RootService;
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
    private RootService rootService;

    @Autowired
    ConfigRepository configRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List role = new ArrayList();

        role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User user = new User("admin", "admin", role);
        return user;
    }

    public UserDetails loginByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {

        Map<String, Object> resBody = new HashMap();

        resBody.put("id", username);
        resBody.put("password", password);

        Map result = null;

        try {
            for (ConfigEntity configEntity: rootService.getConfigs()) {
                LOGGER.info("> configEntity Name: "+ configEntity.getName());
                LOGGER.info("> configEntity ApiUri: "+ configEntity.getApiUri());
                LOGGER.info("> configEntity UaaUri: "+ configEntity.getUaaUri());
                /*getApiUri,resBody(username/password)*/
                result = commonService.procCfApiRestTemplateTest(configEntity.getApiUri(),"/login", HttpMethod.POST, resBody, "");
            }
//            result = commonService.procCfApiRestTemplate("/login", HttpMethod.POST, resBody, "");

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException(e.getMessage());
        }
        Map info = new HashMap();
        try {
            info = commonService.procCommonApiRestTemplate("/v2/user/" + username, HttpMethod.GET, null, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map userInfo = (Map) info.get("User");

        List role = new ArrayList();
        if (userInfo.get("adminYn").toString().equals("Y")) {
            role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            role.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        List userList = new ArrayList();
        User user = new User((String) result.get("id"), (String) result.get("password"), role);

        user.setToken((String) result.get("token"));
        user.setExpireDate((Long) result.get("expireDate"));
        user.setName((String) userInfo.get("name"));
        user.setImgPath((String) userInfo.get("imgPath"));

        userList.add(user);
        return user;
    }
}