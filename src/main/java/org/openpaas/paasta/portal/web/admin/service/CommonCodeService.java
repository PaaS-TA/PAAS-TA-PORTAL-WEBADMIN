package org.openpaas.paasta.portal.web.admin.service;


import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class CommonCodeService {

    @Autowired
    public CommonService commonService;


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param param CommonCode
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCodeDetailList(int key, CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        if (param.getGroupId() != null) {
            search = "?";
            search += "groupId=" + param.getGroupId();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codedetail/" + search, HttpMethod.GET, param);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param no CommonCode
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCodeDetail(int key, int no) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codedetail/" + no , HttpMethod.GET, null);
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getGroupDetailList(int key, CommonCode param) {
        String search = "";

        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }

        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codegroup" + search, HttpMethod.GET, param);
    }



    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getGroupDetail(int key, String id, CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/codegroup/" + id + search, HttpMethod.GET, param);
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param param odeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCommonCodeJoinGroup(int key, CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/commoncode/" + search, HttpMethod.GET, param);
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetailGroup(int key, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codegroup", HttpMethod.POST, param);
    }

    /**
     * 공통 코드을 등록한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetail(int key, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codedetail", HttpMethod.POST, param);
    }


    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> updateCommonGroup(int key, String id, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codegroup/" + id, HttpMethod.PUT, param);
    }


    /**
     * 공통 코드을 수정한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> updateCommonDetail(int key, int no, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codedetail/" + no, HttpMethod.PUT, param);
    }


    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonGroup(int key, String id) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codegroup/" + id, HttpMethod.DELETE, null);
    }


    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonDetail(int key, int no) {
        return commonService.procCommonApiRestTemplate(key,Constants.V2_URL + "/codedetail/" + no, HttpMethod.DELETE, null);
    }

}
