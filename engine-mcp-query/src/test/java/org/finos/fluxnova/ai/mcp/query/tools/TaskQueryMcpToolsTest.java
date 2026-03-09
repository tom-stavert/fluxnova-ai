package org.finos.fluxnova.ai.mcp.query.tools;

import org.finos.fluxnova.ai.mcp.query.model.dto.*;
import org.finos.fluxnova.ai.mcp.query.model.query.*;
import org.finos.fluxnova.bpm.engine.TaskService;
import org.finos.fluxnova.bpm.engine.task.*;
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
class TaskQueryMcpToolsTest {

    @Mock
    private TaskService taskService;

    private TaskQueryMcpTools tools;

    @BeforeEach
    void setUp() {
        tools = new TaskQueryMcpTools(taskService);
    }

    // ========================================================================
    // Task Query Tests
    // ========================================================================

    @Nested
    class QueryTasks {

        @Mock(answer = Answers.RETURNS_SELF)
        private TaskQuery query;

        @BeforeEach
        void setUp() {
            when(taskService.createTaskQuery()).thenReturn(query);
        }

        @Test
        void emptyDto_callsListWithNoFilters() {
            when(query.list()).thenReturn(Collections.emptyList());

            List<TaskResultDto> result = tools.queryTasks(new TaskQueryDto());

            assertTrue(result.isEmpty());
            verify(query).list();
            verify(query, never()).taskId(any());
            verify(query, never()).taskAssignee(any());
        }

        @Test
        void stringFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            TaskQueryDto dto = new TaskQueryDto();
            dto.setTaskId("task-1");
            dto.setProcessInstanceId("pi-1");
            dto.setProcessInstanceBusinessKey("order-123");
            dto.setProcessInstanceBusinessKeyLike("order-%");
            dto.setProcessDefinitionId("def-1");
            dto.setProcessDefinitionKey("invoiceProcess");
            dto.setProcessDefinitionName("Invoice Process");
            dto.setProcessDefinitionNameLike("Invoice%");
            dto.setExecutionId("exec-1");
            dto.setAssignee("john");
            dto.setAssigneeLike("jo%");
            dto.setOwner("jane");
            dto.setCandidateGroup("managers");
            dto.setCandidateUser("john");
            dto.setInvolvedUser("john");
            dto.setTaskDefinitionKey("approveInvoice");
            dto.setTaskDefinitionKeyLike("approve%");
            dto.setName("Approve Invoice");
            dto.setNameNotEqual("Reject Invoice");
            dto.setNameLike("Approve%");
            dto.setNameNotLike("Reject%");
            dto.setDescription("Please approve");
            dto.setDescriptionLike("approve%");
            dto.setParentTaskId("parent-1");
            dto.setCaseInstanceId("case-1");
            dto.setCaseInstanceBusinessKey("case-bk-1");
            dto.setCaseInstanceBusinessKeyLike("case-%");
            dto.setCaseDefinitionId("caseDef-1");
            dto.setCaseDefinitionKey("myCaseDef");
            dto.setCaseDefinitionName("My Case");
            dto.setCaseDefinitionNameLike("My%");
            dto.setCaseExecutionId("caseExec-1");

            tools.queryTasks(dto);

            verify(query).taskId("task-1");
            verify(query).processInstanceId("pi-1");
            verify(query).processInstanceBusinessKey("order-123");
            verify(query).processInstanceBusinessKeyLike("order-%");
            verify(query).processDefinitionId("def-1");
            verify(query).processDefinitionKey("invoiceProcess");
            verify(query).processDefinitionName("Invoice Process");
            verify(query).processDefinitionNameLike("Invoice%");
            verify(query).executionId("exec-1");
            verify(query).taskAssignee("john");
            verify(query).taskAssigneeLike("jo%");
            verify(query).taskOwner("jane");
            verify(query).taskCandidateGroup("managers");
            verify(query).taskCandidateUser("john");
            verify(query).taskInvolvedUser("john");
            verify(query).taskDefinitionKey("approveInvoice");
            verify(query).taskDefinitionKeyLike("approve%");
            verify(query).taskName("Approve Invoice");
            verify(query).taskNameNotEqual("Reject Invoice");
            verify(query).taskNameLike("Approve%");
            verify(query).taskNameNotLike("Reject%");
            verify(query).taskDescription("Please approve");
            verify(query).taskDescriptionLike("approve%");
            verify(query).taskParentTaskId("parent-1");
            verify(query).caseInstanceId("case-1");
            verify(query).caseInstanceBusinessKey("case-bk-1");
            verify(query).caseInstanceBusinessKeyLike("case-%");
            verify(query).caseDefinitionId("caseDef-1");
            verify(query).caseDefinitionKey("myCaseDef");
            verify(query).caseDefinitionName("My Case");
            verify(query).caseDefinitionNameLike("My%");
            verify(query).caseExecutionId("caseExec-1");
        }

