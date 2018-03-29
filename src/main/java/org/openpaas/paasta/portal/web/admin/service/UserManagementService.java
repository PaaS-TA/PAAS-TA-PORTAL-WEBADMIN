package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by MIN on 2018-03-28.
 */
@Service
public class UserManagementService extends Common {

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
        return commonService.procCommonApiRestTemplate(reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> setResetPassword(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateOperatingAuthority(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(reqUrl, httpMethod, param, reqToken);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteUserAccount(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(reqUrl, httpMethod, param, reqToken);
    }


}
