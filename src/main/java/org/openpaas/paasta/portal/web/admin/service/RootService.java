package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.respository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Service
public class RootService {




    @Autowired
    ConfigRepository configRepository;

    /**
     * 기본 설정 정보 추출
     *
     * @return model and view
     */
      public List<ConfigEntity> configs() {

        List<ConfigEntity> configEntities = new ArrayList<>();
        for (ConfigEntity configEntity :  configRepository.findAll()) {
            configEntity.setAuthorization("");
            configEntities.add(configEntity);
        }
        return configEntities;
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
    public ConfigEntity configAuth(int guid) {
        return configRepository.findOne(guid);
    }
}
