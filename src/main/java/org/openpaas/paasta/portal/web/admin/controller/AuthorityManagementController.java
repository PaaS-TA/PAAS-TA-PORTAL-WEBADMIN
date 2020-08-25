package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 권한 컨트롤러 - 권한그룹과 권한을 조회, 수정, 삭제한다.
 *
 * @version 1.0
 * @since 2016.09.19 최초작성
 */
@RestController
public class AuthorityManagementController extends Common{

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
    public Map<String, Object> getAuthorityGroups(HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.getAuthorityGroups(Integer.parseInt(key),"/authority", HttpMethod.GET, null);
    }

    /**
     * Gets uaa user info.
     *
     * @return the uaa user info
     * @throws Exception the exception
     */
    @GetMapping(V2_URL+"/authority/group")
    @ResponseBody
    public Map<String, Object> getUaaUserInfo(HttpServletRequest request) throws Exception {
        //사용안함???
        String key = request.getParameter("key");
        return authorityManagementService.getUaaUserInfo(Integer.parseInt(key),"/authority/group", HttpMethod.GET, null);
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
    public Map<String, Object> createAuthorityGroup(HttpServletRequest request, @RequestBody Map param) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.createAuthorityGroup(Integer.parseInt(key),"/authority/group", HttpMethod.POST, param);
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
    public Map<String, Object> deleteAuthorityGroup(@PathVariable String groupguid, HttpServletRequest request, @RequestBody Map param) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.deleteAuthorityGroup(Integer.parseInt(key),"/authority/group/" + groupguid, HttpMethod.DELETE, param);
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
    public Map<String, Object> addGroupMembers(HttpServletRequest request, @RequestBody Map param) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.addGroupMembers(Integer.parseInt(key),"/authority/member", HttpMethod.POST, param);
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
    public Map<String, Object> deleteGroupMembers(@PathVariable String groupguid, HttpServletRequest request, @RequestBody Map param) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.deleteGroupMembers(Integer.parseInt(key),"/authority/member/"+groupguid, HttpMethod.DELETE, param);
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
    public Map<String, Object> getUserNameList(HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return authorityManagementService.getUserNameList(Integer.parseInt(key),"/users", HttpMethod.GET, null);
    }

    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
