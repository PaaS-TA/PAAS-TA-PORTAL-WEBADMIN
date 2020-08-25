package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ClientController.java
 * 클라이언트 목록 , 클라이언트 삭제 등 클라이언트 관련 API를 호출 받는 컨트롤러
 *
 * @author yjkim
 * @version 1.0
 * @since 2016.9.28 최초작성
 */
@Controller
public class ClientController  extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 클라이언트 메인 페이지 이동
     *
     * @return ModelAndView client main
     */
    @GetMapping(value = {"/clientMain"})
    public ModelAndView getClientMain() {
        return new ModelAndView(){{setViewName("/client/clientMain");}};
    }

    /**
     * 클라이언트 등록 페이지 이동
     *
     * @return ModelAndView client form
     */
//    @GetMapping(value = {"/clientForm"})
//    public ModelAndView getClientForm() {
//        ModelAndView mv = new ModelAndView();
//
//        mv.addObject("INSERT_FLAG", Constants.CUD_C);
//        mv.setViewName("/client/clientForm");
//
//        return mv;
//    }

    /**
     * 클라이언트 조회/수정 페이지 이동
     *
     * @param req the req
     * @return ModelAndView client form
     */
    @GetMapping(value = {"/clientForm"})
    public ModelAndView getClientForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_U);
        mv.addObject("CLIENT_ID", req.getParameter("clientId"));
        mv.setViewName("/client/clientForm");

        return mv;
    }


    /**
     * 클라이언트 목록 조회
     *
     * @param
     * @return Map client list
     * @throws Exception the exception
     */
    @GetMapping(value = {Constants.V2_URL + "/clients"})
    @ResponseBody
    public Map<String, Object> getClientList(HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/clients", HttpMethod.GET, null);
    }


    /**
     * 클라이언트 정보 조회
     *
     * @param clientId
     * @return Map client
     * @throws Exception the exception
     */
    @GetMapping(value = {Constants.V2_URL + "/clients/{clientId}"})
    @ResponseBody
    public Map<String, Object> getClient(@PathVariable String clientId, HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/clients/"+clientId, HttpMethod.GET, null);
    }

    /**
     * 클라이언트 등록
     *
     * @param param Map
     * @return Map map
     * @throws Exception the exception
     */
    @PostMapping(value = {Constants.V2_URL + "/clients"})
    @ResponseBody
    public Map<String, Object> registerClient(@RequestBody Map<String, Object> param, HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key), Constants.V3_URL + "/clients", HttpMethod.POST, param);
    }

    /**
     * 클라이언트 수정
     *
     * @param param Map
     * @return Map map
     * @throws Exception the exception
     */
    @PutMapping(value = {Constants.V2_URL + "/clients"})
    @ResponseBody
    public Map<String, Object> updateClient(@RequestBody Map<String, Object> param, HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/clients", HttpMethod.PUT, param);
    }

    /**
     * 클라이언트 삭제
     *
     * @param clientId
     * @return Map map
     * @throws Exception the exception
     */
    @DeleteMapping(value = {Constants.V2_URL + "/clients/{clientId}"})
    @ResponseBody
    public Map<String, Object> deleteClient(@PathVariable String clientId, HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/clients/" + clientId, HttpMethod.DELETE, null);
    }

    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }

}
