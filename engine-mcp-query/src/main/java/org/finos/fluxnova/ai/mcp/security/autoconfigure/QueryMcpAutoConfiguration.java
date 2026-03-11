package org.finos.fluxnova.ai.mcp.security.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;

@AutoConfiguration
@ConditionalOnClass(AuthenticationProvider.class)
@ComponentScan(basePackages = "org.finos.fluxnova.ai.mcp.security")
public class QueryMcpAutoConfiguration {
}
