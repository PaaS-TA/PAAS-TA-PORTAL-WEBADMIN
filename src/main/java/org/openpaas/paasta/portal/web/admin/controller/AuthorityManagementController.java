package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 권한 컨트롤러 - 권한그룹과 권한을 조회, 수정, 삭제한다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.09.19 최초작성
 */
@Controller
@RequestMapping(value = {"/authority"})
public class AuthorityManagementController extends Common{

    @Autowired
    private CommonService commonService;

    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    @RequestMapping(value = {"/authorityMain"}, method = RequestMethod.GET)
    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView(){{setViewName("/authority/authorityMain");}};
    }

    /**
     * Gets authority groups.
     *
     * @param param the param
     * @return the authority groups
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getAuthorityGroups"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAuthorityGroups(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/getAuthorityGroups", HttpMethod.POST, param, null);
    }

    /**
     * Gets uaa user info.
     *
     * @param param the param
     * @return the uaa user info
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getGroupMembersInfo"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUaaUserInfo(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/getGroupMembersInfo", HttpMethod.POST, param, null);
    }

    /**
     * Create authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/createAuthorityGroup"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createAuthorityGroup(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/createAuthorityGroup", HttpMethod.POST, param, null);
    }

    /**
     * Delete authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteAuthorityGroup"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteAuthorityGroup(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/deleteAuthorityGroup", HttpMethod.POST, param, null);
    }

    /**
     * Add group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/addGroupMembers"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addGroupMembers(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/addGroupMembers", HttpMethod.POST, param, null);
    }

    /**
     * Delete group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteGroupMembers"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteGroupMembers(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/authority/deleteGroupMembers", HttpMethod.POST, param, null);
    }

    /**
     * Gets user name list.
     *
     * @param param the param
     * @return the user name list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserNameList(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/user/getUserInfo", HttpMethod.GET, param, null);
    }
}
