package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DashboardController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);
    /**
     * 로그인처리 후 첫 화면
     *
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        if (!request.isUserInRole("ROLE_ADMIN")) {
            mv.setViewName("redirect:/index");
        } else {
            mv.setViewName("/main/main");
        }

        return mv;
    }


    @ModelAttribute("configs")
    public List<User> configs(){
        return getServerInfos();
    }

}
