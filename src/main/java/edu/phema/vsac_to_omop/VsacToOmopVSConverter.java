package edu.phema.vsac_to_omop;

import edu.phema.data.CTS2ValueSet;
import edu.phema.data.CTS2ValueSetDefinition;
import edu.phema.data.FHIRValueSet;
import edu.phema.vsac_to_omop.helper.Config;
import edu.phema.vsac_to_omop.helper.VsacToOmopPrinter;

/**
 * This utility pulls value sets from CTS2 enabled repositories.
 * Then uses the OHDSI vocabulary search to pull concepts.
 * It creates a concept set container and fills with concepts
 * Posts the container to the OMOP repository.
 * 
 */
public class VsacToOmopVSConverter 
{
    public static void main(String args[]) throws Exception {

        if (args.length > 0) {
            new Config(args);
        }  
        else {
            new Config();
        }

        VsacToOmopPrinter.printConfig();

        FHIRValueSet valueSet = Fhir.getVsacValueSetDefinition(Config.getVsOids());
        if (valueSet == null) {
            System.out.printf("No value set definition could be found for %s\n", Config.getVsOids());
            System.exit(1);
            return;
        }

        Omop.setOmopConceptSet(valueSet.getConcepts(), valueSet.getName(), Config.getOmopCreate().equalsIgnoreCase("true"));
        System.exit(0);
    }
}
