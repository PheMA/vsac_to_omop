# VSAC to OMOP

This is a command-line utility that takes a Value Set Authority Center (VSAC) OID for a particular value set, and using the NLM's FHIR API, retrieves the value set definition including codes.  It then communicates using the OHDSI WebAPI to create the value set (as a concept set) and populate it with all codes that it is able to find.

## Configuration

Currently the program uses a properties file found at `config/config.properties`.  This will be changed in the future to use command line options.  For now, you should set the following options within that file:

```
UMLS_USER=username                           -- Your UMLS username
UMLS_PASS=password                           -- Your UMLS password
FHIR_URL=cts.nlm.nih.gov/fhir/               -- Note the lack of http(s) in front

VS_NAME=Acute Myocardial Infarction          -- The name of the value set you wish to load
VS_OIDS=2.16.840.1.113883.3.67.1.101.1.317   -- The VSAC OID of the value set you wish to load
VS_VER=20170609                              -- The Definition Version (found from the VSAC UI) of the value set to load 

OMOP_BASE_URL=http://api.ohdsi.org/WebAPI/   -- The base WebAPI URL of the OHDSI instance you want to load to
OMOP_SOURCE=SYNPUF5PCT                       -- The data source in OHDSI
OMOP_CREATE=true                             -- If the value set should actually be created or not.
```

## Building
Written in Java, and uses Maven for package dependencies.  Build the system from the command line using:

`mvn clean package`

This will create two versions of an executable JAR, one with all dependencies embedded within it, and one without.  The more portable JAR will be the one with the embedded dependencies, although it is larger.  Assuming you are trying to run v0.0.1-SNAPSHOT, you would invoke it from the command line as:

`java -jar ./target/vsac_to_omop-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

