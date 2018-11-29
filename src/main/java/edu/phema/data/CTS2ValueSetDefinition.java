package edu.phema.data;

import java.util.List;

public class CTS2ValueSetDefinition {
    private String content;
    private String uri;
    private String href;
    private String version;
    private List<CodeSystem> systems;
    private CTS2Concept[] concepts;
    
    public CTS2ValueSetDefinition() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public CTS2ValueSetDefinition(String content, String uri, String href, String version, List<CodeSystem> systems, CTS2Concept[] concepts) {
        super();
        this.content = content;
        this.uri = uri;
        this.href = href;
        this.version = version;
        this.systems = systems;
        this.concepts = concepts;
    }

    public String getContent() {
        return content;
    }
    
    public String getUri() {
        return uri;
    }
    
    public String getHref() {
        return href;
    }
    
    public String getVersion() {
        return version;
    }
    
    public List<CodeSystem> getSystems() {
        return systems;
    }
    
    public CTS2Concept[] getConcepts()  {
        return concepts;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setSystems(List<CodeSystem> systems) {
        this.systems = systems;
    }
    
    public void setConcepts(CTS2Concept[] concepts)  {
        this.concepts = concepts;
    }
    
}
