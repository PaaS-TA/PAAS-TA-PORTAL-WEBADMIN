package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String V2_URL = "/v2";

    /**
     * 스타터 팩 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPacksList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks" + search, HttpMethod.GET, param, null);
    }

    /**
     * 스타터 팩 목록을 조회한다.
     *
     * @param no int,param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPack(int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks/" + no + search, HttpMethod.GET, param, null);
    }


    /**
     * 스타터 팩 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterPackCount(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks" + search, HttpMethod.GET, param, null);
    }

    /**
     * 스타터 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertStarterPack(Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks", HttpMethod.POST, param, null);
    }

    /**
     * 스타터 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateStarterPack(int no, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks/" + no, HttpMethod.PUT, param, null);
    }

    /**
     * 스타터 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteStarterPack(int no) {
        return commonService.procCommonApiRestTemplate(V2_URL + "/starterpacks/" + no, HttpMethod.DELETE, null, null);
    }


    /**
     * 앱 개발환경 카탈로그 목록 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getDevelopPackCatalogList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks" + search, HttpMethod.GET, param, null);
    }


    /**
     * 앱 개발환경 카탈로그 목록 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getDevelopPackCatalog(int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks/" + no + search, HttpMethod.GET, param, null);
    }


    /**
     * 앱 개발환경 카탈로그 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */

    public Map<String, Object> getDevelopPackCatalogCount(Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks/count", HttpMethod.GET, param, null);
    }

    /**
     * 빌드 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertDevelopPackCatalog(Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks", HttpMethod.POST, param, null);
    }

    /**
     * 빌드 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateDevelopPackCatalog(int no, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks/" + no, HttpMethod.PUT, param, null);
    }

    /**
     * 빌드 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteBuildPackCatalog(int no) {
        return commonService.procCommonApiRestTemplate(V2_URL + "/developpacks/" + no, HttpMethod.DELETE, null, null);
    }


    /**
     * 서비스 팩 목록을 조회한다.
     *
     * @param no int, param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalog(int no, Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks/" + no + search, HttpMethod.GET, param, null);
    }

    /**
     * 서비스 팩 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks" + search, HttpMethod.GET, param, null);
    }

    /**
     * 서비스 팩 카운터를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogCount(Catalog param) {
        String search = "";
        if (param.getName() != null) {
            search = "?";
            search += "name=" + param.getName();
        }
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks/count" + search, HttpMethod.GET, param, null);
    }


    /**
     * 빌드 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertServicePackCatalog(Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks", HttpMethod.POST, param, null);
    }

    /**
     * 빌드 팩을 수정한다.
     *
     * @param no int param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateServicePackCatalog(int no, Catalog param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks/" + no, HttpMethod.PUT, param, null);
    }

    /**
     * 빌드 팩을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteServicePackCatalog(int no) {
        return commonService.procCommonApiRestTemplate(V2_URL + "/servicepacks/" + no, HttpMethod.DELETE, null, null);
    }

}
