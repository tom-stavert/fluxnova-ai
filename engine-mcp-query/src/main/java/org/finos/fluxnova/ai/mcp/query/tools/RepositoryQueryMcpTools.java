package org.finos.fluxnova.ai.mcp.query.tools;

import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.bpm.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MCP tools for querying repository data from the process engine.
 * <p>
 * Provides read-only query access to process definitions and deployments
 * through the process engine's RepositoryService Query API.
 */
@Component
public class RepositoryQueryMcpTools {

    private static final Logger LOG = LoggerFactory.getLogger(RepositoryQueryMcpTools.class);

    private final RepositoryService repositoryService;

    public RepositoryQueryMcpTools(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    // ---- Process Definition Query ----

    @McpTool(description = "Query process definitions in the process engine. "
            + "Returns a list of process definitions matching the given filter criteria. "
            + "A process definition is a deployed workflow template (e.g. a BPMN 2.0 process) "
            + "that can be instantiated as a process instance. "
            + "Use this tool to discover available workflows, find specific versions of a process, "
            + "or check which definitions are deployed, active, or suspended. "
            + "All filter parameters are optional.")
    public List<ProcessDefinitionResultDto> queryProcessDefinitions(@McpToolParam ProcessDefinitionQueryDto queryDto) {
        LOG.info("Querying process definitions with criteria: {}", queryDto);

        List<ProcessDefinitionResultDto> resultDtos = queryDto.toQuery(repositoryService).list().stream()
                .map(ProcessDefinitionResultDto::fromProcessDefinition)
                .collect(Collectors.toList());

        LOG.info("Process definition query returned {} results", resultDtos.size());
        return resultDtos;
    }

    // ---- Deployment Query ----

    @McpTool(description = "Query deployments in the process engine. "
            + "Returns a list of deployments matching the given filter criteria. "
            + "A deployment is a container for process definitions, case definitions, "
            + "decision definitions, and other resources that have been deployed to the engine. "
            + "Use this tool to find when and what was deployed, or to list deployments by "
            + "name, source, tenant, or date range. "
            + "All filter parameters are optional.")
    public List<DeploymentResultDto> queryDeployments(@McpToolParam DeploymentQueryDto queryDto) {
        LOG.info("Querying deployments with criteria: {}", queryDto);

        List<DeploymentResultDto> resultDtos = queryDto.toQuery(repositoryService).list().stream()
                .map(DeploymentResultDto::fromDeployment)
                .collect(Collectors.toList());

        LOG.info("Deployment query returned {} results", resultDtos.size());
        return resultDtos;
    }
}
