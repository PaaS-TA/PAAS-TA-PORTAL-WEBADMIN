package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.BuildPack;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 빌드팩 컨트롤러 - 빌드팩 정보를 조회, 수정한다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@Controller
public class BuildPackController extends Common {

    private final String V2_URL = "/v2";

    /**
     * 빌드팩 화면
     *
     * @return model and view
     */
    @RequestMapping(value = {"/buildpacks"}, method = RequestMethod.GET)
    public ModelAndView webIdeUser() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("buildPack/buildPackMain");

        return mv;
    }


    /**
     * 빌드팩 조회
     *
     * @param buildPack the buildPack
     * @return String rspApp
     */
    @RequestMapping(value = {V2_URL + "/buildpacks"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBuildPacks(HttpServletRequest request, @ModelAttribute BuildPack buildPack) {
        String key = request.getParameter("key");
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V2_URL + "/buildpacks", HttpMethod.GET, buildPack);

    }

    /**
     * 빌드팩 정보 수정
     *
     * @param buildPack the buildPack
     * @return String rspApp
     */
    @RequestMapping(value = {V2_URL + "/buildpacks/{guid}"}, method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateBuildPack(HttpServletRequest request, @RequestBody BuildPack buildPack, @PathVariable String guid) {
        String key = request.getParameter("key");
        buildPack.setGuid(UUID.fromString(guid));
        return commonService.procCfApiRestTemplate(Integer.parseInt(key),Constants.V3_URL + "/buildpacks/" + buildPack.getGuid().toString(), HttpMethod.PUT, buildPack);
    }

    @Autowired
    ConfigService configService;

    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }
}
