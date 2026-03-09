package org.finos.fluxnova.ai.mcp.query.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.finos.fluxnova.bpm.engine.RepositoryService;
import org.finos.fluxnova.bpm.engine.repository.DeploymentQuery;

import java.util.Date;
import java.util.List;

/**
 * DTO for querying deployments via the process engine Query API.
 * All fields are optional filter criteria.
 */
@Schema(description = "Query parameters for filtering deployments.")
public class DeploymentQueryDto {

    @Schema(description = "Filter by deployment id.")
    private String deploymentId;

    @Schema(description = "Filter by the deployment name. Exact match.")
    private String name;

    @Schema(description = "Filter by deployment names that the parameter is a substring of. "
            + "The parameter may include the wildcard character '%'.")
    private String nameLike;

    @Schema(description = "Filter by the deployment source.")
    private String source;

    @Schema(description = "Only include deployments that have no source. "
            + "Value may only be true, as false is the default behavior.")
    private Boolean withoutSource;

    @Schema(description = "Restricts to all deployments after the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date after;

    @Schema(description = "Restricts to all deployments before the given date. "
            + "By default, the date must not include a time component - only dates "
            + "(e.g., 2013-01-23) are supported. To include a time and timezone, the "
            + "format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ (e.g., 2013-01-23T14:42:45.000+0200).")
    private Date before;

    @Schema(description = "Filter by a list of tenant ids. A deployment must have one of the given tenant ids.")
    private List<String> tenantIdIn;

    @Schema(description = "Only include deployments which belong to no tenant.")
    private Boolean withoutTenantId;

    @Schema(description = "Include deployments which belong to no tenant. "
            + "Can be used in combination with tenantIdIn.")
    private Boolean includeDeploymentsWithoutTenantId;

    /**
     * Create a new DeploymentQuery from the RepositoryService, with all non-null filter criteria applied.
     */
    public DeploymentQuery toQuery(RepositoryService repositoryService) {
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        if (deploymentId != null) {
            query.deploymentId(deploymentId);
        }
        if (name != null) {
            query.deploymentName(name);
        }
        if (nameLike != null) {
            query.deploymentNameLike(nameLike);
        }
        if (source != null) {
            query.deploymentSource(source);
        }
        if (Boolean.TRUE.equals(withoutSource)) {
            query.deploymentSource(null);
        }
        if (after != null) {
            query.deploymentAfter(after);
        }
        if (before != null) {
            query.deploymentBefore(before);
        }
        if (tenantIdIn != null && !tenantIdIn.isEmpty()) {
            query.tenantIdIn(tenantIdIn.toArray(new String[0]));
        }
        if (Boolean.TRUE.equals(withoutTenantId)) {
            query.withoutTenantId();
        }
        if (Boolean.TRUE.equals(includeDeploymentsWithoutTenantId)) {
            query.includeDeploymentsWithoutTenantId();
        }
        return query;
    }

    // Getters and setters

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getWithoutSource() {
        return withoutSource;
    }

    public void setWithoutSource(Boolean withoutSource) {
        this.withoutSource = withoutSource;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
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

    public Boolean getIncludeDeploymentsWithoutTenantId() {
        return includeDeploymentsWithoutTenantId;
    }

    public void setIncludeDeploymentsWithoutTenantId(Boolean includeDeploymentsWithoutTenantId) {
        this.includeDeploymentsWithoutTenantId = includeDeploymentsWithoutTenantId;
    }
}
