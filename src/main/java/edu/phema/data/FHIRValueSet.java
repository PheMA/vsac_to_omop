package edu.phema.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FHIRValueSet {
  private String oid;
  private String name;
  private String version;
  private ArrayList<FHIRConcept> concepts;
  private ArrayList<FHIRValueSet> valueSets;

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public FHIRValueSet(String oid, String name, String version) {
    this.oid = oid;
    this.name = name;
    this.version = version;
    this.concepts = new ArrayList<FHIRConcept>();
    this.valueSets = new ArrayList<FHIRValueSet>();
  }

  public void addConcept(FHIRConcept concept) {
    concepts.add(concept);
  }

  public void addValueSet(FHIRValueSet valueSet) {
    valueSets.add(valueSet);
  }

  /*
    getConcepts

    Return the list of unique concepts represented by this value set.  If this is a grouping value set, it will
    retrieve the list from each contained value set.
   */
  public List<FHIRConcept> getConcepts() {
    ArrayList<FHIRConcept> combinedConcepts = new ArrayList<FHIRConcept>();
    if (concepts != null) {
      combinedConcepts.addAll(concepts);
    }

    if (valueSets != null) {
      for (FHIRValueSet valueSet : valueSets) {
        combinedConcepts.addAll(valueSet.getConcepts());
      }
    }

    return combinedConcepts.stream().distinct().collect(Collectors.toCollection(ArrayList<FHIRConcept>::new));
  }
}
