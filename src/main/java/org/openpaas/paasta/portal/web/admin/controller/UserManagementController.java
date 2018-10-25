package org.openpaas.paasta.portal.web.admin.controller;

import org.apache.commons.collections.map.HashedMap;
import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
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
@RestController
public class UserManagementController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private final String V2_URL = "/v2";

    /**
     * 사용자 정보 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/usermgnts"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserInfoMain() {
        return userManagementService.getUserInfoMain();
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/usermgnts/{filter}/user")
    @ResponseBody
    public Map<String, Object> getUserInfoList(@PathVariable String filter, @ModelAttribute UserManagement param) {
        return userManagementService.getUserInfoList("/usermgnts/"+filter+"/user?searchKeyword="+param.getSearchKeyword(), HttpMethod.GET, param, null);
    }

    @GetMapping(V2_URL + "/usermgnts/{userid}")
    @ResponseBody
    public Map<String, Object> getUserInfoList(@PathVariable String userid) {
        return userManagementService.getUserInfoList("/usermgnts/" + userid, HttpMethod.GET, null, null);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param map user id
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/usermgnts/password/email")
    @ResponseBody
    public Map<String, Object> setResetPassword(@RequestBody Map map) {
        return userManagementService.setResetPassword("/users/password/email", HttpMethod.POST, map, null);
    }

    /**
     * 운영권한을 수정한다.
     *
     * @param userid user id
     * @param param  model UserManagement
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{userid}/authority")
    @ResponseBody
    public Map<String, Object> updateOperatingAuthority(@PathVariable String userid, @RequestBody UserManagement param) throws UnsupportedEncodingException {
        return userManagementService.updateOperatingAuthority("/usermgnts/" + userid + "/authority", HttpMethod.PUT, param, null);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param guid user Guid
     * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL + "/usermgnts/{guid}")
    @ResponseBody
    public Map<String, Object> deleteUserAccount(@PathVariable String guid) {
        return userManagementService.deleteUserAccount( guid , HttpMethod.DELETE, null, this.getToken());
    }


    /**
     * 사용자 계정을 등록한다.
     *
     * @param param Info
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/usermgnts/user")
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody Map param) {
        return userManagementService.addUser(HttpMethod.POST, param, this.getToken());
    }


    /**
     * 사용자가 로그인 가능 유무 수정
     *
     * @param guid user Guid
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{guid}/active")
    @ResponseBody
    public Map<String, Object> updateUserActive(@PathVariable String guid, @RequestBody UserManagement param) {
        return userManagementService.updateUserActive("/usermgnts/" + guid + "/active", HttpMethod.PUT, param, this.getToken());
    }
}
