package org.finos.fluxnova.ai.mcp.query.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.ai.mcp.query.tools.base.AbstractQueryMcpTool;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.RuntimeService;
import org.finos.fluxnova.bpm.engine.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MCP tools for querying runtime process engine data.
 * <p>
 * Provides read-only query access to process instances, executions, incidents,
 * event subscriptions, and variable instances through the process engine's
 * RuntimeService Query API.
 */
@Component
public class RuntimeQueryMcpTools extends AbstractQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(RuntimeQueryMcpTools.class);

    public RuntimeQueryMcpTools(ProcessEngine processEngine, ObjectMapper objectMapper) {
        super(processEngine, objectMapper);
    }

    // ---- Process Instance Query ----

    @McpTool(description = "Query running process instances in the process engine. "
            + "Returns a list of process instances matching the given filter criteria. "
            + "Process instances represent individual executions of a process definition (workflow). "
            + "Use this tool to find active or suspended process instances by their definition, business key, "
            + "tenant, incident status, or other attributes. All filter parameters are optional.")
    public String queryProcessInstances(@McpToolParam ProcessInstanceQueryDto queryDto) {
        LOG.info("Querying process instances with criteria: {}", serializeToJson(queryDto));

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        queryDto.applyFilters(query);

        List<ProcessInstanceResultDto> resultDtos = query.list().stream()
                .map(ProcessInstanceResultDto::fromProcessInstance)
                .collect(Collectors.toList());

        LOG.info("Process instance query returned {} results", resultDtos.size());
        return serializeToJson(resultDtos);
    }

    // ---- Execution Query ----

    @McpTool(description = "Query executions in the process engine. "
            + "Returns a list of executions matching the given filter criteria. "
            + "An execution represents a path of execution within a process instance - "
            + "a process instance is itself the root execution. Parallel gateways and "
            + "multi-instance activities create additional concurrent executions. "
            + "Use this tool to inspect execution state, find executions waiting for signals or messages, "
            + "or examine execution-level details. All filter parameters are optional.")
    public String queryExecutions(@McpToolParam ExecutionQueryDto queryDto) {
        LOG.info("Querying executions with criteria: {}", serializeToJson(queryDto));

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        ExecutionQuery query = runtimeService.createExecutionQuery();

        queryDto.applyFilters(query);

        List<ExecutionResultDto> resultDtos = query.list().stream()
                .map(ExecutionResultDto::fromExecution)
                .collect(Collectors.toList());

        LOG.info("Execution query returned {} results", resultDtos.size());
        return serializeToJson(resultDtos);
    }

    // ---- Incident Query ----

    @McpTool(description = "Query incidents in the process engine. "
            + "Returns a list of incidents matching the given filter criteria. "
            + "Incidents represent problems that occurred during process execution, "
            + "such as failed jobs, failed external tasks, or other error conditions. "
            + "Use this tool to find and diagnose process execution failures. "
            + "All filter parameters are optional.")
    public String queryIncidents(@McpToolParam IncidentQueryDto queryDto) {
        LOG.info("Querying incidents with criteria: {}", serializeToJson(queryDto));

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        IncidentQuery query = runtimeService.createIncidentQuery();

        queryDto.applyFilters(query);

        List<IncidentResultDto> resultDtos = query.list().stream()
                .map(IncidentResultDto::fromIncident)
                .collect(Collectors.toList());

        LOG.info("Incident query returned {} results", resultDtos.size());
        return serializeToJson(resultDtos);
    }

    // ---- Event Subscription Query ----

    @McpTool(description = "Query event subscriptions in the process engine. "
            + "Returns a list of event subscriptions matching the given filter criteria. "
            + "Event subscriptions represent points where a process instance is waiting for an external event, "
            + "such as a message event, signal event, compensation event, or conditional event. "
            + "Use this tool to find which process instances are waiting for specific events. "
            + "All filter parameters are optional.")
    public String queryEventSubscriptions(@McpToolParam EventSubscriptionQueryDto queryDto) {
        LOG.info("Querying event subscriptions with criteria: {}", serializeToJson(queryDto));

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        EventSubscriptionQuery query = runtimeService.createEventSubscriptionQuery();

        queryDto.applyFilters(query);

        List<EventSubscriptionResultDto> resultDtos = query.list().stream()
                .map(EventSubscriptionResultDto::fromEventSubscription)
                .collect(Collectors.toList());

        LOG.info("Event subscription query returned {} results", resultDtos.size());
        return serializeToJson(resultDtos);
    }

    // ---- Variable Instance Query ----

    @McpTool(description = "Query variable instances in the process engine. "
            + "Returns a list of variable instances matching the given filter criteria. "
            + "Variables store data associated with process instances, executions, tasks, or case instances. "
            + "Each variable has a name, type, and value. Use this tool to inspect the current state of "
            + "process data across running or completed activities. All filter parameters are optional.")
    public String queryVariableInstances(@McpToolParam VariableInstanceQueryDto queryDto) {
        LOG.info("Querying variable instances with criteria: {}", serializeToJson(queryDto));

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        VariableInstanceQuery query = runtimeService.createVariableInstanceQuery();

        // Disable binary fetching by default to avoid loading large blobs
        query.disableBinaryFetching();

        queryDto.applyFilters(query);

        List<VariableInstanceResultDto> resultDtos = query.list().stream()
                .map(VariableInstanceResultDto::fromVariableInstance)
                .collect(Collectors.toList());

        LOG.info("Variable instance query returned {} results", resultDtos.size());
        return serializeToJson(resultDtos);
    }
}
