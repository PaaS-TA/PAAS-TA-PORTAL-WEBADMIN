package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 도메인 컨트롤러 - 도메인 정보를 조회, 수정, 삭제한다.
 *
 * @version 1.0
 * @since 2016.09.19 최초작성
 */
@RestController
public class DomainController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainController.class);
    private final String V2_URL = "/v2";
    /**
     * 도메인 가져오기
     *
     * @param status the status
     * @return domains
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.26 최초작성
     */
    @GetMapping(V2_URL+"/domains/{status}")
    @ResponseBody
    public Map<String, Object> getDomains(@PathVariable String status, HttpServletRequest request) {
        String key = request.getParameter("key");
        LOGGER.info(">>index : key : getDomains----> "  +key);
        LOGGER.info("Start getDomains" + status);
        return domainService.getDomains(Integer.parseInt(key),"/domains-admin/" + status, HttpMethod.GET, null);
    }


    /**
     * 도메인 추가
     *
     * @param body the body
     * @return boolean
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.27 최초작성
     */
    @PostMapping(V2_URL+"/domains")
    @ResponseBody
    public Map<String, Object> addDomain(@RequestBody Map body) {
        LOGGER.info("Start addDomain");
        return domainService.addDomain("/domains-admin", HttpMethod.POST, body, getToken());
    }

    /**
     * 도메인 삭제
     *
     * @param body the body
     * @return boolean
     * @throws Exception
     * @author kimdojun
     * @version 1.0
     * @since 2016.7.27 최초작성
     */
    @DeleteMapping(V2_URL+"/domains")
    @ResponseBody
    public Map<String, Object> deleteDomain(@RequestBody Map body) {
        LOGGER.info("Start deleteDomain");
        return domainService.deleteDomain("/domains-admin", HttpMethod.DELETE, body, getToken());
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}




