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

    public Map<String, Object> getStarterNamesList(Catalog param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        System.out.println("############ " + "/catalog/starternameCatalogs" + search);
        return commonService.procCommonApiRestTemplate("/catalog/starternameCatalogs" + search, HttpMethod.GET, param, null);
    }

    public Map<String, Object> getServicePackCatalogCount(Catalog param){
        return commonService.procRestTemplate("/catalog/getServicePackCatalogCount", HttpMethod.POST, param, null);
    }
}
