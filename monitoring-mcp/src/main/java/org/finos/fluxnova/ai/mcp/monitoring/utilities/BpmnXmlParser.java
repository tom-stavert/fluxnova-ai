package org.finos.fluxnova.ai.mcp.monitoring.utilities;

import io.micrometer.common.util.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public final class BpmnXmlParser {

    private BpmnXmlParser() {
    }

    public static String getPromptFromXml(String bpmnXml) {
        try {
            if (StringUtils.isBlank(bpmnXml)) {
                return null;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(bpmnXml)));

            String value = XPathFactory.newInstance().newXPath().evaluate(
                    "string(//*[local-name()='property' and @name='prompt']/@value)", doc);

            if (value == null || value.isEmpty()) {
                return null;
            }

            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
