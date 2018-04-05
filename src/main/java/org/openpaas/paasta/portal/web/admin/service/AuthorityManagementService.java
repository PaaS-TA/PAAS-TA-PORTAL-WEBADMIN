package org.openpaas.paasta.portal.web.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by MIN on 2018-04-04.
 */
@Service
public class AuthorityManagementService {
    private final String V2_URL = "/v2";
    @Autowired
    CommonService commonService;

    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView(){{setViewName("/authority/authorityMain");}};
    }

    /**
     * Gets authority groups.
     *
     *
     * @return the authority groups
     * @throws Exception the exception
     */
    public Map<String, Object> getAuthorityGroups(String reqUrl, HttpMethod httpMethod, String param, String reqToken) throws Exception {
        return commonService.procCfApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Gets uaa user info.
     *
     * @param param the param
     * @return the uaa user info
     * @throws Exception the exception
     */
    public Map<String, Object> getUaaUserInfo(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Create authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> createAuthorityGroup(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procCfApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Delete authority group map.
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteAuthorityGroup(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procCfApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Add group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> addGroupMembers(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procCfApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Delete group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteGroupMembers(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procCfApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * Gets user name list.
     *
     * @param param the param
     * @return the user name list
     * @throws Exception the exception
     *
     */
    public Map<String, Object> getUserNameList(String reqUrl, HttpMethod httpMethod, Map param, String reqToken) throws Exception {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

}
