package org.finos.fluxnova.ai.mcp.monitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessDefinitionXmlResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("bpmn20Xml")
    private String xml;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String bpmn20Xml) {
        this.xml = bpmn20Xml;
    }
}
