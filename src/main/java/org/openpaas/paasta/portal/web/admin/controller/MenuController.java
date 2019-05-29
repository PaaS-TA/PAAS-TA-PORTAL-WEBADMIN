package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Menu;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 메뉴 목록 조회, 등록, 삭제 등 메뉴 관리의 API 를 호출 하는 컨트롤러이다.
 *
 * @version 1.0
 * @since 2016.09.29 최초작성
 */
@Controller
@RequestMapping(value = {"/menu"})
public class MenuController extends Common {

    /**
     * 메뉴 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/menuMain"}, method = RequestMethod.GET)
    public ModelAndView getMenuMain() {
        return new ModelAndView() {{
            setViewName("/menu/menuMain");
        }};
    }


    /**
     * 메뉴 최대값을 조회한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuMaxNoList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMenuMaxNoList(@RequestBody Menu param) {
        return commonService.procRestTemplate("/menu/getMenuMaxNoList", HttpMethod.POST, param, null);
    }


    /**
     * 메뉴를 조회한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMenuList(@RequestBody Menu param) {
        return commonService.procRestTemplate("/menu/getMenuList", HttpMethod.POST, param, null);
    }


    /**
     * 메뉴 상세 조회를 한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuDetail"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMenuDetail(@RequestBody Menu param) {
        return commonService.procRestTemplate("/menu/getMenuDetail", HttpMethod.POST, param, null);
    }


    /**
     * 메뉴를 등록한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/insertMenu"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertMenu(@RequestBody Menu param) throws Exception {
        return commonService.procRestTemplate("/menu/insertMenu", HttpMethod.POST, commonService.setUserId(param), null);
    }


    /**
     * 메뉴를 수정한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/updateMenu"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMenu(@RequestBody Menu param) throws Exception {
        return commonService.procRestTemplate("/menu/updateMenu", HttpMethod.PUT, commonService.setUserId(param), null);
    }


    /**
     * 메뉴를 삭제한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/deleteMenu"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMenu(@RequestBody Menu param) {
        return commonService.procRestTemplate("/menu/deleteMenu", HttpMethod.POST, param, null);
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
