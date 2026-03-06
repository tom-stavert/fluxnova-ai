package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.runtime.IncidentQuery;

import java.util.Date;
import java.util.List;

/**
 * DTO for querying incidents via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering incidents.")
public class IncidentQueryDto {

    @Schema(description = "Restricts to incidents that have the given id.")
    private String incidentId;

    @Schema(description = "Restricts to incidents that belong to the given incident type. "
            + "See the User Guide for a list of incident types.")
    private String incidentType;

    @Schema(description = "Restricts to incidents that have the given incident message.")
    private String incidentMessage;

    @Schema(description = "Restricts to incidents that have an incident message that is a substring of the given value. "
            + "The string can include the wildcard character '%' to express like-strategy: "
            + "starts with (string%), ends with (%string) or contains (%string%).")
    private String incidentMessageLike;

    @Schema(description = "Restricts to incidents that belong to a process definition with the given id.")
    private String processDefinitionId;

    @Schema(description = "Restricts to incidents that belong to a process definition with one of the given keys.")
    private List<String> processDefinitionKeyIn;

    @Schema(description = "Restricts to incidents that belong to a process instance with the given id.")
    private String processInstanceId;

    @Schema(description = "Restricts to incidents that belong to an execution with the given id.")
    private String executionId;

    @Schema(description = "Restricts to incidents that have an incidentTimestamp date before the given date. "
            + "By default, the date must be in the format yyyy-MM-dd'T'HH:mm:ss.SSSZ.")
    private Date incidentTimestampBefore;

    @Schema(description = "Restricts to incidents that have an incidentTimestamp date after the given date. "
            + "By default, the date must be in the format yyyy-MM-dd'T'HH:mm:ss.SSSZ.")
    private Date incidentTimestampAfter;

    @Schema(description = "Restricts to incidents that belong to an activity with the given id.")
    private String activityId;

    @Schema(description = "Restricts to incidents that were created due to the failure of an activity with the given id.")
    private String failedActivityId;

    @Schema(description = "Restricts to incidents that have the given incident id as cause incident.")
    private String causeIncidentId;

    @Schema(description = "Restricts to incidents that have the given incident id as root cause incident.")
    private String rootCauseIncidentId;

    @Schema(description = "Restricts to incidents that have the given parameter set as configuration.")
    private String configuration;

    @Schema(description = "Restricts to incidents that have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Restricts to incidents that have one of the given job definition ids.")
    private List<String> jobDefinitionIdIn;

    /**
     * Apply all non-null filter criteria from this DTO to the given query.
     */
    public void applyFilters(IncidentQuery query) {
        if (incidentId != null) {
            query.incidentId(incidentId);
        }
        if (incidentType != null) {
            query.incidentType(incidentType);
        }
        if (incidentMessage != null) {
            query.incidentMessage(incidentMessage);
        }
        if (incidentMessageLike != null) {
            query.incidentMessageLike(incidentMessageLike);
        }
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (processDefinitionKeyIn != null && !processDefinitionKeyIn.isEmpty()) {
            query.processDefinitionKeyIn(processDefinitionKeyIn.toArray(new String[0]));
        }
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        if (executionId != null) {
            query.executionId(executionId);
        }
        if (incidentTimestampBefore != null) {
            query.incidentTimestampBefore(incidentTimestampBefore);
        }
        if (incidentTimestampAfter != null) {
            query.incidentTimestampAfter(incidentTimestampAfter);
        }
        if (activityId != null) {
            query.activityId(activityId);
        }
        if (failedActivityId != null) {
            query.failedActivityId(failedActivityId);
        }
        if (causeIncidentId != null) {
            query.causeIncidentId(causeIncidentId);
        }
        if (rootCauseIncidentId != null) {
            query.rootCauseIncidentId(rootCauseIncidentId);
        }
        if (configuration != null) {
            query.configuration(configuration);
        }
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (jobDefinitionIdIn != null && !jobDefinitionIdIn.isEmpty()) {
            query.jobDefinitionIdIn(jobDefinitionIdIn.toArray(new String[0]));
        }
    }

    // Getters and setters

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
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

    public String getIncidentMessageLike() {
        return incidentMessageLike;
    }

    public void setIncidentMessageLike(String incidentMessageLike) {
        this.incidentMessageLike = incidentMessageLike;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public List<String> getProcessDefinitionKeyIn() {
        return processDefinitionKeyIn;
    }

    public void setProcessDefinitionKeyIn(List<String> processDefinitionKeyIn) {
        this.processDefinitionKeyIn = processDefinitionKeyIn;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public Date getIncidentTimestampBefore() {
        return incidentTimestampBefore;
    }

    public void setIncidentTimestampBefore(Date incidentTimestampBefore) {
        this.incidentTimestampBefore = incidentTimestampBefore;
    }

    public Date getIncidentTimestampAfter() {
        return incidentTimestampAfter;
    }

    public void setIncidentTimestampAfter(Date incidentTimestampAfter) {
        this.incidentTimestampAfter = incidentTimestampAfter;
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

    public List<String> getTenantIdIn() {
        return tenantIdIn;
    }

    public void setTenantIdIn(List<String> tenantIdIn) {
        this.tenantIdIn = tenantIdIn;
    }

    public List<String> getJobDefinitionIdIn() {
        return jobDefinitionIdIn;
    }

    public void setJobDefinitionIdIn(List<String> jobDefinitionIdIn) {
        this.jobDefinitionIdIn = jobDefinitionIdIn;
    }
}
