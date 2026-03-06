package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.runtime.Execution;

/**
 * Result DTO for execution query results.
 * Maps the fields from the engine's {@link Execution} interface.
 */
public class ExecutionResultDto {

    private String id;
    private String processInstanceId;
    private boolean suspended;
    private boolean ended;
    private String tenantId;

    public ExecutionResultDto() {
    }

    /**
     * Create a result DTO from a process engine Execution entity.
     */
    public static ExecutionResultDto fromExecution(Execution execution) {
        ExecutionResultDto dto = new ExecutionResultDto();
        dto.setId(execution.getId());
        dto.setProcessInstanceId(execution.getProcessInstanceId());
        dto.setSuspended(execution.isSuspended());
        dto.setEnded(execution.isEnded());
        dto.setTenantId(execution.getTenantId());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
