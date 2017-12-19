package org.openpaas.paasta.portal.web.admin.config.security.userdetail;

import org.openpaas.paasta.common.security.userdetails.User;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
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

    @Autowired
    private CommonService commonService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List role = new ArrayList();

        role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User user = new User("admin", "admin", role);
        // 만약 데이터가 없을 경우 익셉션
        //if (user == null) throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");

        return user;
    }

    public UserDetails loginByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {

        Map<String,Object> resBody = new HashMap();

        resBody.put("id", username);
        resBody.put("password", password);
/*

        HttpEntity requestEntity = new HttpEntity(resBody);
*/

        Map result;


        try{
            result = commonService.procRestTemplate("/login", HttpMethod.POST, resBody, "");

            /*ResponseEntity rssResponse = restTemplate.exchange(
                    API_URL + "/login",
                    HttpMethod.POST,
                    requestEntity,
                    Map.class);

            result = (Map)rssResponse.getBody();
*/
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());

        }


        List role = new ArrayList();


        for(String auth : (List<String>)result.get("auth"))
        {
            role.add(new SimpleGrantedAuthority(auth));
        }


        User user = new User((String)result.get("id"), (String)result.get("password"), role);

        user.setToken((String)result.get("token"));
        user.setExpireDate((Long)result.get("expireDate"));
        user.setName((String)result.get("name"));
        user.setImgPath((String)result.get("imgPath"));

        return user;
    }
}
