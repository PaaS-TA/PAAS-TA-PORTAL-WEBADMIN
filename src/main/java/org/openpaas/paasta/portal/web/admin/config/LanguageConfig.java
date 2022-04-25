package org.openpaas.paasta.portal.web.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@Import(ApplicationConfig.class)
public class LanguageConfig {

    @Value("#{'${languageList}'.split(',')}")
    List<String> languageList;

    public List<String> getLanguageList() {
        return this.languageList;
    }
}
