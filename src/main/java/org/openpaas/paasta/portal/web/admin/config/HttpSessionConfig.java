package org.openpaas.paasta.portal.web.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by mg on 2016-05-09.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=3600)
public class HttpSessionConfig {
}
