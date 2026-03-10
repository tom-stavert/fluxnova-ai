package org.finos.fluxnova.ai.mcp.query.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.history.HistoricVariableInstance;

import java.util.Date;

/**
 * DTO representing a single historic variable instance result.
 */
@Schema(description = "A historic variable instance.")
public record HistoricVariableInstanceResultDto(
        @Schema(description = "The id of the historic variable instance.") String id,
        @Schema(description = "The name of the variable.") String name,
        @Schema(description = "The type name of the variable.") String typeName,
        @Schema(description = "The value of the variable serialized as a string.") String value,
        @Schema(description = "The key of the process definition.") String processDefinitionKey,
        @Schema(description = "The id of the process definition.") String processDefinitionId,
        @Schema(description = "The id of the root process instance.") String rootProcessInstanceId,
        @Schema(description = "The id of the process instance.") String processInstanceId,
        @Schema(description = "The id of the execution.") String executionId,
        @Schema(description = "The id of the activity instance.") String activityInstanceId,
        @Schema(description = "The key of the case definition.") String caseDefinitionKey,
        @Schema(description = "The id of the case definition.") String caseDefinitionId,
        @Schema(description = "The id of the case instance.") String caseInstanceId,
        @Schema(description = "The id of the case execution.") String caseExecutionId,
        @Schema(description = "The id of the task the variable belongs to.") String taskId,
        @Schema(description = "An error message if the variable value could not be loaded.") String errorMessage,
        @Schema(description = "The id of the tenant.") String tenantId,
        @Schema(description = "The state of the variable.") String state,
        @Schema(description = "The time the variable was created.") Date createTime,
        @Schema(description = "The time this historic variable instance will be removed.") Date removalTime
) {
    public static HistoricVariableInstanceResultDto fromHistoricVariableInstance(HistoricVariableInstance hvi) {
        return new HistoricVariableInstanceResultDto(
                hvi.getId(),
                hvi.getName(),
                hvi.getTypeName(),
                hvi.getValue() != null ? String.valueOf(hvi.getValue()) : null,
                hvi.getProcessDefinitionKey(),
                hvi.getProcessDefinitionId(),
                hvi.getRootProcessInstanceId(),
                hvi.getProcessInstanceId(),
                hvi.getExecutionId(),
                hvi.getActivityInstanceId(),
                hvi.getCaseDefinitionKey(),
                hvi.getCaseDefinitionId(),
                hvi.getCaseInstanceId(),
                hvi.getCaseExecutionId(),
                hvi.getTaskId(),
                hvi.getErrorMessage(),
                hvi.getTenantId(),
                hvi.getState(),
                hvi.getCreateTime(),
                hvi.getRemovalTime()
        );
    }
}
