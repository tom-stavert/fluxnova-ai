package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.repository.Deployment;

import java.util.Date;

/**
 * Result DTO for deployment query results.
 * Maps the fields from the engine's {@link Deployment} interface.
 */
public class DeploymentResultDto {

    private String id;
    private String name;
    private Date deploymentTime;
    private String source;
    private String tenantId;

    public DeploymentResultDto() {
    }

    /**
     * Create a result DTO from a process engine Deployment entity.
     */
    public static DeploymentResultDto fromDeployment(Deployment deployment) {
        DeploymentResultDto dto = new DeploymentResultDto();
        dto.setId(deployment.getId());
        dto.setName(deployment.getName());
        dto.setDeploymentTime(deployment.getDeploymentTime());
        dto.setSource(deployment.getSource());
        dto.setTenantId(deployment.getTenantId());
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

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
