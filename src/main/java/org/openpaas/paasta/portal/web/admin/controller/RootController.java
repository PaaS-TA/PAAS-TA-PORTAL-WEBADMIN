package org.openpaas.paasta.portal.web.admin.controller;


import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController

public class RootController {

    @Autowired
    ConfigRepository configRepository;

    /**
     * 기본 설정 정보 ㅊ출
     *
     * @return model and view
     */
    @GetMapping(value = {"/external/configs"})
    public List<ConfigEntity> configs() {
        return configRepository.findAll();
    }


    /**
     * 시큐리티 그룹 메인 화면
     *
     * @return model and view
     */
    @GetMapping(value = {"/external/configs/{guid}/auth"})
    public ConfigEntity configAuth(@PathVariable int guid) {
        return configRepository.findOne(guid);
    }

}
