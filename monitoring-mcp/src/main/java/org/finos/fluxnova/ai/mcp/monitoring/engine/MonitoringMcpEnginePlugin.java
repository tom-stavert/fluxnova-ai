package org.finos.fluxnova.ai.mcp.monitoring.engine;

import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.finos.fluxnova.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringMcpEnginePlugin implements ProcessEnginePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringMcpEnginePlugin.class);

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        LOG.info("Monitoring MCP Engine Plugin - Initializing Monitoring MCP Engine Plugin");
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        LOG.info("Monitoring MCP Engine Plugin - Post-initialization complete");
    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {
        LOG.info("Monitoring MCP Engine Plugin - Process engine built");
    }
}
