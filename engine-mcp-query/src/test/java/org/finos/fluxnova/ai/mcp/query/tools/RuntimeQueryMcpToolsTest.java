package org.finos.fluxnova.ai.mcp.query.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.bpm.engine.ProcessEngine;
import org.finos.fluxnova.bpm.engine.RuntimeService;
import org.finos.fluxnova.bpm.engine.runtime.*;
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
class RuntimeQueryMcpToolsTest {

    @Mock
    private ProcessEngine processEngine;

    @Mock
    private RuntimeService runtimeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private RuntimeQueryMcpTools tools;

    @BeforeEach
    void setUp() {
        when(processEngine.getRuntimeService()).thenReturn(runtimeService);
        tools = new RuntimeQueryMcpTools(processEngine, objectMapper);
    }

    // ========================================================================
    // Process Instance Query Tests
    // ========================================================================

    @Nested
    class QueryProcessInstances {

        @Mock(answer = Answers.RETURNS_SELF)
        private ProcessInstanceQuery query;

        @BeforeEach
        void setUp() {
            when(runtimeService.createProcessInstanceQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            String result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            assertEquals("[]", result);
            verify(query).list();
            verify(query, never()).processInstanceId(any());
            verify(query, never()).processDefinitionKey(any());
        }

        @Test
        void filterByProcessInstanceId() {
            ProcessInstance pi = mockProcessInstance("pi-1", "def-1", "bk-1", null, null, false, "t1");
            when(query.list()).thenReturn(List.of(pi));

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessInstanceId("pi-1");

            String result = tools.queryProcessInstances(dto);

            verify(query).processInstanceId("pi-1");
            assertContains(result, "pi-1");
        }

        @Test
        void filterByProcessInstanceIds() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessInstanceIds(Set.of("pi-1", "pi-2"));

            tools.queryProcessInstances(dto);

            verify(query).processInstanceIds(Set.of("pi-1", "pi-2"));
        }

        @Test
        void filterByBusinessKey() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setBusinessKey("order-123");

            tools.queryProcessInstances(dto);

            verify(query).processInstanceBusinessKey("order-123");
        }

        @Test
        void filterByBusinessKeyLike() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setBusinessKeyLike("order-%");

            tools.queryProcessInstances(dto);

