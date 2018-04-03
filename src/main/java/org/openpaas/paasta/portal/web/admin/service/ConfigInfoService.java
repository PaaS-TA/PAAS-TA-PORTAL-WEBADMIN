package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by MIN on 2018-03-27.
 */
@Service
public class ConfigInfoService extends Common {

    private final String V2_URL = "/v2";

    @Autowired
    CommonService commonService;


    /**
     * 설정 정보 메인 화면이다.
     *
     * @return value model and view
     */
    public ModelAndView configInfo() {
        return new ModelAndView(){{setViewName("/configInfo/configInfoMain");}};
    }


    /**
     * 설정 정보를 조회한다.
     * @param reqUrl RestUrl
     * @param httpMethod  httpMethod
     * @param param param
     * @param reqToken Token
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getValues(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }

    /**
     * 설정 정보를 조회한다.
     * @param reqUrl RestUrl
     * @param httpMethod  httpMethod
     * @param param param
     * @param reqToken Token
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> getValue(String reqUrl, HttpMethod httpMethod, String param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }





    /**
     * 설정 정보를 수정한다.
     * @param reqUrl RestUrl
     * @param httpMethod  httpMethod
     * @param param param
     * @param reqToken Token
     * @return Map (자바 Map 클래스)
     */
    public Map<String, Object> updateValue(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCommonApiRestTemplate(V2_URL+reqUrl, httpMethod, param, reqToken);
    }
}
