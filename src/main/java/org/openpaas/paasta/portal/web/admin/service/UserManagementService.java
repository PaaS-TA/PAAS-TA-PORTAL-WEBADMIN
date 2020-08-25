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

    @Autowired
    CommonService commonService;

    public ModelAndView getUserInfoMain() {
        return new ModelAndView() {{
            setViewName("/userManagement/userManagementMain");
        }};
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserInfoList(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> setResetPassword(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }


    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateOperatingAuthority(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteUserAccount(int key, String guid, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/user/" + guid + "/all", httpMethod, param);
    }

    /**
     * 사용자 계정을 등록한다.
     *
     * @param param Info
     * @return Map(자바클래스)
     */
    public Map<String, Object> addUser(int key,HttpMethod httpMethod, Map param) {
        Map<String, Object> result = commonService.procCfApiRestTemplate(key,Constants.V3_URL + "/users", HttpMethod.POST, param);
        if ((boolean) result.get("result")) {
            param.remove("active");
            param.put("active", param.get("active2").toString());
            commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/user", httpMethod, param);
        }
        return result;
    }

    /**
     * 사용자가 로그인 가능 유무 수정
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateUserActive(int key, String reqUrl, HttpMethod httpMethod, UserManagement param) {
        Map<String, Object> result = commonService.procCfApiRestTemplate(key,Constants.V3_URL + "/user/" + param.getUserGuid() + "/active", httpMethod, param);
        param.setActive(result.get("active").toString().equals("true") ? "Y" : "N");
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }


}
