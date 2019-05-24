package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.controller.DomainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by MIN on 2018-04-05.
 */
@Service
public class DomainService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DomainController.class);
    private final String V2_URL = "/v2";
    private final String V3_URL = "/v3";

    @Autowired
    CommonService commonService;

    /**
     * 도메인 가져오기
     *
     * @return domains
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.26 최초작성
     */
    public Map<String, Object> getDomains(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        return commonService.procCfApiRestTemplate(key,V3_URL + reqUrl, httpMethod, obj);
    }


    /**
     * 도메인 추가
     *
     * @return boolean
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.27 최초작성
     */
    @PostMapping(V2_URL + "/domains")
    @ResponseBody
    public Map<String, Object> addDomain(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCfApiRestTemplate(V3_URL + reqUrl, httpMethod, param, reqToken);
    }

    /**
     * 도메인 삭제
     *
     * @return boolean
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.27 최초작성
     */
    @DeleteMapping(V2_URL + "/domains")
    @ResponseBody
    public Map<String, Object> deleteDomain(String reqUrl, HttpMethod httpMethod, Object param, String reqToken) {
        return commonService.procCfApiRestTemplate(V3_URL + reqUrl, httpMethod, param, reqToken);
    }
}
