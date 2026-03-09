package org.finos.fluxnova.ai.mcp.query.tools;

import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
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
    private RuntimeService runtimeService;

    private RuntimeQueryMcpTools tools;

    @BeforeEach
    void setUp() {
        tools = new RuntimeQueryMcpTools(runtimeService);
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

            List<ProcessInstanceResultDto> result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
            verify(query, never()).processInstanceId(any());
            verify(query, never()).processDefinitionKey(any());
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setProcessInstanceId("pi-1");
            dto.setProcessInstanceIds(Set.of("pi-1", "pi-2"));
            dto.setBusinessKey("order-123");
            dto.setBusinessKeyLike("order-%");
            dto.setProcessDefinitionKey("myProcess");
            dto.setProcessDefinitionKeyIn(List.of("proc1", "proc2"));
            dto.setProcessDefinitionKeyNotIn(List.of("excluded1"));
            dto.setProcessDefinitionId("def:1:abc");
            dto.setDeploymentId("deploy-1");
            dto.setSuperProcessInstanceId("super-1");
            dto.setSubProcessInstanceId("sub-1");
            dto.setCaseInstanceId("case-1");
            dto.setSuperCaseInstanceId("super-case-1");
            dto.setSubCaseInstanceId("sub-case-1");
            dto.setActive(true);
            dto.setSuspended(true);
            dto.setWithIncident(true);
            dto.setIncidentId("inc-1");
            dto.setIncidentType("failedJob");
            dto.setIncidentMessage("Something went wrong");
            dto.setIncidentMessageLike("%error%");
            dto.setTenantIdIn(List.of("t1", "t2"));
            dto.setWithoutTenantId(true);
            dto.setProcessDefinitionWithoutTenantId(true);
            dto.setActivityIdIn(List.of("act1", "act2"));
            dto.setRootProcessInstances(true);
            dto.setLeafProcessInstances(true);
            dto.setVariableNamesIgnoreCase(true);
            dto.setVariableValuesIgnoreCase(true);

            tools.queryProcessInstances(dto);

            verify(query).processInstanceId("pi-1");
            verify(query).processInstanceIds(Set.of("pi-1", "pi-2"));
            verify(query).processInstanceBusinessKey("order-123");
            verify(query).processInstanceBusinessKeyLike("order-%");
            verify(query).processDefinitionKey("myProcess");
            verify(query).processDefinitionKeyIn("proc1", "proc2");
            verify(query).processDefinitionKeyNotIn("excluded1");
            verify(query).processDefinitionId("def:1:abc");
            verify(query).deploymentId("deploy-1");
            verify(query).superProcessInstanceId("super-1");
            verify(query).subProcessInstanceId("sub-1");
            verify(query).caseInstanceId("case-1");
            verify(query).superCaseInstanceId("super-case-1");
            verify(query).subCaseInstanceId("sub-case-1");
            verify(query).active();
            verify(query).suspended();
            verify(query).withIncident();
            verify(query).incidentId("inc-1");
            verify(query).incidentType("failedJob");
            verify(query).incidentMessage("Something went wrong");
            verify(query).incidentMessageLike("%error%");
            verify(query).tenantIdIn("t1", "t2");
            verify(query).withoutTenantId();
            verify(query).processDefinitionWithoutTenantId();
            verify(query).activityIdIn("act1", "act2");
            verify(query).rootProcessInstances();
            verify(query).leafProcessInstances();
            verify(query).matchVariableNamesIgnoreCase();
            verify(query).matchVariableValuesIgnoreCase();
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
            dto.setActive(false);
            dto.setSuspended(null);
            dto.setWithIncident(false);
            dto.setTenantIdIn(Collections.emptyList());

            tools.queryProcessInstances(dto);

            verify(query, never()).active();
            verify(query, never()).suspended();
            verify(query, never()).withIncident();
            verify(query, never()).tenantIdIn(any(String[].class));
        }

        @Test
        void resultMapping() {
            ProcessInstance pi = mockProcessInstance("pi-1", "def:1:abc", "order-123", "root-1", "case-1", true, "tenant-a");
            when(query.list()).thenReturn(List.of(pi));

            List<ProcessInstanceResultDto> result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            assertEquals(1, result.size());
            ProcessInstanceResultDto r = result.get(0);
            assertEquals("pi-1", r.getId());
            assertEquals("def:1:abc", r.getProcessDefinitionId());
            assertEquals("order-123", r.getBusinessKey());
            assertEquals("root-1", r.getRootProcessInstanceId());
            assertEquals("case-1", r.getCaseInstanceId());
            assertTrue(r.isSuspended());
            assertEquals("tenant-a", r.getTenantId());
        }

        @Test
        void multipleResults() {
            ProcessInstance pi1 = mockProcessInstance("pi-1", "def-1", null, null, null, false, null);
            ProcessInstance pi2 = mockProcessInstance("pi-2", "def-2", "bk-2", null, null, true, "t1");
            when(query.list()).thenReturn(List.of(pi1, pi2));

            List<ProcessInstanceResultDto> result = tools.queryProcessInstances(new ProcessInstanceQueryDto());

            assertEquals(2, result.size());
            assertEquals("pi-1", result.get(0).getId());
            assertEquals("pi-2", result.get(1).getId());
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

            List<ExecutionResultDto> result = tools.queryExecutions(new ExecutionQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setExecutionId("exec-1");
            dto.setProcessInstanceId("pi-1");
            dto.setBusinessKey("bk-1");
            dto.setProcessDefinitionId("def-1");
            dto.setProcessDefinitionKey("myProcess");
            dto.setActivityId("task1");
            dto.setSignalEventSubscriptionName("mySignal");
            dto.setMessageEventSubscriptionName("myMessage");
            dto.setActive(true);
            dto.setSuspended(true);
            dto.setIncidentId("inc-1");
            dto.setIncidentType("failedJob");
            dto.setIncidentMessage("Error");
            dto.setIncidentMessageLike("%error%");
            dto.setTenantIdIn(List.of("t1"));
            dto.setWithoutTenantId(true);
            dto.setVariableNamesIgnoreCase(true);
            dto.setVariableValuesIgnoreCase(true);

            tools.queryExecutions(dto);

            verify(query).executionId("exec-1");
            verify(query).processInstanceId("pi-1");
            verify(query).processInstanceBusinessKey("bk-1");
            verify(query).processDefinitionId("def-1");
            verify(query).processDefinitionKey("myProcess");
            verify(query).activityId("task1");
            verify(query).signalEventSubscriptionName("mySignal");
            verify(query).messageEventSubscriptionName("myMessage");
            verify(query).active();
            verify(query).suspended();
            verify(query).incidentId("inc-1");
            verify(query).incidentType("failedJob");
            verify(query).incidentMessage("Error");
            verify(query).incidentMessageLike("%error%");
            verify(query).tenantIdIn("t1");
            verify(query).withoutTenantId();
            verify(query).matchVariableNamesIgnoreCase();
            verify(query).matchVariableValuesIgnoreCase();
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            ExecutionQueryDto dto = new ExecutionQueryDto();
            dto.setActive(false);
            dto.setSuspended(null);

            tools.queryExecutions(dto);

            verify(query, never()).active();
            verify(query, never()).suspended();
        }

        @Test
        void resultMapping() {
            Execution exec = mock(Execution.class);
            when(exec.getId()).thenReturn("exec-1");
            when(exec.getProcessInstanceId()).thenReturn("pi-1");
            when(exec.isSuspended()).thenReturn(false);
            when(exec.isEnded()).thenReturn(false);
            when(exec.getTenantId()).thenReturn("t1");
            when(query.list()).thenReturn(List.of(exec));

            List<ExecutionResultDto> result = tools.queryExecutions(new ExecutionQueryDto());

            assertEquals(1, result.size());
            assertEquals("exec-1", result.get(0).getId());
            assertEquals("pi-1", result.get(0).getProcessInstanceId());
            assertFalse(result.get(0).isSuspended());
            assertEquals("t1", result.get(0).getTenantId());
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

            List<IncidentResultDto> result = tools.queryIncidents(new IncidentQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date before = new Date();
            Date after = new Date();

            IncidentQueryDto dto = new IncidentQueryDto();
            dto.setIncidentId("inc-1");
            dto.setIncidentType("failedJob");
            dto.setIncidentMessage("Error");
            dto.setIncidentMessageLike("%error%");
            dto.setProcessDefinitionId("def-1");
            dto.setProcessDefinitionKeyIn(List.of("proc1", "proc2"));
            dto.setProcessInstanceId("pi-1");
            dto.setExecutionId("exec-1");
            dto.setIncidentTimestampBefore(before);
            dto.setIncidentTimestampAfter(after);
            dto.setActivityId("act-1");
            dto.setFailedActivityId("failed-act-1");
            dto.setCauseIncidentId("cause-1");
            dto.setRootCauseIncidentId("root-cause-1");
            dto.setConfiguration("config-1");
            dto.setTenantIdIn(List.of("t1"));
            dto.setJobDefinitionIdIn(List.of("jd-1", "jd-2"));

            tools.queryIncidents(dto);

            verify(query).incidentId("inc-1");
            verify(query).incidentType("failedJob");
            verify(query).incidentMessage("Error");
            verify(query).incidentMessageLike("%error%");
            verify(query).processDefinitionId("def-1");
            verify(query).processDefinitionKeyIn("proc1", "proc2");
            verify(query).processInstanceId("pi-1");
            verify(query).executionId("exec-1");
            verify(query).incidentTimestampBefore(before);
            verify(query).incidentTimestampAfter(after);
            verify(query).activityId("act-1");
            verify(query).failedActivityId("failed-act-1");
            verify(query).causeIncidentId("cause-1");
            verify(query).rootCauseIncidentId("root-cause-1");
            verify(query).configuration("config-1");
            verify(query).tenantIdIn("t1");
            verify(query).jobDefinitionIdIn("jd-1", "jd-2");
        }

        @Test
        void resultMapping() {
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

            List<IncidentResultDto> result = tools.queryIncidents(new IncidentQueryDto());

            assertEquals(1, result.size());
            assertEquals("inc-1", result.get(0).getId());
            assertEquals("failedJob", result.get(0).getIncidentType());
            assertEquals("Error occurred", result.get(0).getIncidentMessage());
            assertEquals("pi-1", result.get(0).getProcessInstanceId());
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

            List<EventSubscriptionResultDto> result = tools.queryEventSubscriptions(new EventSubscriptionQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            EventSubscriptionQueryDto dto = new EventSubscriptionQueryDto();
            dto.setEventSubscriptionId("es-1");
            dto.setEventName("myEvent");
            dto.setEventType("message");
            dto.setExecutionId("exec-1");
            dto.setProcessInstanceId("pi-1");
            dto.setActivityId("act-1");
            dto.setTenantIdIn(List.of("t1", "t2"));
            dto.setWithoutTenantId(true);
            dto.setIncludeEventSubscriptionsWithoutTenantId(true);

            tools.queryEventSubscriptions(dto);

            verify(query).eventSubscriptionId("es-1");
            verify(query).eventName("myEvent");
            verify(query).eventType("message");
            verify(query).executionId("exec-1");
            verify(query).processInstanceId("pi-1");
            verify(query).activityId("act-1");
            verify(query).tenantIdIn("t1", "t2");
            verify(query).withoutTenantId();
            verify(query).includeEventSubscriptionsWithoutTenantId();
        }

        @Test
        void resultMapping() {
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

            List<EventSubscriptionResultDto> result = tools.queryEventSubscriptions(new EventSubscriptionQueryDto());

            assertEquals(1, result.size());
            assertEquals("es-1", result.get(0).getId());
            assertEquals("message", result.get(0).getEventType());
            assertEquals("orderReceived", result.get(0).getEventName());
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

            List<VariableInstanceResultDto> result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            assertTrue(result.isEmpty());
            verify(query).disableBinaryFetching();
            verify(query).list();
        }

        @Test
        void allFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            VariableInstanceQueryDto dto = new VariableInstanceQueryDto();
            dto.setVariableName("orderId");
            dto.setVariableNameLike("order%");
            dto.setVariableNameIn(List.of("orderId", "status"));
            dto.setProcessInstanceIdIn(List.of("pi-1", "pi-2"));
            dto.setExecutionIdIn(List.of("exec-1"));
            dto.setCaseInstanceIdIn(List.of("case-1"));
            dto.setCaseExecutionIdIn(List.of("ce-1"));
            dto.setTaskIdIn(List.of("task-1"));
            dto.setBatchIdIn(List.of("batch-1"));
            dto.setActivityInstanceIdIn(List.of("ai-1"));
            dto.setVariableScopeIdIn(List.of("scope-1"));
            dto.setTenantIdIn(List.of("t1"));
            dto.setVariableNamesIgnoreCase(true);
            dto.setVariableValuesIgnoreCase(true);

            tools.queryVariableInstances(dto);

            verify(query).variableName("orderId");
            verify(query).variableNameLike("order%");
            verify(query).variableNameIn("orderId", "status");
            verify(query).processInstanceIdIn("pi-1", "pi-2");
            verify(query).executionIdIn("exec-1");
            verify(query).caseInstanceIdIn("case-1");
            verify(query).caseExecutionIdIn("ce-1");
            verify(query).taskIdIn("task-1");
            verify(query).batchIdIn("batch-1");
            verify(query).activityInstanceIdIn("ai-1");
            verify(query).variableScopeIdIn("scope-1");
            verify(query).tenantIdIn("t1");
            verify(query).matchVariableNamesIgnoreCase();
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
        void resultMapping() {
            VariableInstance var = mock(VariableInstance.class);
            when(var.getId()).thenReturn("var-1");
            when(var.getName()).thenReturn("orderId");
            when(var.getValue()).thenReturn("ORD-123");
            when(var.getTypeName()).thenReturn("string");
            when(var.getProcessInstanceId()).thenReturn("pi-1");
            when(var.getExecutionId()).thenReturn("exec-1");
            when(var.getTenantId()).thenReturn("t1");
            when(query.list()).thenReturn(List.of(var));

            List<VariableInstanceResultDto> result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            assertEquals(1, result.size());
            assertEquals("var-1", result.get(0).getId());
            assertEquals("orderId", result.get(0).getName());
            assertEquals("ORD-123", result.get(0).getValue());
            assertEquals("string", result.get(0).getTypeName());
            assertEquals("pi-1", result.get(0).getProcessInstanceId());
        }

        @Test
        void resultMapping_variableValueError() {
            VariableInstance var = mock(VariableInstance.class);
            when(var.getId()).thenReturn("var-1");
            when(var.getName()).thenReturn("binaryData");
            when(var.getValue()).thenThrow(new RuntimeException("Cannot deserialize"));
            when(var.getTypeName()).thenReturn("bytes");
            when(query.list()).thenReturn(List.of(var));

            List<VariableInstanceResultDto> result = tools.queryVariableInstances(new VariableInstanceQueryDto());

            assertEquals(1, result.size());
            assertNull(result.get(0).getValue());
            assertTrue(result.get(0).getErrorMessage().contains("Cannot deserialize"));
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

}
