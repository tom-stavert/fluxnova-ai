package org.finos.fluxnova.ai.mcp.monitoring.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.monitoring.model.query.McpTaskQueryDto;
import org.finos.fluxnova.ai.mcp.monitoring.tools.base.AbstractEngineQueryMcpTool;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.rest.TaskRestService;
import org.finos.fluxnova.bpm.engine.rest.dto.task.TaskDto;
import org.finos.fluxnova.bpm.engine.rest.impl.TaskRestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MCP tools for querying tasks.
 */
@Component
public class TaskMcpTools extends AbstractEngineQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessInstanceMcpTools.class);

    public TaskMcpTools(ProcessEngine processEngine, ObjectMapper objectMapper) {
        super(processEngine, objectMapper);
    }

    @McpTool(description = "Query tasks. All fields are optional.")
    public String queryTasks(@McpToolParam McpTaskQueryDto queryDto) {
        LOG.info("Query result:{}", serializeToJson(queryDto));

        TaskRestService taskRestService = new TaskRestServiceImpl(getProcessEngine().getName(), getObjectMapper());
        List<TaskDto> taskResults = taskRestService.queryTasks(queryDto, null, null);

        LOG.info("Query result:{}", serializeToJson(taskResults));

        try {
            return getObjectMapper().writeValueAsString(taskResults);
        } catch (Exception e) {
            LOG.error("Failed to serialize tasks to JSON", e);
            return "[]";
        }
    }
}
