package edu.phema.data;

public class CTS2Concept {
    private String designation;
    private String uri;
    private String href;
    private String namespace;
    private String name;
    
    public CTS2Concept() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public CTS2Concept(String designation, String uri, String href, String namespace, String name) {
        super();
        this.designation = designation;
        this.uri = uri;
        this.href = href;
        this.namespace = namespace;
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }
    
    public String getUri() {
        return uri;
    }
    
    public String getHref() {
        return href;
    }
    
    public String getNamespace() {
        return namespace;
    }
    
    public String getName() {
        return name;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void print() {
        System.out.println("concept designation - " + designation);
        System.out.println("\t uri - " + uri);
        System.out.println("\t href - " + href);
        System.out.println("\t namespace - " + namespace);
        System.out.println("\t name - " + name);
    }
    
    public void printNames() {
        System.out.println("concept namespace - " + namespace +"\t name - " + name);
    }
    
}
