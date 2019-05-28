package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Support;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * SupportBoardController.java
 * 커뮤니티 게시판 조회, 등록, 수정 등 커뮤니티 게시판 관리에 필요한 API를 호출 받는 컨트롤러
 *
 * @author yjkim
 * @version 1.0
 * @since 2016.07.25 최초작성
 */
@Controller
@RequestMapping(value = {"/support"})
public class SupportBoardController {

    @Autowired
    private CommonService commonService;

    /**
     * 게시판 목록 조회
     *
     * @param param Support
     * @return Map board list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getBoardList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBoardList(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getBoardList", HttpMethod.POST, param, null);
    }

    /**
     * 게시판 상세정보 조회
     *
     * @param param Support
     * @return Map board
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getBoard"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBoard(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getBoard", HttpMethod.POST, param, null);
    }


    /**
     * 게시판 댓글 목록 조회
     *
     * @param param Support
     * @return Map board comment list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getBoardCommentList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBoardCommentList(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getBoardCommentList", HttpMethod.POST, param, null);
    }

    /**
     * 게시판 게시글 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteBoard"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBoard(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/deleteBoard", HttpMethod.POST, param, null);
    }

    /**
     * 게시판 댓글 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteBoardComment"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBoardComment(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/deleteBoardComment", HttpMethod.POST, param, null);
    }


    @Autowired
    ConfigService configService;

    @ModelAttribute("configs")
    public List<ConfigEntity> configs(){
        return configService.getConfigs();
    }


}
