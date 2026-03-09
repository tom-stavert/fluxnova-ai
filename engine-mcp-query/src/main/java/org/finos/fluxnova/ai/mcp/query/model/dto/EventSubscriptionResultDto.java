package org.finos.fluxnova.ai.mcp.query.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.runtime.EventSubscription;

import java.util.Date;

/**
 * Result DTO for event subscription query results.
 * Maps the fields from the engine's {@link EventSubscription} interface.
 */
public class EventSubscriptionResultDto {

    private String id;
    @Schema(description = "The type of the event subscription.", allowableValues = {"message", "signal", "compensate", "conditional"})
    private String eventType;
    private String eventName;
    private String executionId;
    private String processInstanceId;
    private String activityId;
    private String tenantId;
    private Date createdDate;

    public EventSubscriptionResultDto() {
    }

    /**
     * Create a result DTO from a process engine EventSubscription entity.
     */
    public static EventSubscriptionResultDto fromEventSubscription(EventSubscription eventSubscription) {
        EventSubscriptionResultDto dto = new EventSubscriptionResultDto();
        dto.setId(eventSubscription.getId());
        dto.setEventType(eventSubscription.getEventType());
        dto.setEventName(eventSubscription.getEventName());
        dto.setExecutionId(eventSubscription.getExecutionId());
        dto.setProcessInstanceId(eventSubscription.getProcessInstanceId());
        dto.setActivityId(eventSubscription.getActivityId());
        dto.setTenantId(eventSubscription.getTenantId());
        dto.setCreatedDate(eventSubscription.getCreated());
        return dto;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
