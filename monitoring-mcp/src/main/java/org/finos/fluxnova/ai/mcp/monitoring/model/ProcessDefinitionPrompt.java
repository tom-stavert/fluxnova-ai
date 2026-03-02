package org.finos.fluxnova.ai.mcp.monitoring.model;

public class ProcessDefinitionPrompt {
    private String id;
    private String prompt;

    public ProcessDefinitionPrompt() {
    }

    public ProcessDefinitionPrompt(String id, String prompt) {
        this.id = id;
        this.prompt = prompt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
