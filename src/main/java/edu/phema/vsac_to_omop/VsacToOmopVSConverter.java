package edu.phema.vsac_to_omop;

import edu.phema.data.CTS2ValueSet;
import edu.phema.data.CTS2ValueSetDefinition;
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

        if (args.length > 0)  
        {
            new Config(args);
        }  
        else   
        {
            new Config();
        }
        VsacToOmopPrinter.printConfig();
        
        CTS2ValueSet[] valueSets = Vsac.getVsacValueSets();
        
        System.out.println("There are " +valueSets.length +" value sets");
        
        for (int i = 0; i < valueSets.length; i++) 
        {
            if(!valueSets[i].getValueSetOid().equalsIgnoreCase("2.16.840.1.113762.1.4.1095.42")  
                    && !valueSets[i].getValueSetOid().equalsIgnoreCase("2.16.840.1.113883.3.666.5.1036") )  
            {
                System.out.println(i+1 +". " +valueSets[i].getValueSetFormalName() +" - " +valueSets[i].getValueSetOid());
                
                CTS2ValueSetDefinition vsd = Vsac.getVsacValueSetDefinition(valueSets[i]);
                
                Omop.setOmopConceptSet(vsd.getConcepts(), valueSets[i].getValueSetFormalName());
            }
        }
        System.out.println("\tdone");
    }


    
    
}
