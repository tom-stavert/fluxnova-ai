package org.finos.fluxnova.ai.mcp.monitoring.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.monitoring.tools.base.AbstractEngineQueryMcpTool;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.repository.ProcessDefinition;
import org.finos.fluxnova.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.finos.fluxnova.bpm.engine.rest.dto.repository.ProcessDefinitionQueryDto;
import org.finos.fluxnova.bpm.engine.rest.util.QueryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * MCP tools for querying process definitions.
 */
@Component
public class ProcessDefinitionMcpTools extends AbstractEngineQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessDefinitionMcpTools.class);


    public ProcessDefinitionMcpTools(ProcessEngine processEngine, ObjectMapper objectMapper) {
        super(processEngine, objectMapper);
    }

    @McpTool(description = "Query process definitions. All fields are optional.")
    public String queryProcessDefinitions(@McpToolParam ProcessDefinitionQueryDto query) {
        LOG.info("Querying process definitions with criteria: {}", serializeToJson(query));

        List<ProcessDefinitionDto> definitions = new ArrayList<>();

        List<ProcessDefinition> definitionResults = QueryUtil.list(query.toQuery(getProcessEngine()), null, null);
        for (ProcessDefinition definition : definitionResults) {
            ProcessDefinitionDto def = ProcessDefinitionDto.fromProcessDefinition(definition);
            definitions.add(def);
        }
        return serializeToJson(definitions);
    }
}
