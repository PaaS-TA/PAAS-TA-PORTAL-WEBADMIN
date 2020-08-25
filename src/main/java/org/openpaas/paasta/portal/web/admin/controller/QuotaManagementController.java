package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Quota;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    /**
     * 조직 할당량 리스트를 조회한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/orgs/quota-definitions"})
    @ResponseBody
    public Map<String, Object> getOrgQuotaDefinitions(HttpServletRequest request, @ModelAttribute Quota quota) {
        String key = request.getParameter("key");
       return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/orgs/quota-definitions", HttpMethod.GET, quota);
    }


    /**
     * 조직 할당량 정의를 조회한다.
     *
     * @param quotaId Quota GUID
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/orgs/quota-definitions/{quotaId}"})
    @ResponseBody
    public Map<String, Object> getOrgQuotaDefinition(@ModelAttribute Quota quota, @PathVariable String quotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/orgs/quota-definitions/"+quotaId, HttpMethod.GET, quota);
    }

    /**
     * 조직 할당량 정의를 등록한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @PostMapping(value = {Constants.V2_URL + "/orgs/quota-definitions"})
    @ResponseBody
    public Map<String, Object> createOrgQuotaDefinition(HttpServletRequest request, @RequestBody Quota quota) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/orgs/quota-definitions", HttpMethod.POST, quota);
    }

    /**
     * 조직 할당량 정의를 수정한다.
     *
     * @param quota Quota
     *
     * @return ModelAndView model
     */
    @PutMapping(value = {Constants.V2_URL + "/orgs/quota-definitions/{quotaId}"})
    @ResponseBody
    public Map<String, Object> updateOrgQuotaDefinition(@RequestBody Quota quota, @PathVariable String quotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/orgs/quota-definitions/"+quotaId, HttpMethod.PUT, quota);
    }

    /**
     * 조직 할당량 정의를 삭제한다.
     *
     * @param quotaId the quota GUID
     * @return Map <String, Object>
     */
    @DeleteMapping(value = {Constants.V2_URL + "/orgs/quota-definitions/{quotaId}"})
    @ResponseBody
    public Map<String, Object> deleteOrgQuotaDefinition(@PathVariable String quotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/orgs/quota-definitions/"+quotaId, HttpMethod.DELETE, null);
    }

    /**
     * 공간 할당량 리스트를 조회한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/spaces/quota-definitions"})
    @ResponseBody
    public Map<String, Object> getSpaceQuotaDefinitions(HttpServletRequest request, @ModelAttribute Quota quota) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/spaces/quota-definitions", HttpMethod.GET, quota);
    }

    /**
     * 공간 할당량 정의를 조회한다.
     *
     * @param spaceQuotaId Quota GUID
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/spaces/quota-definitions/{spaceQuotaId}"})
    @ResponseBody
    public Map<String, Object> getSpaceQuotaDefinition(@ModelAttribute Quota quota, @PathVariable String spaceQuotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/spaces/quota-definitions/"+spaceQuotaId, HttpMethod.GET, quota);
    }

    /**
     * 공간 할당량 정의를 등록한다.
     *
     * @param quota Quota
     * @return ModelAndView model
     */
    @PostMapping(value = {Constants.V2_URL + "/spaces/quota-definitions"})
    @ResponseBody
    public Map<String, Object> createSpaceQuotaDefinition(HttpServletRequest request, @RequestBody Quota quota) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/spaces/quota-definitions", HttpMethod.POST, quota);
    }

    /**
     * 공간 할당량 정의를 수정한다.
     *
     * @param quota Quota
     *
     * @return ModelAndView model
     */
    @PutMapping(value = {Constants.V2_URL + "/spaces/quota-definitions/{spaceQuotaId}"})
    @ResponseBody
    public Map<String, Object> updateSpaceQuotaDefinition(@RequestBody Quota quota, @PathVariable String spaceQuotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/spaces/quota-definitions/"+spaceQuotaId, HttpMethod.PUT, quota);
    }

    /**
     * 공간 할당량 정의를 삭제한다.
     *
     * @param quotaId the quota GUID
     * @return Map <String, Object>
     */
    @DeleteMapping(value = {Constants.V2_URL + "/spaces/quota-definitions/{quotaId}"})
    @ResponseBody
    public Map<String, Object> deleteSpaceQuotaDefinition(@PathVariable String quotaId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key), Constants.V3_URL + "/spaces/quota-definitions/"+quotaId, HttpMethod.DELETE, null);
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}

