package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.openpaas.paasta.portal.web.admin.service.SecurityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class SecurityGroupController extends Common {

    @Autowired
    SecurityGroupService securityGroupService;

    /**
     * 시큐리티 그룹 메인 화면
     *
     * @return model and view
     */
    @GetMapping("/securitygroups")
    public ModelAndView securityGroup(){
        return securityGroupService.getSecurityGroupMain();
    }


    /**
     * 시큐리티 그룹 리스트를 가져온다.
     *
     * @param page  SecurityGroup List page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+ "/securitygroups/{page}")
    public Map<String, Object> getSecurityGroupList(HttpServletRequest request,  @PathVariable String page) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.getSecurityGroupList(Integer.parseInt(key), page);
    }

    /**
     * 시큐리티그룹을 상세조회한다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+ "/securitygroup/{securityid}")
    public Map<String, Object> getSecurityGroupResponse(HttpServletRequest request, @PathVariable String securityid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.getSecurityGroupResponse(Integer.parseInt(key), securityid);
    }

    /**
     * 시큐리티그룹을 생성한다.
     *
     * @param groupname  the SecurityGroup Name
     * @param rule the Object
     * @return Map<String, Object>
     * @throws Exception the exception
     * @author 박철한
     */
    @PostMapping(Constants.V2_URL+"/securitygroup/{groupname:.+}")
    public Map<String, Object> createSecurityGroupResponse(HttpServletRequest request, @PathVariable String groupname, @RequestBody Object rule) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.createSecurityGroupResponse(Integer.parseInt(key), groupname, rule);
    }


    /**
     * 시큐리티그룹을 수정한다.
     *
     * @param securityid  the security guid
     * @param groupname  the SecurityGroup Name
     * @param rule the Object
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @PutMapping(Constants.V2_URL+"/securitygroup/{securityid}/{groupname:.+}")
    public Map<String, Object> updateSecurityGroupResponse(HttpServletRequest request,@PathVariable String securityid, @PathVariable String groupname, @RequestBody Object rule) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.updateSecurityGroupResponse(Integer.parseInt(key), securityid, groupname, rule);
    }


    /**
     * 시큐리티그룹을 삭제한다.
     *
     * @param securityid  the security guid
     * @return Map<String, Object>
     * @throws Exception the exception
     * @author 박철한
     */
    @DeleteMapping(Constants.V2_URL+"/securitygroup/{securityid}")
    public Map<String, Object> deleteSecurityGroupResponse(HttpServletRequest request, @PathVariable String securityid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.deleteSecurityGroupResponse(Integer.parseInt(key), securityid);
    }


    /**
     * 준비 응용 프로그램에 사용할 보안 그룹 목록에 보안 그룹을 바인딩합니다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @PutMapping(Constants.V2_URL+"/securitygroup/{securityid}/staging")
    public Map<String, Object> setSecurityGroupStagingDefaultResponse(HttpServletRequest request, @PathVariable String securityid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.setSecurityGroupStagingDefaultResponse(Integer.parseInt(key), securityid);
    }


    /**
     * 시큐리티 스테이징 그룹을 조회한다.
     *
     * @param page  listStagingDefaults page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+"/securitygroups/staging/{page}")
    public Map<String, Object>  listSecurityGroupStagingDefaultsResponse(HttpServletRequest request, @PathVariable int page) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.listSecurityGroupStagingDefaultsResponse(Integer.parseInt(key), page);
    }


    /**
     * 스테이징 시큐리티 그룹을 언바인드 한다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @DeleteMapping(Constants.V2_URL+"/securitygroup/{securityid}/staging")
    public Map<String, Object> removeSecurityGroupStaging(HttpServletRequest request, @PathVariable String securityid)  throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.removeSecurityGroupStaging(Integer.parseInt(key), securityid);
    }


    /**
     *
     * 응용 프로그램 실행에 사용할 보안 그룹 목록에 보안 그룹을 바인딩합니다.
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @PutMapping(Constants.V2_URL+"/securitygroup/{securityid}/running")
    public Map<String, Object> setSecurityGroupRunningDefaultResponse(HttpServletRequest request, @PathVariable String securityid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.setSecurityGroupRunningDefaultResponse(Integer.parseInt(key), securityid);
    }


    /**
     * 실행중인 응용 프로그램에 대한 시큐리티 그룹 조회
     *
     * @param page  ListSecurityGroups page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+"/securitygroups/running/{page}")
    public Map<String, Object> listSecurityGroupRunningDefaultsResponse(HttpServletRequest request, @PathVariable int page) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.listSecurityGroupRunningDefaultsResponse(Integer.parseInt(key), page);
    }


    /**
     * 실행중인 응용 프로그램에 대한 시큐리티 그룹 언바인드
     *
     * @param securityid  the security guid
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @DeleteMapping(Constants.V2_URL+"/securitygroup/{securityid}/running")
    public Map<String, Object> removeSecurityGroupRunning(HttpServletRequest request, @PathVariable String securityid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.removeSecurityGroupRunning(Integer.parseInt(key), securityid);
    }


    /**
     * 공간 시큐리티그룹을 조회한다.
     *
     * @param page  ListSecurityGroups page
     * @param securityid the security guid
     * @return ListSecurityGroupSpacesResponse
     * @throws Exception the exception
     */
    @GetMapping(Constants.V2_URL+"/securitygroup/{securityid}/{page}")
    public Map<String, Object> listSecurityGroupSpacesResponse(HttpServletRequest request, @PathVariable String securityid, @PathVariable int page) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.listSecurityGroupSpacesResponse(Integer.parseInt(key), securityid, page);
    }

    /**
     * 시큐리티그룹과 공간을 연결한다.
     *
     * @param securityid  the security guid
     * @param spaceid the space guid
     * @return AssociateSecurityGroupSpaceResponse
     * @throws Exception the exception
     */
    @PutMapping(Constants.V2_URL+"/securitygroup/{securityid}/spaces/{spaceid}")
    public Map<String, Object> associateSecurityGroupSpaceResponse(HttpServletRequest request, @PathVariable String securityid, @PathVariable String spaceid) throws Exception {
        System.out.println("*****************************");
        String key = request.getParameter("key");
        return securityGroupService.associateSecurityGroupSpaceResponse(Integer.parseInt(key), securityid, spaceid);
    }

    /**
     * 공간 시큐리티 그룹을 언바인드 한다.
     *
     * @param securityid  the security guid
     * @param spaceid the space guid
     * @return Map
     * @throws Exception the exception
     */
    @DeleteMapping(Constants.V2_URL+"/securitygroup/{securityid}/spaces/{spaceid}")
    public Map<String, Object> removeSecurityGroupSpace(HttpServletRequest request, @PathVariable String securityid, @PathVariable String spaceid) throws Exception {
        String key = request.getParameter("key");
        return securityGroupService.removeSecurityGroupSpace(Integer.parseInt(key), securityid, spaceid);
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
