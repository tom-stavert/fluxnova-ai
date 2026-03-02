package org.finos.fluxnova.ai.mcp.monitoring.tools.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract base class for all MCP tool classes.
 * Provides shared HTTP execution logic using the Template Method pattern.
 */
public abstract class AbstractMcpTools {

    protected final RestTemplate restTemplate;
    protected final String engineRestBaseUrl;

    protected AbstractMcpTools(RestTemplate restTemplate, String engineRestBaseUrl) {
        this.restTemplate = restTemplate;
        this.engineRestBaseUrl = engineRestBaseUrl;
    }

    /**
     * Execute a GET request to the engine REST API.
     *
     * @param endpoint The endpoint path (relative to engine-rest base URL)
     * @return JSON response as string
     */
    protected <T> T executeGetRequest(String endpoint, Class<T> responseType) {
        try {
            String url = engineRestBaseUrl + endpoint;
            ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("HTTP " + response.getStatusCode().value() + ": Request failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("Request failed: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
        }
    }

    protected String executeGetRequest(String endpoint) {
        try {
            String url = engineRestBaseUrl + endpoint;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody() != null ? response.getBody() : "{}";
            } else {
                return "{\"error\": \"HTTP " + response.getStatusCode().value() + "\", \"message\": \"Request failed\"}";
            }
        } catch (Exception e) {
            return "{\"error\": \"" + e.getClass().getSimpleName() + "\", \"message\": \"" + e.getMessage() + "\"}";
        }
    }
}
