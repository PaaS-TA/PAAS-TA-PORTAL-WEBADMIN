package org.openpaas.paasta.portal.web.admin.controller;


import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.openpaas.paasta.portal.web.admin.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RootController {


    @Autowired
    RootService rootService;


    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/external/configs"})
    public List<ConfigEntity> configs() {
        return rootService.configs();
    }


    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/v3/external/configs"})
    public List<ConfigEntity> getConfigs() {
        return rootService.getConfigs();
    }



    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    @CrossOrigin
    @GetMapping(value = {"/external/configs/{guid}/auth"})
    public ConfigEntity configAuth(@PathVariable int guid) {
        return rootService.configAuth(guid);
    }

}
