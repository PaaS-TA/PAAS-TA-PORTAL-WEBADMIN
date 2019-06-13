package org.openpaas.paasta.portal.web.admin.controller;


import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RootController {




    @Autowired
    ConfigService configService;


    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/external/configs"})
    public List<ConfigEntity> configs() {
        return configService.configs();
    }



    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/external/configs/all"})
    public List<ConfigEntity> configsall() {
        return configService.configsAll();
    }

    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/external/configs/{guid}/auth"})
    public ConfigEntity configAuth(@PathVariable int guid) {
        return configService.getConfig(guid);
    }

}
