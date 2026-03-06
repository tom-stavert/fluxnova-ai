package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.runtime.Incident;

import java.util.Date;

/**
 * Result DTO for incident query results.
 * Maps the fields from the engine's {@link Incident} interface.
 */
public class IncidentResultDto {

    private String id;
    private Date incidentTimestamp;
    private String incidentType;
    private String incidentMessage;
    private String executionId;
    private String activityId;
    private String failedActivityId;
    private String processInstanceId;
    private String processDefinitionId;
    private String causeIncidentId;
    private String rootCauseIncidentId;
    private String configuration;
    private String tenantId;
    private String jobDefinitionId;
    private String annotation;

    public IncidentResultDto() {
    }

    /**
     * Create a result DTO from a process engine Incident entity.
     */
    public static IncidentResultDto fromIncident(Incident incident) {
        IncidentResultDto dto = new IncidentResultDto();
        dto.setId(incident.getId());
        dto.setIncidentTimestamp(incident.getIncidentTimestamp());
        dto.setIncidentType(incident.getIncidentType());
        dto.setIncidentMessage(incident.getIncidentMessage());
        dto.setExecutionId(incident.getExecutionId());
        dto.setActivityId(incident.getActivityId());
        dto.setFailedActivityId(incident.getFailedActivityId());
        dto.setProcessInstanceId(incident.getProcessInstanceId());
        dto.setProcessDefinitionId(incident.getProcessDefinitionId());
        dto.setCauseIncidentId(incident.getCauseIncidentId());
        dto.setRootCauseIncidentId(incident.getRootCauseIncidentId());
        dto.setConfiguration(incident.getConfiguration());
        dto.setTenantId(incident.getTenantId());
        dto.setJobDefinitionId(incident.getJobDefinitionId());
        dto.setAnnotation(incident.getAnnotation());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getIncidentTimestamp() {
        return incidentTimestamp;
    }

    public void setIncidentTimestamp(Date incidentTimestamp) {
        this.incidentTimestamp = incidentTimestamp;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentMessage() {
        return incidentMessage;
    }

    public void setIncidentMessage(String incidentMessage) {
        this.incidentMessage = incidentMessage;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getFailedActivityId() {
        return failedActivityId;
    }

    public void setFailedActivityId(String failedActivityId) {
        this.failedActivityId = failedActivityId;
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

    public String getCauseIncidentId() {
        return causeIncidentId;
    }

    public void setCauseIncidentId(String causeIncidentId) {
        this.causeIncidentId = causeIncidentId;
    }

    public String getRootCauseIncidentId() {
        return rootCauseIncidentId;
    }

    public void setRootCauseIncidentId(String rootCauseIncidentId) {
        this.rootCauseIncidentId = rootCauseIncidentId;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getJobDefinitionId() {
        return jobDefinitionId;
    }

    public void setJobDefinitionId(String jobDefinitionId) {
        this.jobDefinitionId = jobDefinitionId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
