package edu.phema.data;

public class FHIRConcept {
  private String codeSystem;
  private String code;
  private String display;

  public String getCodeSystem() {
    return codeSystem;
  }

  public void setCodeSystem(String codeSystem) {
    this.codeSystem = codeSystem;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public FHIRConcept(String codeSystem, String code, String display) {
    this.codeSystem = codeSystem;
    this.code = code;
    this.display = display;
  }

  @Override
  public boolean equals(Object obj) {

    // If the object is compared with itself then return true
    if (obj == this) {
      return true;
    }

    /* Check if o is an instance of FHIRConcept or not
       "null instanceof [type]" also returns false */
    if (!(obj instanceof FHIRConcept)) {
      return false;
    }

    // typecast obj to FHIRConcept so that we can compare data members
    FHIRConcept concept = (FHIRConcept) obj;

    // For us, equality is code system + code.  If the display value differs but the other members match, the
    // objects are equal.
    return getCodeSystem().equals(concept.getCodeSystem()) &&
            getCode().equals(concept.getCode());
  }
}
