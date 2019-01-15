package edu.phema.vsac_to_omop;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import edu.phema.data.CTS2Concept;
import edu.phema.data.FHIRConcept;
import edu.phema.data.OMOPConcept;
import edu.phema.data.OMOPConceptSetContainer;
import edu.phema.vsac_to_omop.helper.Config;

public class Omop {
    private static String omopBaseURL = Config.getOmopBaseUrl();
    private static String omopSource = Config.getOmopSource();
    
    public static void setOmopConceptSet(CTS2Concept[] concepts, String valueSetName, boolean createInOmop) throws Exception {
        ArrayList<OMOPConcept[]> omopConcepts = new ArrayList<OMOPConcept[]>();
        
        for (CTS2Concept concept : concepts) {
            OMOPConcept[] omopConcept = getOmopConcepts(concept.getName(), concept.getNamespace());
            //System.out.println(omopConcept.length); 
            if(omopConcept.length != 0) {
                omopConcepts.add(omopConcept);
            }
            else  {
                System.out.println("\t" + concept.getNamespace() + " code " + concept.getName() + " has size " + omopConcept.length);
            }
            
        }

        if (createInOmop) {
            // create the container in the repository and populate with omop concepts
            String containerId = createNewConceptSetContainer(valueSetName);
            setConceptSet(containerId, omopConcepts);
        }
    }

    public static void setOmopConceptSet(List<FHIRConcept> concepts, String valueSetName, boolean createInOmop) throws Exception {
        ArrayList<OMOPConcept[]> omopConcepts = new ArrayList<OMOPConcept[]>();

        for (FHIRConcept concept : concepts) {
            OMOPConcept[] omopConcept = getOmopConcepts(concept.getCode(), concept.getCodeSystem());
            if(omopConcept.length != 0) {
                omopConcepts.add(omopConcept);
            }
            else  {
                System.out.printf("\t%s code %s has size %ld\n", concept.getDisplay(), concept.getCode(), omopConcept.length);
            }
        }

        System.out.printf("Mapped %d FHIR concepts to %d OMOP concepts\r\n", concepts.size(), omopConcepts.size());

        if (createInOmop) {
            System.out.print("Creating in OMOP...");
            // create the container in the repository and populate with omop concepts
            String containerId = createNewConceptSetContainer(valueSetName);
            setConceptSet(containerId, omopConcepts);
            System.out.println("...Concept set created");
        }
        else {
            System.out.println("Skipping creation in OMOP");
        }
    }
    
    
    private static OMOPConcept[] getOmopConcepts(String code, String vocabularyId) throws Exception {
        String omopURL = String.format("%svocabulary/%s/search", omopBaseURL, omopSource);
        String postJsonData = String.format("{\"QUERY\":\"%s\", \"VOCABULARY_ID\":[\"%s\"]}",
                code.replace("\"", "\\\""),
                vocabularyId.replace("\"", "\\\""));
        Gson gson = new Gson();
        String jsonData = getPostResponse(omopURL, postJsonData);
        OMOPConcept[] omopConcept = gson.fromJson(jsonData, OMOPConcept[].class);
        
        return omopConcept;
    }

    private static String getPostResponse(String url, String postJsonData) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        BufferedReader buffRead = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer jsonData = new StringBuffer();
        while ((output = buffRead.readLine()) != null) {
            jsonData.append(output);
        }
        buffRead.close();

        return jsonData.toString();
    }
    
    private static String createNewConceptSetContainer(String valueSetName) throws Exception {
        String omopURL = omopBaseURL + "conceptset/";
        String postJsonData = "{\"name\":\"" +valueSetName +"\", \"id\":0}";
        Gson gson = new Gson();
        String jsonData = getPostResponse(omopURL, postJsonData);
        OMOPConceptSetContainer omopConceptSetContainer = gson.fromJson(jsonData, OMOPConceptSetContainer.class);
        
        return omopConceptSetContainer.getId();
        
    }
    
    private static void setConceptSet(String id, ArrayList<OMOPConcept[]> omopConcepts)  throws IOException { 
        String omopURL = omopBaseURL + "conceptset/" +id +"/items";
        
//        System.out.println(omopURL);
        
        URL obj = new URL(omopURL); 
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
        
        // Setting basic post request 
        con.setRequestMethod("PUT");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5"); 
        con.setRequestProperty("Content-Type","application/json");  
        
        String postJsonData = "[";
        for (Iterator<OMOPConcept[]> iterator = omopConcepts.iterator(); iterator.hasNext();) {
            OMOPConcept[] omopConcept = (OMOPConcept[]) iterator.next();
            postJsonData = postJsonData +",{\"conceptId\":" +omopConcept[0].getConceptId() +",\"isExcluded\":0,\"includeDescendants\":0,\"includeMapped\":0}"; 
        }
        postJsonData = postJsonData.replaceFirst(",", "");
        postJsonData = postJsonData +"]";
        
        //System.out.println(postJsonData);
        
        // Send post request 
        con.setDoOutput(true); 
        DataOutputStream wr = new DataOutputStream(con.getOutputStream()); 
        wr.writeBytes(postJsonData); 
        wr.flush(); 
        wr.close(); 
        
        BufferedReader buffRead = new BufferedReader( new InputStreamReader(con.getInputStream())); 
        String output; 
        StringBuffer jsonData = new StringBuffer(); 
        while ((output = buffRead.readLine()) != null) 
        { 
            jsonData.append(output); 
        } 
        buffRead.close(); 

        
    }
    
    
//    private static void setConceptSet(String id, OMOPConcept[] omopConcept)  throws IOException {
//        //id="0";
//        String omopURL = omopBaseURL + "conceptset/" +id +"/items";
//        
//        System.out.println(omopURL);
//        
//        URL obj = new URL(omopURL); 
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
//        
//        // Setting basic post request 
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5"); 
//        con.setRequestProperty("Content-Type","application/json");  
//        
//        String postJsonData = "[{\"conceptId\":" +omopConcept[0].getConceptId() +",\"isExcluded\":0,\"includeDescendants\":0,\"includeMapped\":0}]";
//        
//        
//        System.out.println(postJsonData);
//        
//        // Send post request 
//        con.setDoOutput(true); 
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream()); 
//        wr.writeBytes(postJsonData); 
//        wr.flush(); 
//        wr.close(); 
//        
//        BufferedReader buffRead = new BufferedReader( new InputStreamReader(con.getInputStream())); 
//        String output; 
//        StringBuffer jsonData = new StringBuffer(); 
//        while ((output = buffRead.readLine()) != null) 
//        { 
//            jsonData.append(output); 
//        } 
//        buffRead.close(); 
//
//        System.out.println("done");
//    }
    
}
