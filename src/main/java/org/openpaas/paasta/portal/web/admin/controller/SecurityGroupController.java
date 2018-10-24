package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.service.SecurityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public Map<String, Object> getSecurityGroupList(@PathVariable String page) throws Exception {
        return securityGroupService.getSecurityGroupList(page);
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
    public Map<String, Object> getSecurityGroupResponse(@PathVariable String securityid) throws Exception {
        return securityGroupService.getSecurityGroupResponse(securityid);
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
    public Map<String, Object> createSecurityGroupResponse(@PathVariable String groupname, @RequestBody Object rule) throws Exception {
        return securityGroupService.createSecurityGroupResponse(groupname, rule);
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
    public Map<String, Object> updateSecurityGroupResponse(@PathVariable String securityid, @PathVariable String groupname, @RequestBody Object rule) throws Exception {
        System.out.println("실행합니다.");
        return securityGroupService.updateSecurityGroupResponse(securityid, groupname, rule);
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
    public Map<String, Object> deleteSecurityGroupResponse(@PathVariable String securityid) throws Exception {
        return securityGroupService.deleteSecurityGroupResponse(securityid);
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
    public Map<String, Object> setSecurityGroupStagingDefaultResponse(@PathVariable String securityid) throws Exception {
        return securityGroupService.setSecurityGroupStagingDefaultResponse(securityid);
    }


    /**
     * 시큐리티 스테이징 그룹을 조회한다.
     *
     * @param page  listStagingDefaults page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+"/securitygroup/{page}/staging")
    public Map<String, Object>  listSecurityGroupStagingDefaultsResponse(String page) throws Exception {
        return securityGroupService.listSecurityGroupStagingDefaultsResponse(page);
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
    public Map<String, Object> removeSecurityGroupStaging(@PathVariable String securityid)  throws Exception {
        return securityGroupService.removeSecurityGroupStaging(securityid);
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
    public Map<String, Object> setSecurityGroupRunningDefaultResponse(@PathVariable String securityid) throws Exception {
        return securityGroupService.setSecurityGroupRunningDefaultResponse(securityid);
    }


    /**
     * 실행중인 응용 프로그램에 대한 시큐리티 그룹 조회
     *
     * @param page  ListSecurityGroups page
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 박철한
     */
    @GetMapping(Constants.V2_URL+"/securitygroup/{page}/running")
    public Map<String, Object> listSecurityGroupRunningDefaultsResponse(@PathVariable int page) throws Exception {
        return securityGroupService.listSecurityGroupRunningDefaultsResponse(page);
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
    public Map<String, Object> removeSecurityGroupRunning(@PathVariable String securityid) throws Exception {
        return securityGroupService.removeSecurityGroupRunning(securityid);
    }
}
