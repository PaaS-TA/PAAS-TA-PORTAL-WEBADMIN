package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.Quota;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 허용량 관리 컨트롤러 - 서비스 브로커를 관리하는 컨트롤러이다.
 *
 * @author 최윤석
 * @version 1.0
 * @since 2016.4.12 최초작성
 */
@RestController
public class QuotaManagementController extends Common {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBrokerController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 할당량 관리 메인 화면이다.
     *
     * @return model and view
     */
    @GetMapping(value = {"/quotadefinitions"})
    public ModelAndView quotadefinitionsMain() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("quotaManagement/quotaManagement");

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
     * 조직 할당량 리스트를 조회한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/orgs/quota-definitions"})
    @ResponseBody
    public Map<String, Object> getOrgQuotaDefinitions(@ModelAttribute Quota quota) {
       return commonService.procCfApiRestTemplate(Constants.V2_URL + "/orgs/quota-definitions", HttpMethod.GET, quota, this.getToken());
    }


    /**
     * 조직 할당량 정의를 조회한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/orgs/quota-definitions/{guid}"})
    @ResponseBody
    public Map<String, Object> getOrgQuotaDefinition(@ModelAttribute Quota quota, @PathVariable String guid) {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/orgs/quota-definitions/"+guid, HttpMethod.GET, quota, this.getToken());
    }

    /**
     * 조직 할당량 정의를 등록한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @PostMapping(value = {Constants.V2_URL + "/orgs/quota-definitions"})
    @ResponseBody
    public Map<String, Object> createOrgQuotaDefinition(@RequestBody Quota quota) {

        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/orgs/quota-definitions", HttpMethod.POST, quota, this.getToken());
    }
//
//
//    /**
//     * 서비스 브로커를 수정한다.
//     *
//     * @param serviceBroker the serviceBroker
//     * @return ModelAndView model
//     */
//    @PutMapping(value = {Constants.V2_URL + "/servicebrokers/{guid}"})
//    @ResponseBody
//    public Map<String, Object> updateServiceBroker(@RequestBody ServiceBroker serviceBroker, @PathVariable String guid) {
//
//        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/servicebrokers/"+guid, HttpMethod.PUT, serviceBroker, this.getToken());
//    }
//
//
//    /**
//     * 서비스 브로커를 삭제한다.
//     *
//     * @param guid the serviceBroker guid
//     * @return Map <String, Object>
//     */
//    @DeleteMapping(value = {Constants.V2_URL + "/servicebrokers/{guid}"})
//    @ResponseBody
//    public Map<String, Object> deleteServiceBroker(@PathVariable String guid ) {
//
//        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/servicebrokers/"+guid, HttpMethod.DELETE, null, this.getToken());
//
//    }

}

