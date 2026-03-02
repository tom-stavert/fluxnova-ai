package org.finos.fluxnova.ai.mcp.monitoring.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.rest.dto.VariableQueryParameterDto;
import org.finos.fluxnova.bpm.engine.rest.dto.task.TaskQueryDto;

import java.util.Date;
import java.util.List;

public class McpTaskQueryDto extends org.finos.fluxnova.bpm.engine.rest.dto.task.TaskQueryDto {

    @Schema(description = "Sort the results lexicographically by a given criterion.",
            allowableValues = {"instanceId", "caseInstanceId", "dueDate", "followUpDate", "executionId",
                    "caseExecutionId", "assignee", "created", "lastUpdated", "description", "id", "name",
                    "nameCaseInsensitive", "priority", "tenantId", "processVariable", "executionVariable",
                    "taskVariable", "caseInstanceVariable", "caseExecutionVariable"})
    protected String sortBy;

    @Schema(description = "Sort the results in a given order. Values may be asc for ascending order or desc for descending order.\n" +
            "Must be used in conjunction with the sortBy parameter.",
            allowableValues = {"asc", "desc"})
    protected String sortOrder;

    @Schema(description = "Filter by process instance business key.")
    private String processInstanceBusinessKey;

    @Schema(description = "Filter by process instance business key using an expression.")
    private String processInstanceBusinessKeyExpression;

    @Schema(description = "Filter by a comma-separated list of process instance business keys.")
    private String[] processInstanceBusinessKeyIn;

    @Schema(description = "Filter by process instance business key that the parameter is a substring of.")
    private String processInstanceBusinessKeyLike;

    @Schema(description = "Filter by process instance business key like using an expression.")
    private String processInstanceBusinessKeyLikeExpression;

    @Schema(description = "Filter by the key of the process definition the task belongs to.")
    private String processDefinitionKey;

    @Schema(description = "Filter by a comma-separated list of process definition keys.")
    private String[] processDefinitionKeyIn;

    @Schema(description = "Filter by the id of the process definition the task belongs to.")
    private String processDefinitionId;

    @Schema(description = "Filter by the id of the execution the task belongs to.")
    private String executionId;

    @Schema(description = "Filter by a comma-separated list of activity instance ids.")
    private String[] activityInstanceIdIn;

    @Schema(description = "Filter by a comma-separated list of tenant ids.")
    private String[] tenantIdIn;

    @Schema(description = "Only include tasks which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Filter by the name of the process definition.")
    private String processDefinitionName;

    @Schema(description = "Filter by process definition name that the parameter is a substring of.")
    private String processDefinitionNameLike;

    @Schema(description = "Filter by the id of the process instance the task belongs to.")
    private String processInstanceId;

    @Schema(description = "Filter by a comma-separated list of process instance ids.")
    private String[] processInstanceIdIn;

    @Schema(description = "Filter by the assignee of the task.")
    private String assignee;

    @Schema(description = "Filter by assignee using an expression.")
    private String assigneeExpression;

    @Schema(description = "Filter by assignee that the parameter is a substring of.")
    private String assigneeLike;

    @Schema(description = "Filter by assignee like using an expression.")
    private String assigneeLikeExpression;

    @Schema(description = "Filter by a comma-separated list of assignees.")
    private String[] assigneeIn;

    @Schema(description = "Filter by a comma-separated list of assignees to exclude.")
    private String[] assigneeNotIn;

    @Schema(description = "Filter by candidate group.")
    private String candidateGroup;

    @Schema(description = "Filter by candidate group using an expression.")
    private String candidateGroupExpression;

    @Schema(description = "Filter by candidate group that the parameter is a substring of.")
    private String candidateGroupLike;

    @Schema(description = "Filter by candidate user.")
    private String candidateUser;

    @Schema(description = "Filter by candidate user using an expression.")
    private String candidateUserExpression;

    @Schema(description = "Include tasks that are assigned to a user, as candidate tasks.")
    private Boolean includeAssignedTasks;

    @Schema(description = "Filter by a comma-separated list of task ids.")
    private String[] taskIdIn;

    @Schema(description = "Filter by task id.")
    private String taskId;

    @Schema(description = "Filter by a comma-separated list of task definition keys.")
    private String[] taskDefinitionKeyIn;

    @Schema(description = "Filter by task definition key.")
    private String taskDefinitionKey;

    @Schema(description = "Filter by task definition key that the parameter is a substring of.")
    private String taskDefinitionKeyLike;

    @Schema(description = "Filter by task description.")
    private String description;

    @Schema(description = "Filter by task description that the parameter is a substring of.")
    private String descriptionLike;

    @Schema(description = "Filter by user involved in the task.")
    private String involvedUser;

    @Schema(description = "Filter by involved user using an expression.")
    private String involvedUserExpression;

    @Schema(description = "Filter by maximum task priority.")
    private Integer maxPriority;

    @Schema(description = "Filter by minimum task priority.")
    private Integer minPriority;

    @Schema(description = "Filter by task name.")
    private String name;

    @Schema(description = "Filter by task name that is not equal to the parameter.")
    private String nameNotEqual;

