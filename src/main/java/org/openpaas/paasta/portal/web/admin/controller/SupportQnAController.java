package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Support;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by YJKim on 2016-07-25.
 */
@Controller
@RequestMapping(value = {"/support"})
public class SupportQnAController {

    @Autowired
    private CommonService commonService;


    /**
     * 문의 목록 조회
     *
     * @param param Support
     * @return Map qn a list
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getQnAList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getQnAList(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getQnAList", HttpMethod.POST, param, null);
    }

    /**
     * 문의 상세정보 조회
     *
     * @param param Support
     * @return Map question
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getQuestion"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getQuestion(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getQuestion", HttpMethod.POST, param, null);
    }

    /**
     * 답변 조회
     *
     * @param param Support
     * @return Map answer
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getAnswer"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAnswer(@RequestBody Support param) throws Exception{
        return commonService.procRestTemplate("/support/getAnswer", HttpMethod.POST, param, null);
    }

    /**
     * 답변 등록
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/insertAnswer"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertAnswer(@RequestBody Support param) throws Exception{

        param.setAnswerer(commonService.getUserId());
        return commonService.procRestTemplate("/support/insertAnswer", HttpMethod.POST, param, null);
    }

    /**
     * 답변 수정
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateAnswer"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAnswer(@RequestBody Support param) throws Exception{

        param.setAnswerer(commonService.getUserId());
        return commonService.procRestTemplate("/support/updateAnswer", HttpMethod.PUT, param, null);
    }

    /**
     * 답변 삭제
     *
     * @param param Support
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteAnswer"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteAnswer(@RequestBody Support param) throws Exception{

        return commonService.procRestTemplate("/support/deleteAnswer", HttpMethod.POST, param, null);
    }


    @Autowired
    RootService rootService;

    @ModelAttribute("configs")
    public List<ConfigEntity> configs(){
        return rootService.getConfigs();
    }
}
