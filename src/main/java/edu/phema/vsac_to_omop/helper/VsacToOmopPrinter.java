package edu.phema.vsac_to_omop.helper;

import edu.phema.data.CTS2Concept;

/**
 * Prints out collections of information.
 */
public final class VsacToOmopPrinter {

    /**
     * Do not want others to use default constructor.
     */
    private VsacToOmopPrinter()  {
        throw new AssertionError("Instantiating utility class...");
    }
    
    /**
     * Prints the configuration  information.
     * @param config configuration information
     */
    public static void printConfig()  {
        System.out.println("UMLS User = " + Config.getUmlsUser());
        System.out.println("Value Set OID List = " + Config.getVsOids());
        System.out.println("Value Set Version = " + Config.getVsVer());
        System.out.println("FHIR service URL = " + Config.getCts2Url());
        System.out.println("OMOP base URL = " + Config.getOmopBaseUrl());
    }
    
    public static void printVSAC(CTS2Concept concept)  {
        System.out.println("concept name = " + concept.getName());
        System.out.println("concept namespace = " + concept.getNamespace());
        System.out.println("concept designation = " + concept.getDesignation());
        System.out.println("concept href = " + concept.getHref());
        System.out.println("concept uri = " + concept.getUri());
    }

}
