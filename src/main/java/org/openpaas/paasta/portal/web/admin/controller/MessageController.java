package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.config.LanguageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 다국어 처리 컨트롤러
 *
 * @author joy
 * @version 1.0
 * @since 2022.3.3 최초작성
 */
@Controller
public class MessageController extends Common {

    @Autowired
    LanguageConfig languageConfig;

    @RequestMapping(value = {"/getMessageProperties"})
    @ResponseBody
    public Map<String, Object> getMessageProperties(Locale locale, @RequestParam("msgCode") String msgCode, @RequestParam("inputStr") String inputStr) throws Exception {
        String replaceMsg = msgCode.replaceAll("-", ".");
        String replaceArg;

        Map<String, Object> responseBody = new HashMap<>();

        if(!Objects.equals(inputStr, "")) {
            replaceArg = inputStr.replaceAll("--", ".");
            responseBody.put("RESULT", messageSource.getMessage(replaceMsg, new String[]{replaceArg}, locale));
        } else {
            responseBody.put("RESULT", messageSource.getMessage(replaceMsg, null, locale));
        }


        return responseBody;
    }

    @RequestMapping(value = {"/getLanguageList"})
    @ResponseBody
    public List<String> getLanguageList() throws Exception {
        return languageConfig.getLanguageList();
    }
}
