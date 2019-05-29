package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
     * @return Map
     */
    @GetMapping(value = {Constants.V3_URL + "/isolationSegments"})
    @ResponseBody
    public Map<String, Object> getIsolationSegments() {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments", HttpMethod.GET, null, this.getToken());
    }

    /**
     * Segments 할당 리스트를 조회한다. (organizationsId)
     *
     * @param organizationsId  the organizations id
     * @return Map
     */
    @GetMapping(value = {Constants.V3_URL + "/isolationSegments/organizations/{organizationsId:.+}"})
    @ResponseBody
    public Map<String, Object> getIsolationSegmentsByOrgGuid(@PathVariable String organizationsId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/organizations/"+organizationsId, HttpMethod.GET, null, this.getToken());
    }

    /**
     * isolationSegmentId 로 org 리스트를 조회한다.
     *
     * @param isolationSegmentId  the isolation segement id
     * @return Map
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
     * @return Map
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

    /**
     * Isolation Segments에 조직 권한을 부여한다.
     *
     * @param isolationSegmentId  the isolation segement id
     * @param organizationsId  the organizations id
     * @return Map
     * @throws Exception the exception
     */
    @PostMapping(Constants.V3_URL+"/isolationSegments/{isolationSegmentId:.+}/organizations/{organizationsId:.+}")
    @ResponseBody
    public Map eanbleIsolationSegments(@PathVariable String isolationSegmentId, @PathVariable String organizationsId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/"+isolationSegmentId+"/organizations/"+organizationsId, HttpMethod.POST, null, this.getToken());
    }

    /**
     * Isolation Segments에 조직 권한을 해제한다.
     *
     * @param isolationSegmentId  the isolation segement id
     * @param organizationsId  the organizations id
     * @return Map
     * @throws Exception the exception
     */
    @DeleteMapping(Constants.V3_URL+"/isolationSegments/{isolationSegmentId:.+}/organizations/{organizationsId:.+}")
    @ResponseBody
    public Map disableIsolationSegments(@PathVariable String isolationSegmentId, @PathVariable String organizationsId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/isolationSegments/"+isolationSegmentId+"/organizations/"+organizationsId, HttpMethod.DELETE, null, this.getToken());
    }

    /**
     * Organizations 에 Isolation Segments default 를 설정한다.
     *
     * @param organizationsId  the organizations id
     * @param isolationSegmentId  the isolation segement id
     * @return AddIsolationSegmentOrganizationEntitlementResponse
     * @throws Exception the exception
     */
    @PutMapping(Constants.V3_URL+"/orgs/{organizationsId:.+}/isolationSegments/{isolationSegmentId:.+}")
    @ResponseBody
    public Map setOrgDefaultIsolationSegments(@PathVariable String organizationsId, @PathVariable String isolationSegmentId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/orgs/"+organizationsId+"/isolationSegments/"+isolationSegmentId, HttpMethod.PUT, null, this.getToken());
    }

    /**
     * Organizations 에 Isolation Segments default 를 재설정한다.
     *
     * @param organizationsId  the organizations id
     * @return AddIsolationSegmentOrganizationEntitlementResponse
     * @throws Exception the exception
     */
    @PutMapping(Constants.V3_URL+"/orgs/{organizationsId:.+}/isolationSegments/reset")
    public Map resetOrgDefaultIsolationSegments(@PathVariable String organizationsId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/orgs/"+organizationsId+"/isolationSegments/reset", HttpMethod.PUT, null, this.getToken());
    }

    /**
     * Space Isolation 에 Isolation Segments 를 설정한다.
     *
     * @param spaceId  the space id
     * @param isolationSegmentId  the isolation segement id
     * @return Map
     * @throws Exception the exception
     */
    @PutMapping(Constants.V3_URL+"/spaces/{spaceId:.+}/isolationSegments/{isolationSegmentId:.+}")
    @ResponseBody
    public Map setSpaceDefaultIsolationSegments(@PathVariable String spaceId, @PathVariable String isolationSegmentId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/spaces/"+spaceId+"/isolationSegments/"+isolationSegmentId, HttpMethod.PUT, null, this.getToken());
    }

    /**
     * Space Isolation 에 Isolation Segments 를 해제한다.
     *
     * @param spaceId  the space id
     * @return AssignSpaceIsolationSegmentResponse
     * @throws Exception the exception
     */
    @PutMapping(Constants.V3_URL+"/spaces/{spaceId:.+}/isolationSegments/reset")
    @ResponseBody
    public Map resetSpaceDefaultIsolationSegments(@PathVariable String spaceId) throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V3_URL + "/spaces/"+spaceId+"/isolationSegments/reset", HttpMethod.PUT, null, this.getToken());
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
