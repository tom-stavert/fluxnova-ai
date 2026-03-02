package org.finos.fluxnova.ai.mcp.monitoring.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.monitoring.engine.MonitoringMcpEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
public class MonitoringMcpAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MonitoringMcpAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public MonitoringMcpEnginePlugin mcpEnginePlugin() {
        log.info("Auto-configuring MonitoringMcpEnginePlugin");
        return new MonitoringMcpEnginePlugin();
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
