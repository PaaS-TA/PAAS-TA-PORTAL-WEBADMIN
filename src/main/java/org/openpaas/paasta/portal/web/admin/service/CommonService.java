package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.common.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 공통 기능을 구현한 서비스 클래스이다.
 *
 * @author 김도준
 * @version 1.0
 * @since 2016.07.07 최초작성
 */
@Service
public class CommonService extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String CF_AUTHORIZATION_HEADER_KEY = "cf-Authorization";
//    private final RestTemplate restTemplate;


    RestTemplate restTemplate;


    private String apiUrl;

    @Value("${paasta.portal.api.authorization.base64}")
    private String base64Authorization;

    @Value("${paasta.portal.api.zuulUrl.cfapi}")
    private String cfApiUrl;

    @Value("${paasta.portal.api.zuulUrl.commonapi}")
    private String commonApiUrl;

    @Value("${paasta.portal.api.zuulUrl.storageapi}")
    private String storageApiUrl;

    @Value("${paasta.portal.storageapi.type}")
    private String storageApiType;

    /**
     * REST TEMPLATE 처리
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    public Map<String, Object> procRestTemplate(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUrl + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        LOGGER.info("procRestTemplate reqUrl :: {} || status code :: {}", reqUrl, resEntity.getStatusCode());

        return resultMap;
    }

    /**
     * REST TEMPLATE 처리
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @return map map
     */
    public Map<String, Object> procRestTemplate(int key, String reqUrl, HttpMethod httpMethod, Object obj) {
        LOGGER.info("> Init procRestTemplate");

        Map map = getServerInfo(key);
        String apiUri = map.get("apiuri").toString();
        String authorization = map.get("authorization").toString();
        String token = map.get("token").toString();

        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        if (null != token && !"".equals(token)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, token);

        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUrl + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        LOGGER.info("procRestTemplate reqUrl :: {} ", reqUrl);

        return resultMap;
    }


    /**
     * REST TEMPLATE 처리
     *
     * @param reqUrl   the req url
     * @param file     the file
     * @param reqToken the req token
     * @return map map
     * @throws Exception the exception
     */
    public Map<String, Object> procRestTemplate(String reqUrl, MultipartFile file, String reqToken) throws Exception {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        final MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() throws IllegalStateException {
                return file.getOriginalFilename();
            }
        };

        data.add("file", resource);
        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data, reqHeaders);
        final ResponseEntity<Map> resEntity = restTemplate.exchange(apiUrl + reqUrl, HttpMethod.POST, requestEntity, Map.class);

        Map resultMap = resEntity.getBody();

        LOGGER.info("procRestTemplate status code :: {}", resEntity.getStatusCode());
        return resultMap;
    }


    /**
     * REST TEMPLATE 처리
     *
     * @param <T>          the type parameter
     * @param reqUrl       the req url
     * @param httpMethod   the http method
     * @param obj          the obj
     * @param reqToken     the req token
     * @param responseType the response type
     * @return response entity
     */
    public <T> ResponseEntity<T> procRestTemplate(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken, Class<T> responseType) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);

        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        ResponseEntity<T> result = restTemplate.exchange(apiUrl + reqUrl, httpMethod, reqEntity, responseType);

        return result;
    }

    /**
     * REST TEMPLATE 처리
     *
     * @param <T>          the type parameter
     * @param reqUrl       the req url
     * @param httpMethod   the http method
     * @param obj          the obj
     * @param responseType the response type
     * @return response entity
     */
    public <T> ResponseEntity<T> procRestTemplateV2(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken, Class<T> responseType) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);

        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);
        LOGGER.info("procRestTemplateV2 reqToken :: " + reqToken);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("apiUrl::" + apiUrl);
        ResponseEntity<T> result = restTemplate.exchange(apiUrl + reqUrl, httpMethod, reqEntity, responseType);

        return result;
    }

    /**
     * REST TEMPLATE 처리
     *
     * @param <T>          the type parameter
     * @param reqUrl       the req url
     * @param httpMethod   the http method
     * @param obj          the obj
     * @param responseType the response type
     * @return response entity
     */
    public <T> ResponseEntity<T> procRestTemplateV2(String reqUrl, HttpMethod httpMethod, Object obj, Class<T> responseType) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("apiUrl::" + apiUrl);
        ResponseEntity<T> result = restTemplate.exchange(apiUrl + reqUrl, httpMethod, reqEntity, responseType);

        return result;
    }


    /**
     * USER ID를 조회한다.
     *
     * @return user id
     */
    public String getUserId() {
        UserList userList = (UserList) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userList.getUsername();
    }


    /**
     * USER ID를 설정한다.
     *
     * @param classObject the class object
     * @return the object
     * @throws Exception the exception
     */
    public Object setUserId(Object classObject) throws Exception {
        String methodName = "setUserId";
        Method method = classObject.getClass().getMethod(methodName, String.class);
        Object[] paramObject = new Object[]{this.getUserId()};

        method.invoke(classObject, paramObject);

        return classObject;
    }


    /**
     * REST TEMPLATE 처리 - CfApi(PortalApi)
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    public Map<String, Object> procCfApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        LOGGER.info(base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("cfApiUrl::" + cfApiUrl + reqUrl);
        ResponseEntity<Map> resEntity = restTemplate.exchange(cfApiUrl + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        if (resultMap != null) {
            LOGGER.info("procCfApiRestTemplate reqUrl :: {} || status code :: {}", reqUrl, resEntity.getStatusCode());
        }
        return resultMap;
    }

    /**
     * REST TEMPLATE 처리 - CfApi(PortalApi)
     *
     * @param apiUri
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    public Map<String, Object> procCfApiRestTemplate(String apiUri, String reqUrl, String authorization,HttpMethod httpMethod, Object obj, String reqToken) {

        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        LOGGER.info(base64Authorization);

        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("> cfApiUrl : " + apiUri + "/portalapi" + reqUrl); //http://+apiUri+"/portalapi"+reqUrl

        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUri + "/portalapi" + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();
        if (resultMap != null) {
            LOGGER.info("procCfApiRestTemplate reqUrl :: {} || status code :: {}", reqUrl, resEntity.getStatusCode());
        }
        return resultMap;
    }


    /**
     * REST TEMPLATE 처리 - CfApi(PortalApi)
     *
     * @param key
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @return map map
     */
    public Map<String, Object> procCfApiRestTemplate(int key, String reqUrl, HttpMethod httpMethod, Object obj) {

        LOGGER.info("> Init");

        Map map = getServerInfo(key);
        String apiUri = map.get("apiuri").toString();
        String authorization = map.get("authorization").toString();
        String token = map.get("token").toString();

        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        LOGGER.info(authorization);

        if (null != token && !"".equals(token)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, token);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("> apiUri : " + apiUri);
        LOGGER.info("> cfApiUrl : " + apiUri + "/portalapi" + reqUrl);

        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUri + "/portalapi" + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();
        if (resultMap != null) {
            LOGGER.info("procCfApiRestTemplate reqUrl :: {} ", reqUrl);
        }
        return resultMap;
    }


    /**
     * REST TEMPLATE 처리 - CommonApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    public Map<String, Object> procCommonApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        LOGGER.info("CommonApiUrl::" + commonApiUrl + reqUrl);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        ResponseEntity<Map> resEntity = restTemplate.exchange(commonApiUrl + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        LOGGER.info("procCommonApiRestTemplate reqUrl :: {} || status code :: {}", reqUrl, resEntity.getStatusCode());
        return resultMap;
    }


    /**
     * REST TEMPLATE 처리 - CommonApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    public Map<String, Object> procCommonApiRestTemplate(String apiUri, String reqUrl, String authorization, HttpMethod httpMethod, Object obj, String reqToken) {
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        LOGGER.info("CommonApiUrl::" + apiUri + "/commonapi" + reqUrl);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUri + "/commonapi" + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        LOGGER.info("procCommonApiRestTemplate reqUrl :: {} || status code :: {}", reqUrl, resEntity.getStatusCode());
        return resultMap;
    }

    /**
     * REST TEMPLATE 처리 - CommonApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @return map map
     */
    public Map<String, Object> procCommonApiRestTemplate(int key, String reqUrl, HttpMethod httpMethod, Object obj) {

        LOGGER.info("> Init");

        Map map = getServerInfo(key);
        String apiUri = map.get("apiuri").toString();
        String authorization = map.get("authorization").toString();
        String token = map.get("token").toString();

        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        LOGGER.info(authorization);

        if (null != token && !"".equals(token)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, token);
        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);

        LOGGER.info("> apiUricommonApiUrl[key] : " + apiUri + "/commonapi" + reqUrl);

        ResponseEntity<Map> resEntity = restTemplate.exchange(apiUri + "/commonapi" + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        LOGGER.info("> resEntity : " +resEntity);

        if (resultMap != null) {
            LOGGER.info("procCfApiRestTemplate reqUrl :: {} ", reqUrl);
        }
        return resultMap;
    }


    /**
     * REST TEMPLATE 처리 - StorageApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param bodyObject the obj
     * @param reqToken   the req token
     * @return map map
     */
    public <T> ResponseEntity<T> procStorageApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken, Class<T> resClazz) {
        restTemplate = new RestTemplate();

        // create url
        String storageRequestURL = storageApiUrl + "/v2/" + storageApiType + '/';
        if (null != reqUrl && false == "".equals(reqUrl)) storageRequestURL += reqUrl;

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);
        if (null == bodyObject) bodyObject = new LinkedMultiValueMap<>();
        HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);


        ResponseEntity<T> resEntity = restTemplate.exchange(storageRequestURL, httpMethod, reqEntity, resClazz);
        LOGGER.info("procRestStorageApiTemplate reqUrl :: {} || resultEntity type :: {}", storageRequestURL, resEntity.getHeaders().getContentType());
        LOGGER.info("procRestStorageApiTemplate response Http status code :: {}", resEntity.getStatusCode());
        return resEntity;
    }

    public ResponseEntity<String> procStorageApiRestTemplateText(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
        LOGGER.info(">>> Init procStorageApiRestTemplateText >> reqUrl "+reqUrl +" :: httpMethod "+ httpMethod+" :: "+ "reqToken"+ reqToken);
        return procStorageApiRestTemplate(reqUrl, httpMethod, bodyObject, reqToken, String.class);
    }

    public ResponseEntity<byte[]> procStorageApiRestTemplateBinary(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
        LOGGER.info(">>> Init procStorageApiRestTemplateBinary >> reqUrl "+reqUrl +" :: httpMethod"+ httpMethod+" :: " + bodyObject+" :: "+ "reqToken"+ reqToken);
        return procStorageApiRestTemplate(reqUrl, httpMethod, bodyObject, reqToken, byte[].class);
    }


    /**
     * REST TEMPLATE 처리 - StorageApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param bodyObject the obj
     * @return map map
     */
    public <T> ResponseEntity<T> procStorageApiRestTemplate(int key, String reqUrl, HttpMethod httpMethod, Object bodyObject, Class<T> resClazz) {

        LOGGER.info("> Init");

        Map map = getServerInfo(key);
        String apiUri = map.get("apiuri").toString();
        String authorization = map.get("authorization").toString();
        String token = map.get("token").toString();

        // create url
        String storageRequestURL =  apiUri + "/storageapi/v2/" + storageApiType + '/';
        LOGGER.info("storageRequestURL" + storageRequestURL);
        if (null != reqUrl && false == "".equals(reqUrl)) storageRequestURL += reqUrl;

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, authorization);
        if (null != token && !"".equals(token)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, token);
        if (null == bodyObject) bodyObject = new LinkedMultiValueMap<>();
        HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);


        ResponseEntity<T> resEntity = restTemplate.exchange(storageRequestURL, httpMethod, reqEntity, resClazz);
        LOGGER.info("procRestStorageApiTemplate reqUrl :: {} || resultEntity type :: {}", storageRequestURL, resEntity.getHeaders().getContentType());
        LOGGER.info("procRestStorageApiTemplate response Http status code :: {}", resEntity.getStatusCode());
        return resEntity;
    }

    public ResponseEntity<String> procStorageApiRestTemplateText(int key, String reqUrl, HttpMethod httpMethod, Object bodyObject) {
        LOGGER.info(">>> Init procStorageApiRestTemplateText >> reqUrl "+reqUrl +" :: httpMethod "+ httpMethod+" ::");
        return procStorageApiRestTemplate(key,reqUrl, httpMethod, bodyObject, String.class);
    }

    public ResponseEntity<byte[]> procStorageApiRestTemplateBinary(int key, String reqUrl, HttpMethod httpMethod, Object bodyObject) {
        LOGGER.info(">>> Init procStorageApiRestTemplateBinary >> reqUrl" +reqUrl +"  :: httpMethod "+ httpMethod+" ::");
        return procStorageApiRestTemplate(key,reqUrl, httpMethod, bodyObject, byte[].class);
    }

}