            verify(query).processInstanceBusinessKeyLike("order-%");
        }

        @Test
        void filterByProcessDefinitionKey() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionKey("myProcess");

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionKey("myProcess");
        }

        @Test
        void filterByProcessDefinitionKeyIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionKeyIn(List.of("proc1", "proc2"));

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionKeyIn("proc1", "proc2");
        }

        @Test
        void filterByProcessDefinitionKeyNotIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionKeyNotIn(List.of("excluded1"));

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionKeyNotIn("excluded1");
        }

        @Test
        void filterByProcessDefinitionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionId("def:1:abc");

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionId("def:1:abc");
        }

        @Test
        void filterByDeploymentId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setDeploymentId("deploy-1");

            tools.queryProcessInstances(dto);

            verify(query).deploymentId("deploy-1");
        }

        @Test
        void filterBySuperProcessInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSuperProcessInstanceId("super-1");

            tools.queryProcessInstances(dto);

            verify(query).superProcessInstanceId("super-1");
        }

        @Test
        void filterBySubProcessInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSubProcessInstanceId("sub-1");

            tools.queryProcessInstances(dto);

            verify(query).subProcessInstanceId("sub-1");
        }

        @Test
        void filterByCaseInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setCaseInstanceId("case-1");

            tools.queryProcessInstances(dto);

            verify(query).caseInstanceId("case-1");
        }

        @Test
        void filterBySuperCaseInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSuperCaseInstanceId("super-case-1");

            tools.queryProcessInstances(dto);

            verify(query).superCaseInstanceId("super-case-1");
        }

        @Test
        void filterBySubCaseInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSubCaseInstanceId("sub-case-1");

            tools.queryProcessInstances(dto);

            verify(query).subCaseInstanceId("sub-case-1");
        }

        @Test
        void filterByActiveTrue() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setActive(true);

            tools.queryProcessInstances(dto);

            verify(query).active();
        }

        @Test
        void filterByActiveFalse_doesNotApply() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setActive(false);

            tools.queryProcessInstances(dto);

            verify(query, never()).active();
        }

        @Test
        void filterByActiveNull_doesNotApply() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setActive(null);

            tools.queryProcessInstances(dto);

            verify(query, never()).active();
        }

        @Test
        void filterBySuspendedTrue() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSuspended(true);

            tools.queryProcessInstances(dto);

            verify(query).suspended();
        }

        @Test
        void filterBySuspendedFalse_doesNotApply() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setSuspended(false);

            tools.queryProcessInstances(dto);

            verify(query, never()).suspended();
        }

        @Test
        void filterByWithIncidentTrue() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setWithIncident(true);

            tools.queryProcessInstances(dto);

            verify(query).withIncident();
        }

        @Test
        void filterByIncidentId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setIncidentId("inc-1");

            tools.queryProcessInstances(dto);

            verify(query).incidentId("inc-1");
        }

        @Test
        void filterByIncidentType() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setIncidentType("failedJob");

            tools.queryProcessInstances(dto);

            verify(query).incidentType("failedJob");
        }

        @Test
        void filterByIncidentMessage() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setIncidentMessage("Something went wrong");

            tools.queryProcessInstances(dto);

            verify(query).incidentMessage("Something went wrong");
        }

        @Test
        void filterByIncidentMessageLike() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setIncidentMessageLike("%error%");

            tools.queryProcessInstances(dto);

            verify(query).incidentMessageLike("%error%");
        }

        @Test
        void filterByTenantIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setTenantIdIn(List.of("t1", "t2"));

            tools.queryProcessInstances(dto);

            verify(query).tenantIdIn("t1", "t2");
        }

        @Test
        void filterByTenantIdIn_emptyList_doesNotApply() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setTenantIdIn(Collections.emptyList());

            tools.queryProcessInstances(dto);

            verify(query, never()).tenantIdIn(any(String[].class));
        }

        @Test
        void filterByWithoutTenantId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setWithoutTenantId(true);

            tools.queryProcessInstances(dto);

            verify(query).withoutTenantId();
        }

        @Test
        void filterByProcessDefinitionWithoutTenantId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionWithoutTenantId(true);

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionWithoutTenantId();
        }

        @Test
        void filterByActivityIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setActivityIdIn(List.of("act1", "act2"));

            tools.queryProcessInstances(dto);

            verify(query).activityIdIn("act1", "act2");
        }

        @Test
        void filterByRootProcessInstances() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setRootProcessInstances(true);

            tools.queryProcessInstances(dto);

            verify(query).rootProcessInstances();
        }

        @Test
        void filterByLeafProcessInstances() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setLeafProcessInstances(true);

            tools.queryProcessInstances(dto);

            verify(query).leafProcessInstances();
        }

        @Test
        void filterByVariableNamesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setVariableNamesIgnoreCase(true);

            tools.queryProcessInstances(dto);

            verify(query).matchVariableNamesIgnoreCase();
        }

        @Test
        void filterByVariableValuesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setVariableValuesIgnoreCase(true);

            tools.queryProcessInstances(dto);

            verify(query).matchVariableValuesIgnoreCase();
        }

        @Test
        void multipleFilters_allApplied() {
            ProcessInstance pi = mockProcessInstance("pi-1", "def-1", "bk-1", null, null, false, "t1");
            when(query.list()).thenReturn(List.of(pi));

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessDefinitionKey("myProcess");
            dto.setActive(true);
            dto.setTenantIdIn(List.of("t1"));

            tools.queryProcessInstances(dto);

            verify(query).processDefinitionKey("myProcess");
            verify(query).active();
            verify(query).tenantIdIn("t1");
        }

        @Test
        void resultMapping_correctJson() throws JsonProcessingException {
            ProcessInstance pi = mockProcessInstance("pi-1", "def:1:abc", "order-123", "root-1", "case-1", true, "tenant-a");
            when(query.list()).thenReturn(List.of(pi));

            String result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            List<ProcessInstanceResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<ProcessInstanceResultDto>>() {});

            assertEquals(1, parsed.size());
            ProcessInstanceResultDto dto = parsed.get(0);
            assertEquals("pi-1", dto.getId());
            assertEquals("def:1:abc", dto.getProcessDefinitionId());
            assertEquals("order-123", dto.getBusinessKey());
            assertEquals("root-1", dto.getRootProcessInstanceId());
            assertEquals("case-1", dto.getCaseInstanceId());
            assertTrue(dto.isSuspended());
            assertEquals("tenant-a", dto.getTenantId());
        }

        @Test
        void multipleResults() throws JsonProcessingException {
            ProcessInstance pi1 = mockProcessInstance("pi-1", "def-1", null, null, null, false, null);
            ProcessInstance pi2 = mockProcessInstance("pi-2", "def-2", "bk-2", null, null, true, "t1");
            when(query.list()).thenReturn(List.of(pi1, pi2));

            String result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            List<ProcessInstanceResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<ProcessInstanceResultDto>>() {});
            assertEquals(2, parsed.size());
            assertEquals("pi-1", parsed.get(0).getId());
            assertEquals("pi-2", parsed.get(1).getId());
        }
    }

    // ========================================================================
    // Execution Query Tests
    // ========================================================================

    @Nested
    class QueryExecutions {

        @Mock(answer = Answers.RETURNS_SELF)
        private ExecutionQuery query;

        @BeforeEach
        void setUp() {
            when(runtimeService.createExecutionQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            String result = tools.queryExecutions(new ExecutionQueryDto());

            assertEquals("[]", result);
            verify(query).list();
        }

        @Test
        void filterByExecutionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setExecutionId("exec-1");

            tools.queryExecutions(dto);

            verify(query).executionId("exec-1");
        }

        @Test
        void filterByProcessInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setProcessInstanceId("pi-1");

            tools.queryExecutions(dto);

            verify(query).processInstanceId("pi-1");
        }

        @Test
        void filterByBusinessKey() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setBusinessKey("bk-1");

            tools.queryExecutions(dto);

            verify(query).processInstanceBusinessKey("bk-1");
        }

        @Test
        void filterByProcessDefinitionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setProcessDefinitionId("def-1");

            tools.queryExecutions(dto);

            verify(query).processDefinitionId("def-1");
        }

        @Test
        void filterByProcessDefinitionKey() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setProcessDefinitionKey("myProcess");

            tools.queryExecutions(dto);

            verify(query).processDefinitionKey("myProcess");
        }

        @Test
        void filterByActivityId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setActivityId("task1");

            tools.queryExecutions(dto);

            verify(query).activityId("task1");
        }

        @Test
        void filterBySignalEventSubscriptionName() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setSignalEventSubscriptionName("mySignal");

            tools.queryExecutions(dto);

            verify(query).signalEventSubscriptionName("mySignal");
        }

        @Test
        void filterByMessageEventSubscriptionName() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setMessageEventSubscriptionName("myMessage");

            tools.queryExecutions(dto);

            verify(query).messageEventSubscriptionName("myMessage");
        }

        @Test
        void filterByActiveTrue() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setActive(true);

            tools.queryExecutions(dto);

            verify(query).active();
        }

        @Test
        void filterByActiveFalse_doesNotApply() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setActive(false);

            tools.queryExecutions(dto);

            verify(query, never()).active();
        }

        @Test
        void filterBySuspendedTrue() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setSuspended(true);

            tools.queryExecutions(dto);

            verify(query).suspended();
        }

        @Test
        void filterByIncidentFields() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setIncidentId("inc-1");
            dto.setIncidentType("failedJob");
            dto.setIncidentMessage("Error");
            dto.setIncidentMessageLike("%error%");

            tools.queryExecutions(dto);

            verify(query).incidentId("inc-1");
            verify(query).incidentType("failedJob");
            verify(query).incidentMessage("Error");
            verify(query).incidentMessageLike("%error%");
        }

        @Test
        void filterByTenantIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setTenantIdIn(List.of("t1"));

            tools.queryExecutions(dto);

            verify(query).tenantIdIn("t1");
        }

        @Test
        void filterByWithoutTenantId() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setWithoutTenantId(true);

            tools.queryExecutions(dto);

            verify(query).withoutTenantId();
        }

        @Test
        void filterByVariableNamesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setVariableNamesIgnoreCase(true);

            tools.queryExecutions(dto);

            verify(query).matchVariableNamesIgnoreCase();
        }

        @Test
        void filterByVariableValuesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setVariableValuesIgnoreCase(true);

            tools.queryExecutions(dto);

            verify(query).matchVariableValuesIgnoreCase();
        }

        @Test
        void resultMapping() throws JsonProcessingException {
            Execution exec = mock(Execution.class);
            when(exec.getId()).thenReturn("exec-1");
            when(exec.getProcessInstanceId()).thenReturn("pi-1");
            when(exec.isSuspended()).thenReturn(false);
            when(exec.isEnded()).thenReturn(false);
            when(exec.getTenantId()).thenReturn("t1");
            when(query.list()).thenReturn(List.of(exec));

            String result = tools.queryExecutions(new ExecutionQueryDto());

            List<ExecutionResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<ExecutionResultDto>>() {});
            assertEquals(1, parsed.size());
            assertEquals("exec-1", parsed.get(0).getId());
            assertEquals("pi-1", parsed.get(0).getProcessInstanceId());
            assertFalse(parsed.get(0).isSuspended());
            assertEquals("t1", parsed.get(0).getTenantId());
        }
    }

    // ========================================================================
    // Incident Query Tests
    // ========================================================================

    @Nested
    class QueryIncidents {

        @Mock(answer = Answers.RETURNS_SELF)
        private IncidentQuery query;

        @BeforeEach
        void setUp() {
            when(runtimeService.createIncidentQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            String result = tools.queryIncidents(new IncidentQueryDto());

            assertEquals("[]", result);
            verify(query).list();
        }

        @Test
        void filterByIncidentId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentId("inc-1");

            tools.queryIncidents(dto);

            verify(query).incidentId("inc-1");
        }

        @Test
        void filterByIncidentType() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentType("failedJob");

            tools.queryIncidents(dto);

            verify(query).incidentType("failedJob");
        }

        @Test
        void filterByIncidentMessage() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentMessage("Error");

            tools.queryIncidents(dto);

            verify(query).incidentMessage("Error");
        }

        @Test
        void filterByIncidentMessageLike() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentMessageLike("%error%");

            tools.queryIncidents(dto);

            verify(query).incidentMessageLike("%error%");
        }

        @Test
        void filterByProcessDefinitionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setProcessDefinitionId("def-1");

            tools.queryIncidents(dto);

            verify(query).processDefinitionId("def-1");
        }

        @Test
        void filterByProcessDefinitionKeyIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setProcessDefinitionKeyIn(List.of("proc1", "proc2"));

            tools.queryIncidents(dto);

            verify(query).processDefinitionKeyIn("proc1", "proc2");
        }

        @Test
        void filterByProcessInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setProcessInstanceId("pi-1");

            tools.queryIncidents(dto);

            verify(query).processInstanceId("pi-1");
        }

        @Test
        void filterByExecutionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setExecutionId("exec-1");

            tools.queryIncidents(dto);

            verify(query).executionId("exec-1");
        }

        @Test
        void filterByTimestampBefore() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date before = new Date();

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentTimestampBefore(before);

            tools.queryIncidents(dto);

            verify(query).incidentTimestampBefore(before);
        }

        @Test
        void filterByTimestampAfter() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date after = new Date();

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentTimestampAfter(after);

            tools.queryIncidents(dto);

            verify(query).incidentTimestampAfter(after);
        }

        @Test
        void filterByActivityId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setActivityId("act-1");

            tools.queryIncidents(dto);

            verify(query).activityId("act-1");
        }

        @Test
        void filterByFailedActivityId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setFailedActivityId("failed-act-1");

            tools.queryIncidents(dto);

            verify(query).failedActivityId("failed-act-1");
        }

        @Test
        void filterByCauseIncidentId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setCauseIncidentId("cause-1");

            tools.queryIncidents(dto);

            verify(query).causeIncidentId("cause-1");
        }

        @Test
        void filterByRootCauseIncidentId() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setRootCauseIncidentId("root-cause-1");

            tools.queryIncidents(dto);

            verify(query).rootCauseIncidentId("root-cause-1");
        }

        @Test
        void filterByConfiguration() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setConfiguration("config-1");

            tools.queryIncidents(dto);

            verify(query).configuration("config-1");
        }

        @Test
        void filterByTenantIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setTenantIdIn(List.of("t1"));

            tools.queryIncidents(dto);

            verify(query).tenantIdIn("t1");
        }

        @Test
        void filterByJobDefinitionIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setJobDefinitionIdIn(List.of("jd-1", "jd-2"));

            tools.queryIncidents(dto);

            verify(query).jobDefinitionIdIn("jd-1", "jd-2");
        }

        @Test
        void resultMapping() throws JsonProcessingException {
            Incident incident = mock(Incident.class);
            when(incident.getId()).thenReturn("inc-1");
            when(incident.getIncidentType()).thenReturn("failedJob");
            when(incident.getIncidentMessage()).thenReturn("Error occurred");
            when(incident.getProcessInstanceId()).thenReturn("pi-1");
            when(incident.getExecutionId()).thenReturn("exec-1");
            when(incident.getActivityId()).thenReturn("serviceTask1");
            when(incident.getProcessDefinitionId()).thenReturn("def-1");
            when(incident.getTenantId()).thenReturn("t1");
            Date timestamp = new Date();
            when(incident.getIncidentTimestamp()).thenReturn(timestamp);
            when(query.list()).thenReturn(List.of(incident));

            String result = tools.queryIncidents(new IncidentQueryDto());

            List<IncidentResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<IncidentResultDto>>() {});
            assertEquals(1, parsed.size());
            assertEquals("inc-1", parsed.get(0).getId());
            assertEquals("failedJob", parsed.get(0).getIncidentType());
            assertEquals("Error occurred", parsed.get(0).getIncidentMessage());
            assertEquals("pi-1", parsed.get(0).getProcessInstanceId());
        }
    }

    // ========================================================================
    // Event Subscription Query Tests
    // ========================================================================

    @Nested
    class QueryEventSubscriptions {

        @Mock(answer = Answers.RETURNS_SELF)
        private EventSubscriptionQuery query;

        @BeforeEach
        void setUp() {
            when(runtimeService.createEventSubscriptionQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            String result = tools.queryEventSubscriptions(new EventSubscriptionQueryDto());

            assertEquals("[]", result);
            verify(query).list();
        }

        @Test
        void filterByEventSubscriptionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setEventSubscriptionId("es-1");

            tools.queryEventSubscriptions(dto);

            verify(query).eventSubscriptionId("es-1");
        }

        @Test
        void filterByEventName() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setEventName("myEvent");

            tools.queryEventSubscriptions(dto);

            verify(query).eventName("myEvent");
        }

        @Test
        void filterByEventType() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setEventType("message");

            tools.queryEventSubscriptions(dto);

            verify(query).eventType("message");
        }

        @Test
        void filterByExecutionId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setExecutionId("exec-1");

            tools.queryEventSubscriptions(dto);

            verify(query).executionId("exec-1");
        }

        @Test
        void filterByProcessInstanceId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setProcessInstanceId("pi-1");

            tools.queryEventSubscriptions(dto);

            verify(query).processInstanceId("pi-1");
        }

        @Test
        void filterByActivityId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setActivityId("act-1");

            tools.queryEventSubscriptions(dto);

            verify(query).activityId("act-1");
        }

        @Test
        void filterByTenantIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setTenantIdIn(List.of("t1", "t2"));

            tools.queryEventSubscriptions(dto);

            verify(query).tenantIdIn("t1", "t2");
        }

        @Test
        void filterByWithoutTenantId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setWithoutTenantId(true);

            tools.queryEventSubscriptions(dto);

            verify(query).withoutTenantId();
        }

        @Test
        void filterByIncludeEventSubscriptionsWithoutTenantId() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setIncludeEventSubscriptionsWithoutTenantId(true);

            tools.queryEventSubscriptions(dto);

            verify(query).includeEventSubscriptionsWithoutTenantId();
        }

        @Test
        void resultMapping() throws JsonProcessingException {
            EventSubscription es = mock(EventSubscription.class);
            when(es.getId()).thenReturn("es-1");
            when(es.getEventType()).thenReturn("message");
            when(es.getEventName()).thenReturn("orderReceived");
            when(es.getExecutionId()).thenReturn("exec-1");
            when(es.getProcessInstanceId()).thenReturn("pi-1");
            when(es.getActivityId()).thenReturn("receiveTask1");
            when(es.getTenantId()).thenReturn("t1");
            Date created = new Date();
            when(es.getCreated()).thenReturn(created);
            when(query.list()).thenReturn(List.of(es));

            String result = tools.queryEventSubscriptions(new EventSubscriptionQueryDto());

            List<EventSubscriptionResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<EventSubscriptionResultDto>>() {});
            assertEquals(1, parsed.size());
            assertEquals("es-1", parsed.get(0).getId());
            assertEquals("message", parsed.get(0).getEventType());
            assertEquals("orderReceived", parsed.get(0).getEventName());
        }
    }

    // ========================================================================
    // Variable Instance Query Tests
    // ========================================================================

    @Nested
    class QueryVariableInstances {

        @Mock(answer = Answers.RETURNS_SELF)
        private VariableInstanceQuery query;

        @BeforeEach
        void setUp() {
            when(runtimeService.createVariableInstanceQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters_andDisablesBinaryFetching() {
            when(query.list()).thenReturn(Collections.emptyList());

            String result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            assertEquals("[]", result);
            verify(query).disableBinaryFetching();
            verify(query).list();
        }

        @Test
        void filterByVariableName() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableName("orderId");

            tools.queryVariableInstances(dto);

            verify(query).variableName("orderId");
        }

        @Test
        void filterByVariableNameLike() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableNameLike("order%");

            tools.queryVariableInstances(dto);

            verify(query).variableNameLike("order%");
        }

        @Test
        void filterByVariableNameIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableNameIn(List.of("orderId", "status"));

            tools.queryVariableInstances(dto);

            verify(query).variableNameIn("orderId", "status");
        }

        @Test
        void filterByProcessInstanceIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setProcessInstanceIdIn(List.of("pi-1", "pi-2"));

            tools.queryVariableInstances(dto);

            verify(query).processInstanceIdIn("pi-1", "pi-2");
        }

        @Test
        void filterByExecutionIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setExecutionIdIn(List.of("exec-1"));

            tools.queryVariableInstances(dto);

            verify(query).executionIdIn("exec-1");
        }

        @Test
        void filterByCaseInstanceIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setCaseInstanceIdIn(List.of("case-1"));

            tools.queryVariableInstances(dto);

            verify(query).caseInstanceIdIn("case-1");
        }

        @Test
        void filterByCaseExecutionIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setCaseExecutionIdIn(List.of("ce-1"));

            tools.queryVariableInstances(dto);

            verify(query).caseExecutionIdIn("ce-1");
        }

        @Test
        void filterByTaskIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setTaskIdIn(List.of("task-1"));

            tools.queryVariableInstances(dto);

            verify(query).taskIdIn("task-1");
        }

        @Test
        void filterByBatchIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setBatchIdIn(List.of("batch-1"));

            tools.queryVariableInstances(dto);

            verify(query).batchIdIn("batch-1");
        }

        @Test
        void filterByActivityInstanceIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setActivityInstanceIdIn(List.of("ai-1"));

            tools.queryVariableInstances(dto);

            verify(query).activityInstanceIdIn("ai-1");
        }

        @Test
        void filterByVariableScopeIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableScopeIdIn(List.of("scope-1"));

            tools.queryVariableInstances(dto);

            verify(query).variableScopeIdIn("scope-1");
        }

        @Test
        void filterByTenantIdIn() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setTenantIdIn(List.of("t1"));

            tools.queryVariableInstances(dto);

            verify(query).tenantIdIn("t1");
        }

        @Test
        void filterByVariableNamesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableNamesIgnoreCase(true);

            tools.queryVariableInstances(dto);

            verify(query).matchVariableNamesIgnoreCase();
        }

        @Test
        void filterByVariableValuesIgnoreCase() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableValuesIgnoreCase(true);

            tools.queryVariableInstances(dto);

            verify(query).matchVariableValuesIgnoreCase();
        }

        @Test
        void emptyLists_doNotApplyFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setProcessInstanceIdIn(Collections.emptyList());
            dto.setExecutionIdIn(Collections.emptyList());
            dto.setTaskIdIn(Collections.emptyList());
            dto.setTenantIdIn(Collections.emptyList());

            tools.queryVariableInstances(dto);

            verify(query, never()).processInstanceIdIn(any(String[].class));
            verify(query, never()).executionIdIn(any(String[].class));
            verify(query, never()).taskIdIn(any(String[].class));
            verify(query, never()).tenantIdIn(any(String[].class));
        }

        @Test
        void resultMapping() throws JsonProcessingException {
            VariableInstance var = mock(VariableInstance.class);
            when(var.getId()).thenReturn("var-1");
            when(var.getName()).thenReturn("orderId");
            when(var.getValue()).thenReturn("ORD-123");
            when(var.getTypeName()).thenReturn("string");
            when(var.getProcessInstanceId()).thenReturn("pi-1");
            when(var.getExecutionId()).thenReturn("exec-1");
            when(var.getTenantId()).thenReturn("t1");
            when(query.list()).thenReturn(List.of(var));

            String result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            List<VariableInstanceResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<VariableInstanceResultDto>>() {});
            assertEquals(1, parsed.size());
            assertEquals("var-1", parsed.get(0).getId());
            assertEquals("orderId", parsed.get(0).getName());
            assertEquals("ORD-123", parsed.get(0).getValue());
            assertEquals("string", parsed.get(0).getTypeName());
            assertEquals("pi-1", parsed.get(0).getProcessInstanceId());
        }

        @Test
        void resultMapping_variableValueError() throws JsonProcessingException {
            VariableInstance var = mock(VariableInstance.class);
            when(var.getId()).thenReturn("var-1");
            when(var.getName()).thenReturn("binaryData");
            when(var.getValue()).thenThrow(new RuntimeException("Cannot deserialize"));
            when(var.getTypeName()).thenReturn("bytes");
            when(query.list()).thenReturn(List.of(var));

            String result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            List<VariableInstanceResultDto> parsed = objectMapper.readValue(result,
                    new TypeReference<List<VariableInstanceResultDto>>() {});
            assertEquals(1, parsed.size());
            assertNull(parsed.get(0).getValue());
            assertTrue(parsed.get(0).getErrorMessage().contains("Cannot deserialize"));
        }
    }

    // ========================================================================
    // Helpers
    // ========================================================================

    private ProcessInstance mockProcessInstance(String id, String defId, String businessKey,
                                                String rootId, String caseId, boolean suspended, String tenantId) {
        ProcessInstance pi = mock(ProcessInstance.class);
        when(pi.getId()).thenReturn(id);
        when(pi.getProcessDefinitionId()).thenReturn(defId);
        when(pi.getBusinessKey()).thenReturn(businessKey);
        when(pi.getRootProcessInstanceId()).thenReturn(rootId);
        when(pi.getCaseInstanceId()).thenReturn(caseId);
        when(pi.isSuspended()).thenReturn(suspended);
        when(pi.getTenantId()).thenReturn(tenantId);
        return pi;
    }

    private void assertContains(String json, String expected) {
        assertTrue(json.contains(expected), "Expected JSON to contain '" + expected + "' but was: " + json);
    }
}
