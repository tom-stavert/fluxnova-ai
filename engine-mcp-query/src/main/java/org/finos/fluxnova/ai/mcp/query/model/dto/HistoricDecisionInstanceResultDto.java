package org.finos.fluxnova.ai.mcp.query.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.history.HistoricDecisionInstance;

import java.util.Date;

/**
 * DTO representing a single historic decision instance result.
 */
@Schema(description = "A historic decision instance.")
public record HistoricDecisionInstanceResultDto(
        @Schema(description = "The id of the historic decision instance.") String id,
        @Schema(description = "The id of the decision definition.") String decisionDefinitionId,
        @Schema(description = "The key of the decision definition.") String decisionDefinitionKey,
        @Schema(description = "The name of the decision definition.") String decisionDefinitionName,
        @Schema(description = "The time the decision was evaluated.") Date evaluationTime,
        @Schema(description = "The time this historic decision instance will be removed.") Date removalTime,
        @Schema(description = "The key of the process definition.") String processDefinitionKey,
        @Schema(description = "The id of the process definition.") String processDefinitionId,
        @Schema(description = "The id of the process instance.") String processInstanceId,
        @Schema(description = "The key of the case definition.") String caseDefinitionKey,
        @Schema(description = "The id of the case definition.") String caseDefinitionId,
        @Schema(description = "The id of the case instance.") String caseInstanceId,
        @Schema(description = "The id of the activity that triggered the decision.") String activityId,
        @Schema(description = "The id of the activity instance that triggered the decision.") String activityInstanceId,
        @Schema(description = "The id of the user who evaluated the decision.") String userId,
        @Schema(description = "The result of the collect operation for decisions using a collect hit policy.") Double collectResultValue,
        @Schema(description = "The id of the root decision instance.") String rootDecisionInstanceId,
        @Schema(description = "The id of the root process instance.") String rootProcessInstanceId,
        @Schema(description = "The id of the decision requirements definition.") String decisionRequirementsDefinitionId,
        @Schema(description = "The key of the decision requirements definition.") String decisionRequirementsDefinitionKey,
        @Schema(description = "The id of the tenant.") String tenantId
) {
    public static HistoricDecisionInstanceResultDto fromHistoricDecisionInstance(HistoricDecisionInstance hdi) {
        return new HistoricDecisionInstanceResultDto(
                hdi.getId(),
                hdi.getDecisionDefinitionId(),
                hdi.getDecisionDefinitionKey(),
                hdi.getDecisionDefinitionName(),
                hdi.getEvaluationTime(),
                hdi.getRemovalTime(),
                hdi.getProcessDefinitionKey(),
                hdi.getProcessDefinitionId(),
                hdi.getProcessInstanceId(),
                hdi.getCaseDefinitionKey(),
                hdi.getCaseDefinitionId(),
                hdi.getCaseInstanceId(),
                hdi.getActivityId(),
                hdi.getActivityInstanceId(),
                hdi.getUserId(),
                hdi.getCollectResultValue(),
                hdi.getRootDecisionInstanceId(),
                hdi.getRootProcessInstanceId(),
                hdi.getDecisionRequirementsDefinitionId(),
                hdi.getDecisionRequirementsDefinitionKey(),
                hdi.getTenantId()
        );
    }
}
