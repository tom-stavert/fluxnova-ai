package org.finos.fluxnova.ai.mcp.monitoring.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.monitoring.model.query.McpProcessInstanceQueryDto;
import org.finos.fluxnova.ai.mcp.monitoring.tools.base.AbstractEngineQueryMcpTool;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.rest.ProcessInstanceRestService;
import org.finos.fluxnova.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.finos.fluxnova.bpm.engine.rest.impl.ProcessInstanceRestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MCP tools for querying process instances.
 */
@Component
public class ProcessInstanceMcpTools extends AbstractEngineQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessInstanceMcpTools.class);

    public ProcessInstanceMcpTools(ProcessEngine processEngine, ObjectMapper objectMapper) {
        super(processEngine, objectMapper);
    }

    @McpTool(description = "Query process instances. Required fields: sortBy, sortOrder.")
    public String queryProcessInstances(@McpToolParam McpProcessInstanceQueryDto queryDto) {
        LOG.info("Querying process instances with criteria: {}", serializeToJson(queryDto));

        ProcessInstanceRestService processInstanceRestService = new ProcessInstanceRestServiceImpl(getProcessEngine().getName(), getObjectMapper());
        List<ProcessInstanceDto> instanceResults = processInstanceRestService.queryProcessInstances(queryDto, null, null);

        LOG.info("Query result:{}", serializeToJson(instanceResults));

        try {
            return getObjectMapper().writeValueAsString(instanceResults);
        } catch (Exception e) {
            LOG.error("Failed to serialize process instances to JSON", e);
            return "[]";
        }
    }
}
