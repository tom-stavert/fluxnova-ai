package org.finos.fluxnova.ai.mcp.monitoring.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.rest.dto.runtime.ProcessInstanceQueryDto;

import java.util.List;
import java.util.Set;

public class McpProcessInstanceQueryDto extends ProcessInstanceQueryDto {

    @Schema(description = "Sort the results lexicographically by a given criterion.",
            allowableValues = {"instanceId", "definitionKey", "definitionId", "tenantId", "businessKey"})
    // Need better solution than hard coding
    protected String sortBy;

    @Schema(description = "Sort the results in a given order. Values may be asc for ascending order or desc for descending order.\n" +
            "Must be used in conjunction with the sortBy parameter.",
            allowableValues = {"asc", "desc"})
    protected String sortOrder;

    @Schema(description = "Filter by the deployment the id belongs to.")
    private String deploymentId;

    @Schema(description = "Filter by the key of the process definition the instances run on.")
    private String processDefinitionKey;

    @Schema(description = "Filter by a comma-separated list of process definition keys.\n" +
            "A process instance must have one of the given process definition keys.")
    private List<String> processDefinitionKeys;

    @Schema(description = "Exclude instances by a comma-separated list of process definition keys.\n" +
            "A process instance must not have one of the given process definition keys.")
    private List<String> processDefinitionKeyNotIn;

    @Schema(description = "Filter by process instance business key.")
    private String businessKey;

    @Schema(description = "Filter by process instance business key that the parameter is a substring of.")
    private String businessKeyLike;

    @Schema(description = "Filter by case instance id.")
    private String caseInstanceId;

    @Schema(description = "Filter by the deployment the id belongs to.")
    private String processDefinitionId;

    @Schema(description = "Restrict query to all process instances that are sub process instances of the given process instance.\n" +
            "Takes a process instance id.")
    private String superProcessInstance;

    @Schema(description = "Restrict query to all process instances that have the given process instance as a sub process instance.\n")
    private String subProcessInstance;

    @Schema(description = "Restrict query to all process instances that are sub process instances of the given process instance.\n" +
            "Takes a process instance id.")
    private String superCaseInstance;

    @Schema(description = "Restrict query to all process instances that have the given case instance as a sub case instance.\n" +
            "Takes a case instance id.")
    private String subCaseInstance;

    @Schema(description = "Only include active process instances. Value may only be true,\n" +
            "as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended process instances. Value may only be true,\n" +
            "as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by a comma-separated list of process instance ids.")
    private Set<String> processInstanceIds;

    @Schema(description = "Filter by presence of incidents. Selects only process instances that have an incident.")
    private Boolean withIncident;

    @Schema(description = "Filter by the incident id.")
    private String incidentId;

    @Schema(description = "Filter by the incident type.")
    private String incidentType;

    @Schema(description = "Filter by the incident message. Exact match.")
    private String incidentMessage;

    @Schema(description = "Filter by the incident message that the parameter is a substring of.")
    private String incidentMessageLike;

    @Schema(description = "Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids.")
    private List<String> tenantIds;

    @Schema(description = "Only include process instances which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Filter by a comma-separated list of activity ids.\n" +
            "A process instance must currently wait in a leaf activity with one of the given activity ids.")
    private List<String> activityIds;

    @Schema(description = "Restrict the query to all process instances that are top level process instances.")
    private Boolean rootProcessInstances;

    @Schema(description = "Restrict the query to all process instances that are leaf instances. (i.e. don't have any sub instances).")
    private Boolean leafProcessInstances;

    @Schema(description = "Only include process instances which process definition has no tenant id.")
    private Boolean isProcessDefinitionWithoutTenantId;
}
