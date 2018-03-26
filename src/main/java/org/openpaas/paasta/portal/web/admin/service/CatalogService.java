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

    /**
     * 앱 템플릿명 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterNamesList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate("/catalog/starternameCatalogs" + search, HttpMethod.GET, param, null);
    }


    /**
     * 앱 개발환경 카탈로그 목로 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getBuildPackCatalogList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate("/catalog/buildpackCatalogs" + search, HttpMethod.GET, param, null);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getServicePackCatalogList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate("/catalog/servicepackCatalogs" + search, HttpMethod.GET, param, null);
    }

    public Map<String, Object> getServicePackCatalogCount(Catalog param){
        return commonService.procRestTemplate("/catalog/getServicePackCatalogCount", HttpMethod.POST, param, null);
    }
}
