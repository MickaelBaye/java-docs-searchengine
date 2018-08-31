package io.mibay.java.docs.javadocssearchengine.config;

import io.mibay.java.docs.javadocssearchengine.integration.config.IntegrationConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(IntegrationConfig.class)
public class ApplicationConfiguration {
}
