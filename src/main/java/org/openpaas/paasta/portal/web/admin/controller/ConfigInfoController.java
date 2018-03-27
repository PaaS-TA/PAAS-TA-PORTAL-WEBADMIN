package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.ConfigInfo;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * 설정정보 컨트롤러 - 포탈 설정정보를 수정 관리하는 컨트롤러이다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@Controller
public class ConfigInfoController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInfoController.class);

    @Autowired
    private CommonService commonService;


    /**
     * 설정 정보 메인 화면이다.
     *
     * @return value model and view
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/configInfo/configInfoMain"}, method = RequestMethod.GET)
    public ModelAndView configInfo() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("configInfo/configInfoMain");

        return mv;
    }


    /**
     * 설정 정보를 조회한다.
     *
     * @param configInfo the ConfigInfo
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/configInfos"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getValues(@ModelAttribute ConfigInfo configInfo) {
        return commonService.procCommonApiRestTemplate("/configInfos", HttpMethod.GET, configInfo, null);
    }

    /**
     * 설정 정보를 조회한다.
     * @param name - primary key
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/configInfos/{name}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getValue(@PathVariable String name) {
        return commonService.procCommonApiRestTemplate("/configInfos/"+name, HttpMethod.GET, null, null);
    }





    /**
     * 설정 정보를 수정한다.
     * @param name - primary key
     * @param configInfo the configInfo
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/configInfos/{name}"}, method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateValue(@PathVariable String name, @RequestBody ConfigInfo configInfo) {
        return commonService.procCommonApiRestTemplate("/configInfos/"+name, HttpMethod.PUT, configInfo, null);
    }

    /*
     *예제~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~₩
     */


//    @RequestMapping(value = {"/configInfos/{name:.+}"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getValue(@PathVariable("name")String name, @ModelAttribute ConfigInfo configInfo) {
//        return commonService.procCommonApiRestTemplate("/configInfos"+name, HttpMethod.GET, configInfo, null);
//    }


}

