package org.finos.fluxnova.ai.mcp.monitoring.tools.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.rest.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public abstract class AbstractEngineQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractEngineQueryMcpTool.class);

    private ProcessEngine processEngine;

    protected ObjectMapper objectMapper;

    public AbstractEngineQueryMcpTool(ProcessEngine processEngine, final ObjectMapper objectMapper) {
        this.processEngine = processEngine;
        this.objectMapper = objectMapper;
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected ProcessEngine getProcessEngine() {
        if (processEngine == null) {
            throw new InvalidRequestException(Response.Status.BAD_REQUEST, "No process engine available");
        }
        return processEngine;
    }

    protected String serializeToJson(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("Failed to serialize process instances to JSON", e);
            return "[]";
        }
    }
}
