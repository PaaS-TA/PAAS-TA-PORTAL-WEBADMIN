package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

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
        return userManagementService.getUserInfoMain();
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/UserInfoList"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserInfoList() {
        return userManagementService.getUserInfoList("/userManagement/UserInfoList", HttpMethod.GET, null, null);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param userId user id
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/ResetPassword/{userId}"}, method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> setResetPassword(@PathVariable String userId, @RequestBody UserManagement param) {
        return userManagementService.setResetPassword("/userManagement/ResetPassword/"+userId, HttpMethod.PUT, param, null);
    }


    /**
     * 운영권한을 부여한다.
     * @param userId user id
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/OperatingAuthority/{userId}"}, method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateOperatingAuthority(@PathVariable String userId, @RequestBody UserManagement param ) throws UnsupportedEncodingException {
        return userManagementService.updateOperatingAuthority("/userManagement/OperatingAuthority/"+ userId, HttpMethod.PUT, param, null);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param userId userId
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/deleteUserAccount/{userId}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteUserAccount(@PathVariable String userId, @RequestBody UserManagement param) {
        return userManagementService.deleteUserAccount("/userManagement/deleteUserAccount/" + userId, HttpMethod.DELETE, param, null);
    }
}
