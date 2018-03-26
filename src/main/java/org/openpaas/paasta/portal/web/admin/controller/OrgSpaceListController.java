package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.Org;
import org.openpaas.paasta.portal.web.admin.model.Space;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 도메인 정보 조회, 추가, 삭제 등 도메인 관리의 API 를 호출 받는 컨트롤러이다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016 -09-06
 */
@Controller
@RequestMapping(value = {"/orgSpaceList"})
public class OrgSpaceListController extends Common {

    @Autowired
    private CommonService commonService;

    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    @RequestMapping(value = {"/orgSpaceListMain"}, method = RequestMethod.GET)
    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView(){{setViewName("/orgSpaceList/orgSpaceListMain");}};
    }


    /**
     * admin 유저로 접근 가능한 조직 목록(모든 조직 목록)을 조회한다.
     *
     * @param param (자바 Map 클래스)
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 김도준
     * @thorws Exception
     */
    @RequestMapping(value = {"/OrgAndSpace"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrgsForAdmin(@ModelAttribute String param) throws Exception {
        return commonService.procCfApiRestTemplate("/org/OrgAndSpace", HttpMethod.GET, param, null);
    }

    /**
     * admin 유저로 접근 가능한 영역 목록(모든 영역 목록)을 조회한다.
     *
     * @param orgName (자바 Map 클래스)
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 김도준
     */
    @RequestMapping(value = {"/Space/{orgName}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSpacesForAdmin(@PathVariable String orgName) throws Exception {
        System.out.println(orgName);
        return commonService.procCfApiRestTemplate("/space/"+orgName, HttpMethod.GET, null, getToken());
    }

    /**
     * 조직 요약 정보를 조회한다.
     *
     * @param orgName 조직 객체
     * @return Org 조직 객체
     */
    @RequestMapping(value = {"/OrgSummary/{orgName}"}, method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> getOrgSummary(@PathVariable String orgName) {
//
//        Org rspOrg = null;
//
//        if (org.getOrgName() == null) {
//            throw new IllegalArgumentException("조직정보가 존재하지 않습니다.");
//        }
//        ResponseEntity rssResponse =
//        rspOrg = (Org) rssResponse.getBody();

        return commonService.procCfApiRestTemplate("/org/OrgSummary/"+orgName, HttpMethod.GET, null, getToken());
    }


    /**
     * 조직 이름으로 조직의 정보를 조회한다.
     *
     * @param orgName 조직 이름
     * @return Org 조직 객체
     */
    @RequestMapping(value = {"/OrgByName/{orgName}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrgByName(@PathVariable String orgName) {

        return commonService.procCfApiRestTemplate("/org/OrgByName/"+orgName, HttpMethod.GET, null, getToken());
    }


    /**
     * 영역 요약 정보를 조회한다.
     *
     * @param spaceId space id값
     * @return Space 영역 객체
     */
    @RequestMapping(value = {"/SpaceSummary/{spaceId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSpaceSummary(@PathVariable String spaceId) {

        return commonService.procCfApiRestTemplate("/space/SpaceSummary/"+ spaceId, HttpMethod.GET, null, getToken());
    }

    /**
     * 영역 쿼터 정보를 조회한다.
     *
     * @param spaceQuotaId 영역 객체
     * @return Map (자바 Map 클래스)
     */
    @RequestMapping(value = {"/SpaceQuota/{spaceQuotaId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSpaceQuota(@PathVariable String spaceQuotaId) {

        return commonService.procCfApiRestTemplate("/space/SpaceQuota/"+ spaceQuotaId, HttpMethod.GET, null, getToken());

    }

    /**
     * 특정 영역을 선택하여 영역을 조회한다.
     *
     * @param space 영역 객체
     * @return Map (자바 Map 클래스)
     */
    @RequestMapping(value = {"/getSpace"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSpace(@RequestBody Space space) {

        return commonService.procRestTemplate("/space/getSpace", HttpMethod.POST, space, getToken());

    }

}
