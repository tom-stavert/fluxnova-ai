package org.finos.fluxnova.ai.mcp.query.tools;

import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.bpm.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MCP tools for querying task data from the process engine.
 * <p>
 * Provides read-only query access to user tasks
 * through the process engine's TaskService Query API.
 */
@Component
public class TaskQueryMcpTools {

    private static final Logger LOG = LoggerFactory.getLogger(TaskQueryMcpTools.class);

    private final TaskService taskService;

    public TaskQueryMcpTools(TaskService taskService) {
        this.taskService = taskService;
    }

    // ---- Task Query ----

    @McpTool(description = "Query user tasks in the process engine. "
            + "Returns a list of tasks matching the given filter criteria. "
            + "A task represents a piece of work that needs to be done by a human user, "
            + "typically a user task in a BPMN process or a human task in a CMMN case. "
            + "Use this tool to find tasks assigned to or available for a specific user or group, "
            + "filter by process or case context, priority, due dates, follow-up dates, "
            + "delegation state, or other task attributes. All filter parameters are optional.")
    public List<TaskResultDto> queryTasks(@McpToolParam TaskQueryDto queryDto) {
        LOG.info("Querying tasks with criteria: {}", queryDto);

        List<TaskResultDto> resultDtos = queryDto.toQuery(taskService).list().stream()
                .map(TaskResultDto::fromTask)
                .collect(Collectors.toList());

        LOG.info("Task query returned {} results", resultDtos.size());
        return resultDtos;
    }
}
