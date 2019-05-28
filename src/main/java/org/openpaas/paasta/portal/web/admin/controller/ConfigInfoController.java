package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.ConfigInfo;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 * 설정정보 컨트롤러 - 포탈 설정정보를 수정 관리하는 컨트롤러이다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@RestController
public class ConfigInfoController extends Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInfoController.class);
    private final String V2_URL = "/v2";

    /**
     * 설정 정보 메인 화면이다.
     *
     * @return value model and view
     * @throws Exception the exception
     */


    @GetMapping("/configInfos")
    public ModelAndView configInfo() {
        return configInfoService.configInfo();
    }


    /**
     * 설정 정보를 조회한다.
     *
     * @param configInfo the ConfigInfo
     * @return ModelAndView model
     */
    @GetMapping(V2_URL + "/configInfos")
    @ResponseBody
    public Map<String, Object> getValues(@ModelAttribute ConfigInfo configInfo) {
        return configInfoService.getValues("/configInfos", HttpMethod.GET, configInfo, null);
    }

    /**
     * 설정 정보를 삭제한다.
     *
     * @param key - primary key
     * @return ModelAndView model
     */
    @GetMapping(V2_URL + "/configInfos/{key}")
    @ResponseBody
    public Map<String, Object> getValue(@PathVariable String key) {
        return null;
    }


    /**
     * 설정 정보를 수정한다.
     *
     * @param name       - primary key
     * @param configInfo the configInfo
     * @return ModelAndView model
     */
    @PutMapping(V2_URL + "/configInfos/{name}")
    @ResponseBody
    public Map<String, Object> updateValue(@PathVariable String name, @RequestBody ConfigInfo configInfo) {
        return configInfoService.updateValue("/configInfos/" + name, HttpMethod.PUT, configInfo, null);
    }

    /*
     *예제~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~₩
     */


//    @RequestMapping(value = {"/configInfos/{name:.+}"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getValue(@PathVariable("name")String name, @ModelAttribute ConfigInfo configInfo) {
//        return commonService.procCommonApiRestTemplate("/configInfos"+name, HttpMethod.GET, configInfo, null);
//    }

    @Autowired
    ConfigService configService;

    @ModelAttribute("configs")
    public List<ConfigEntity> configs() {
        return configService.getConfigs();
    }
}

