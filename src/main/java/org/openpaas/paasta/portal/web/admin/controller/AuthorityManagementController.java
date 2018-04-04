package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.service.AuthorityManagementService;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 권한 컨트롤러 - 권한그룹과 권한을 조회, 수정, 삭제한다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.09.19 최초작성
 */
@RestController
public class AuthorityManagementController extends Common{

    @Autowired
    private AuthorityManagementService authorityManagementService;
    private final String V2_URL = "/v2";
    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    @GetMapping("/authority")
    public ModelAndView getOrgSpaceListMain() {
        return authorityManagementService.getOrgSpaceListMain();
    }

    /**
     * Gets authority groups.
     *
     *
     * @return the authority groups
     * @throws Exception the exception
     */
    @GetMapping(V2_URL+"/authority")
    @ResponseBody
    public Map<String, Object> getAuthorityGroups() throws Exception {
        return authorityManagementService.getAuthorityGroups("/authority", HttpMethod.GET, null, null);
    }

    /**
     * Gets uaa user info.
     *
     * @return the uaa user info
     * @throws Exception the exception
     */
    @GetMapping(V2_URL+"/authority/group")
    @ResponseBody
    public Map<String, Object> getUaaUserInfo() throws Exception {
        return authorityManagementService.getUaaUserInfo("/authority/group", HttpMethod.GET, null, null);
    }

    /**
     * Create authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @PostMapping(V2_URL+"/authority/group")
    @ResponseBody
    public Map<String, Object> createAuthorityGroup(@RequestBody Map param) throws Exception {
        return authorityManagementService.createAuthorityGroup("/authority/group", HttpMethod.POST, param, null);
    }

    /**
     * Delete authority group map.
     * @param groupguid the group guid
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping(V2_URL+"/authority/group/{groupguid}")
    @ResponseBody
    public Map<String, Object> deleteAuthorityGroup(@PathVariable String groupguid, @RequestBody Map param) throws Exception {
        return authorityManagementService.deleteAuthorityGroup("/authority/group/" + groupguid, HttpMethod.DELETE, param, null);
    }

    /**
     * Add group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @PostMapping(V2_URL+"/authority/member")
    @ResponseBody
    public Map<String, Object> addGroupMembers(@RequestBody Map param) throws Exception {
        return authorityManagementService.addGroupMembers("/authority/member", HttpMethod.POST, param, null);
    }

    /**
     * Delete group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping(V2_URL+"/authority/member/{groupguid}")
    @ResponseBody
    public Map<String, Object> deleteGroupMembers(@PathVariable String groupguid, @RequestBody Map param) throws Exception {
        return authorityManagementService.deleteGroupMembers("/authority/member/"+groupguid, HttpMethod.DELETE, param, null);
    }

    /**
     * Gets user name list.
     *
     * @return the user name list
     * @throws Exception the exception
     *
     */
    @GetMapping(V2_URL+"/users")
    @ResponseBody
    public Map<String, Object> getUserNameList() throws Exception {
        return authorityManagementService.getUserNameList("/users", HttpMethod.GET, null, null);
    }
}
