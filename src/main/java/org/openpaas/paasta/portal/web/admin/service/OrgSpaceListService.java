package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.Org;
import org.openpaas.paasta.portal.web.admin.model.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by MIN on 2018-03-22.
 */
@Service
public class OrgSpaceListService extends Common {


    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    @Autowired
    CommonService commonService;

    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView() {{
            setViewName("/orgSpaceList/orgSpaceListMain");
        }};
    }


    /**
     * admin 유저로 접근 가능한 조직 목록(모든 조직 목록)을 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     */
//    public Map<String, Object> getOrgsForAdmin(String reqUrl, HttpMethod httpMethod, String param, String reqToken) throws Exception {
//        return commonService.procCfApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, reqToken);
//    }

    public Map<String, Object> getOrgsForAdmin(int key, String reqUrl, HttpMethod httpMethod, Object obj) throws Exception {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }

    /**
     * admin 유저로 접근 가능한 영역 목록(모든 영역 목록)을 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     * @throws Exception the exception
     */
    public Map<String, Object> getSpacesForAdmin(int key, String reqUrl, HttpMethod httpMethod, Object obj) throws Exception {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }

    /**
     * 조직 요약 정보를 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getOrgSummary(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }


    /**
     * 조직 이름으로 조직의 정보를 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getOrgQuota(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }


    /**
     * 영역 요약 정보를 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getSpaceSummary(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }

    /**
     * 영역 쿼터 정보를 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getSpaceQuota(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }

    /**
     * 특정 영역을 선택하여 영역을 조회한다.
     *
     * @param reqUrl     RestUrl
     * @param httpMethod httpMethod
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getSpace(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,Constants.V3_URL + reqUrl, httpMethod, obj);
    }
}
