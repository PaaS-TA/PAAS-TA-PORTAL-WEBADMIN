package org.openpaas.paasta.portal.web.admin.service;


import org.openpaas.paasta.portal.web.admin.controller.CommonCodeController;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Service
public class CommonCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeController.class);

    @Autowired
    public CommonService commonService;


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param key String(아이디)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getCommonCode(String key) {
        return commonService.procCommonApiRestTemplate("/code/codeDetail/" + key, HttpMethod.GET, new CommonCode(), null);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param commonCode the param
     * @return Map(자바클래스)
     */

    public Map<String, Object> getCommonCode(CommonCode commonCode) {

        StringBuffer param = new StringBuffer();
        param.append("?");
        param.append("groupId=" + commonCode.getGroupId());
        param.append("&key=" + commonCode.getKey());
        return commonService.procCommonApiRestTemplate("/code/codeDetail" + param, HttpMethod.GET, param, null);
    }


    /**
     * 공통코드를 저장한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertCommonCode(CommonCode param) {
        return commonService.procCommonApiRestTemplate("/code/codeDetail", HttpMethod.POST, param, null);
    }


    /**
     * 공통코드를 수정한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateCommonCode(CommonCode param) {
        return commonService.procCommonApiRestTemplate("/code/codeDetail", HttpMethod.PUT, param, null);
    }


    /**
     * 공통코드를 삭제한다.
     *
     * @param param the param
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteCommonCode(CommonCode param) {
        return commonService.procCommonApiRestTemplate("/code/codeDetail", HttpMethod.DELETE, param, null);
    }


}
