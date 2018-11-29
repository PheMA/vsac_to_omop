package edu.phema.data;

import java.util.ArrayList;

public class OMOPConceptSet {
    
    private String id;
    private String name;
    private String description;
    private String expressionType;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private ArrayList<OMOPConcept> expression;
    
    
    public OMOPConceptSet(String id, String name, String description, String expressionType, String createdBy, String createdDate, String modifiedBy,
            String modifiedDate, ArrayList<OMOPConcept> expression) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.expressionType = expressionType;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.expression = expression;
    }

    public OMOPConceptSet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getExpressionType() {
        return expressionType;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public String getModifiedDate() {
        return modifiedDate;
    }
    
    public ArrayList<OMOPConcept> getExpression() {
        return expression;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setExpressionType(String expressionType) {
        this.expressionType = expressionType;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public void setExpression(ArrayList<OMOPConcept> expression) {
        this.expression = expression;
    }

}
