package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.runtime.ProcessInstanceQuery;

import java.util.List;
import java.util.Set;

/**
 * DTO for querying process instances via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering process instances.")
public class ProcessInstanceQueryDto {

    @Schema(description = "Filter by process instance id.")
    private String processInstanceId;

    @Schema(description = "Filter by a list of process instance ids.")
    private Set<String> processInstanceIds;

    @Schema(description = "Filter by process instance business key.")
    private String businessKey;

    @Schema(description = "Filter by process instance business key that the parameter is a substring of. "
            + "The string can include the wildcard character '%' to express like-strategy: "
            + "starts with (string%), ends with (%string) or contains (%string%).")
    private String businessKeyLike;

    @Schema(description = "Filter by the key of the process definition the instances run on.")
    private String processDefinitionKey;

    @Schema(description = "Filter by a list of process definition keys. "
            + "A process instance must have one of the given process definition keys.")
    private List<String> processDefinitionKeyIn;

    @Schema(description = "Exclude instances by a list of process definition keys. "
            + "A process instance must not have one of the given process definition keys.")
    private List<String> processDefinitionKeyNotIn;

    @Schema(description = "Filter by the id of the process definition the instances run on.")
    private String processDefinitionId;

    @Schema(description = "Filter by the deployment the process instance belongs to.")
    private String deploymentId;

    @Schema(description = "Restrict query to all process instances that are sub process instances of the given process instance. "
            + "Takes a process instance id.")
    private String superProcessInstanceId;

    @Schema(description = "Restrict query to all process instances that have the given process instance as a sub process instance. "
            + "Takes a process instance id.")
    private String subProcessInstanceId;

    @Schema(description = "Filter by case instance id.")
    private String caseInstanceId;

    @Schema(description = "Restrict query to all process instances that are sub process instances of the given case instance. "
            + "Takes a case instance id.")
    private String superCaseInstanceId;

    @Schema(description = "Restrict query to all process instances that have the given case instance as a sub case instance. "
            + "Takes a case instance id.")
    private String subCaseInstanceId;

    @Schema(description = "Only include active process instances. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended process instances. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by presence of incidents. Selects only process instances that have an incident.")
    private Boolean withIncident;

    @Schema(description = "Filter by the incident id.")
    private String incidentId;

    @Schema(description = "Filter by the incident type. See the User Guide for a list of incident types.")
    private String incidentType;

    @Schema(description = "Filter by the incident message. Exact match.")
    private String incidentMessage;

    @Schema(description = "Filter by the incident message that the parameter is a substring of.")
    private String incidentMessageLike;

    @Schema(description = "Filter by a list of tenant ids. A process instance must have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include process instances which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Only include process instances which process definition has no tenant id.")
    private Boolean processDefinitionWithoutTenantId;

    @Schema(description = "Filter by a list of activity ids. "
            + "A process instance must currently wait in a leaf activity with one of the given activity ids.")
    private List<String> activityIdIn;

    @Schema(description = "Restrict the query to all process instances that are top level process instances.")
    private Boolean rootProcessInstances;

    @Schema(description = "Restrict the query to all process instances that are leaf instances "
            + "(i.e. don't have any sub instances).")
    private Boolean leafProcessInstances;

    @Schema(description = "Match all variable names case-insensitively when used with variable value filters.")
    private Boolean variableNamesIgnoreCase;

    @Schema(description = "Match all variable values case-insensitively when used with variable value filters.")
    private Boolean variableValuesIgnoreCase;

    /**
     * Apply all non-null filter criteria from this DTO to the given query.
     */
    public void applyFilters(ProcessInstanceQuery query) {
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        if (processInstanceIds != null && !processInstanceIds.isEmpty()) {
            query.processInstanceIds(processInstanceIds);
        }
        if (businessKey != null) {
            query.processInstanceBusinessKey(businessKey);
        }
        if (businessKeyLike != null) {
            query.processInstanceBusinessKeyLike(businessKeyLike);
        }
        if (processDefinitionKey != null) {
            query.processDefinitionKey(processDefinitionKey);
        }
        if (processDefinitionKeyIn != null && !processDefinitionKeyIn.isEmpty()) {
            query.processDefinitionKeyIn(processDefinitionKeyIn.toArray(new String[0]));
        }
        if (processDefinitionKeyNotIn != null && !processDefinitionKeyNotIn.isEmpty()) {
            query.processDefinitionKeyNotIn(processDefinitionKeyNotIn.toArray(new String[0]));
        }
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (deploymentId != null) {
            query.deploymentId(deploymentId);
        }
        if (superProcessInstanceId != null) {
            query.superProcessInstanceId(superProcessInstanceId);
        }
        if (subProcessInstanceId != null) {
            query.subProcessInstanceId(subProcessInstanceId);
        }
        if (caseInstanceId != null) {
            query.caseInstanceId(caseInstanceId);
        }
        if (superCaseInstanceId != null) {
            query.superCaseInstanceId(superCaseInstanceId);
        }
        if (subCaseInstanceId != null) {
            query.subCaseInstanceId(subCaseInstanceId);
        }
        if (Boolean.TRUE.equals(active)) {
            query.active();
        }
        if (Boolean.TRUE.equals(suspended)) {
            query.suspended();
        }
        if (Boolean.TRUE.equals(withIncident)) {
            query.withIncident();
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
        if (Boolean.TRUE.equals(processDefinitionWithoutTenantId)) {
            query.processDefinitionWithoutTenantId();
        }
        if (activityIdIn != null && !activityIdIn.isEmpty()) {
            query.activityIdIn(activityIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(rootProcessInstances)) {
            query.rootProcessInstances();
        }
        if (Boolean.TRUE.equals(leafProcessInstances)) {
            query.leafProcessInstances();
        }
        if (Boolean.TRUE.equals(variableNamesIgnoreCase)) {
            query.matchVariableNamesIgnoreCase();
        }
        if (Boolean.TRUE.equals(variableValuesIgnoreCase)) {
            query.matchVariableValuesIgnoreCase();
        }
    }

    // Getters and setters

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Set<String> getProcessInstanceIds() {
        return processInstanceIds;
    }

    public void setProcessInstanceIds(Set<String> processInstanceIds) {
        this.processInstanceIds = processInstanceIds;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getBusinessKeyLike() {
        return businessKeyLike;
    }

    public void setBusinessKeyLike(String businessKeyLike) {
        this.businessKeyLike = businessKeyLike;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public List<String> getProcessDefinitionKeyIn() {
        return processDefinitionKeyIn;
    }

    public void setProcessDefinitionKeyIn(List<String> processDefinitionKeyIn) {
        this.processDefinitionKeyIn = processDefinitionKeyIn;
    }

    public List<String> getProcessDefinitionKeyNotIn() {
        return processDefinitionKeyNotIn;
    }

    public void setProcessDefinitionKeyNotIn(List<String> processDefinitionKeyNotIn) {
        this.processDefinitionKeyNotIn = processDefinitionKeyNotIn;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getSuperProcessInstanceId() {
        return superProcessInstanceId;
    }

    public void setSuperProcessInstanceId(String superProcessInstanceId) {
        this.superProcessInstanceId = superProcessInstanceId;
    }

    public String getSubProcessInstanceId() {
        return subProcessInstanceId;
    }

    public void setSubProcessInstanceId(String subProcessInstanceId) {
        this.subProcessInstanceId = subProcessInstanceId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getSuperCaseInstanceId() {
        return superCaseInstanceId;
    }

    public void setSuperCaseInstanceId(String superCaseInstanceId) {
        this.superCaseInstanceId = superCaseInstanceId;
    }

    public String getSubCaseInstanceId() {
        return subCaseInstanceId;
    }

    public void setSubCaseInstanceId(String subCaseInstanceId) {
        this.subCaseInstanceId = subCaseInstanceId;
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

    public Boolean getWithIncident() {
        return withIncident;
    }

    public void setWithIncident(Boolean withIncident) {
        this.withIncident = withIncident;
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

    public Boolean getProcessDefinitionWithoutTenantId() {
        return processDefinitionWithoutTenantId;
    }

    public void setProcessDefinitionWithoutTenantId(Boolean processDefinitionWithoutTenantId) {
        this.processDefinitionWithoutTenantId = processDefinitionWithoutTenantId;
    }

    public List<String> getActivityIdIn() {
        return activityIdIn;
    }

    public void setActivityIdIn(List<String> activityIdIn) {
        this.activityIdIn = activityIdIn;
    }

    public Boolean getRootProcessInstances() {
        return rootProcessInstances;
    }

    public void setRootProcessInstances(Boolean rootProcessInstances) {
        this.rootProcessInstances = rootProcessInstances;
    }

    public Boolean getLeafProcessInstances() {
        return leafProcessInstances;
    }

    public void setLeafProcessInstances(Boolean leafProcessInstances) {
        this.leafProcessInstances = leafProcessInstances;
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
