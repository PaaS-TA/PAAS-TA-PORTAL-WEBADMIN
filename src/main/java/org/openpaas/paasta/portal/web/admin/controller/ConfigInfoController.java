package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.ConfigInfo;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    //private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInfoController.class);

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
    @RequestMapping(value = {"/configInfo/getValue"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getValue(@RequestBody ConfigInfo configInfo)  {

        return commonService.procRestTemplate("/configInfo/getValue", HttpMethod.POST, configInfo, null);

    }

    /**
     * 설정 정보를 수정한다.
     *
     * @param configInfo the configInfo
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/configInfo/updateValue"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateValue(@RequestBody ConfigInfo configInfo)  {

        return commonService.procRestTemplate("/configInfo/updateValue", HttpMethod.POST, configInfo, null);
    }

}

