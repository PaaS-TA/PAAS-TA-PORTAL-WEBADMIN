package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
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

    /**
     * 서비스 브로커 리스트를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/servicebrokers"})
    @ResponseBody
    public Map<String, Object> getServiceBrokers(@ModelAttribute ServiceBroker serviceBroker) {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/servicebrokers", HttpMethod.GET, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> getServiceBroker(@ModelAttribute ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/servicebrokers/" + guid, HttpMethod.GET, serviceBroker, this.getToken());
    }

    /**
     * 서비스 브로커를 등록한다..
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @PostMapping(value = {Constants.V2_URL + "/servicebrokers"})
    @ResponseBody
    public Map<String, Object> createServiceBroker(@RequestBody ServiceBroker serviceBroker) {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/servicebrokers", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 수정한다.
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @PutMapping(value = {Constants.V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> updateServiceBroker(@RequestBody ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/servicebrokers/" + guid, HttpMethod.PUT, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커를 삭제한다.
     *
     * @param guid the serviceBroker guid
     * @return Map <String, Object>
     */
    @DeleteMapping(value = {Constants.V2_URL + "/servicebrokers/{guid}"})
    @ResponseBody
    public Map<String, Object> deleteServiceBroker(@PathVariable String guid, @RequestParam boolean purge) {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/servicebrokers/"+guid+"?purge="+purge, HttpMethod.DELETE, null, this.getToken());
    }

}

