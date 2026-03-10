package org.finos.fluxnova.ai.mcp.query.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.history.HistoricExternalTaskLog;

import java.util.Date;

/**
 * DTO representing a single historic external task log entry result.
 */
@Schema(description = "A historic external task log entry.")
public record HistoricExternalTaskLogResultDto(
        @Schema(description = "The id of the historic external task log entry.") String id,
        @Schema(description = "The time the log entry was created.") Date timestamp,
        @Schema(description = "The id of the external task.") String externalTaskId,
        @Schema(description = "The number of retries the external task had at the time this log was created.") Integer retries,
        @Schema(description = "The priority of the external task at the time this log was created.") long priority,
        @Schema(description = "The topic name of the external task.") String topicName,
        @Schema(description = "The id of the worker that locked the external task.") String workerId,
        @Schema(description = "The error message if the external task failed.") String errorMessage,
        @Schema(description = "The id of the activity the external task is associated with.") String activityId,
        @Schema(description = "The id of the activity instance.") String activityInstanceId,
        @Schema(description = "The id of the execution.") String executionId,
        @Schema(description = "The id of the root process instance.") String rootProcessInstanceId,
        @Schema(description = "The id of the process instance.") String processInstanceId,
        @Schema(description = "The id of the process definition.") String processDefinitionId,
        @Schema(description = "The key of the process definition.") String processDefinitionKey,
        @Schema(description = "The id of the tenant.") String tenantId,
        @Schema(description = "Whether this log entry records an external task creation.") boolean creationLog,
        @Schema(description = "Whether this log entry records an external task failure.") boolean failureLog,
        @Schema(description = "Whether this log entry records an external task success.") boolean successLog,
        @Schema(description = "Whether this log entry records an external task deletion.") boolean deletionLog,
        @Schema(description = "The time this historic external task log entry will be removed.") Date removalTime
) {
    public static HistoricExternalTaskLogResultDto fromHistoricExternalTaskLog(HistoricExternalTaskLog hetl) {
        return new HistoricExternalTaskLogResultDto(
                hetl.getId(),
                hetl.getTimestamp(),
                hetl.getExternalTaskId(),
                hetl.getRetries(),
                hetl.getPriority(),
                hetl.getTopicName(),
                hetl.getWorkerId(),
                hetl.getErrorMessage(),
                hetl.getActivityId(),
                hetl.getActivityInstanceId(),
                hetl.getExecutionId(),
                hetl.getRootProcessInstanceId(),
                hetl.getProcessInstanceId(),
                hetl.getProcessDefinitionId(),
                hetl.getProcessDefinitionKey(),
                hetl.getTenantId(),
                hetl.isCreationLog(),
                hetl.isFailureLog(),
                hetl.isSuccessLog(),
                hetl.isDeletionLog(),
                hetl.getRemovalTime()
        );
    }
}
