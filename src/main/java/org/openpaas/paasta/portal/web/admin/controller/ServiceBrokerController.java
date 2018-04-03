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
import java.util.UUID;

/**
 * 서비스 브로커 관리 컨트롤러 - 서비스 브로커를 관리하는 컨트롤러이다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@RestController
public class ServiceBrokerController extends Common {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBrokerController.class);
    private final String V2_URL = "/v2";

    @Autowired
    private CommonService commonService;

    /**
     * 서비스 브로커 메인 화면이다.
     *
     * @return model and view
     */
    @GetMapping(value = {"/servicebrokers"})
    public ModelAndView serviceBroker() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerMain");

        return mv;
    }

//    /**
//     * 서비스 브로커 상세 화면이다.
//     *
//     * @return model and view
//     */
//    @RequestMapping(value = {"/service/serviceBrokerDetail"}, method = RequestMethod.GET)
//    public ModelAndView serviceBrokerDetail() {
//        ModelAndView mv = new ModelAndView();
//
//        mv.setViewName("service/serviceBrokerDetail");
//
//        return mv;
//    }

//    /**
//     * 서비스 브로커 등록 화면이다.
//     *
//     * @return model and view
//     */
//    @RequestMapping(value = {"/service/serviceBrokerCreate"}, method = RequestMethod.GET)
//    public ModelAndView serviceBrokerCreate() {
//        ModelAndView mv = new ModelAndView();
//
//        mv.setViewName("service/serviceBrokerCreate");
//
//        return mv;
//    }

    /**
     * 서비스 브로커 리스트를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {V2_URL + "/servicebrokers"})
    @ResponseBody
    public Map<String, Object> getServiceBrokers(@ModelAttribute ServiceBroker serviceBroker) {
       return commonService.procCfApiRestTemplate(V2_URL + "/servicebrokers", HttpMethod.GET, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> getServiceBroker(@ModelAttribute ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procCfApiRestTemplate(V2_URL + "/servicebrokers/"+guid, HttpMethod.GET, serviceBroker, this.getToken());
    }

    /**
     * 서비스 브로커를 등록한다..
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @PostMapping(value = {V2_URL + "/servicebrokers"})
    @ResponseBody
    public Map<String, Object> createServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procCfApiRestTemplate(V2_URL + "/servicebrokers", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 수정한다.
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @PutMapping(value = {V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> updateServiceBroker(@RequestBody ServiceBroker serviceBroker, @PathVariable String guid) {

        return commonService.procCfApiRestTemplate(V2_URL + "/servicebrokers/"+guid, HttpMethod.PUT, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 삭제한다.
     *
     * @param guid the serviceBroker guid
     * @return Map <String, Object>
     */
    @DeleteMapping(value = {V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> deleteServiceBroker(@PathVariable String guid ) {

        return commonService.procCfApiRestTemplate(V2_URL + "/servicebrokers/"+guid, HttpMethod.DELETE, null, this.getToken());

    }

}

