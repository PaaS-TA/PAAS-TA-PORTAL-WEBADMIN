package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.BuildPack;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    //private static final Logger LOGGER = LoggerFactory.getLogger(BuildPackController.class);

    /**
     * 빌드팩 화면
     *
     * @return model and view
     */
    @RequestMapping(value = {"/buildPack/buildPackMain"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/buildPack/buildpacks"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBuildPacks(@ModelAttribute BuildPack buildPack) {

        return commonService.procCfApiRestTemplate("/buildPack/buildpacks", HttpMethod.GET, buildPack, getToken());

    }

    /**
     * 빌드팩 정보 수정
     *
     * @param buildPack the buildPack
     * @return String rspApp
     */
    @RequestMapping(value = {"/buildPack/buildpacks/{guid}"}, method = RequestMethod.PUT)
    @ResponseBody
        public Map<String, Object> updateBuildPack(@RequestBody BuildPack buildPack , @PathVariable String guid) {

        buildPack.setGuid(UUID.fromString(guid));
        return commonService.procCfApiRestTemplate("/buildPack/buildpacks/"+buildPack.getGuid().toString(), HttpMethod.PUT, buildPack, getToken());
    }


}
