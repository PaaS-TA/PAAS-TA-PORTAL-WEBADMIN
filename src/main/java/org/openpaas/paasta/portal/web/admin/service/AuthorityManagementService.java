package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Constants;
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

    @Autowired
    CommonService commonService;

    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView() {{
            setViewName("/authority/authorityMain");
        }};
    }

    /**
     * Gets authority groups.
     *
     * @return the authority groups
     * @throws Exception the exception
     */
    public Map<String, Object> getAuthorityGroups(int key, String reqUrl, HttpMethod httpMethod, String param) throws Exception {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, param);
    }

    /**
     * Gets uaa user info.
     *
     * @param param the param
     * @return the uaa user info
     * @throws Exception the exception
     */
    public Map<String, Object> getUaaUserInfo(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }

    /**
     * Create authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> createAuthorityGroup(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procCfApiRestTemplate(key, Constants.V3_URL + reqUrl, httpMethod, param);
    }

    /**
     * Delete authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteAuthorityGroup(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procCfApiRestTemplate(key, Constants.V3_URL + reqUrl, httpMethod, param);
    }

    /**
     * Add group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> addGroupMembers(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, param);
    }

    /**
     * Delete group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteGroupMembers(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, param);
    }

    /**
     * Gets user name list.
     *
     * @param param the param
     * @return the user name list
     * @throws Exception the exception
     */
    public Map<String, Object> getUserNameList(int key, String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + reqUrl, httpMethod, param);
    }

}
