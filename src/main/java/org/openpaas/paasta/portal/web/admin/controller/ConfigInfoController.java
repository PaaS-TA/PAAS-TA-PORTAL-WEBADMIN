package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.ConfigInfo;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    ConfigService configService;

    /**
     * 설정 정보 메인 화면이다.
     *
     * @return value model and view
     * @throws Exception the exception
     */


    @GetMapping("/configInfos")
    public ModelAndView configInfo() {
        return new ModelAndView() {{
            setViewName("/configInfo/configInfoMain");
        }}.addObject("infra_infos",configService.getConfigs());
    }


    /**
     * 설정 정보를 조회한다.
     *
     * @return ModelAndView model
     */
    @GetMapping(V2_URL + "/configInfos/{key}")
    @ResponseBody
    public ConfigEntity getValues(@PathVariable int key) {
        return configService.getConfig(key);
    }

    /**
     * 설정 정보를 삭제한다.
     *
     * @param key - primary key
     * @return ModelAndView model
     */
    @DeleteMapping(V2_URL + "/configInfos/{key}")
    @ResponseBody
    public Map<String, Object> deleteConfig(@PathVariable int key) {
        return configService.deleteConfig(key);
    }


    /**
     * 설정 정보를 수정한다.
     *
     * @param configEntity - primary key
     * @return ModelAndView model
     */
    @PutMapping(V2_URL + "/configInfos/{key}")
    @ResponseBody
    public Map<String, Object> updateConfig(@PathVariable int key, @RequestBody ConfigEntity configEntity) {
        configEntity.setKey(key);
        return configService.saveConfig(configEntity);
    }


    /**
     * 설정 정보를 저장한다.
     *
     * @return ModelAndView model
     */
    @PostMapping(V2_URL + "/configInfos")
    @ResponseBody
    public Map<String, Object> addConfig(@RequestBody ConfigEntity configEntity) {
        return configService.saveConfig(configEntity);
    }


    /*
     *예제~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~₩
     */


//    @RequestMapping(value = {"/configInfos/{name:.+}"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getValue(@PathVariable("name")String name, @ModelAttribute ConfigInfo configInfo) {
//        return commonService.procCommonApiRestTemplate("/configInfos"+name, HttpMethod.GET, configInfo, null);
//    }


    @ModelAttribute("configs")
    public List<User> configs(){

        return getServerInfos();
    }
}

