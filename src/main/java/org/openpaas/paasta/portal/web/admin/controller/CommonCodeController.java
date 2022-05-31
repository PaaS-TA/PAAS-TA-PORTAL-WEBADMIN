package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.config.LanguageConfig;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 코드 목록 조회, 등록, 삭제 등 코드 관리의 API 를 호출 하는 컨트롤러이다.
 *
 * @author SEJI
 * @since 2018.04.01
 */
@Controller
public class CommonCodeController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeController.class);

    private final String V2_URL = "/v2";

    @Autowired
    LanguageConfig languageConfig;

    /**
     * 공통코드 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/code"}, method = RequestMethod.GET)
    public ModelAndView getCommonCodeMain() {
        return new ModelAndView() {{
            setViewName("/commonCode/commonCodeMain");
        }};
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param param odeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/commoncode")
    @ResponseBody
    public Map<String, Object> getCommonCodeDetail(HttpServletRequest request, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.getCommonCodeJoinGroup(Integer.parseInt(key), param);
    }


    /**
     * Group Table 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/codegroup")
    @ResponseBody
    public Map<String, Object> getGroupDetailList(@CookieValue(value = "lang", required = false) Cookie cookie, HttpServletRequest request, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
        String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        LOGGER.info(param.toString());
//        String key = request.getParameter("key");
        return commonCodeService.getGroupDetailList(Integer.parseInt(key), param, useLang);
    }


    /**
     * Group Table 상세 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> getGroupDetail(@CookieValue(value = "lang", required = false) Cookie cookie,HttpServletRequest request, @PathVariable("id") String  id, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
        String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        return commonCodeService.getGroupDetail(Integer.parseInt(key), id, param, useLang);
    }


    /**
     * Group Table을 생성(등록)한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/codegroup")
    @ResponseBody
    public Map<String, Object> insertDetailGroup(@CookieValue(value = "lang", required = false) Cookie cookie,HttpServletRequest request, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        return commonCodeService.insertDetailGroup(Integer.parseInt(key), param, useLang);
    }


    /**
     * Group Table을 수정한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> updateCommonGroup(@CookieValue(value = "lang", required = false) Cookie cookie,HttpServletRequest request, @PathVariable String id, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        return commonCodeService.updateCommonGroup(Integer.parseInt(key), id, param, useLang);
    }


    /**
     * Group Table을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> deleteCommonGroup(@CookieValue(value = "lang", required = false) Cookie cookie, HttpServletRequest request, @PathVariable String id) {
        String key = request.getParameter("key");
        String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        LOGGER.info("########## " + id);
        return commonCodeService.deleteCommonGroup(Integer.parseInt(key), id, useLang);
    }


    /**
     * Detail Table 전체목록 조회한다.
     *
     * @param param CodeDetail
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL+"/codedetail")
    @ResponseBody
    public Map<String, Object> getCodeDetailList(@CookieValue(value = "lang", required = false) Cookie cookie, HttpServletRequest request, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
	    String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        LOGGER.info("key :: " + key);
        return commonCodeService.getCodeDetailList(Integer.parseInt(key), param, useLang);
    }


    /**
     * Detail Table 상세 목록을 조회한다.
     *
     * @param no CodeDetail
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> getCodeDetail(@CookieValue(value = "lang", required = false) Cookie cookie, HttpServletRequest request, @PathVariable("no") int no) {
        String key = request.getParameter("key");
	    String useLang = languageConfig.getLanguageList().get(0);
        if(cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            useLang = cookie.getValue();
        }
        LOGGER.info("NO :: " + no);
        return commonCodeService.getCodeDetail(Integer.parseInt(key), no, useLang);
    }


    /**
     * Detail Table을 생성(등록)한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/codedetail")
    @ResponseBody
    public Map<String, Object> insertDetail(HttpServletRequest request, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.insertDetail(Integer.parseInt(key),param);
    }


    /**
     * Detail Table을 수정한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> updateCommonDetail(HttpServletRequest request,  @PathVariable int no, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.updateCommonDetail(Integer.parseInt(key), no, param);
    }


    /**
     * Detail Table을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> deleteCommonDetail(HttpServletRequest request, @PathVariable int no) {
        String key = request.getParameter("key");
        return commonCodeService.deleteCommonDetail(Integer.parseInt(key), no);
    }

    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