        @Test
        void listFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            TaskQueryDto dto = new TaskQueryDto();
            dto.setTaskIdIn(List.of("t1", "t2"));
            dto.setProcessInstanceIdIn(List.of("pi-1", "pi-2"));
            dto.setProcessInstanceBusinessKeyIn(List.of("bk-1", "bk-2"));
            dto.setProcessDefinitionKeyIn(List.of("proc1", "proc2"));
            dto.setAssigneeIn(List.of("john", "jane"));
            dto.setCandidateGroups(List.of("managers", "admins"));
            dto.setTaskDefinitionKeyIn(List.of("approve", "review"));
            dto.setTenantIdIn(List.of("t1", "t2"));

            tools.queryTasks(dto);

            verify(query).taskIdIn("t1", "t2");
            verify(query).processInstanceIdIn("pi-1", "pi-2");
            verify(query).processInstanceBusinessKeyIn("bk-1", "bk-2");
            verify(query).processDefinitionKeyIn("proc1", "proc2");
            verify(query).taskAssigneeIn("john", "jane");
            verify(query).taskCandidateGroupIn(List.of("managers", "admins"));
            verify(query).taskDefinitionKeyIn("approve", "review");
            verify(query).tenantIdIn("t1", "t2");
        }

        @Test
        void integerAndDateFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());
            Date dueDate = new Date();
            Date dueAfter = new Date();
            Date dueBefore = new Date();
            Date followUpDate = new Date();
            Date followUpAfter = new Date();
            Date followUpBefore = new Date();
            Date followUpBeforeOrNotExistent = new Date();
            Date createdOn = new Date();
            Date createdAfter = new Date();
            Date createdBefore = new Date();
            Date updatedAfter = new Date();

            TaskQueryDto dto = new TaskQueryDto();
            dto.setPriority(5);
            dto.setMaxPriority(10);
            dto.setMinPriority(1);
            dto.setDueDate(dueDate);
            dto.setDueAfter(dueAfter);
            dto.setDueBefore(dueBefore);
            dto.setFollowUpDate(followUpDate);
            dto.setFollowUpAfter(followUpAfter);
            dto.setFollowUpBefore(followUpBefore);
            dto.setFollowUpBeforeOrNotExistent(followUpBeforeOrNotExistent);
            dto.setCreatedOn(createdOn);
            dto.setCreatedAfter(createdAfter);
            dto.setCreatedBefore(createdBefore);
            dto.setUpdatedAfter(updatedAfter);

            tools.queryTasks(dto);

            verify(query).taskPriority(5);
            verify(query).taskMaxPriority(10);
            verify(query).taskMinPriority(1);
            verify(query).dueDate(dueDate);
            verify(query).dueAfter(dueAfter);
            verify(query).dueBefore(dueBefore);
            verify(query).followUpDate(followUpDate);
            verify(query).followUpAfter(followUpAfter);
            verify(query).followUpBefore(followUpBefore);
            verify(query).followUpBeforeOrNotExistent(followUpBeforeOrNotExistent);
            verify(query).taskCreatedOn(createdOn);
            verify(query).taskCreatedAfter(createdAfter);
            verify(query).taskCreatedBefore(createdBefore);
            verify(query).taskUpdatedAfter(updatedAfter);
        }

        @Test
        void booleanFiltersApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            TaskQueryDto dto = new TaskQueryDto();
            dto.setAssigned(true);
            dto.setUnassigned(true);
            dto.setWithoutDueDate(true);
            dto.setWithCandidateGroups(true);
            dto.setWithoutCandidateGroups(true);
            dto.setWithCandidateUsers(true);
            dto.setWithoutCandidateUsers(true);
            dto.setActive(true);
            dto.setSuspended(true);
            dto.setWithoutTenantId(true);
            dto.setExcludeSubtasks(true);

            tools.queryTasks(dto);

            verify(query).taskAssigned();
            verify(query).taskUnassigned();
            verify(query).withoutDueDate();
            verify(query).withCandidateGroups();
            verify(query).withoutCandidateGroups();
            verify(query).withCandidateUsers();
            verify(query).withoutCandidateUsers();
            verify(query).active();
            verify(query).suspended();
            verify(query).withoutTenantId();
            verify(query).excludeSubtasks();
        }

        @Test
        void delegationStateFilter() {
            when(query.list()).thenReturn(Collections.emptyList());

            TaskQueryDto dto = new TaskQueryDto();
            dto.setDelegationState("PENDING");

            tools.queryTasks(dto);

            verify(query).taskDelegationState(DelegationState.PENDING);
        }

        @Test
        void booleanFalseAndNull_notApplied() {
            when(query.list()).thenReturn(Collections.emptyList());

            TaskQueryDto dto = new TaskQueryDto();
            dto.setActive(false);
            dto.setSuspended(null);
            dto.setAssigned(false);
            dto.setUnassigned(null);
            dto.setWithoutDueDate(false);
            dto.setExcludeSubtasks(null);
            dto.setTenantIdIn(Collections.emptyList());
            dto.setTaskIdIn(Collections.emptyList());
            dto.setProcessInstanceIdIn(Collections.emptyList());

            tools.queryTasks(dto);

            verify(query, never()).active();
            verify(query, never()).suspended();
            verify(query, never()).taskAssigned();
            verify(query, never()).taskUnassigned();
            verify(query, never()).withoutDueDate();
            verify(query, never()).excludeSubtasks();
            verify(query, never()).tenantIdIn(any(String[].class));
            verify(query, never()).taskIdIn(any(String[].class));
            verify(query, never()).processInstanceIdIn(any(String[].class));
        }

        @Test
        void resultMapping() {
            Task task = mock(Task.class);
            when(task.getId()).thenReturn("task-1");
            when(task.getName()).thenReturn("Approve Invoice");
            when(task.getAssignee()).thenReturn("john");
            when(task.getOwner()).thenReturn("jane");
            Date created = new Date();
            when(task.getCreateTime()).thenReturn(created);
            Date lastUpdated = new Date();
            when(task.getLastUpdated()).thenReturn(lastUpdated);
            Date due = new Date();
            when(task.getDueDate()).thenReturn(due);
            Date followUp = new Date();
            when(task.getFollowUpDate()).thenReturn(followUp);
            when(task.getDelegationState()).thenReturn(DelegationState.PENDING);
            when(task.getDescription()).thenReturn("Please approve this invoice");
            when(task.getExecutionId()).thenReturn("exec-1");
            when(task.getParentTaskId()).thenReturn("parent-1");
            when(task.getPriority()).thenReturn(50);
            when(task.getProcessDefinitionId()).thenReturn("def:1:abc");
            when(task.getProcessInstanceId()).thenReturn("pi-1");
            when(task.getCaseExecutionId()).thenReturn("caseExec-1");
            when(task.getCaseDefinitionId()).thenReturn("caseDef-1");
            when(task.getCaseInstanceId()).thenReturn("case-1");
            when(task.getTaskDefinitionKey()).thenReturn("approveInvoice");
            when(task.isSuspended()).thenReturn(false);
            when(task.getFormKey()).thenReturn("embedded:app:approve-form.html");
            when(task.getTenantId()).thenReturn("t1");
            when(task.getTaskState()).thenReturn("Created");
            when(query.list()).thenReturn(List.of(task));

            List<TaskResultDto> result = tools.queryTasks(new TaskQueryDto());

            assertEquals(1, result.size());
            TaskResultDto r = result.get(0);
            assertEquals("task-1", r.getId());
            assertEquals("Approve Invoice", r.getName());
            assertEquals("john", r.getAssignee());
            assertEquals("jane", r.getOwner());
            assertEquals(created, r.getCreated());
            assertEquals(lastUpdated, r.getLastUpdated());
            assertEquals(due, r.getDue());
            assertEquals(followUp, r.getFollowUp());
            assertEquals("PENDING", r.getDelegationState());
            assertEquals("Please approve this invoice", r.getDescription());
            assertEquals("exec-1", r.getExecutionId());
            assertEquals("parent-1", r.getParentTaskId());
            assertEquals(50, r.getPriority());
            assertEquals("def:1:abc", r.getProcessDefinitionId());
            assertEquals("pi-1", r.getProcessInstanceId());
            assertEquals("caseExec-1", r.getCaseExecutionId());
            assertEquals("caseDef-1", r.getCaseDefinitionId());
            assertEquals("case-1", r.getCaseInstanceId());
            assertEquals("approveInvoice", r.getTaskDefinitionKey());
            assertFalse(r.isSuspended());
            assertEquals("embedded:app:approve-form.html", r.getFormKey());
            assertEquals("t1", r.getTenantId());
            assertEquals("Created", r.getTaskState());
        }

        @Test
        void resultMapping_nullDelegationState() {
            Task task = mock(Task.class);
            when(task.getId()).thenReturn("task-1");
            when(task.getDelegationState()).thenReturn(null);
            when(query.list()).thenReturn(List.of(task));

            List<TaskResultDto> result = tools.queryTasks(new TaskQueryDto());

            assertEquals(1, result.size());
            assertNull(result.get(0).getDelegationState());
        }
    }
}
