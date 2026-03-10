package org.finos.fluxnova.ai.mcp.security;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OAuth2ClientsConfiguredCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        OAuth2ClientProperties properties = context.getBeanFactory().getBean(OAuth2ClientProperties.class);
        if (properties != null && !properties.getRegistration().isEmpty()) {
            return ConditionOutcome.match("OAuth2 client registrations configured");
        }
        return ConditionOutcome.noMatch("No OAuth2 client registrations configured");
    }
}
