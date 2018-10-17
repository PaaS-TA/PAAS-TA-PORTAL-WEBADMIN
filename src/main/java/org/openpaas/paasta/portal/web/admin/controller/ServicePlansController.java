package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class ServicePlansController extends Common {

    @Autowired
    private CommonService commonService;

    /**
     * 서비스 제어 메인 화면이다.
     *
     * @return model and view
     */
    @GetMapping(value = {"/serviceplans"})
    public ModelAndView serviceBroker() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("servicePlan/servicePlansMain");

        return mv;
    }

    /**
     * 서비스 전체 목록을 가져온다.
     *
     * @return ListServicesResponse
     * @throws Exception Exception(자바클래스)
     */
//    @GetMapping(Constants.V2_URL+"/services")
//    @ResponseBody
//    public  Map<String, Object> getService(@ModelAttribute Service service) throws Exception {
//        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/services", HttpMethod.GET, service, this.getToken());
//    }

    /**
     * 카탈로그 서비스 이용사양 목록을 조회한다.
     *
     * @param servicename String(자바클래스)
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/catalogs/serviceplan/{servicename}"})
    @ResponseBody
    public Map<String, Object> getCatalogService(@PathVariable String servicename) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/catalogs/serviceplan/"+servicename, HttpMethod.GET, null, this.getToken());
    }

    /**
     * 서비스 제어 리스트를 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/serviceplans"})
    @ResponseBody
    public Map<String, Object> getServicePlans(@ModelAttribute ServiceBroker serviceBroker) {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/serviceplans", HttpMethod.GET, serviceBroker, this.getToken());
    }

    /**
     *  해당 서비스 제어 상세내용을 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/serviceplans/{guid}"})
    @ResponseBody
    public Map<String, Object> getServicePlan(@ModelAttribute ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/serviceplans/"+guid, HttpMethod.GET, serviceBroker, this.getToken());
    }


}
