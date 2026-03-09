package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.repository.ProcessDefinition;

/**
 * Result DTO for process definition query results.
 * Maps the fields from the engine's {@link ProcessDefinition} interface.
 */
public class ProcessDefinitionResultDto {

    private String id;
    private String key;
    private String category;
    private String description;
    private String name;
    private int version;
    private String resourceName;
    private String deploymentId;
    private String diagramResourceName;
    private boolean suspended;
    private String tenantId;
    private String versionTag;
    private Integer historyTimeToLive;
    private boolean startableInTasklist;

    public ProcessDefinitionResultDto() {
    }

    /**
     * Create a result DTO from a process engine ProcessDefinition entity.
     */
    public static ProcessDefinitionResultDto fromProcessDefinition(ProcessDefinition processDefinition) {
        ProcessDefinitionResultDto dto = new ProcessDefinitionResultDto();
        dto.setId(processDefinition.getId());
        dto.setKey(processDefinition.getKey());
        dto.setCategory(processDefinition.getCategory());
        dto.setDescription(processDefinition.getDescription());
        dto.setName(processDefinition.getName());
        dto.setVersion(processDefinition.getVersion());
        dto.setResourceName(processDefinition.getResourceName());
        dto.setDeploymentId(processDefinition.getDeploymentId());
        dto.setDiagramResourceName(processDefinition.getDiagramResourceName());
        dto.setSuspended(processDefinition.isSuspended());
        dto.setTenantId(processDefinition.getTenantId());
        dto.setVersionTag(processDefinition.getVersionTag());
        dto.setHistoryTimeToLive(processDefinition.getHistoryTimeToLive());
        dto.setStartableInTasklist(processDefinition.isStartableInTasklist());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
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

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public Integer getHistoryTimeToLive() {
        return historyTimeToLive;
    }

    public void setHistoryTimeToLive(Integer historyTimeToLive) {
        this.historyTimeToLive = historyTimeToLive;
    }

    public boolean isStartableInTasklist() {
        return startableInTasklist;
    }

    public void setStartableInTasklist(boolean startableInTasklist) {
        this.startableInTasklist = startableInTasklist;
    }
}
