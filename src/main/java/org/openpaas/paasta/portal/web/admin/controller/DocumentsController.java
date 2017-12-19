package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.Support;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * DocumentsController.java
 * 문서 조회, 등록, 수정 등 문서 관리에 필요한 API 를 호출 받는 컨트롤러
 *
 * @author yjkim
 * @version 1.0
 * @since 2016.07.25 최초작성
 */
@Controller
@RequestMapping(value = {"/documents"})
public class DocumentsController {

    @Autowired
    private CommonService commonService;

    /**
     * 문서 목록 조회
     *
     * @param param Support
     * @return Map documents list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getDocumentsList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDocumentsList(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/documents/getDocumentsList", HttpMethod.POST, param, null);
    }

    /**
     * 문서 상세정보 조회
     *
     * @param param Support
     * @return Map document
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getDocument"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDocument(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/documents/getDocument", HttpMethod.POST, param, null);
    }

    /**
     * 문서 등록
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/insertDocument"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertDocument(@RequestBody Support param) throws Exception{
        param.setUserId(commonService.getUserId());
        return commonService.procRestTemplate("/documents/insertDocument", HttpMethod.POST, param, null);
    }

    /**
     * 문서 수정
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateDocument"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDocument(@RequestBody Support param) throws Exception{

        param.setUserId(commonService.getUserId());
        return commonService.procRestTemplate("/documents/updateDocument", HttpMethod.PUT, param, null);
    }

    /**
     * 문서 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteDocument"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteDocument(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/documents/deleteDocument", HttpMethod.POST, param, null);
    }


    /**
     * 문서 메인 페이지 이동
     *
     * @return ModelAndView documents main
     */
    @RequestMapping(value = {"/documentsMain"}, method = RequestMethod.GET)
    public ModelAndView getDocumentsMain() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/documents/documentsMain");

        return mv;
    }

    /**
     * 문서 등록 페이지 이동
     *
     * @return ModelAndView model and view
     */
    @RequestMapping(value = {"/documentsForm"}, method = RequestMethod.GET)
    public ModelAndView documentForm() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_C);
        mv.addObject("REQUEST_NO", -1);
        mv.setViewName("/documents/documentsForm");

        return mv;
    }

    /**
     * 문서 조회/수정 페이지 이동
     *
     * @param req HttpServletRequest
     * @return ModelAndView model and view
     */
    @RequestMapping(value = {"/documentsForm"}, method = RequestMethod.POST)
    public ModelAndView documentForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_U);
        mv.addObject("REQUEST_NO", req.getParameter("no"));
        mv.setViewName("/documents/documentsForm");

        return mv;
    }

}
