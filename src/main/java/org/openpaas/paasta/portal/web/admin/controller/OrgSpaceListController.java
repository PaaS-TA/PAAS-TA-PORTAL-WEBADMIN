package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.Org;
import org.openpaas.paasta.portal.web.admin.model.Space;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping(value = {"/getOrgsForAdmin"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getOrgsForAdmin(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/org/getOrgsForAdmin", HttpMethod.POST, param, null);
    }

    /**
     * admin 유저로 접근 가능한 영역 목록(모든 영역 목록)을 조회한다.
     *
     * @param param (자바 Map 클래스)
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     * @author 김도준
     */
    @RequestMapping(value = {"/getSpacesForAdmin"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSpacesForAdmin(@RequestBody Map param) throws Exception {
        return commonService.procRestTemplate("/space/getSpacesForAdmin", HttpMethod.POST, param, null);
    }


    /**
     * 조직 요약 정보를 조회한다.
     *
     * @param org 조직 객체
     * @return Org 조직 객체
     */
    @RequestMapping(value = {"/getOrgSummary"}, method = RequestMethod.POST)
    @ResponseBody
    public Org getOrgSummary(@RequestBody Org org) {

        Org rspOrg = null;

        if (org.getOrgName() == null) {
            throw new IllegalArgumentException("조직정보가 존재하지 않습니다.");
        }
        ResponseEntity rssResponse = commonService.procRestTemplate("/org/getOrgSummary", HttpMethod.POST, org, getToken(), Org.class);
        rspOrg = (Org) rssResponse.getBody();

        return rspOrg;
    }


    /**
     * 조직 이름으로 조직의 정보를 조회한다.
     *
     * @param org 조직 객체
     * @return Org 조직 객체
     */
    @RequestMapping(value = {"/getOrgByName"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getOrgByName(@RequestBody Org org) {

        if (org.getOrgName() == null) {
            throw new IllegalArgumentException("조직정보가 존재하지 않습니다.");
        }

        return commonService.procRestTemplate("/org/getOrgByName", HttpMethod.POST, org, getToken());
    }


    /**
     * 영역 요약 정보를 조회한다.
     *
     * @param space 영역 객체
     * @return Space 영역 객체
     */
    @RequestMapping(value = {"/getSpaceSummary"}, method = RequestMethod.POST)
    @ResponseBody
    public Space getSpaceSummary(@RequestBody Space space) {

        Space rspSpace = new Space();

        ResponseEntity rssResponse = commonService.procRestTemplate("/space/getSpaceSummary", HttpMethod.POST, space, getToken(), Space.class);
        rspSpace = (Space) rssResponse.getBody();

        return rspSpace;
    }

    /**
     * 영역 쿼터 정보를 조회한다.
     *
     * @param space 영역 객체
     * @return Map (자바 Map 클래스)
     */
    @RequestMapping(value = {"/getSpaceQuota"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSpaceQuota(@RequestBody Space space) {

        return commonService.procRestTemplate("/space/getSpaceQuota", HttpMethod.POST, space, getToken());

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
