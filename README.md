# VSAC to OMOP

This is a command-line utility that takes a Value Set Authority Center (VSAC) OID for a particular value set, and using the NLM's FHIR API, retrieves the value set definition including codes.  It then communicates using the OHDSI WebAPI to create the value set (as a concept set) and populate it with all codes that it is able to find.

## Building
Written in Java, and uses Maven for package dependencies.  Build the system from the command line using:

`mvn clean package`
