package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.RepositoryService;
import org.finos.fluxnova.bpm.engine.repository.ProcessDefinitionQuery;

import java.util.Date;
import java.util.List;

/**
 * DTO for querying process definitions via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering process definitions.")
public class ProcessDefinitionQueryDto {

    @Schema(description = "Filter by process definition id.")
    private String processDefinitionId;

    @Schema(description = "Filter by a list of process definition ids.")
    private List<String> processDefinitionIdIn;

    @Schema(description = "Filter by the category of the process definition.")
    private String category;

    @Schema(description = "Filter by process definition categories that the parameter is a substring of.")
    private String categoryLike;

    @Schema(description = "Filter by the name of the process definition.")
    private String name;

    @Schema(description = "Filter by process definition names that the parameter is a substring of.")
    private String nameLike;

    @Schema(description = "Filter by the deployment id of the process definition.")
    private String deploymentId;

    @Schema(description = "Filter by a deployment date after the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date deployedAfter;

    @Schema(description = "Filter by a deployment date at the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date deployedAt;

    @Schema(description = "Filter by the key of the process definition.")
    private String key;

    @Schema(description = "Filter by a list of process definition keys. "
            + "A process definition must have one of the given keys.")
    private List<String> keysIn;

    @Schema(description = "Filter by process definition keys that the parameter is a substring of.")
    private String keyLike;

    @Schema(description = "Filter by the version of the process definition.")
    private Integer version;

    @Schema(description = "Only include those process definitions that are latest versions. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean latestVersion;

    @Schema(description = "Filter by the name of the process definition resource. Exact match.")
    private String resourceName;

    @Schema(description = "Filter by names of process definition resources that the parameter is a substring of.")
    private String resourceNameLike;

    @Schema(description = "Filter by a user name who is allowed to start the process.")
    private String startableBy;

    @Schema(description = "Only include active process definitions. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean active;

    @Schema(description = "Only include suspended process definitions. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean suspended;

    @Schema(description = "Filter by the incident id.")
    private String incidentId;

    @Schema(description = "Filter by the incident type.", allowableValues = {"failedJob", "failedExternalTask"})
    private String incidentType;

    @Schema(description = "Filter by the incident message. Exact match.")
    private String incidentMessage;

    @Schema(description = "Filter by the incident message that the parameter is a substring of.")
    private String incidentMessageLike;

    @Schema(description = "Filter by a list of tenant ids. A process definition must have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include process definitions which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Include process definitions which belong to no tenant. "
            + "Can be used in combination with tenantIdIn.")
    private Boolean includeProcessDefinitionsWithoutTenantId;

    @Schema(description = "Filter by the version tag of the process definition.")
    private String versionTag;

    @Schema(description = "Filter by version tags of process definitions that the parameter is a substring of.")
    private String versionTagLike;

    @Schema(description = "Only include process definitions without a version tag.")
    private Boolean withoutVersionTag;

    @Schema(description = "Filter by process definitions which are startable in Tasklist.")
    private Boolean startableInTasklist;

    @Schema(description = "Filter by process definitions which are not startable in Tasklist.")
    private Boolean notStartableInTasklist;

    /**
     * Create a new ProcessDefinitionQuery from the RepositoryService, with all non-null filter criteria applied.
     */
    public ProcessDefinitionQuery toQuery(RepositoryService repositoryService) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        if (processDefinitionId != null) {
            query.processDefinitionId(processDefinitionId);
        }
        if (processDefinitionIdIn != null && !processDefinitionIdIn.isEmpty()) {
            query.processDefinitionIdIn(processDefinitionIdIn.toArray(new String[0]));
        }
        if (category != null) {
            query.processDefinitionCategory(category);
        }
        if (categoryLike != null) {
            query.processDefinitionCategoryLike(categoryLike);
        }
        if (name != null) {
            query.processDefinitionName(name);
        }
        if (nameLike != null) {
            query.processDefinitionNameLike(nameLike);
        }
        if (deploymentId != null) {
            query.deploymentId(deploymentId);
        }
        if (deployedAfter != null) {
            query.deployedAfter(deployedAfter);
        }
        if (deployedAt != null) {
            query.deployedAt(deployedAt);
        }
        if (key != null) {
            query.processDefinitionKey(key);
        }
        if (keysIn != null && !keysIn.isEmpty()) {
            query.processDefinitionKeysIn(keysIn.toArray(new String[0]));
        }
        if (keyLike != null) {
            query.processDefinitionKeyLike(keyLike);
        }
        if (version != null) {
            query.processDefinitionVersion(version);
        }
        if (Boolean.TRUE.equals(latestVersion)) {
            query.latestVersion();
        }
        if (resourceName != null) {
            query.processDefinitionResourceName(resourceName);
        }
        if (resourceNameLike != null) {
            query.processDefinitionResourceNameLike(resourceNameLike);
        }
        if (startableBy != null) {
            query.startableByUser(startableBy);
        }
        if (Boolean.TRUE.equals(active)) {
            query.active();
        }
        if (Boolean.TRUE.equals(suspended)) {
            query.suspended();
        }
        if (incidentId != null) {
            query.incidentId(incidentId);
        }
        if (incidentType != null) {
            query.incidentType(incidentType);
        }
        if (incidentMessage != null) {
            query.incidentMessage(incidentMessage);
        }
        if (incidentMessageLike != null) {
            query.incidentMessageLike(incidentMessageLike);
        }
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(withoutTenantId)) {
            query.withoutTenantId();
        }
        if (Boolean.TRUE.equals(includeProcessDefinitionsWithoutTenantId)) {
            query.includeProcessDefinitionsWithoutTenantId();
        }
        if (versionTag != null) {
            query.versionTag(versionTag);
        }
        if (versionTagLike != null) {
            query.versionTagLike(versionTagLike);
        }
        if (Boolean.TRUE.equals(withoutVersionTag)) {
            query.withoutVersionTag();
        }
        if (Boolean.TRUE.equals(startableInTasklist)) {
            query.startableInTasklist();
        }
        if (Boolean.TRUE.equals(notStartableInTasklist)) {
            query.notStartableInTasklist();
        }
        return query;
    }

    // Getters and setters

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public List<String> getProcessDefinitionIdIn() {
        return processDefinitionIdIn;
    }

    public void setProcessDefinitionIdIn(List<String> processDefinitionIdIn) {
        this.processDefinitionIdIn = processDefinitionIdIn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryLike() {
        return categoryLike;
    }

    public void setCategoryLike(String categoryLike) {
        this.categoryLike = categoryLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Date getDeployedAfter() {
        return deployedAfter;
    }

    public void setDeployedAfter(Date deployedAfter) {
        this.deployedAfter = deployedAfter;
    }

    public Date getDeployedAt() {
        return deployedAt;
    }

    public void setDeployedAt(Date deployedAt) {
        this.deployedAt = deployedAt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getKeysIn() {
        return keysIn;
    }

    public void setKeysIn(List<String> keysIn) {
        this.keysIn = keysIn;
    }

    public String getKeyLike() {
        return keyLike;
    }

    public void setKeyLike(String keyLike) {
        this.keyLike = keyLike;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Boolean latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceNameLike() {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike) {
        this.resourceNameLike = resourceNameLike;
    }

    public String getStartableBy() {
        return startableBy;
    }

    public void setStartableBy(String startableBy) {
        this.startableBy = startableBy;
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

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentMessage() {
        return incidentMessage;
    }

    public void setIncidentMessage(String incidentMessage) {
        this.incidentMessage = incidentMessage;
    }

    public String getIncidentMessageLike() {
        return incidentMessageLike;
    }

    public void setIncidentMessageLike(String incidentMessageLike) {
        this.incidentMessageLike = incidentMessageLike;
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

    public Boolean getIncludeProcessDefinitionsWithoutTenantId() {
        return includeProcessDefinitionsWithoutTenantId;
    }

    public void setIncludeProcessDefinitionsWithoutTenantId(Boolean includeProcessDefinitionsWithoutTenantId) {
        this.includeProcessDefinitionsWithoutTenantId = includeProcessDefinitionsWithoutTenantId;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public String getVersionTagLike() {
        return versionTagLike;
    }

    public void setVersionTagLike(String versionTagLike) {
        this.versionTagLike = versionTagLike;
    }

    public Boolean getWithoutVersionTag() {
        return withoutVersionTag;
    }

    public void setWithoutVersionTag(Boolean withoutVersionTag) {
        this.withoutVersionTag = withoutVersionTag;
    }

    public Boolean getStartableInTasklist() {
        return startableInTasklist;
    }

    public void setStartableInTasklist(Boolean startableInTasklist) {
        this.startableInTasklist = startableInTasklist;
    }

    public Boolean getNotStartableInTasklist() {
        return notStartableInTasklist;
    }

    public void setNotStartableInTasklist(Boolean notStartableInTasklist) {
        this.notStartableInTasklist = notStartableInTasklist;
    }
}
