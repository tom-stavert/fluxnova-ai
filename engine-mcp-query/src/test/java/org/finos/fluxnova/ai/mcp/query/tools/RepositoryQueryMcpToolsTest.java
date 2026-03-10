package org.finos.fluxnova.ai.mcp.query.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.query.model.dto.DeploymentResultDto;
import org.finos.fluxnova.ai.mcp.query.model.dto.ProcessDefinitionResultDto;
import org.finos.fluxnova.ai.mcp.query.model.query.DeploymentQueryDto;
import org.finos.fluxnova.ai.mcp.query.model.query.ProcessDefinitionQueryDto;
import org.finos.fluxnova.bpm.engine.RepositoryService;
import org.finos.fluxnova.bpm.engine.repository.Deployment;
import org.finos.fluxnova.bpm.engine.repository.DeploymentQuery;
import org.finos.fluxnova.bpm.engine.repository.ProcessDefinition;
import org.finos.fluxnova.bpm.engine.repository.ProcessDefinitionQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositoryQueryMcpToolsTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static <T> T empty(Class<T> type) {
        try {
            return MAPPER.readValue("{}", type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Mock
    private RepositoryService repositoryService;

    private RepositoryQueryMcpTools tools;

    @BeforeEach
    void setUp() {
        tools = new RepositoryQueryMcpTools(repositoryService, 200);
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

            List<ProcessDefinitionResultDto> result = tools.queryProcessDefinitions(empty(ProcessDefinitionQueryDto.class), null);

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

            ProcessDefinitionQueryDto dto = new ProcessDefinitionQueryDto(
                    "def-1", List.of("def-1", "def-2"),
                    "billing", "bill%",
                    "Invoice Process", "Invoice%",
                    "deploy-1", deployedAfter, deployedAt,
                    "invoiceProcess", "invoice%",
                    2, true,
                    "invoice.bpmn", "%.bpmn",
                    "john",
                    true, true,
                    "inc-1", "failedJob", "Error", "%error%",
                    List.of("t1", "t2"), true, true,
                    "v1.0", "v1%", true,
                    true, true
            );

            tools.queryProcessDefinitions(dto, null);

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

            ProcessDefinitionQueryDto dto = new ProcessDefinitionQueryDto(
                    null, Collections.emptyList(),
                    null, null, null, null, null, null, null,
                    null, null,
                    null, false,
                    null, null, null,
                    false, null,
                    null, null, null, null,
                    Collections.emptyList(), null, null,
                    null, null, null,
                    null, null
            );

            tools.queryProcessDefinitions(dto, null);

            verify(query, never()).active();
            verify(query, never()).suspended();
            verify(query, never()).latestVersion();
            verify(query, never()).withoutTenantId();
            verify(query, never()).tenantIdIn(any(String[].class));
            verify(query, never()).processDefinitionIdIn(any(String[].class));
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

            List<ProcessDefinitionResultDto> result = tools.queryProcessDefinitions(empty(ProcessDefinitionQueryDto.class), null);

            assertEquals(1, result.size());
            ProcessDefinitionResultDto r = result.getFirst();
            assertEquals("def:1:abc", r.id());
            assertEquals("invoiceProcess", r.key());
            assertEquals("billing", r.category());
            assertEquals("Handles invoices", r.description());
            assertEquals("Invoice Process", r.name());
            assertEquals(3, r.version());
            assertEquals("invoice.bpmn", r.resourceName());
            assertEquals("deploy-1", r.deploymentId());
            assertEquals("invoice.png", r.diagramResourceName());
            assertTrue(r.suspended());
            assertEquals("t1", r.tenantId());
            assertEquals("v1.0", r.versionTag());
            assertEquals(180, r.historyTimeToLive());
            assertTrue(r.startableInTasklist());
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

            List<DeploymentResultDto> result = tools.queryDeployments(empty(DeploymentQueryDto.class), null);

            assertTrue(result.isEmpty());
            verify(query).list();
            verify(query, never()).deploymentId(any());
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date after = new Date();
            Date before = new Date();

            DeploymentQueryDto dto = new DeploymentQueryDto(
                    "deploy-1", "my-deployment", "my-%",
                    "process-application", null,
                    after, before,
                    List.of("t1", "t2"), true, true
            );

            tools.queryDeployments(dto, null);

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

            DeploymentQueryDto dto = new DeploymentQueryDto(
                    null, null, null, null, true,
                    null, null, null, null, null
            );

            tools.queryDeployments(dto, null);

            verify(query).deploymentSource(null);
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            DeploymentQueryDto dto = new DeploymentQueryDto(
                    null, null, null, null, null,
                    null, null, Collections.emptyList(), false, null
            );

            tools.queryDeployments(dto, null);

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

            List<DeploymentResultDto> result = tools.queryDeployments(empty(DeploymentQueryDto.class), null);

            assertEquals(1, result.size());
            DeploymentResultDto r = result.getFirst();
            assertEquals("deploy-1", r.id());
            assertEquals("my-deployment", r.name());
            assertEquals(deployTime, r.deploymentTime());
            assertEquals("process-application", r.source());
            assertEquals("t1", r.tenantId());
        }
    }
}
