package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 클라이언트 메인 페이지 이동
     *
     * @return ModelAndView client main
     */
    @RequestMapping(value = {"/clientMain"}, method = RequestMethod.GET)
    public ModelAndView getClientMain() {
        return new ModelAndView(){{setViewName("/client/clientMain");}};
    }

    /**
     * 클라이언트 등록 페이지 이동
     *
     * @return ModelAndView client form
     */
    @RequestMapping(value = {"/clientForm"}, method = RequestMethod.GET)
    public ModelAndView getClientForm() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_C);
        mv.setViewName("/client/clientForm");

        return mv;
    }

    /**
     * 클라이언트 조회/수정 페이지 이동
     *
     * @param req the req
     * @return ModelAndView client form
     */
    @RequestMapping(value = {"/clientForm"}, method = RequestMethod.POST)
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
    public Map<String, Object> getClientList() throws Exception {
        return commonService.procCfApiRestTemplate(Constants.V2_URL + "/clients", HttpMethod.GET, null, null);
    }


    /**
     * 클라이언트 정보 조회
     *
     * @param param Map
     * @return Map client
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procCfApiRestTemplate("/client/getClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 등록
     *
     * @param param Map
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/registerClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procCfApiRestTemplate("/client/registerClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 수정
     *
     * @param param Map
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procCfApiRestTemplate("/client/updateClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 삭제
     *
     * @param param Map
     * @return Map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procRestTemplate("/client/deleteClient", HttpMethod.POST, param, null);
    }

}
