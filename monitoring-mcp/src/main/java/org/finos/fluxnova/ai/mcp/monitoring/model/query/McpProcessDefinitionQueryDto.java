package org.finos.fluxnova.ai.mcp.monitoring.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.rest.dto.repository.ProcessDefinitionQueryDto;

import java.util.Date;
import java.util.List;

public class McpProcessDefinitionQueryDto extends ProcessDefinitionQueryDto {

    @Schema(description = "Sort the results lexicographically by a given criterion.",
            allowableValues = {"category", "key", "id", "name", "version", "deploymentId", "deployTime", "tenantId", "versionTag"})
    protected String sortBy;

    @Schema(description = "Sort the results in a given order. Values may be asc for ascending order or desc for descending order.\n" +
            "Must be used in conjunction with the sortBy parameter.",
            allowableValues = {"asc", "desc"})
    protected String sortOrder;

    @Schema(description = "Filter by the process definition id.")
    private String processDefinitionId;

    @Schema(description = "Filter by a comma-separated list of process definition ids.")
    private List<String> processDefinitionIdIn;

    @Schema(description = "Filter by the category of the process definition.")
    private String category;

    @Schema(description = "Filter by the category of the process definition that the parameter is a substring of.")
    private String categoryLike;

    @Schema(description = "Filter by the name of the process definition.")
    private String name;

    @Schema(description = "Filter by the name of the process definition that the parameter is a substring of.")
    private String nameLike;

    @Schema(description = "Filter by the deployment the process definition belongs to.")
    private String deploymentId;

    @Schema(description = "Filter by the deployment date after the given date.")
    private Date deployedAfter;

    @Schema(description = "Filter by the deployment date.")
    private Date deployedAt;

    @Schema(description = "Filter by the key of the process definition.")
    private String key;

    @Schema(description = "Filter by the key of the process definition that the parameter is a substring of.")
    private String keyLike;

    @Schema(description = "Filter by the version of the process definition.")
    private Integer version;

    @Schema(description = "Only include the latest version of the process definition.")
    private Boolean latestVersion;

    @Schema(description = "Filter by the resource name of the process definition.")
    private String resourceName;

    @Schema(description = "Filter by the resource name of the process definition that the parameter is a substring of.")
    private String resourceNameLike;

    @Schema(description = "Filter by the user that can start the process definition.")
    private String startableBy;

    @Schema(description = "Only include active process definitions. Value may only be true, as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended process definitions. Value may only be true, as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by the incident id.")
    private String incidentId;

    @Schema(description = "Filter by the incident type.")
    private String incidentType;

    @Schema(description = "Filter by the incident message. Exact match.")
    private String incidentMessage;

    @Schema(description = "Filter by the incident message that the parameter is a substring of.")
    private String incidentMessageLike;

    @Schema(description = "Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids.")
    private List<String> tenantIds;

    @Schema(description = "Only include process definitions which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Include process definitions that belong to no tenant.")
    private Boolean includeDefinitionsWithoutTenantId;

    @Schema(description = "Filter by the version tag of the process definition.")
    private String versionTag;

    @Schema(description = "Filter by the version tag of the process definition that the parameter is a substring of.")
    private String versionTagLike;

    @Schema(description = "Only include process definitions that have no version tag.")
    private Boolean withoutVersionTag;

    @Schema(description = "Filter by a comma-separated list of process definition keys.")
    private List<String> keys;

    @Schema(description = "Only include process definitions that are startable in the tasklist.")
    private Boolean startableInTasklist;

    @Schema(description = "Only include process definitions that are not startable in the tasklist.")
    private Boolean notStartableInTasklist;

    @Schema(description = "Check startable permission on the process definition.")
    private Boolean startablePermissionCheck;
}
