package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class IsolationSegmentsController extends Common {

    /**
     * Segments 할당 관리 메인 화면이다.
     *
     * @return model and view
     */
    @GetMapping(value = {"/isolationSegments"})
    public ModelAndView isolationSegmentsMain() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("isolationSegments/isolationSegments");

        return mv;
    }

    /**
     * Segments 할당 리스트를 조회한다.
     *
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V3_URL + "/isolationSegments"})
    @ResponseBody
    public Map<String, Object> getIsolationSegments() {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments", HttpMethod.GET, null, this.getToken());
    }

    /**
     * isolationSegmentId 로 org 리스트를 조회한다.
     *
     * @param isolationSegmentId  the isolation segement id
     * @return ModelAndView model
     * @throws Exception the exception
     */
    @GetMapping(Constants.V3_URL+"/isolationSegments/{isolationSegmentId:.+}/organizations")
    @ResponseBody
    public Map<String, Object> getIsolationSegmentsOrgs(@PathVariable String isolationSegmentId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/"+isolationSegmentId+"/organizations", HttpMethod.GET, null, this.getToken());
    }

    /**
     * Isolation Segments를 생성한다.
     *
     * @param segementName  the segement name
     * @return ModelAndView model
     * @throws Exception the exception
     */
    @PostMapping(Constants.V3_URL+"/isolationSegments/{segementName:.+}")
    @ResponseBody
    public Map<String, Object> createIsolationSegments(@PathVariable String segementName) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/"+segementName, HttpMethod.POST, null, this.getToken());
    }

    /**
     * Isolation Segments를 삭제한다.
     *
     * @param isolationSegmentId  the isolation segement id
     * @return Map
     * @throws Exception the exception
     */
    @DeleteMapping(Constants.V3_URL+"/isolationSegments/{isolationSegmentId:.+}")
    @ResponseBody
    public Map<String, Object> deleteIsolationSegments(@PathVariable String isolationSegmentId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/"+isolationSegmentId, HttpMethod.DELETE, null, this.getToken());
    }
}
