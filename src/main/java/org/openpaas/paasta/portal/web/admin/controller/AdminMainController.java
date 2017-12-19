package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.AdminMain;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 운영자 포탈 관리자 대시보드 관련 API 를 호출 하는 컨트롤러이다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.09.08 최초작성
 */
@Controller
@RequestMapping(value = {"/main"})
public class AdminMainController extends Common {

    /**
     * 관리자포탈 조직 선택 메인페이지로 이동한다.
     *
     * @param organizationId 조직 아이디(String)
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/organization/{organizationId}"}, method = RequestMethod.GET)
    public ModelAndView getAdminMain(@PathVariable("organizationId") String organizationId) {
        return new ModelAndView() {{
            setViewName("/main/main");
            addObject("ORGANIZATION_ID", organizationId);
        }};
    }


    /**
     * 전체 조직 수, 영역 수, APPLICATION 수, 사용자 수 목록을 조회한다.
     *
     * @param param AdminMain(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getTotalCountList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTotalCountList(@RequestBody AdminMain param) {
        return commonService.procRestTemplate("/adminMain/getTotalCountList", HttpMethod.POST, param, null);
    }


    /**
     * 전체 조직 통계 목록을 조회한다.
     * *
     *
     * @param param AdminMain(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getTotalOrganizationList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTotalOrganizationList(@RequestBody AdminMain param) {
        return commonService.procRestTemplate("/adminMain/getTotalOrganizationList", HttpMethod.POST, param, null);
    }


    /**
     * 전체 공간 통계 목록을 조회한다.
     * *
     *
     * @param param AdminMain(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getTotalSpaceList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTotalSpaceList(@RequestBody AdminMain param) {
        return commonService.procRestTemplate("/adminMain/getTotalSpaceList", HttpMethod.POST, param, null);
    }

}
