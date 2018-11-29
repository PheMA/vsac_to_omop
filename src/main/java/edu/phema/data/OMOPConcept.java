package edu.phema.data;

import com.google.gson.annotations.SerializedName;

public class OMOPConcept {

    @SerializedName("CONCEPT_CLASS_ID")
    private String conceptClassId;
    @SerializedName("CONCEPT_CODE")
    private String conceptCode;
    @SerializedName("CONCEPT_ID")
    private String conceptId;
    @SerializedName("CONCEPT_NAME")
    private String conceptName;
    @SerializedName("DOMAIN_ID")
    private String domainId;
    @SerializedName("INVALID_REASON")
    private String invalidReason;
    @SerializedName("INVALID_REASON_CAPTION")
    private String invalidReasonCaption;
    @SerializedName("STANDARD_CONCEPT")
    private String standardConcept;
    @SerializedName("STANDARD_CONCEPT_CAPTION")
    private String standardConceptCaption;
    @SerializedName("VOCABULARY_ID")
    private String vocabularyId;

    public OMOPConcept() {
        super();

    }

    public OMOPConcept(String conceptClassId, String conceptCode, String conceptId, String conceptName, String domainId, String invalidReason,
            String invalidReasonCaption, String standardConcept, String standardConceptCaption, String vocabularyId) {
        super();
        this.conceptClassId = conceptClassId;
        this.conceptCode = conceptCode;
        this.conceptId = conceptId;
        this.conceptName = conceptName;
        this.domainId = domainId;
        this.invalidReason = invalidReason;
        this.invalidReasonCaption = invalidReasonCaption;
        this.standardConcept = standardConcept;
        this.standardConceptCaption = standardConceptCaption;
        this.vocabularyId = vocabularyId;
    }


    public String getConceptClassId() {
        return conceptClassId;
    }
    
    public String getConceptCode() {
        return conceptCode;
    }
    
    public String getConceptId() {
        return conceptId;
    }
    
    public String getConceptName() {
        return conceptName;
    }
    
    public String getDomainId() {
        return domainId;
    }
    
    public String getInvalidReason() {
        return invalidReason;
    }
    
    public String getInvalidReasonCaption() {
        return invalidReasonCaption;
    }
    
    public String getStandardConcept() {
        return standardConcept;
    }
    
    public String getStandardConceptCaption() {
        return standardConceptCaption;
    }
    
    public String getVocabularyId() {
        return vocabularyId;
    }
    
    public void setConceptClassId(String conceptClassId) {
        this.conceptClassId = conceptClassId;
    }
    
    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }
    
    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }
    
    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }
    
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    
    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }
    
    public void setInvalidReasonCaption(String invalidReasonCaption) {
        this.invalidReasonCaption = invalidReasonCaption;
    }
    
    public void setStandardConcept(String standardConcept) {
        this.standardConcept = standardConcept;
    }
    
    public void setStandardConceptCaption(String standardConceptCaption) {
        this.standardConceptCaption = standardConceptCaption;
    }
    
    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
    }
    



    
}
