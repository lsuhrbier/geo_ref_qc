# geo_ref_qc
Data Quality library for dwc:decimalLatitude, dwc:decimalLongitude and other Locality terms.

Tools for working with georeferences in forms found in biodiversity data.

DOI: 10.5281/zenodo.14064324

[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.14064325.svg)](https://doi.org/10.5281/zenodo.14064325)


## BDQ Core Tests

The geo_ref_qc library implements the following BDQ Core tests:

- AMENDMENT_MINDEPTH-MAXDEPTH_FROM_VERBATIM 
- AMENDMENT_COUNTRYCODE_STANDARDIZED 
- VALIDATION_GEODETICDATUM_NOTEMPTY 
- VALIDATION_COUNTRY_COUNTRYCODE_CONSISTENT 
- VALIDATION_COUNTRYSTATEPROVINCE_UNAMBIGUOUS 
- AMENDMENT_GEODETICDATUM_ASSUMEDDEFAULT 
- VALIDATION_MINDEPTH_LESSTHAN_MAXDEPTH 
- VALIDATION_DECIMALLATITUDE_INRANGE 
- VALIDATION_LOCATION_NOTEMPTY 
- ISSUE_COORDINATES_CENTEROFCOUNTRY 
- VALIDATION_MINDEPTH_INRANGE 
- AMENDMENT_COORDINATES_FROM_VERBATIM 
- VALIDATION_STATEPROVINCE_FOUND 
- AMENDMENT_COUNTRYCODE_FROM_COORDINATES 
- VALIDATION_COUNTRY_NOTEMPTY 
- VALIDATION_COORDINATEUNCERTAINTY_INRANGE 
- AMENDMENT_GEODETICDATUM_STANDARDIZED 
- VALIDATION_MINELEVATION_INRANGE 
- VALIDATION_DECIMALLONGITUDE_NOTEMPTY 
- VALIDATION_COORDINATES_STATE-PROVINCE_CONSISTENT 
- VALIDATION_DECIMALLONGITUDE_INRANGE 
- VALIDATION_COUNTRYCODE_STANDARD 
- AMENDMENT_COORDINATES_TRANSPOSED 
- VALIDATION_COORDINATES_COUNTRYCODE_CONSISTENT 
- VALIDATION_MAXDEPTH_INRANGE 
- VALIDATION_COORDINATES_ZERO 
- VALIDATION_MINELEVATION_LESSTHAN_MAXELEVATION 
- VALIDATION_GEODETICDATUM_STANDARD 
- VALIDATION_COORDINATES_TERRESTRIALMARINE 
- AMENDMENT_MINELEVATION-MAXELEVATION_FROM_VERBATIM 
- VALIDATION_COUNTRYCODE_NOTEMPTY 
- VALIDATION_MAXELEVATION_INRANGE 
- VALIDATION_DECIMALLATITUDE_NOTEMPTY 
- VALIDATION_COUNTRY_FOUND 

# Include using maven

Available in Maven Central.  

    <dependency>
        <groupId>org.filteredpush</groupId>
        <artifactId>geo_ref_qc</artifactId>
        <version>2.0.0</version>
    </dependency>


# Building

    mvn package

# Developer deployment: 

To deploy a snapshot to the snapshotRepository: 

    mvn clean deploy

To deploy a new release to maven central, set the version in pom.xml to a non-snapshot version, then deploy with the release profile (which adds package signing and deployment to release staging:

    mvn clean deploy -P release

After this, you will need to login to the sonatype oss repository hosting nexus instance, find the staged release in the staging repositories, and perform the release.  It should be possible (haven't verified this yet) to perform the release from the command line instead by running: 

    mvn nexus-staging:release -P release