    @Schema(description = "Filter by task name that the parameter is a substring of.")
    private String nameLike;

    @Schema(description = "Filter by task name that the parameter is not a substring of.")
    private String nameNotLike;

    @Schema(description = "Filter by task owner.")
    private String owner;

    @Schema(description = "Filter by task owner using an expression.")
    private String ownerExpression;

    @Schema(description = "Filter by task priority.")
    private Integer priority;

    @Schema(description = "Filter by parent task id.")
    private String parentTaskId;

    @Schema(description = "Only include assigned tasks.")
    private Boolean assigned;

    @Schema(description = "Only include unassigned tasks.")
    private Boolean unassigned;

    @Schema(description = "Only include active tasks.")
    private Boolean active;

    @Schema(description = "Only include suspended tasks.")
    private Boolean suspended;

    @Schema(description = "Filter by due date after the given date.")
    private Date dueAfter;

    @Schema(description = "Filter by due date after using an expression.")
    private String dueAfterExpression;

    @Schema(description = "Filter by due date before the given date.")
    private Date dueBefore;

    @Schema(description = "Filter by due date before using an expression.")
    private String dueBeforeExpression;

    @Schema(description = "Filter by exact due date.")
    private Date dueDate;

    @Schema(description = "Filter by due date using an expression.")
    private String dueDateExpression;

    @Schema(description = "Only include tasks with no due date.")
    private Boolean withoutDueDate;

    @Schema(description = "Filter by follow-up date after the given date.")
    private Date followUpAfter;

    @Schema(description = "Filter by follow-up date after using an expression.")
    private String followUpAfterExpression;

    @Schema(description = "Filter by follow-up date before the given date.")
    private Date followUpBefore;

    @Schema(description = "Filter by follow-up date before using an expression.")
    private String followUpBeforeExpression;

    @Schema(description = "Filter by follow-up date before the given date or not existent.")
    private Date followUpBeforeOrNotExistent;

    @Schema(description = "Filter by follow-up date before or not existent using an expression.")
    private String followUpBeforeOrNotExistentExpression;

    @Schema(description = "Filter by exact follow-up date.")
    private Date followUpDate;

    @Schema(description = "Filter by follow-up date using an expression.")
    private String followUpDateExpression;

    @Schema(description = "Filter by creation date after the given date.")
    private Date createdAfter;

    @Schema(description = "Filter by creation date after using an expression.")
    private String createdAfterExpression;

    @Schema(description = "Filter by creation date before the given date.")
    private Date createdBefore;

    @Schema(description = "Filter by creation date before using an expression.")
    private String createdBeforeExpression;

    @Schema(description = "Filter by exact creation date.")
    private Date createdOn;

    @Schema(description = "Filter by creation date using an expression.")
    private String createdOnExpression;

    @Schema(description = "Filter by update date after the given date.")
    private Date updatedAfter;

    @Schema(description = "Filter by update date after using an expression.")
    private String updatedAfterExpression;

    @Schema(description = "Filter by delegation state.")
    private String delegationState;

    @Schema(description = "Filter by a comma-separated list of candidate groups.")
    private List<String> candidateGroups;

    @Schema(description = "Filter by candidate groups using an expression.")
    private String candidateGroupsExpression;

    @Schema(description = "Filter by task variables.")
    private List<VariableQueryParameterDto> taskVariables;

    @Schema(description = "Filter by process variables.")
    private List<VariableQueryParameterDto> processVariables;

    @Schema(description = "Filter by case instance variables.")
    private List<VariableQueryParameterDto> caseInstanceVariables;

    @Schema(description = "Filter by case definition id.")
    private String caseDefinitionId;

    @Schema(description = "Filter by case definition key.")
    private String caseDefinitionKey;

    @Schema(description = "Filter by case definition name.")
    private String caseDefinitionName;

    @Schema(description = "Filter by case definition name that the parameter is a substring of.")
    private String caseDefinitionNameLike;

    @Schema(description = "Filter by case execution id.")
    private String caseExecutionId;

    @Schema(description = "Filter by case instance business key.")
    private String caseInstanceBusinessKey;

    @Schema(description = "Filter by case instance business key that the parameter is a substring of.")
    private String caseInstanceBusinessKeyLike;

    @Schema(description = "Filter by case instance id.")
    private String caseInstanceId;

    @Schema(description = "Ignore case when matching variable names.")
    private Boolean variableNamesIgnoreCase;

    @Schema(description = "Ignore case when matching variable values.")
    private Boolean variableValuesIgnoreCase;

    @Schema(description = "Include comment attachment information.")
    private Boolean withCommentAttachmentInfo;

    @Schema(description = "Include tasks with candidate groups.")
    private Boolean withCandidateGroups;

    @Schema(description = "Exclude tasks with candidate groups.")
    private Boolean withoutCandidateGroups;

    @Schema(description = "Include tasks with candidate users.")
    private Boolean withCandidateUsers;

    @Schema(description = "Exclude tasks with candidate users.")
    private Boolean withoutCandidateUsers;

    @Schema(description = "Nested or queries.")
    private List<TaskQueryDto> orQueries;

}
