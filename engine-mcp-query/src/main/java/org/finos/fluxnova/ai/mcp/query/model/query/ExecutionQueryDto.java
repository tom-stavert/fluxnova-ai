package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.runtime.ExecutionQuery;

import java.util.List;

/**
 * DTO for querying executions via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering executions.")
public class ExecutionQueryDto {

    @Schema(description = "Filter by the id of the execution.")
    private String executionId;

    @Schema(description = "Filter by the id of the process instance the execution belongs to.")
    private String processInstanceId;

    @Schema(description = "Filter by the business key of the process instances the executions belong to.")
    private String businessKey;

    @Schema(description = "Filter by the id of the process definition the executions run on.")
    private String processDefinitionId;

    @Schema(description = "Filter by the key of the process definition the executions run on.")
    private String processDefinitionKey;

    @Schema(description = "Filter by the id of the activity the execution currently executes.")
    private String activityId;

    @Schema(description = "Select only those executions that expect a signal of the given name.")
    private String signalEventSubscriptionName;

    @Schema(description = "Select only those executions that expect a message of the given name.")
    private String messageEventSubscriptionName;

    @Schema(description = "Only include active executions. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended executions. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by the incident id.")
    private String incidentId;

    @Schema(description = "Filter by the incident type. See the User Guide for a list of incident types.")
    private String incidentType;

    @Schema(description = "Filter by the incident message. Exact match.")
    private String incidentMessage;

    @Schema(description = "Filter by the incident message that the parameter is a substring of.")
    private String incidentMessageLike;

    @Schema(description = "Filter by a list of tenant ids. An execution must have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include executions which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Match all variable names case-insensitively when used with variable value filters.")
    private Boolean variableNamesIgnoreCase;

    @Schema(description = "Match all variable values case-insensitively when used with variable value filters.")
    private Boolean variableValuesIgnoreCase;

    /**
     * Apply all non-null filter criteria from this DTO to the given query.
     */
    public void applyFilters(ExecutionQuery query) {
        if (executionId != null) {
            query.executionId(executionId);
        }
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        if (businessKey != null) {
            query.processInstanceBusinessKey(businessKey);
        }
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (processDefinitionKey != null) {
            query.processDefinitionKey(processDefinitionKey);
        }
        if (activityId != null) {
            query.activityId(activityId);
        }
        if (signalEventSubscriptionName != null) {
            query.signalEventSubscriptionName(signalEventSubscriptionName);
        }
        if (messageEventSubscriptionName != null) {
            query.messageEventSubscriptionName(messageEventSubscriptionName);
        }
        if (Boolean.TRUE.equals(active)) {
            query.active();
        }
        if (Boolean.TRUE.equals(suspended)) {
            query.suspended();
        }
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
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(withoutTenantId)) {
            query.withoutTenantId();
        }
        if (Boolean.TRUE.equals(variableNamesIgnoreCase)) {
            query.matchVariableNamesIgnoreCase();
        }
        if (Boolean.TRUE.equals(variableValuesIgnoreCase)) {
            query.matchVariableValuesIgnoreCase();
        }
    }

    // Getters and setters

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getSignalEventSubscriptionName() {
        return signalEventSubscriptionName;
    }

    public void setSignalEventSubscriptionName(String signalEventSubscriptionName) {
        this.signalEventSubscriptionName = signalEventSubscriptionName;
    }

    public String getMessageEventSubscriptionName() {
        return messageEventSubscriptionName;
    }

    public void setMessageEventSubscriptionName(String messageEventSubscriptionName) {
        this.messageEventSubscriptionName = messageEventSubscriptionName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

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

    public List<String> getTenantIdIn() {
        return tenantIdIn;
    }

    public void setTenantIdIn(List<String> tenantIdIn) {
        this.tenantIdIn = tenantIdIn;
    }

    public Boolean getWithoutTenantId() {
        return withoutTenantId;
    }

    public void setWithoutTenantId(Boolean withoutTenantId) {
        this.withoutTenantId = withoutTenantId;
    }

    public Boolean getVariableNamesIgnoreCase() {
        return variableNamesIgnoreCase;
    }

    public void setVariableNamesIgnoreCase(Boolean variableNamesIgnoreCase) {
        this.variableNamesIgnoreCase = variableNamesIgnoreCase;
    }

    public Boolean getVariableValuesIgnoreCase() {
        return variableValuesIgnoreCase;
    }

    public void setVariableValuesIgnoreCase(Boolean variableValuesIgnoreCase) {
        this.variableValuesIgnoreCase = variableValuesIgnoreCase;
    }
}
