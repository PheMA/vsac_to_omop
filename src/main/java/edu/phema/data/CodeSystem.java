package edu.phema.data;

public class CodeSystem {
    private String name;
    private String version;
    
    public CodeSystem() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CodeSystem(String name, String version) {
        super();
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public void print() {
        System.out.println("code system name - " + name +"\tversion - " +version);
    }
}
