package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 서비스 브로커 관리 컨트롤러 - 서비스 브로커를 관리하는 컨트롤러이다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@Controller
public class ServiceBrokerController extends Common {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBrokerController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 서비스 브로커 메인 화면이다.
     *
     * @return model and view
     */
    @RequestMapping(value = {"/service/serviceBrokerMain"}, method = RequestMethod.GET)
    public ModelAndView serviceBroker() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerMain");

        return mv;
    }

    /**
     * 서비스 브로커 상세 화면이다.
     *
     * @return model and view
     */
    @RequestMapping(value = {"/service/serviceBrokerDetail"}, method = RequestMethod.GET)
    public ModelAndView serviceBrokerDetail() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerDetail");

        return mv;
    }


    /**
     * 서비스 브로커 등록 화면이다.
     *
     * @return model and view
     */
    @RequestMapping(value = {"/service/serviceBrokerCreate"}, method = RequestMethod.GET)
    public ModelAndView serviceBrokerCreate() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerCreate");

        return mv;
    }


    /**
     * 서비스 브로커 리스트를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/service_brokers"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getServiceBrokers(@ModelAttribute ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate("/service/service_brokers", HttpMethod.GET, serviceBroker, this.getToken());

    }

//    /**
//     * 서비스 브로커를 조회한다.
//     *
//     * @param serviceBroker serviceBroker
//     * @return ModelAndView model
//     */
//    @RequestMapping(value = {"/service/service_brokers"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getServiceBroker(@ModelAttribute ServiceBroker serviceBroker, @RequestParam("name") String serviceName) {
//
//        return commonService.procCfApiRestTemplate("/service_brokers?name="+serviceName, HttpMethod.GET, serviceBroker, this.getToken());
//
//    }


    /**
     * 서비스 브로커를 등록한다..
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/createServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate("/service/createServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 수정한다.
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/updateServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate("/service/updateServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 삭제한다.
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/deleteServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate("/service/deleteServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());

    }


    /**
     * 서비스 브로커 이름을 수정한다.
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/renameServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> renameServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate("/service/renameServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());

    }


}

