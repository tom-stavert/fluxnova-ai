package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.runtime.ProcessInstance;

/**
 * Result DTO for process instance query results.
 * Maps the fields from the engine's {@link ProcessInstance} interface.
 */
public class ProcessInstanceResultDto {

    private String id;
    private String processDefinitionId;
    private String businessKey;
    private String rootProcessInstanceId;
    private String caseInstanceId;
    private boolean suspended;
    private String tenantId;

    public ProcessInstanceResultDto() {
    }

    /**
     * Create a result DTO from a process engine ProcessInstance entity.
     */
    public static ProcessInstanceResultDto fromProcessInstance(ProcessInstance processInstance) {
        ProcessInstanceResultDto dto = new ProcessInstanceResultDto();
        dto.setId(processInstance.getId());
        dto.setProcessDefinitionId(processInstance.getProcessDefinitionId());
        dto.setBusinessKey(processInstance.getBusinessKey());
        dto.setRootProcessInstanceId(processInstance.getRootProcessInstanceId());
        dto.setCaseInstanceId(processInstance.getCaseInstanceId());
        dto.setSuspended(processInstance.isSuspended());
        dto.setTenantId(processInstance.getTenantId());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getRootProcessInstanceId() {
        return rootProcessInstanceId;
    }

    public void setRootProcessInstanceId(String rootProcessInstanceId) {
        this.rootProcessInstanceId = rootProcessInstanceId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
