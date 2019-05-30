package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 코드 목록 조회, 등록, 삭제 등 코드 관리의 API 를 호출 하는 컨트롤러이다.
 *
 * @author SEJI
 * @since 2018.04.01
 */
@Controller
public class CommonCodeController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeController.class);

    private final String V2_URL = "/v2";

    /**
     * 공통코드 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/code"}, method = RequestMethod.GET)
    public ModelAndView getCommonCodeMain() {
        return new ModelAndView() {{
            setViewName("/commonCode/commonCodeMain");
        }};
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param param odeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/commoncode")
    @ResponseBody
    public Map<String, Object> getCommonCodeDetail(HttpServletRequest request, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.getCommonCodeJoinGroup(Integer.parseInt(key), param);
    }


    /**
     * Group Table 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/codegroup")
    @ResponseBody
    public Map<String, Object> getGroupDetailList(HttpServletRequest request, @ModelAttribute CommonCode param) {
        LOGGER.info(param.toString());
//        String key = request.getParameter("key");
        return commonCodeService.getGroupDetailList(param);
    }


    /**
     * Group Table 상세 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> getGroupDetail( HttpServletRequest request, @PathVariable("id") String  id, @ModelAttribute CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.getGroupDetail(Integer.parseInt(key), id, param);
    }


    /**
     * Group Table을 생성(등록)한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/codegroup")
    @ResponseBody
    public Map<String, Object> insertDetailGroup(HttpServletRequest request, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.insertDetailGroup(Integer.parseInt(key), param);
    }


    /**
     * Group Table을 수정한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> updateCommonGroup( HttpServletRequest request, @PathVariable String id, @RequestBody CommonCode param) {
        String key = request.getParameter("key");
        return commonCodeService.updateCommonGroup(Integer.parseInt(key), id, param);
    }


    /**
     * Group Table을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/codegroup/{id}")
    @ResponseBody
    public Map<String, Object> deleteCommonGroup(HttpServletRequest request, @PathVariable String id) {
        String key = request.getParameter("key");
        LOGGER.info("########## " + id);
        return commonCodeService.deleteCommonGroup(Integer.parseInt(key), id);
    }


    /**
     * Detail Table 전체목록 조회한다.
     *
     * @param param CodeDetail
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL+"/codedetail")
    @ResponseBody
    public Map<String, Object> getCodeDetailList(@ModelAttribute CommonCode param) {
        LOGGER.info("getCodeDetailList :: " + param);
        return commonCodeService.getCodeDetailList(param);
    }


    /**
     * Detail Table 상세 목록을 조회한다.
     *
     * @param no CodeDetail
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> getCodeDetail(@PathVariable("no") int no) {
        LOGGER.info("NO :: " + no);
        return commonCodeService.getCodeDetail(no);
    }


    /**
     * Detail Table을 생성(등록)한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/codedetail")
    @ResponseBody
    public Map<String, Object> insertDetail(@RequestBody CommonCode param) {
        return commonCodeService.insertDetail(param);
    }


    /**
     * Detail Table을 수정한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> updateCommonDetail(@PathVariable int no, @RequestBody CommonCode param) {
        return commonCodeService.updateCommonDetail(no, param);
    }


    /**
     * Detail Table을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/codedetail/{no}")
    @ResponseBody
    public Map<String, Object> deleteCommonDetail(@PathVariable int no) {
        return commonCodeService.deleteCommonDetail(no);
    }

    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
