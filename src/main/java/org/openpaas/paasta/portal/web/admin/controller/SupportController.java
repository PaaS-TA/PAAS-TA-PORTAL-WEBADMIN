package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Support;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * SupportController.java
 * 지원 관련 메뉴들(커뮤니티 게시판, 공지사항, 문의사항)의 공통 기능인 파일관련 API와
 * 각각의 화면을 호출하는 API
 *
 * @author yjkim
 * @version 1.0
 * @since 2016.07.25 최초작성
 */
@Controller
@RequestMapping(value = {"/support"})
public class SupportController {

    @Autowired
    private CommonService commonService;

    /**
     * 파일 업로드
     *
     * @param multipartFile MultipartFile
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/uploadFile"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadThumbnailImage(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return commonService.procRestTemplate("/file/uploadFile", multipartFile, null);
    }


    /**
     * 파일 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteFile"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteThumbnailImage(@RequestBody Support param) throws Exception {
        return commonService.procRestTemplate("/file/deleteFile", HttpMethod.POST, param, null);
    }


    /**
     * 지원 메인 페이지 이동
     *
     * @return ModelAndView support main
     */
    @RequestMapping(value = {"/supportMain"}, method = RequestMethod.GET)
    public ModelAndView getSupportMain() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/support/supportMain");

        return mv;
    }

    /**
     * 지원 메인 페이지 이동 (tab 이름에 따라 이동)
     *
     * @param tabName String(PathVariable)
     * @return ModelAndView support main
     */
    @RequestMapping(value = {"/supportMain/{tabName}"}, method = RequestMethod.GET)
    public ModelAndView getSupportMain(@PathVariable("tabName") String tabName) {
        ModelAndView mv = new ModelAndView();
        String reqTabName = Constants.TAB_NAME_NOTICE;

        if (Constants.TAB_NAME_NOTICE.equals(tabName)) {
            reqTabName = Constants.TAB_NAME_NOTICE;
        } else if (Constants.TAB_NAME_QNA.equals(tabName)) {
            reqTabName = Constants.TAB_NAME_QNA;
        } else if (Constants.TAB_NAME_BOARD.equals(tabName)) {
            reqTabName = Constants.TAB_NAME_BOARD;
        }
        mv.addObject("TAB_NAME", reqTabName);
        mv.setViewName("/support/supportMain");

        return mv;
    }

    /**
     * 공지 등록 페이지 이동
     *
     * @return ModelAndView notice form
     */
    @RequestMapping(value = {"/noticeForm"}, method = RequestMethod.GET)
    public ModelAndView getNoticeForm() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_C);
        mv.addObject("REQUEST_NO", -1);
        mv.setViewName("/support/noticeForm");

        return mv;
    }

    /**
     * 공지 조회/수정 페이지 이동
     *
     * @param req HttpServletRequest
     * @return ModelAndView notice form
     */
    @RequestMapping(value = {"/noticeForm"}, method = RequestMethod.POST)
    public ModelAndView getNoticeForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_U);
        mv.addObject("REQUEST_NO", req.getParameter("no"));
        mv.setViewName("/support/noticeForm");

        return mv;
    }

    /**
     * 문의 조회/답변조회/답변등록/답변수정 페이지 이동
     *
     * @param req HttpServletRequest
     * @return ModelAndView qna form
     */
    @RequestMapping(value = {"/qnaForm"}, method = RequestMethod.POST)
    public ModelAndView getQNAForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("REQUEST_NO", req.getParameter("no"));
        mv.addObject("REQUEST_STATUS", req.getParameter("status"));
        mv.setViewName("/support/qnaForm");

        return mv;
    }

    /**
     * 게시판 등록 페이지 이동
     *
     * @return ModelAndView board form
     */
    @RequestMapping(value = {"/boardForm"}, method = RequestMethod.GET)
    public ModelAndView getBoardForm() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_C);
        mv.addObject("REQUEST_NO", -1);
        mv.setViewName("/support/boardForm");

        return mv;
    }

    /**
     * 게시판 조회/수정 페이지 이동
     *
     * @param req HttpServletRequest
     * @return ModelAndView board form
     */
    @RequestMapping(value = {"/boardForm"}, method = RequestMethod.POST)
    public ModelAndView getBoardForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_U);
        mv.addObject("REQUEST_NO", req.getParameter("no"));
        mv.setViewName("/support/boardForm");

        return mv;
    }

    @Autowired
    ConfigService configService;

    @ModelAttribute("configs")
    public List<ConfigEntity> configs(){
        return configService.getConfigs();
    }

}
