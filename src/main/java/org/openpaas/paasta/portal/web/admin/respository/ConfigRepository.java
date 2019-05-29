package org.openpaas.paasta.portal.web.admin.respository;

import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by indra on 2018-02-06.
 */
@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Integer> {

    List<ConfigEntity> findAll();

    ConfigEntity findByApiUri(String apiUri);

    void deleteByKey(int key);

}
