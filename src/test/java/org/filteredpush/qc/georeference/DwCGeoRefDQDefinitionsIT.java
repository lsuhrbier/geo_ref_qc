/**
 * DwCGeoRefDQDefinitionsIT.java
 */
package org.filteredpush.qc.georeference;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.datakurator.ffdq.api.DQResponse;
import org.datakurator.ffdq.api.result.AmendmentValue;
import org.datakurator.ffdq.api.result.ComplianceValue;
import org.datakurator.ffdq.model.ResultState;
import org.filteredpush.qc.georeference.util.CountryLookup;
import org.filteredpush.qc.georeference.util.GeoUtilSingleton;
import org.junit.Test;

/**
 * Integration tests for DwCGeoRefDQ
 * 
 * @author mole
 *
 */
public class DwCGeoRefDQDefinitionsIT {

	private static final Log logger = LogFactory.getLog(DwCGeoRefDQDefinitionsIT.class);

	/**
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#validationCountryExists(java.lang.String)}.
	 */
	@Test
	public void testValidationCountryExists() {
		
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCountryFound(null,null);
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		assertNotNull(result.getComment());
		
		result = DwCGeoRefDQ.validationCountryFound("Uganda",null);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		result = DwCGeoRefDQ.validationCountryFound("dwc:country",null);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		result = DwCGeoRefDQ.validationCountryFound("Uganda","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		result = DwCGeoRefDQ.validationCountryFound("dwc:country","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		// check name with spaces
		result = DwCGeoRefDQ.validationCountryFound("République centrafricaine","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		result = DwCGeoRefDQ.validationCountryFound("Eswatini","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
		// Preferred name: Eswatini
		result = DwCGeoRefDQ.validationCountryFound("Swaziland","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());
		
	}
	
	/**
	 * Test that a lookup using Getty TGN is cached.
	 */
	@Test
	public void testValidationCountryFoundCache() {
		
		// ensure that name has been looked up and should be in cache (may have been done by another test)
		String country = "Uganda";
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCountryFound(country,"The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());

		assertNotNull(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));
		assertTrue(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));

		country = "dwc:country";
		result = DwCGeoRefDQ.validationCountryFound(country,"The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertNotNull(result.getComment());

		assertNotNull(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));
		assertFalse(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));

	} 
	
	@Test
	public void testamendmentCountrycodeStandardized() { 
		String countryCode = null;
		DQResponse<AmendmentValue> result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode);
		logger.debug(result.getComment());
		assertNotNull(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET.getLabel(), result.getResultState().getLabel());
		
		countryCode="not a country code";
		result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode);
		logger.debug(result.getComment());
		assertNotNull(result.getComment());
		assertEquals(ResultState.NOT_AMENDED.getLabel(), result.getResultState().getLabel());
		assertNull(result.getValue());
		
		List<String> codes = CountryLookup.getCountryCodes2();
		logger.debug(codes.size());
		Iterator<String> i = codes.iterator();
		while (i.hasNext()) { 
			countryCode = i.next();
			result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode.toLowerCase());
			logger.debug(result.getComment());
			assertNotNull(result.getComment());
			assertEquals(ResultState.AMENDED.getLabel(), result.getResultState().getLabel());
			assertEquals(1, result.getValue().getObject().size());
			assertEquals(countryCode, result.getValue().getObject().get("dwc:countryCode"));
		}
		
	}
	

}
