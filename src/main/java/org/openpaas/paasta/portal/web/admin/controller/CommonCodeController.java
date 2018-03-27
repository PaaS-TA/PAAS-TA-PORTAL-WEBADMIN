package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = {"/code"})
public class CommonCodeController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeController.class);


    /**
     * 공통코드 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/commonCodeMain"}, method = RequestMethod.GET)
    public ModelAndView getCommonCodeMain() {
        return new ModelAndView() {{
            setViewName("/commonCode/commonCodeMain");
        }};
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param key String(아이디)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/codeDetail/{key}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommonCode(@PathVariable("key") String key, @ModelAttribute CommonCode param) {
        param.setKey(key);
        return commonCodeService.getCommonCode(key);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/codeDetail"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommonCode(@ModelAttribute CommonCode param) {
        return commonCodeService.getCommonCode(param);
    }


    /**
     * 공통코드를 저장한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/codeDetail"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertCommonCode(@RequestBody CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonCodeService.insertCommonCode(param);
    }


    /**
     * 공통코드를 수정한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/codeDetail"}, method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateCommonCode(@RequestBody CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonCodeService.updateCommonCode(param);
    }


    /**
     * 공통코드를 삭제한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/codeDetail"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteCommonCode(@RequestBody CommonCode param) {
        return commonCodeService.deleteCommonCode(param);
    }
}
