package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 코드 목록 조회, 등록, 삭제 등 코드 관리의 API 를 호출 하는 컨트롤러이다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.06.17 최초작성
 */
@Controller
@RequestMapping(value = {"/commonCode"})
public class CommonCodeController extends Common {

    /**
     * 공통코드 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/commonCodeMain"}, method = RequestMethod.GET)
    public ModelAndView getCommonCodeMain() {
        return new ModelAndView(){{setViewName("/commonCode/commonCodeMain");}};
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeId String(아이디)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getCommonCode/{codeId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommonCode(@PathVariable("codeId") String codeId) {
        System.out.println("#########################################");
        System.out.println("#########################################");
        System.out.println(codeId);
        System.out.println("#########################################");
        System.out.println("#########################################");

        return commonService.procRestTemplate("/commonCode/getCommonCode/" + codeId, HttpMethod.GET, new CommonCode(), null);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getCommonCode"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCommonCode(@RequestBody CommonCode param) {
        return commonService.procRestTemplate("/commonCode/getCommonCode", HttpMethod.POST, param, null);
    }


    /**
     * 공통코드를 저장한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/insertCommonCode"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertCommonCode(@RequestBody CommonCode param) {
        param.setUserId(commonService.getUserId());

        return commonService.procRestTemplate("/commonCode/insertCommonCode", HttpMethod.POST, param, null);
    }


    /**
     * 공통코드를 수정한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/updateCommonCode"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCommonCode(@RequestBody CommonCode param) {
        param.setUserId(commonService.getUserId());

        return commonService.procRestTemplate("/commonCode/updateCommonCode", HttpMethod.PUT, param, null);
    }


    /**
     * 공통코드를 삭제한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/deleteCommonCode"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCommonCode(@RequestBody CommonCode param) {
        return commonService.procRestTemplate("/commonCode/deleteCommonCode", HttpMethod.POST, param, null);
    }
}
