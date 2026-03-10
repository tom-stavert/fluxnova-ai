# engine-mcp-query

A read-only [MCP](https://modelcontextprotocol.io/) server extension for the [Fluxnova](https://github.com/finos/fluxnova) process engine. It exposes process engine query functionality as MCP tools, allowing LLM-based agents to inspect and monitor running workflows without being able to modify them.

## Overview

`engine-mcp-query` interacts directly with the Fluxnova process engine Query API to provide safe, read-only access to runtime data. It does **not** depend on the `engine-rest` module — all queries are executed in-process via the engine's Java services.

### Available Tools

The extension ships tool components covering three engine services:

#### RuntimeService (`RuntimeQueryMcpTools`)

| Tool                      | Description                                                                                                 |
| ------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `queryProcessInstances`   | Find active or suspended process instances by definition, business key, tenant, incident status, and more.  |
| `queryExecutions`         | Inspect execution paths within process instances, including those waiting for signals or messages.          |
| `queryIncidents`          | Diagnose process execution failures such as failed jobs or failed external tasks.                           |
| `queryEventSubscriptions` | Find which process instances are waiting for specific message, signal, compensation, or conditional events. |
| `queryVariableInstances`  | Inspect the current values of process variables across instances, executions, or tasks.                     |

#### RepositoryService (`RepositoryQueryMcpTools`)

| Tool                       | Description                                                                                    |
| -------------------------- | ---------------------------------------------------------------------------------------------- |
| `queryProcessDefinitions`  | Discover available workflow templates, find specific versions, or check deployment status.      |
| `queryDeployments`         | List deployments by name, source, tenant, or date range.                                       |

#### TaskService (`TaskQueryMcpTools`)

| Tool         | Description                                                                                                        |
| ------------ | ------------------------------------------------------------------------------------------------------------------ |
| `queryTasks` | Find user tasks by assignee, candidate group, process context, priority, due dates, delegation state, and more.    |

Each tool accepts a query DTO with optional filter criteria and an optional `maxResults` parameter to control the number of results returned. Results are serialized to JSON.

## Requirements

- Java 21+
- Fluxnova BPM Engine 1.0.0+
- Spring Boot 3.5+
- Spring AI 1.1+

## Installation

Add the dependency to your Fluxnova Spring Boot application:

```xml
<dependency>
    <groupId>org.finos.fluxnova.ai.mcp</groupId>
    <artifactId>engine-mcp-query</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

The extension uses Spring Boot auto-configuration. Once the JAR is on the classpath, the `QueryMcpAutoConfiguration` class is detected automatically via `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.

No additional configuration is required — the extension picks up the engine service beans already present in your Fluxnova application context.

## How It Works

1. **Auto-configuration** component-scans the extension's packages.
2. **Tool components** (`RuntimeQueryMcpTools`, `RepositoryQueryMcpTools`, `TaskQueryMcpTools`) are Spring `@Component` classes that inject their respective engine service and expose `@McpTool`-annotated methods.
3. Each tool method:
   - Accepts a query DTO (e.g. `ProcessInstanceQueryDto`) describing the filter criteria, and an optional `maxResults` parameter.
   - Builds a native engine query (`RuntimeService.createProcessInstanceQuery()`, etc.) by applying only the non-null filters from the DTO.
   - Applies a result limit: `maxResults` if provided (capped at the configured maximum), otherwise the configured default.
   - Maps the engine entity results into lightweight result DTOs and serializes them to JSON.

## Configuration

The extension supports the following application properties:

| Property | Default | Description |
| --- | --- | --- |
| `fluxnova.mcp.query.max-results` | `200` | Maximum number of results any tool call can return. Individual tool calls may request fewer via the `maxResults` tool parameter, but this value acts as an absolute ceiling. |

Example `application.properties`:

```properties
fluxnova.mcp.query.max-results=500
```

## Usage Examples

### Query all active process instances

```json
{
  "active": true
}
```

### Query process instances by definition key

Query DTO:
```json
{
  "processDefinitionKey": "invoice-approval"
}
```

Pass `maxResults` as a separate tool parameter (e.g. `10`) to limit how many results are returned.

### Find incidents for a specific process instance

```json
{
  "processInstanceId": "abc-123"
}
```

### Find executions waiting for a message

```json
{
  "messageEventSubscriptionName": "paymentReceived",
  "active": true
}
```

### Query variables for a process instance

```json
{
  "processInstanceIdIn": ["abc-123"],
  "variableName": "orderTotal"
}
```

### Find event subscriptions by type

```json
{
  "eventType": "signal",
  "tenantIdIn": ["tenant-a", "tenant-b"]
}
```

## Project Structure

```
src/main/java/org/finos/fluxnova/ai/mcp/query/
├── autoconfigure/
│   └── QueryMcpAutoConfiguration.java        # Spring Boot auto-configuration
├── model/
│   ├── dto/                                  # Result DTOs (tool output)
│   │   ├── ProcessInstanceResultDto.java
│   │   ├── ExecutionResultDto.java
│   │   ├── IncidentResultDto.java
│   │   ├── EventSubscriptionResultDto.java
│   │   ├── VariableInstanceResultDto.java
│   │   ├── ProcessDefinitionResultDto.java
│   │   ├── DeploymentResultDto.java
│   │   └── TaskResultDto.java
│   └── query/                                # Query DTOs (tool input)
│       ├── ProcessInstanceQueryDto.java
│       ├── ExecutionQueryDto.java
│       ├── IncidentQueryDto.java
│       ├── EventSubscriptionQueryDto.java
│       ├── VariableInstanceQueryDto.java
│       ├── ProcessDefinitionQueryDto.java
│       ├── DeploymentQueryDto.java
│       └── TaskQueryDto.java
└── tools/
    ├── RuntimeQueryMcpTools.java             # MCP tools for RuntimeService queries
    ├── RepositoryQueryMcpTools.java          # MCP tools for RepositoryService queries
    └── TaskQueryMcpTools.java                # MCP tools for TaskService queries
```

## Design Principles

- **Read-only** — No tool can start, modify, suspend, or delete any engine entity. Only query operations are exposed.
- **No engine-rest dependency** — Queries use the engine's native Java Query API directly, avoiding coupling to the REST layer.
- **Self-describing for LLMs** — Tool descriptions and `@Schema` annotations on query DTO fields are written to be meaningful to an LLM agent, sourced from the engine's Javadoc and OpenAPI specifications.
- **Safe defaults** — Binary variable fetching is disabled by default in variable queries to avoid loading large blobs. All queries are subject to a configurable result cap (default: 200) to prevent unbounded data retrieval.

## Building

```bash
mvn clean install
```

## License

See [LICENSE](../LICENSE) for details.
