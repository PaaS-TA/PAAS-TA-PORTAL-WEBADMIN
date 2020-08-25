package org.openpaas.paasta.portal.web.admin.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-07.
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.openpaas.paasta.portal.web.admin.respository", entityManagerFactoryRef = "cfgEntityManager", transactionManagerRef = "cfgTransactionManager")
public class CfgConfig {

    private static final Logger logger = getLogger(CfgConfig.class);

    String cfgDriverClassName = "com.mysql.jdbc.Driver";

    @Value("${datasource.cfg.url}")
    String cfgUrl;
    @Value("${datasource.cfg.username}")
    String cfgusername;
    @Value("${datasource.cfg.password}")
    String cfgPassword;

    @Value("${datasource.cfg.ddl-auto}")
    String ddlAuto;
    @Value("${datasource.cfg.naming.strategy}")
    String dialect;


    @Bean
    public boolean loggerPrintConfig() {

        logger.info("[CfgConfig]=======================================================================");
        logger.info(cfgDriverClassName + "CfgDriverClassName");
        logger.info(cfgUrl + " cCfgUrl");
        logger.info(cfgusername + " Cfgusername");
        logger.info(cfgPassword + " CfgPassword");
        logger.info(ddlAuto + " CfgddlAuto");
        logger.info(dialect + " Cfgdialect");
        logger.info("==================================================================================");
        return true;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean cfgEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cfgDataSource());
        em.setPackagesToScan(new String[]{"org.openpaas.paasta.portal.web.admin.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.dialect", dialect);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource cfgDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(cfgDriverClassName);
        dataSource.setUrl(cfgUrl);
        dataSource.setUsername(cfgusername);
        dataSource.setPassword(cfgPassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager cfgTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(cfgEntityManager().getObject());
        return transactionManager;
    }
}
