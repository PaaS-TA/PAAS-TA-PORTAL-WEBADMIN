package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.WebIdeUser;
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
 * WEB IDE 관리 컨트롤러 - WEB IDE 신청자를 관리하는 컨트롤러이다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@Controller
public class WebIdeUserController extends Common {

    //private static final Logger LOGGER = LoggerFactory.getLogger(WebIdeUserController.class);

    @Autowired
    private CommonService commonService;

    /**
     * WEB IDE 메인 화면이다.
     *
     * @return model and view
     */
    @RequestMapping(value = {"/webIdeUser/webIdeUserMain"}, method = RequestMethod.GET)
    public ModelAndView webIdeUser() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("webIdeUser/webIdeUserMain");

        return mv;
    }


    /**
     * WEB IDE 사용자 정보를 조회한다.
     *
     * @param webIdeUser the ConfigInfo
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/webIdeUser/getUser"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUser(@RequestBody WebIdeUser webIdeUser)  {

        return commonService.procRestTemplate("/webIdeUser/getUser", HttpMethod.POST, webIdeUser, null);

    }

    /**
     * WEB IDE 사용자 정보를 수정한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/webIdeUser/updateUser"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateValue(@RequestBody WebIdeUser webIdeUser)  {

        return commonService.procRestTemplate("/webIdeUser/updateUser", HttpMethod.POST, webIdeUser, null);
    }


    /**
     * WEB IDE 사용자 정보를 삭제한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/webIdeUser/deleteUser"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestBody WebIdeUser webIdeUser)  {

        return commonService.procRestTemplate("/webIdeUser/deleteUser", HttpMethod.POST, webIdeUser, null);

    }

    /**
     * WEB IDE 사용자 리스트를 조회한다.
     *
     * @param webIdeUser the web ide user
     * @return the list
     */
    @RequestMapping(value = {"/webIdeUser/getList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(@RequestBody WebIdeUser webIdeUser)  {

        return commonService.procRestTemplate("/webIdeUser/getList", HttpMethod.POST, webIdeUser, null);

    }

}

