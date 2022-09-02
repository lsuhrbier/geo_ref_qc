/**
 * DwCGeoRefDQDefinitionsTest.java
 */
package org.filteredpush.qc.georeference;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.datakurator.ffdq.api.DQResponse;
import org.datakurator.ffdq.api.result.ComplianceValue;
import org.datakurator.ffdq.api.result.IssueValue;
import org.datakurator.ffdq.model.ResultState;
import org.junit.Test;

/**
 * @author mole
 *
 */
public class DwCGeoRefDQDefinitionsTest {

	private static final Log logger = LogFactory.getLog(DwCGeoRefDQDefinitionsTest.class);

	@Test
	public void testValidationCoordinatesNotzero() { 
        // INTERNAL_PREREQUISITES_NOT_MET if dwc:decimalLatitude and/or 
        // dwc:decimalLongitude are EMPTY or both of the values are 
        // not interpretable as numbers; COMPLIANT if either the value 
        // of dwc:decimalLatitude is not = 0 or the value of dwc:decimalLongitude 
        // is not = 0; otherwise NOT_COMPLIANT 
		
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCoordinatesNotzero(null,null);
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		
		result = DwCGeoRefDQ.validationCoordinatesNotzero("1.4","3.6");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		
		result = DwCGeoRefDQ.validationCoordinatesNotzero("0","0");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
		result = DwCGeoRefDQ.validationCoordinatesNotzero("0","3.6");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		
		result = DwCGeoRefDQ.validationCoordinatesNotzero("1.4","0");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
	}
	
	/**
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#validationCountrycodeStandard(java.lang.String)}.
	 */
	@Test
	public void testValidationCountrycodeStandard() {
		
        // Specification
        // EXTERNAL_PREREQUISITES_NOT_MET if the bdq:SourceAuthority 
        // is not available; INTERNAL_PREREQUISITES_NOT_MET if the 
        // dwc:countryCode was EMPTY; COMPLIANT if the value of dwc:countryCode 
        // is found in bdq:sourceAuthority; otherwise NOT_COMPLIANT 
		
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCountrycodeStandard(null);
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		
		result = DwCGeoRefDQ.validationCountrycodeStandard("a");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.NOT_COMPLIANT, result.getValue());
		
		result = DwCGeoRefDQ.validationCountrycodeStandard("UG");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.COMPLIANT, result.getValue());
		
	}

	@Test
    public void testIssueDatageneralizationsNotEmpty() { 
        // Specification
        // POTENTIAL_ISSUE if dwc:dataGeneralizations is not EMPTY; 
        // otherwise NOT_ISSUE 
    	
    	String dataGeneralizations = "";
    	DQResponse<IssueValue> response = DwCGeoRefDQ.issueDatageneralizationsNotempty(dataGeneralizations);
    	assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), response.getResultState().getLabel());
    	assertEquals(IssueValue.NOT_PROBLEM.getLabel(), response.getValue().getLabel());

    	dataGeneralizations = null;
    	response = DwCGeoRefDQ.issueDatageneralizationsNotempty(dataGeneralizations);
    	assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), response.getResultState().getLabel());
    	assertEquals(IssueValue.NOT_PROBLEM.getLabel(), response.getValue().getLabel());

    	dataGeneralizations = "Some generalization";
    	response = DwCGeoRefDQ.issueDatageneralizationsNotempty(dataGeneralizations);
    	assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), response.getResultState().getLabel());
    	assertEquals(IssueValue.POTENTIAL_PROBLEM.getLabel(), response.getValue().getLabel());
    }
	
	/**
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#validationMaxdepthOutofrange(java.lang.String)}.
	 */
	@Test
	public void testValidationMaxdepthOutofrange() {
		
        // Specification
        // INTERNAL_PREREQUISITES_NOT_MET if dwc:maximumDepthInMeters 
        // is EMPTY or is not interpretable as a number; COMPLIANT 
        // if the value of dwc:maximumDepthInMeters is within the range 
        // of bdq:minimumValidDepthInMeters to bdq:maximumValidDepthInMeters 
        // inclusive; otherwise NOT_COMPLIANT 

        // Parameters. This test is defined as parameterized.
        // Default values: bdq:minimumValidDepthInMeters="0"; bdq:maximumValidDepthInMeters="11000
		
		DQResponse<ComplianceValue> result = DwCGeoRefDQDefaults.validationMaxdepthOutofrange(null);
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		
		result = DwCGeoRefDQDefaults.validationMaxdepthOutofrange("a");
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		
		result = DwCGeoRefDQDefaults.validationMaxdepthOutofrange("10");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.COMPLIANT, result.getValue());
		
		result = DwCGeoRefDQDefaults.validationMaxdepthOutofrange("-1");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.NOT_COMPLIANT, result.getValue());
		
		result = DwCGeoRefDQDefaults.validationMaxdepthOutofrange("11001");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.NOT_COMPLIANT, result.getValue());
		
		// Testing parameters 
		
		result = DwCGeoRefDQ.validationMaxdepthOutofrange("11001", 0d, 12000d);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.COMPLIANT, result.getValue());
		
		result = DwCGeoRefDQ.validationMaxdepthOutofrange("10", 100d, null);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.NOT_COMPLIANT, result.getValue());
		
		result = DwCGeoRefDQ.validationMaxdepthOutofrange("-10", -100d, 200d);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT, result.getResultState());
		assertEquals(ComplianceValue.COMPLIANT, result.getValue());
		
	}
	

}