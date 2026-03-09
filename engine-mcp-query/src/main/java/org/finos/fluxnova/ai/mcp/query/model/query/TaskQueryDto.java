package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.TaskService;
import org.finos.fluxnova.bpm.engine.task.DelegationState;
import org.finos.fluxnova.bpm.engine.task.TaskQuery;

import java.util.Date;
import java.util.List;

/**
 * DTO for querying tasks via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering tasks.")
public class TaskQueryDto {

    @Schema(description = "Restrict to task with the given id.")
    private String taskId;

    @Schema(description = "Restrict to tasks with any of the given ids.")
    private List<String> taskIdIn;

    @Schema(description = "Restrict to tasks that belong to process instances with the given id.")
    private String processInstanceId;

    @Schema(description = "Restrict to tasks that belong to process instances with the given ids.")
    private List<String> processInstanceIdIn;

    @Schema(description = "Restrict to tasks that belong to process instances with the given business key.")
    private String processInstanceBusinessKey;

    @Schema(description = "Restrict to tasks that belong to process instances with one of the given business keys. "
            + "The keys need to be in a comma-separated list.")
    private List<String> processInstanceBusinessKeyIn;

    @Schema(description = "Restrict to tasks that have a process instance business key that has the parameter value "
            + "as a substring.")
    private String processInstanceBusinessKeyLike;

    @Schema(description = "Restrict to tasks that belong to a process definition with the given id.")
    private String processDefinitionId;

    @Schema(description = "Restrict to tasks that belong to a process definition with the given key.")
    private String processDefinitionKey;

    @Schema(description = "Restrict to tasks that belong to a process definition with one of the given keys.")
    private List<String> processDefinitionKeyIn;

    @Schema(description = "Restrict to tasks that belong to a process definition with the given name.")
    private String processDefinitionName;

    @Schema(description = "Restrict to tasks that have a process definition name that has the parameter value "
            + "as a substring.")
    private String processDefinitionNameLike;

    @Schema(description = "Restrict to tasks that belong to an execution with the given id.")
    private String executionId;

    @Schema(description = "Restrict to tasks that the given user is assigned to.")
    private String assignee;

    @Schema(description = "Restrict to tasks that the user described by the given expression is assigned to.")
    private String assigneeLike;

    @Schema(description = "Restrict to tasks that are assigned to users with one of the given ids.")
    private List<String> assigneeIn;

    @Schema(description = "Restrict to tasks that the given user owns.")
    private String owner;

    @Schema(description = "Only include tasks that are offered to the given group.")
    private String candidateGroup;

    @Schema(description = "Only include tasks that are offered to the given user or to one of his groups.")
    private String candidateUser;

    @Schema(description = "Only include tasks that are offered to one of the given candidate groups.")
    private List<String> candidateGroups;

    @Schema(description = "Restrict to tasks that the given user or any of the user's candidate groups is involved in. "
            + "A user is involved in a task if the user is the assignee, the owner, one of the candidate users, "
            + "or a member of one of the candidate groups.")
    private String involvedUser;

    @Schema(description = "If set to true, restricts the query to all tasks that are assigned.")
    private Boolean assigned;

    @Schema(description = "If set to true, restricts the query to all tasks that are unassigned.")
    private Boolean unassigned;

    @Schema(description = "Restrict to tasks that have the given key.")
    private String taskDefinitionKey;

    @Schema(description = "Restrict to tasks that have one of the given keys.")
    private List<String> taskDefinitionKeyIn;

    @Schema(description = "Restrict to tasks that have a key that has the parameter value as a substring.")
    private String taskDefinitionKeyLike;

    @Schema(description = "Restrict to tasks that have the given name.")
    private String name;

    @Schema(description = "Restrict to tasks that do not have the given name.")
    private String nameNotEqual;

    @Schema(description = "Restrict to tasks that have a name with the given parameter value as a substring.")
    private String nameLike;

    @Schema(description = "Restrict to tasks that do not have a name with the given parameter value as a substring.")
    private String nameNotLike;

    @Schema(description = "Restrict to tasks that have the given description.")
    private String description;

    @Schema(description = "Restrict to tasks that have a description that has the parameter value as a substring.")
    private String descriptionLike;

    @Schema(description = "Restrict to tasks that have the given priority.")
    private Integer priority;

    @Schema(description = "Restrict to tasks that have a lower or equal priority.")
    private Integer maxPriority;

    @Schema(description = "Restrict to tasks that have a higher or equal priority.")
    private Integer minPriority;

    @Schema(description = "Restrict to tasks that are due on the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date dueDate;

    @Schema(description = "Restrict to tasks that are due after the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date dueAfter;

    @Schema(description = "Restrict to tasks that are due before the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date dueBefore;

    @Schema(description = "Only include tasks which have no due date. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withoutDueDate;

    @Schema(description = "Restrict to tasks that have a followUp date on the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date followUpDate;

    @Schema(description = "Restrict to tasks that have a followUp date after the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date followUpAfter;

    @Schema(description = "Restrict to tasks that have a followUp date before the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date followUpBefore;

    @Schema(description = "Restrict to tasks that have no followUp date or a followUp date before the given date. "
            + "Serves the typical use case 'give me all tasks without follow-up or follow-up date which is already due'.")
    private Date followUpBeforeOrNotExistent;

    @Schema(description = "Restrict to tasks that were created on the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date createdOn;

    @Schema(description = "Restrict to tasks that were created after the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date createdAfter;

    @Schema(description = "Restrict to tasks that were created before the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date createdBefore;

    @Schema(description = "Restrict to tasks that were updated after the given date. "
            + "Every action on a task (e.g. claim, delegate, complete) updates the timestamp.")
    private Date updatedAfter;

    @Schema(description = "Restrict to tasks that have the given delegation state.",
            allowableValues = {"PENDING", "RESOLVED"})
    private String delegationState;

    @Schema(description = "Only include tasks which have candidate groups. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withCandidateGroups;

    @Schema(description = "Only include tasks which have no candidate groups. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withoutCandidateGroups;

    @Schema(description = "Only include tasks which have candidate users. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withCandidateUsers;

    @Schema(description = "Only include tasks which have no candidate users. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withoutCandidateUsers;

    @Schema(description = "Only include active tasks. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended tasks. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by a list of tenant ids. A task must have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include tasks which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Restrict to tasks that are sub tasks of the given task. "
            + "Takes a task id.")
    private String parentTaskId;

    @Schema(description = "Restrict to tasks that belong to a case instance with the given id.")
    private String caseInstanceId;

    @Schema(description = "Restrict to tasks that belong to a case instance with the given business key.")
    private String caseInstanceBusinessKey;

    @Schema(description = "Restrict to tasks that have a case instance business key that has the parameter value "
            + "as a substring.")
    private String caseInstanceBusinessKeyLike;

    @Schema(description = "Restrict to tasks that belong to a case definition with the given id.")
    private String caseDefinitionId;

    @Schema(description = "Restrict to tasks that belong to a case definition with the given key.")
    private String caseDefinitionKey;

    @Schema(description = "Restrict to tasks that belong to a case definition with the given name.")
    private String caseDefinitionName;

    @Schema(description = "Restrict to tasks that have a case definition name that has the parameter value as a substring.")
    private String caseDefinitionNameLike;

    @Schema(description = "Restrict to tasks that belong to a case execution with the given id.")
    private String caseExecutionId;

    @Schema(description = "Only select tasks which have no parent (i.e. do not select subtasks). "
            + "Value may only be true, as false is the default behavior.")
    private Boolean excludeSubtasks;

    /**
     * Create a new TaskQuery from the TaskService, with all non-null filter criteria applied.
     */
    public TaskQuery toQuery(TaskService taskService) {
        TaskQuery query = taskService.createTaskQuery();
        if (taskId != null) {
            query.taskId(taskId);
        }
        if (taskIdIn != null && !taskIdIn.isEmpty()) {
            query.taskIdIn(taskIdIn.toArray(new String[0]));
        }
        if (processInstanceId != null) {
            query.processInstanceId(processInstanceId);
        }
        if (processInstanceIdIn != null && !processInstanceIdIn.isEmpty()) {
            query.processInstanceIdIn(processInstanceIdIn.toArray(new String[0]));
        }
        if (processInstanceBusinessKey != null) {
            query.processInstanceBusinessKey(processInstanceBusinessKey);
        }
        if (processInstanceBusinessKeyIn != null && !processInstanceBusinessKeyIn.isEmpty()) {
            query.processInstanceBusinessKeyIn(processInstanceBusinessKeyIn.toArray(new String[0]));
        }
        if (processInstanceBusinessKeyLike != null) {
            query.processInstanceBusinessKeyLike(processInstanceBusinessKeyLike);
        }
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (processDefinitionKey != null) {
            query.processDefinitionKey(processDefinitionKey);
        }
        if (processDefinitionKeyIn != null && !processDefinitionKeyIn.isEmpty()) {
            query.processDefinitionKeyIn(processDefinitionKeyIn.toArray(new String[0]));
        }
        if (processDefinitionName != null) {
            query.processDefinitionName(processDefinitionName);
        }
        if (processDefinitionNameLike != null) {
            query.processDefinitionNameLike(processDefinitionNameLike);
        }
        if (executionId != null) {
            query.executionId(executionId);
        }
        if (assignee != null) {
            query.taskAssignee(assignee);
        }
        if (assigneeLike != null) {
            query.taskAssigneeLike(assigneeLike);
        }
        if (assigneeIn != null && !assigneeIn.isEmpty()) {
            query.taskAssigneeIn(assigneeIn.toArray(new String[0]));
        }
        if (owner != null) {
            query.taskOwner(owner);
        }
        if (candidateGroup != null) {
            query.taskCandidateGroup(candidateGroup);
        }
        if (candidateUser != null) {
            query.taskCandidateUser(candidateUser);
        }
        if (candidateGroups != null && !candidateGroups.isEmpty()) {
            query.taskCandidateGroupIn(candidateGroups);
        }
        if (involvedUser != null) {
            query.taskInvolvedUser(involvedUser);
        }
        if (Boolean.TRUE.equals(assigned)) {
            query.taskAssigned();
        }
        if (Boolean.TRUE.equals(unassigned)) {
            query.taskUnassigned();
        }
        if (taskDefinitionKey != null) {
            query.taskDefinitionKey(taskDefinitionKey);
        }
        if (taskDefinitionKeyIn != null && !taskDefinitionKeyIn.isEmpty()) {
            query.taskDefinitionKeyIn(taskDefinitionKeyIn.toArray(new String[0]));
        }
        if (taskDefinitionKeyLike != null) {
            query.taskDefinitionKeyLike(taskDefinitionKeyLike);
        }
        if (name != null) {
            query.taskName(name);
        }
        if (nameNotEqual != null) {
            query.taskNameNotEqual(nameNotEqual);
        }
        if (nameLike != null) {
            query.taskNameLike(nameLike);
        }
        if (nameNotLike != null) {
            query.taskNameNotLike(nameNotLike);
        }
        if (description != null) {
            query.taskDescription(description);
        }
        if (descriptionLike != null) {
            query.taskDescriptionLike(descriptionLike);
        }
        if (priority != null) {
            query.taskPriority(priority);
        }
        if (maxPriority != null) {
            query.taskMaxPriority(maxPriority);
        }
        if (minPriority != null) {
            query.taskMinPriority(minPriority);
        }
        if (dueDate != null) {
            query.dueDate(dueDate);
        }
        if (dueAfter != null) {
            query.dueAfter(dueAfter);
        }
        if (dueBefore != null) {
            query.dueBefore(dueBefore);
        }
        if (Boolean.TRUE.equals(withoutDueDate)) {
            query.withoutDueDate();
        }
        if (followUpDate != null) {
            query.followUpDate(followUpDate);
        }
        if (followUpAfter != null) {
            query.followUpAfter(followUpAfter);
        }
        if (followUpBefore != null) {
            query.followUpBefore(followUpBefore);
        }
        if (followUpBeforeOrNotExistent != null) {
            query.followUpBeforeOrNotExistent(followUpBeforeOrNotExistent);
        }
        if (createdOn != null) {
            query.taskCreatedOn(createdOn);
        }
        if (createdAfter != null) {
            query.taskCreatedAfter(createdAfter);
        }
        if (createdBefore != null) {
            query.taskCreatedBefore(createdBefore);
        }
        if (updatedAfter != null) {
            query.taskUpdatedAfter(updatedAfter);
        }
        if (delegationState != null) {
            query.taskDelegationState(DelegationState.valueOf(delegationState));
        }
        if (Boolean.TRUE.equals(withCandidateGroups)) {
            query.withCandidateGroups();
        }
        if (Boolean.TRUE.equals(withoutCandidateGroups)) {
            query.withoutCandidateGroups();
        }
        if (Boolean.TRUE.equals(withCandidateUsers)) {
            query.withCandidateUsers();
        }
        if (Boolean.TRUE.equals(withoutCandidateUsers)) {
            query.withoutCandidateUsers();
        }
        if (Boolean.TRUE.equals(active)) {
            query.active();
        }
        if (Boolean.TRUE.equals(suspended)) {
            query.suspended();
        }
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(withoutTenantId)) {
            query.withoutTenantId();
        }
        if (parentTaskId != null) {
            query.taskParentTaskId(parentTaskId);
        }
        if (caseInstanceId != null) {
            query.caseInstanceId(caseInstanceId);
        }
        if (caseInstanceBusinessKey != null) {
            query.caseInstanceBusinessKey(caseInstanceBusinessKey);
        }
        if (caseInstanceBusinessKeyLike != null) {
            query.caseInstanceBusinessKeyLike(caseInstanceBusinessKeyLike);
        }
        if (caseDefinitionId != null) {
            query.caseDefinitionId(caseDefinitionId);
        }
        if (caseDefinitionKey != null) {
            query.caseDefinitionKey(caseDefinitionKey);
        }
        if (caseDefinitionName != null) {
            query.caseDefinitionName(caseDefinitionName);
        }
        if (caseDefinitionNameLike != null) {
            query.caseDefinitionNameLike(caseDefinitionNameLike);
        }
        if (caseExecutionId != null) {
            query.caseExecutionId(caseExecutionId);
        }
        if (Boolean.TRUE.equals(excludeSubtasks)) {
            query.excludeSubtasks();
        }
        return query;
    }

    // Getters and setters

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<String> getTaskIdIn() {
        return taskIdIn;
    }

    public void setTaskIdIn(List<String> taskIdIn) {
        this.taskIdIn = taskIdIn;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<String> getProcessInstanceIdIn() {
        return processInstanceIdIn;
    }

    public void setProcessInstanceIdIn(List<String> processInstanceIdIn) {
        this.processInstanceIdIn = processInstanceIdIn;
    }

    public String getProcessInstanceBusinessKey() {
        return processInstanceBusinessKey;
    }

    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }

    public List<String> getProcessInstanceBusinessKeyIn() {
        return processInstanceBusinessKeyIn;
    }

    public void setProcessInstanceBusinessKeyIn(List<String> processInstanceBusinessKeyIn) {
        this.processInstanceBusinessKeyIn = processInstanceBusinessKeyIn;
    }

    public String getProcessInstanceBusinessKeyLike() {
        return processInstanceBusinessKeyLike;
    }

    public void setProcessInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
        this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
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

    public List<String> getProcessDefinitionKeyIn() {
        return processDefinitionKeyIn;
    }

    public void setProcessDefinitionKeyIn(List<String> processDefinitionKeyIn) {
        this.processDefinitionKeyIn = processDefinitionKeyIn;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public String getProcessDefinitionNameLike() {
        return processDefinitionNameLike;
    }

    public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
        this.processDefinitionNameLike = processDefinitionNameLike;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeLike() {
        return assigneeLike;
    }

    public void setAssigneeLike(String assigneeLike) {
        this.assigneeLike = assigneeLike;
    }

    public List<String> getAssigneeIn() {
        return assigneeIn;
    }

    public void setAssigneeIn(List<String> assigneeIn) {
        this.assigneeIn = assigneeIn;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup;
    }

    public String getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(String candidateUser) {
        this.candidateUser = candidateUser;
    }

    public List<String> getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(List<String> candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public String getInvolvedUser() {
        return involvedUser;
    }

    public void setInvolvedUser(String involvedUser) {
        this.involvedUser = involvedUser;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Boolean getUnassigned() {
        return unassigned;
    }

    public void setUnassigned(Boolean unassigned) {
        this.unassigned = unassigned;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public List<String> getTaskDefinitionKeyIn() {
        return taskDefinitionKeyIn;
    }

    public void setTaskDefinitionKeyIn(List<String> taskDefinitionKeyIn) {
        this.taskDefinitionKeyIn = taskDefinitionKeyIn;
    }

    public String getTaskDefinitionKeyLike() {
        return taskDefinitionKeyLike;
    }

    public void setTaskDefinitionKeyLike(String taskDefinitionKeyLike) {
        this.taskDefinitionKeyLike = taskDefinitionKeyLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNotEqual() {
        return nameNotEqual;
    }

    public void setNameNotEqual(String nameNotEqual) {
        this.nameNotEqual = nameNotEqual;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getNameNotLike() {
        return nameNotLike;
    }

    public void setNameNotLike(String nameNotLike) {
        this.nameNotLike = nameNotLike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLike() {
        return descriptionLike;
    }

    public void setDescriptionLike(String descriptionLike) {
        this.descriptionLike = descriptionLike;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(Integer maxPriority) {
        this.maxPriority = maxPriority;
    }

    public Integer getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Integer minPriority) {
        this.minPriority = minPriority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueAfter() {
        return dueAfter;
    }

    public void setDueAfter(Date dueAfter) {
        this.dueAfter = dueAfter;
    }

    public Date getDueBefore() {
        return dueBefore;
    }

    public void setDueBefore(Date dueBefore) {
        this.dueBefore = dueBefore;
    }

    public Boolean getWithoutDueDate() {
        return withoutDueDate;
    }

    public void setWithoutDueDate(Boolean withoutDueDate) {
        this.withoutDueDate = withoutDueDate;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Date getFollowUpAfter() {
        return followUpAfter;
    }

    public void setFollowUpAfter(Date followUpAfter) {
        this.followUpAfter = followUpAfter;
    }

    public Date getFollowUpBefore() {
        return followUpBefore;
    }

    public void setFollowUpBefore(Date followUpBefore) {
        this.followUpBefore = followUpBefore;
    }

    public Date getFollowUpBeforeOrNotExistent() {
        return followUpBeforeOrNotExistent;
    }

    public void setFollowUpBeforeOrNotExistent(Date followUpBeforeOrNotExistent) {
        this.followUpBeforeOrNotExistent = followUpBeforeOrNotExistent;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Date getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Date getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public String getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(String delegationState) {
        this.delegationState = delegationState;
    }

    public Boolean getWithCandidateGroups() {
        return withCandidateGroups;
    }

    public void setWithCandidateGroups(Boolean withCandidateGroups) {
        this.withCandidateGroups = withCandidateGroups;
    }

    public Boolean getWithoutCandidateGroups() {
        return withoutCandidateGroups;
    }

    public void setWithoutCandidateGroups(Boolean withoutCandidateGroups) {
        this.withoutCandidateGroups = withoutCandidateGroups;
    }

    public Boolean getWithCandidateUsers() {
        return withCandidateUsers;
    }

    public void setWithCandidateUsers(Boolean withCandidateUsers) {
        this.withCandidateUsers = withCandidateUsers;
    }

    public Boolean getWithoutCandidateUsers() {
        return withoutCandidateUsers;
    }

    public void setWithoutCandidateUsers(Boolean withoutCandidateUsers) {
        this.withoutCandidateUsers = withoutCandidateUsers;
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

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getCaseInstanceBusinessKey() {
        return caseInstanceBusinessKey;
    }

    public void setCaseInstanceBusinessKey(String caseInstanceBusinessKey) {
        this.caseInstanceBusinessKey = caseInstanceBusinessKey;
    }

    public String getCaseInstanceBusinessKeyLike() {
        return caseInstanceBusinessKeyLike;
    }

    public void setCaseInstanceBusinessKeyLike(String caseInstanceBusinessKeyLike) {
        this.caseInstanceBusinessKeyLike = caseInstanceBusinessKeyLike;
    }

    public String getCaseDefinitionId() {
        return caseDefinitionId;
    }

    public void setCaseDefinitionId(String caseDefinitionId) {
        this.caseDefinitionId = caseDefinitionId;
    }

    public String getCaseDefinitionKey() {
        return caseDefinitionKey;
    }

    public void setCaseDefinitionKey(String caseDefinitionKey) {
        this.caseDefinitionKey = caseDefinitionKey;
    }

    public String getCaseDefinitionName() {
        return caseDefinitionName;
    }

    public void setCaseDefinitionName(String caseDefinitionName) {
        this.caseDefinitionName = caseDefinitionName;
    }

    public String getCaseDefinitionNameLike() {
        return caseDefinitionNameLike;
    }

    public void setCaseDefinitionNameLike(String caseDefinitionNameLike) {
        this.caseDefinitionNameLike = caseDefinitionNameLike;
    }

    public String getCaseExecutionId() {
        return caseExecutionId;
    }

    public void setCaseExecutionId(String caseExecutionId) {
        this.caseExecutionId = caseExecutionId;
    }

    public Boolean getExcludeSubtasks() {
        return excludeSubtasks;
    }

    public void setExcludeSubtasks(Boolean excludeSubtasks) {
        this.excludeSubtasks = excludeSubtasks;
    }
}
