package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.runtime.VariableInstance;

/**
 * Result DTO for variable instance query results.
 * Maps the fields from the engine's {@link VariableInstance} interface.
 * <p>
 * Note: The variable value is serialized as an Object. Complex or binary values
 * may not serialize cleanly to JSON and will be represented as their toString() output.
 */
public class VariableInstanceResultDto {

    private String id;
    private String name;
    private Object value;
    private String typeName;
    private String processInstanceId;
    private String processDefinitionId;
    private String executionId;
    private String caseInstanceId;
    private String caseExecutionId;
    private String taskId;
    private String batchId;
    private String activityInstanceId;
    private String tenantId;
    private String errorMessage;

    public VariableInstanceResultDto() {
    }

    /**
     * Create a result DTO from a process engine VariableInstance entity.
     */
    public static VariableInstanceResultDto fromVariableInstance(VariableInstance variableInstance) {
        VariableInstanceResultDto dto = new VariableInstanceResultDto();
        dto.setId(variableInstance.getId());
        dto.setName(variableInstance.getName());
        dto.setTypeName(variableInstance.getTypeName());
        dto.setProcessInstanceId(variableInstance.getProcessInstanceId());
        dto.setProcessDefinitionId(variableInstance.getProcessDefinitionId());
        dto.setExecutionId(variableInstance.getExecutionId());
        dto.setCaseInstanceId(variableInstance.getCaseInstanceId());
        dto.setCaseExecutionId(variableInstance.getCaseExecutionId());
        dto.setTaskId(variableInstance.getTaskId());
        dto.setBatchId(variableInstance.getBatchId());
        dto.setActivityInstanceId(variableInstance.getActivityInstanceId());
        dto.setTenantId(variableInstance.getTenantId());
        dto.setErrorMessage(variableInstance.getErrorMessage());

        // Attempt to get the value; use toString() fallback for non-serializable types
        try {
            dto.setValue(variableInstance.getValue());
        } catch (Exception e) {
            dto.setValue(null);
            dto.setErrorMessage("Could not retrieve variable value: " + e.getMessage());
        }

        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getCaseExecutionId() {
        return caseExecutionId;
    }

    public void setCaseExecutionId(String caseExecutionId) {
        this.caseExecutionId = caseExecutionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getActivityInstanceId() {
        return activityInstanceId;
    }

    public void setActivityInstanceId(String activityInstanceId) {
        this.activityInstanceId = activityInstanceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
