package org.finos.fluxnova.ai.mcp.monitoring.tools;

import org.finos.fluxnova.ai.mcp.monitoring.model.ProcessDefinitionPrompt;
import org.finos.fluxnova.ai.mcp.monitoring.model.ProcessDefinitionXmlResponse;
import org.finos.fluxnova.ai.mcp.monitoring.tools.base.AbstractMcpTools;
import org.finos.fluxnova.ai.mcp.monitoring.utilities.BpmnXmlParser;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * MCP tools for querying process definitions.
 */
@Component
public class RestProcessDefinitionMcpTools extends AbstractMcpTools {

    public RestProcessDefinitionMcpTools(RestTemplate restTemplate, @Value("${fluxnova.engine.rest.url:http://localhost:8080/engine-rest}") String engineRestBaseUrl) {
        super(restTemplate, engineRestBaseUrl);
    }

    @McpTool(description = "Get a process definition's LLM prompt from a BPMN 2.0 XML source")
    public ProcessDefinitionPrompt getProcessDefinitionXmlPromptString(
            @McpToolParam(description = "Process definition id") String processDefinitionId) {
        try {
            ProcessDefinitionXmlResponse response = executeGetRequest(
                    "/process-definition/" + processDefinitionId + "/xml",
                    ProcessDefinitionXmlResponse.class);
            String prompt = BpmnXmlParser.getPromptFromXml(response.getXml());
            return new ProcessDefinitionPrompt(processDefinitionId, prompt);
        } catch (Exception e) {
            return new ProcessDefinitionPrompt(processDefinitionId, null);
        }
    }
}
