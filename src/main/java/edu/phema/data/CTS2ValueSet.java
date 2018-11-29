package edu.phema.data;

public class CTS2ValueSet {

    private String valueSetOid;
    private String valueSetFormalName;
    
    public CTS2ValueSet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CTS2ValueSet(String valueSetOid, String valueSetFormalName) {
        super();
        this.valueSetOid = valueSetOid;
        this.valueSetFormalName = valueSetFormalName;
    }
    
    public String getValueSetOid() {
        return valueSetOid;
    }
    
    public String getValueSetFormalName() {
        return valueSetFormalName;
    }
    
    public void setValueSetOid(String valueSetOid) {
        this.valueSetOid = valueSetOid;
    }
    
    public void setValueSetFormalName(String valueSetFormalName) {
        this.valueSetFormalName = valueSetFormalName;
    }
    

}
