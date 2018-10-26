package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by MIN on 2018-03-28.
 */
@Service
public class UserManagementService extends Common {

    private final String V2_URL = "/v2";
    @Autowired
    CommonService commonService;

    public ModelAndView getUserInfoMain() {
        return new ModelAndView(){{setViewName("/userManagement/userManagementMain");}};
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserInfoList(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> setResetPassword(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateOperatingAuthority(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteUserAccount(String guid, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+"/user/" + guid + "/all", httpMethod, param, reqToken);
    }

    /**
     * 사용자 계정을 등록한다.
     *
     * @param param Info
     * @return Map(자바클래스)
     */
    public Map<String, Object> addUser(HttpMethod httpMethod, Map param, String reqToken) {
        Map<String, Object> result = commonService.procCfApiRestTemplate(Constants.V2_URL + "/users", HttpMethod.POST, param, this.getToken());
        if((boolean) result.get("result")) {
            param.remove("active");
            param.put("active", param.get("active2").toString());
            commonService.procCommonApiRestTemplate(V2_URL+"/user", httpMethod, param, reqToken);
        }
        return result;
    }

    /**
     * 사용자가 로그인 가능 유무 수정
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateUserActive(String reqUrl, HttpMethod httpMethod, UserManagement param, String reqToken) {
       Map<String, Object> result = commonService.procCfApiRestTemplate(V2_URL+"/user/" + param.getUserGuid() + "/active", httpMethod, param, reqToken);
       param.setActive(result.get("active").toString().equals("true")?"Y":"N");
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }


}
