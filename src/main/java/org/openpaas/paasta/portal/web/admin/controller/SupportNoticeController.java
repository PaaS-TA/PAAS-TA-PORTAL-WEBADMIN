package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.model.Support;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * SupportNoticeController.java
 * 공지사항 조회, 등록, 수정 등 공지사항 관리에 필요한 API 를 호출 받는 컨트롤러
 *
 * @author yjkim
 * @version 1.0
 * @since 2016.07.25 최초작성
 */
@Controller
@RequestMapping(value = {"/support"})
public class SupportNoticeController {

    @Autowired
    private CommonService commonService;

    /**
     * 공지 목록 조회
     *
     * @param param Support
     * @return Map notice list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getNoticeList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNoticeList(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getNoticeList", HttpMethod.POST, param, null);
    }

    /**
     * 공지 상세정보 조회
     *
     * @param param Support
     * @return Map notice
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNotice(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getNotice", HttpMethod.POST, param, null);
    }

    /**
     * 공지 등록
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/insertNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertNotice(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/insertNotice", HttpMethod.POST, param, null);
    }

    /**
     * 공지 수정
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateNotice(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/updateNotice", HttpMethod.PUT, param, null);
    }

    /**
     * 공지 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteNotice(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/deleteNotice", HttpMethod.POST, param, null);
    }



}
