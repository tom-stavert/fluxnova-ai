# MCP Rest Engine MCP Server Plugin

This plugin adds MCP server capabilities to the Fluxnova platform allow MCP clients to connect via Server-Sent Events (
SSE) and make use of MCP tools which make queries directly to the Fluxnova engine.

```xml

<dependencies>
    <!-- REST engine dependency -->
    <dependency>
        <groupId>org.finos.fluxnova.bpm.springboot</groupId>
        <artifactId>fluxnova-bpm-spring-boot-starter-rest</artifactId>
    </dependency>
    <!-- Rest Engine MCP Server plugin -->
    <dependency>
        <groupId>org.finos.fluxnova.ai.mcp</groupId>
        <artifactId>monitoring-mcp</artifactId>
    </dependency>
</dependencies>
```

## Configuration

To discover MCP tools and make them available to clients, add the following configuration to your `application.yaml` or
`application.properties` file:

### application.yaml

```yaml
spring:
  ai:
    mcp:
      server:
        type: SYNC  # or ASYNC
        annotation-scanner:
          enabled: true
```

### application.properties

```properties
spring.ai.mcp.server.type=SYNC  # or ASYNC
spring.ai.mcp.server.annotation-scanner.enabled=true
```

## Building

To build the plugin, run the following command in the root directory of the project:

```bash
mvn clean install
```