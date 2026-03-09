package org.finos.fluxnova.ai.mcp.query.model.dto;

import org.finos.fluxnova.bpm.engine.task.DelegationState;
import org.finos.fluxnova.bpm.engine.task.Task;

import java.util.Date;

/**
 * Result DTO for task query results.
 * Maps the fields from the engine's {@link Task} interface.
 */
public class TaskResultDto {

    private String id;
    private String name;
    private String assignee;
    private String owner;
    private Date created;
    private Date lastUpdated;
    private Date due;
    private Date followUp;
    private String delegationState;
    private String description;
    private String executionId;
    private String parentTaskId;
    private int priority;
    private String processDefinitionId;
    private String processInstanceId;
    private String caseExecutionId;
    private String caseDefinitionId;
    private String caseInstanceId;
    private String taskDefinitionKey;
    private boolean suspended;
    private String formKey;
    private String tenantId;
    private String taskState;

    public TaskResultDto() {
    }

    /**
     * Create a result DTO from a process engine Task entity.
     */
    public static TaskResultDto fromTask(Task task) {
        TaskResultDto dto = new TaskResultDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setAssignee(task.getAssignee());
        dto.setOwner(task.getOwner());
        dto.setCreated(task.getCreateTime());
        dto.setLastUpdated(task.getLastUpdated());
        dto.setDue(task.getDueDate());
        dto.setFollowUp(task.getFollowUpDate());
        DelegationState ds = task.getDelegationState();
        dto.setDelegationState(ds != null ? ds.name() : null);
        dto.setDescription(task.getDescription());
        dto.setExecutionId(task.getExecutionId());
        dto.setParentTaskId(task.getParentTaskId());
        dto.setPriority(task.getPriority());
        dto.setProcessDefinitionId(task.getProcessDefinitionId());
        dto.setProcessInstanceId(task.getProcessInstanceId());
        dto.setCaseExecutionId(task.getCaseExecutionId());
        dto.setCaseDefinitionId(task.getCaseDefinitionId());
        dto.setCaseInstanceId(task.getCaseInstanceId());
        dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
        dto.setSuspended(task.isSuspended());
        dto.setFormKey(task.getFormKey());
        dto.setTenantId(task.getTenantId());
        dto.setTaskState(task.getTaskState());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getFollowUp() {
        return followUp;
    }

    public void setFollowUp(Date followUp) {
        this.followUp = followUp;
    }

    public String getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(String delegationState) {
        this.delegationState = delegationState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getCaseExecutionId() {
        return caseExecutionId;
    }

    public void setCaseExecutionId(String caseExecutionId) {
        this.caseExecutionId = caseExecutionId;
    }

    public String getCaseDefinitionId() {
        return caseDefinitionId;
    }

    public void setCaseDefinitionId(String caseDefinitionId) {
        this.caseDefinitionId = caseDefinitionId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
