package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.controller.ConfigInfoController;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInfoController.class);

    @Autowired
    ConfigRepository configRepository;

    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    public List<ConfigEntity> configs() {

        List<ConfigEntity> configEntities = new ArrayList<>();
        for (ConfigEntity configEntity : configRepository.findAll()) {
            configEntity.setAuthorization("");
            configEntities.add(configEntity);
        }
        return configEntities;
    }


    public List<ConfigEntity> configsAll() {
        return  configRepository.findAll();
    }


    /**
     * 기본 설정 삭제 한다.
     *
     * @return model and view
     */

    public Map deleteConfig(int key) {
        Map rs = new HashMap();
        try {
            configRepository.delete(key);
            rs.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.put("result", false);
        }
        return rs;

    }


    /**
     * 기본 설정 수정 및 추가 한다.
     *
     * @return model and view
     */

    public Map saveConfig(ConfigEntity configEntity) {
        Map rs = new HashMap();
        try {
            LOGGER.info("saveConfig : " + configEntity.toString());
            configRepository.save(configEntity);
            rs.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.put("result", false);
        }
        return rs;
    }

    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
    public List<ConfigEntity> getConfigs() {
        return configRepository.findAll();
    }


    /**
     * 시큐리티 그룹 메인 화면
     *
     * @return model and view
     */
    public ConfigEntity getConfig(int key) {
        return configRepository.findOne(key);
    }
}
