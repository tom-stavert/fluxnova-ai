package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.runtime.VariableInstanceQuery;

import java.util.List;

/**
 * DTO for querying variable instances via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering variable instances.")
public class VariableInstanceQueryDto {

    @Schema(description = "Filter by variable instance name.")
    private String variableName;

    @Schema(description = "Filter by the variable instance name. The string can include the wildcard character '%' "
            + "to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).")
    private String variableNameLike;

    @Schema(description = "Only include variable instances which belong to one of the passed process instance ids.")
    private List<String> processInstanceIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed execution ids.")
    private List<String> executionIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed case instance ids.")
    private List<String> caseInstanceIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed case execution ids.")
    private List<String> caseExecutionIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed task ids.")
    private List<String> taskIdIn;

    @Schema(description = "Only include variable instances which are related to one of the passed batch ids.")
    private List<String> batchIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed activity instance ids.")
    private List<String> activityInstanceIdIn;

    @Schema(description = "Only include variable instances which belong to one of the passed tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include variable instances which have one of the passed variable names.")
    private List<String> variableNameIn;

    @Schema(description = "Only include variable instances which belong to one of the passed scope ids.")
    private List<String> variableScopeIdIn;

    @Schema(description = "Match all variable names case-insensitively when used with variable value filters.")
    private Boolean variableNamesIgnoreCase;

    @Schema(description = "Match all variable values case-insensitively when used with variable value filters.")
    private Boolean variableValuesIgnoreCase;

    /**
     * Apply all non-null filter criteria from this DTO to the given query.
     */
    public void applyFilters(VariableInstanceQuery query) {
        if (variableName != null) {
            query.variableName(variableName);
        }
        if (variableNameLike != null) {
            query.variableNameLike(variableNameLike);
        }
        if (variableNameIn != null && !variableNameIn.isEmpty()) {
            query.variableNameIn(variableNameIn.toArray(new String[0]));
        }
        if (processInstanceIdIn != null && !processInstanceIdIn.isEmpty()) {
            query.processInstanceIdIn(processInstanceIdIn.toArray(new String[0]));
        }
        if (executionIdIn != null && !executionIdIn.isEmpty()) {
            query.executionIdIn(executionIdIn.toArray(new String[0]));
        }
        if (caseInstanceIdIn != null && !caseInstanceIdIn.isEmpty()) {
            query.caseInstanceIdIn(caseInstanceIdIn.toArray(new String[0]));
        }
        if (caseExecutionIdIn != null && !caseExecutionIdIn.isEmpty()) {
            query.caseExecutionIdIn(caseExecutionIdIn.toArray(new String[0]));
        }
        if (taskIdIn != null && !taskIdIn.isEmpty()) {
            query.taskIdIn(taskIdIn.toArray(new String[0]));
        }
        if (batchIdIn != null && !batchIdIn.isEmpty()) {
            query.batchIdIn(batchIdIn.toArray(new String[0]));
        }
        if (activityInstanceIdIn != null && !activityInstanceIdIn.isEmpty()) {
            query.activityInstanceIdIn(activityInstanceIdIn.toArray(new String[0]));
        }
        if (variableScopeIdIn != null && !variableScopeIdIn.isEmpty()) {
            query.variableScopeIdIn(variableScopeIdIn.toArray(new String[0]));
        }
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(variableNamesIgnoreCase)) {
            query.matchVariableNamesIgnoreCase();
        }
        if (Boolean.TRUE.equals(variableValuesIgnoreCase)) {
            query.matchVariableValuesIgnoreCase();
        }
    }

    // Getters and setters

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableNameLike() {
        return variableNameLike;
    }

    public void setVariableNameLike(String variableNameLike) {
        this.variableNameLike = variableNameLike;
    }

    public List<String> getProcessInstanceIdIn() {
        return processInstanceIdIn;
    }

    public void setProcessInstanceIdIn(List<String> processInstanceIdIn) {
        this.processInstanceIdIn = processInstanceIdIn;
    }

    public List<String> getExecutionIdIn() {
        return executionIdIn;
    }

    public void setExecutionIdIn(List<String> executionIdIn) {
        this.executionIdIn = executionIdIn;
    }

    public List<String> getCaseInstanceIdIn() {
        return caseInstanceIdIn;
    }

    public void setCaseInstanceIdIn(List<String> caseInstanceIdIn) {
        this.caseInstanceIdIn = caseInstanceIdIn;
    }

    public List<String> getCaseExecutionIdIn() {
        return caseExecutionIdIn;
    }

    public void setCaseExecutionIdIn(List<String> caseExecutionIdIn) {
        this.caseExecutionIdIn = caseExecutionIdIn;
    }

    public List<String> getTaskIdIn() {
        return taskIdIn;
    }

    public void setTaskIdIn(List<String> taskIdIn) {
        this.taskIdIn = taskIdIn;
    }

    public List<String> getBatchIdIn() {
        return batchIdIn;
    }

    public void setBatchIdIn(List<String> batchIdIn) {
        this.batchIdIn = batchIdIn;
    }

    public List<String> getActivityInstanceIdIn() {
        return activityInstanceIdIn;
    }

    public void setActivityInstanceIdIn(List<String> activityInstanceIdIn) {
        this.activityInstanceIdIn = activityInstanceIdIn;
    }

    public List<String> getTenantIdIn() {
        return tenantIdIn;
    }

    public void setTenantIdIn(List<String> tenantIdIn) {
        this.tenantIdIn = tenantIdIn;
    }

    public List<String> getVariableNameIn() {
        return variableNameIn;
    }

    public void setVariableNameIn(List<String> variableNameIn) {
        this.variableNameIn = variableNameIn;
    }

    public List<String> getVariableScopeIdIn() {
        return variableScopeIdIn;
    }

    public void setVariableScopeIdIn(List<String> variableScopeIdIn) {
        this.variableScopeIdIn = variableScopeIdIn;
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
