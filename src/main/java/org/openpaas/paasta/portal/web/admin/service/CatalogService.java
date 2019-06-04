package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by SEJI on 2018-03-22.
 */
@Service
public class CatalogService {

    @Autowired
    public CommonService commonService;


    @Value("${paasta.portal.storageapi.type}")
    private String storageType;

    /**
     * 스타터 팩 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPacksList(int key, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/starterpacks" + search, HttpMethod.GET, param);
    }

    /**
     * 스타터 팩 목록을 조회한다.
     *
     * @param no int,param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPack(int key, int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/starterpacks/" + no + search, HttpMethod.GET, param);
    }


    /**
     * 스타터 팩 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPackCount(int key, Catalog param) {
        String search = "";
        if (param.getName() != null) {
            search = "?";
            search += "name=" + param.getName();
        }
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/starterpacks/count" + search, HttpMethod.GET, param);
    }

    /**
     * 스타터 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertStarterPack(int key, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/starterpacks", HttpMethod.POST, param);
    }

    /**
     * 스타터 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateStarterPack(int key, int no, Catalog param) {

        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/starterpacks/" + no, HttpMethod.PUT, param);
    }

    /**
     * 스타터 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteStarterPack(int key, int no) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/starterpacks/" + no, HttpMethod.DELETE,null);
    }


    /**
     * 앱 개발환경 카탈로그 목록 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getDevelopPackCatalogList(int key, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/developpacks" + search, HttpMethod.GET, param);
    }


    /**
     * 앱 개발환경 카탈로그 목록 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getDevelopPackCatalog(int key, int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/developpacks/" + no + search, HttpMethod.GET, param);
    }


    /**
     * 앱 개발환경 카탈로그 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */

    public Map<String, Object> getDevelopPackCatalogCount(int key, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/developpacks/count", HttpMethod.GET, param);
    }

    /**
     * 앱 개발환경 카탈로그 카운터를 조회한다.
     *
     * @return Map(자바클래스)
     */

    public Map<String, Object> getServices(int key) {
        return commonService.procCfApiRestTemplate(key,Constants.V2_URL + "/services", HttpMethod.GET, null);
    }

    /**
     * 빌드 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertDevelopPackCatalog(int key, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/developpacks", HttpMethod.POST, param);
    }

    /**
     * 빌드 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateDevelopPackCatalog(int key, int no, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/developpacks/" + no, HttpMethod.PUT, param);
    }

    /**
     * 빌드 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteBuildPackCatalog(int key, int no) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/developpacks/" + no, HttpMethod.DELETE, null);
    }


    /**
     * 서비스 팩 목록을 조회한다.
     *
     * @param no int, param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalog(int key, int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/servicepacks/" + no + search, HttpMethod.GET, param);
    }

    /**
     * 서비스 팩 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogList(int key, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/servicepacks" + search, HttpMethod.GET, param);
    }

    /**
     * 서비스 팩 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogCount(int key, Catalog param) {
        String search = "";
        if (param.getName() != null) {
            search = "?";
            search += "name=" + param.getName();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/servicepacks/count" + search, HttpMethod.GET, param);
    }


    /**
     * 빌드 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertServicePackCatalog(int key, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/servicepacks", HttpMethod.POST, param);
    }

    /**
     * 빌드 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateServicePackCatalog(int key, int no, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/servicepacks/" + no, HttpMethod.PUT, param);
    }

    /**
     * 빌드 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteServicePackCatalog(int key, int no) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/servicepacks/" + no, HttpMethod.DELETE, null);
    }
}
