package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class SecurityGroupService extends Common {

    private final String V2_URL = "/v2";

    @Autowired
    CommonService commonService;

    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView(){{setViewName("securityGroup/securityGroupMain");}};
    }

}
