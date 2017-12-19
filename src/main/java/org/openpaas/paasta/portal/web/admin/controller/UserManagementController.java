package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 사용자 목록, 사용자 삭제 및 운영자 권한 부여 등의 API 를 호출 하는 컨트롤러이다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.08.31 최초작성
 */
@Controller
@RequestMapping(value = {"/userManagement"})
public class UserManagementController extends Common {

    /**
     * 사용자 정보 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/userManagementMain"}, method = RequestMethod.GET)
    public ModelAndView getUserInfoMain() {
        return new ModelAndView(){{setViewName("/userManagement/userManagementMain");}};
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getUserInfoList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserInfoList(@RequestBody UserManagement param) {
        return commonService.procRestTemplate("/userManagement/getUserInfoList", HttpMethod.POST, param, this.getToken());
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/setResetPassword"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setResetPassword(@RequestBody UserManagement param) {
        return commonService.procRestTemplate("/userManagement/setResetPassword", HttpMethod.POST, param, this.getToken());
    }


    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/updateOperatingAuthority"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateOperatingAuthority(@RequestBody UserManagement param) {
        return commonService.procRestTemplate("/userManagement/updateOperatingAuthority", HttpMethod.POST, param, this.getToken());
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/deleteUserAccount"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUserAccount(@RequestBody UserManagement param) {
        return commonService.procRestTemplate("/userManagement/deleteUserAccount", HttpMethod.POST, param, this.getToken());
    }
}
