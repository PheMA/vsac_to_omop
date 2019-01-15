package edu.phema.vsac_to_omop;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import edu.phema.data.FHIRConcept;
import edu.phema.data.FHIRValueSet;
import edu.phema.vsac_to_omop.helper.Config;

public class Fhir {
  public static FHIRValueSet getVsacValueSetDefinition(String oid) throws Exception {
    String url = "https://" + Config.getUmlsUser() + ":" + Config.getUmlsPass() + "@" + Config.getFhirUrl() + "ValueSet/" + oid;

    HttpResponse<String> response = Unirest.get(url).header("Accept",  "application/json").header("Content-Type", "application/json").asString();

    JsonElement responseElement = new JsonParser().parse(response.getBody());
    JsonObject responseObject = responseElement.getAsJsonObject();

    FHIRValueSet valueSet = new FHIRValueSet(
      responseObject.get("id").getAsString(),
      responseObject.get("name").getAsString(),
      responseObject.get("version").getAsString()
    );

    // Look to see if this is a grouping value set
    if (responseObject.has("compose")) {
      JsonObject composeObject = responseObject.getAsJsonObject("compose");
      if (composeObject != null && composeObject.has("include")) {
        JsonArray includeArray = composeObject.getAsJsonArray("include");
        for (int index = 0; index < includeArray.size(); index++) {
          JsonObject valueSetObject = includeArray.get(index).getAsJsonObject();
          // We've only seen examples to date where there is a valueSet member.  If we find something else, we need
          // to throw an exception and handle that situation through more development.
          if (valueSetObject.has("valueSet")) {
            JsonArray valueSetElements = valueSetObject.getAsJsonArray("valueSet");
            for (int valueSetIndex = 0; valueSetIndex < valueSetElements.size(); valueSetIndex++) {
              String valueSetValue = valueSetElements.get(valueSetIndex).getAsString();
              String[] valueSetUriParts = valueSetValue.split("\\/");
              if (valueSetUriParts == null || valueSetUriParts.length == 0) {
                throw new Exception(String.format("Unknown value set identifier: %s", valueSetValue));
              }
              FHIRValueSet innerValueSet = getVsacValueSetDefinition(valueSetUriParts[valueSetUriParts.length - 1]);
              valueSet.addValueSet(innerValueSet);
            }
          }
          else if (valueSetObject.has("concept") && valueSetObject.has("system")) {
            String codeSystem = mapCodeSystem(valueSetObject.get("system").getAsString());
            JsonArray conceptElements = valueSetObject.get("concept").getAsJsonArray();
            for (int conceptIndex = 0; conceptIndex < conceptElements.size(); conceptIndex++) {
              JsonObject conceptObject = conceptElements.get(conceptIndex).getAsJsonObject();
              FHIRConcept concept = new FHIRConcept(
                      codeSystem,
                      conceptObject.get("code").getAsString(),
                      conceptObject.get("display").getAsString()
              );
              valueSet.addConcept(concept);
            }
          }
          else {
            throw new Exception(String.format("We are unable to process this value set - unknown type of included element.  Please report this issue to the developers."));
          }

        }
      }
    }

    return valueSet;
  }

  private static String mapCodeSystem(String codeSystemUri) throws Exception {
    if (codeSystemUri.equalsIgnoreCase("http://hl7.org/fhir/sid/icd-10-cm")) {
      return "ICD10CM";
    }
    else if (codeSystemUri.equalsIgnoreCase("http://hl7.org/fhir/sid/icd-9")) {
      return "ICD9CM";
    }
    else if (codeSystemUri.equalsIgnoreCase("http://www.ama-assn.org/go/cpt")) {
      return "CPT";
    }
    else if (codeSystemUri.equalsIgnoreCase("http://loinc.org")) {
      return "LOINC";
    }
    else if (codeSystemUri.equalsIgnoreCase("http://snomed.info/sct")) {
      return "SNOMED";
    }
    else if (codeSystemUri.equalsIgnoreCase("http://www.nlm.nih.gov/research/umls/rxnorm")) {
      return "RxNorm";
    }

    throw new Exception("This code system has not yet been mapped: " + codeSystemUri);
  }
}
