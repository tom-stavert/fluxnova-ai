package org.finos.fluxnova.ai.mcp.query.tools;

import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.bpm.engine.RepositoryService;
import org.finos.fluxnova.bpm.engine.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositoryQueryMcpToolsTest {

    @Mock
    private RepositoryService repositoryService;

    private RepositoryQueryMcpTools tools;

    @BeforeEach
    void setUp() {
        tools = new RepositoryQueryMcpTools(repositoryService);
    }

    // ========================================================================
    // Process Definition Query Tests
    // ========================================================================

    @Nested
    class QueryProcessDefinitions {

        @Mock(answer = Answers.RETURNS_SELF)
        private ProcessDefinitionQuery query;

        @BeforeEach
        void setUp() {
            when(repositoryService.createProcessDefinitionQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            List<ProcessDefinitionResultDto> result = tools.queryProcessDefinitions(new ProcessDefinitionQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
            verify(query, never()).processDefinitionId(any());
            verify(query, never()).processDefinitionKey(any());
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date deployedAfter = new Date();
            Date deployedAt = new Date();

            ProcessDefinitionQueryDto dto = new ProcessDefinitionQueryDto();
            dto.setProcessDefinitionId("def-1");
            dto.setProcessDefinitionIdIn(List.of("def-1", "def-2"));
            dto.setCategory("billing");
            dto.setCategoryLike("bill%");
            dto.setName("Invoice Process");
            dto.setNameLike("Invoice%");
            dto.setDeploymentId("deploy-1");
            dto.setDeployedAfter(deployedAfter);
            dto.setDeployedAt(deployedAt);
            dto.setKey("invoiceProcess");
            dto.setKeysIn(List.of("invoiceProcess", "orderProcess"));
            dto.setKeyLike("invoice%");
            dto.setVersion(2);
            dto.setLatestVersion(true);
            dto.setResourceName("invoice.bpmn");
            dto.setResourceNameLike("%.bpmn");
            dto.setStartableBy("john");
            dto.setActive(true);
            dto.setSuspended(true);
            dto.setIncidentId("inc-1");
            dto.setIncidentType("failedJob");
            dto.setIncidentMessage("Error");
            dto.setIncidentMessageLike("%error%");
            dto.setTenantIdIn(List.of("t1", "t2"));
            dto.setWithoutTenantId(true);
            dto.setIncludeProcessDefinitionsWithoutTenantId(true);
            dto.setVersionTag("v1.0");
            dto.setVersionTagLike("v1%");
            dto.setWithoutVersionTag(true);
            dto.setStartableInTasklist(true);
            dto.setNotStartableInTasklist(true);

            tools.queryProcessDefinitions(dto);

            verify(query).processDefinitionId("def-1");
            verify(query).processDefinitionIdIn("def-1", "def-2");
            verify(query).processDefinitionCategory("billing");
            verify(query).processDefinitionCategoryLike("bill%");
            verify(query).processDefinitionName("Invoice Process");
            verify(query).processDefinitionNameLike("Invoice%");
            verify(query).deploymentId("deploy-1");
            verify(query).deployedAfter(deployedAfter);
            verify(query).deployedAt(deployedAt);
            verify(query).processDefinitionKey("invoiceProcess");
            verify(query).processDefinitionKeysIn("invoiceProcess", "orderProcess");
            verify(query).processDefinitionKeyLike("invoice%");
            verify(query).processDefinitionVersion(2);
            verify(query).latestVersion();
            verify(query).processDefinitionResourceName("invoice.bpmn");
            verify(query).processDefinitionResourceNameLike("%.bpmn");
            verify(query).startableByUser("john");
            verify(query).active();
            verify(query).suspended();
            verify(query).incidentId("inc-1");
            verify(query).incidentType("failedJob");
            verify(query).incidentMessage("Error");
            verify(query).incidentMessageLike("%error%");
            verify(query).tenantIdIn("t1", "t2");
            verify(query).withoutTenantId();
            verify(query).includeProcessDefinitionsWithoutTenantId();
            verify(query).versionTag("v1.0");
            verify(query).versionTagLike("v1%");
            verify(query).withoutVersionTag();
            verify(query).startableInTasklist();
            verify(query).notStartableInTasklist();
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessDefinitionQueryDto dto = new ProcessDefinitionQueryDto();
            dto.setActive(false);
            dto.setSuspended(null);
            dto.setLatestVersion(false);
            dto.setWithoutTenantId(null);
            dto.setTenantIdIn(Collections.emptyList());
            dto.setProcessDefinitionIdIn(Collections.emptyList());
            dto.setKeysIn(Collections.emptyList());

            tools.queryProcessDefinitions(dto);

            verify(query, never()).active();
            verify(query, never()).suspended();
            verify(query, never()).latestVersion();
            verify(query, never()).withoutTenantId();
            verify(query, never()).tenantIdIn(any(String[].class));
            verify(query, never()).processDefinitionIdIn(any(String[].class));
            verify(query, never()).processDefinitionKeysIn(any(String[].class));
        }

        @Test
        void resultMapping() {
            ProcessDefinition pd = mock(ProcessDefinition.class);
            when(pd.getId()).thenReturn("def:1:abc");
            when(pd.getKey()).thenReturn("invoiceProcess");
            when(pd.getCategory()).thenReturn("billing");
            when(pd.getDescription()).thenReturn("Handles invoices");
            when(pd.getName()).thenReturn("Invoice Process");
            when(pd.getVersion()).thenReturn(3);
            when(pd.getResourceName()).thenReturn("invoice.bpmn");
            when(pd.getDeploymentId()).thenReturn("deploy-1");
            when(pd.getDiagramResourceName()).thenReturn("invoice.png");
            when(pd.isSuspended()).thenReturn(true);
            when(pd.getTenantId()).thenReturn("t1");
            when(pd.getVersionTag()).thenReturn("v1.0");
            when(pd.getHistoryTimeToLive()).thenReturn(180);
            when(pd.isStartableInTasklist()).thenReturn(true);
            when(query.list()).thenReturn(List.of(pd));

            List<ProcessDefinitionResultDto> result = tools.queryProcessDefinitions(new ProcessDefinitionQueryDto());

            assertEquals(1, result.size());
            ProcessDefinitionResultDto r = result.get(0);
            assertEquals("def:1:abc", r.getId());
            assertEquals("invoiceProcess", r.getKey());
            assertEquals("billing", r.getCategory());
            assertEquals("Handles invoices", r.getDescription());
            assertEquals("Invoice Process", r.getName());
            assertEquals(3, r.getVersion());
            assertEquals("invoice.bpmn", r.getResourceName());
            assertEquals("deploy-1", r.getDeploymentId());
            assertEquals("invoice.png", r.getDiagramResourceName());
            assertTrue(r.isSuspended());
            assertEquals("t1", r.getTenantId());
            assertEquals("v1.0", r.getVersionTag());
            assertEquals(180, r.getHistoryTimeToLive());
            assertTrue(r.isStartableInTasklist());
        }
    }

    // ========================================================================
    // Deployment Query Tests
    // ========================================================================

    @Nested
    class QueryDeployments {

        @Mock(answer = Answers.RETURNS_SELF)
        private DeploymentQuery query;

        @BeforeEach
        void setUp() {
            when(repositoryService.createDeploymentQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            List<DeploymentResultDto> result = tools.queryDeployments(new DeploymentQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
            verify(query, never()).deploymentId(any());
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date after = new Date();
            Date before = new Date();

            DeploymentQueryDto dto = new DeploymentQueryDto();
            dto.setDeploymentId("deploy-1");
            dto.setName("my-deployment");
            dto.setNameLike("my-%");
            dto.setSource("process-application");
            dto.setAfter(after);
            dto.setBefore(before);
            dto.setTenantIdIn(List.of("t1", "t2"));
            dto.setWithoutTenantId(true);
            dto.setIncludeDeploymentsWithoutTenantId(true);

            tools.queryDeployments(dto);

            verify(query).deploymentId("deploy-1");
            verify(query).deploymentName("my-deployment");
            verify(query).deploymentNameLike("my-%");
            verify(query).deploymentSource("process-application");
            verify(query).deploymentAfter(after);
            verify(query).deploymentBefore(before);
            verify(query).tenantIdIn("t1", "t2");
            verify(query).withoutTenantId();
            verify(query).includeDeploymentsWithoutTenantId();
        }

        @Test
        void withoutSource_passesNullToDeploymentSource() {
            when(query.list()).thenReturn(Collections.emptyList());

            DeploymentQueryDto dto = new DeploymentQueryDto();
            dto.setWithoutSource(true);

            tools.queryDeployments(dto);

            verify(query).deploymentSource(null);
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            DeploymentQueryDto dto = new DeploymentQueryDto();
            dto.setWithoutTenantId(false);
            dto.setWithoutSource(null);
            dto.setTenantIdIn(Collections.emptyList());

            tools.queryDeployments(dto);

            verify(query, never()).withoutTenantId();
            verify(query, never()).deploymentSource(any());
            verify(query, never()).tenantIdIn(any(String[].class));
        }

        @Test
        void resultMapping() {
            Deployment dep = mock(Deployment.class);
            when(dep.getId()).thenReturn("deploy-1");
            when(dep.getName()).thenReturn("my-deployment");
            Date deployTime = new Date();
            when(dep.getDeploymentTime()).thenReturn(deployTime);
            when(dep.getSource()).thenReturn("process-application");
            when(dep.getTenantId()).thenReturn("t1");
            when(query.list()).thenReturn(List.of(dep));

            List<DeploymentResultDto> result = tools.queryDeployments(new DeploymentQueryDto());

            assertEquals(1, result.size());
            DeploymentResultDto r = result.get(0);
            assertEquals("deploy-1", r.getId());
            assertEquals("my-deployment", r.getName());
            assertEquals(deployTime, r.getDeploymentTime());
            assertEquals("process-application", r.getSource());
            assertEquals("t1", r.getTenantId());
        }
    }
}
