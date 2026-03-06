package org.finos.fluxnova.ai.mcp.query.tools.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class for all MCP query tool classes.
 * Provides access to the process engine and JSON serialization.
 */
public abstract class AbstractQueryMcpTool {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractQueryMcpTool.class);

    private final ProcessEngine processEngine;
    protected final ObjectMapper objectMapper;

    protected AbstractQueryMcpTool(ProcessEngine processEngine, ObjectMapper objectMapper) {
        this.processEngine = processEngine;
        this.objectMapper = objectMapper;
    }

    protected ProcessEngine getProcessEngine() {
        if (processEngine == null) {
            throw new IllegalStateException("No process engine available");
        }
        return processEngine;
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Serialize an object to JSON string.
     *
     * @param object the object to serialize
     * @return JSON string representation
     */
    protected String serializeToJson(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("Failed to serialize object to JSON", e);
            return "[]";
        }
    }
}
