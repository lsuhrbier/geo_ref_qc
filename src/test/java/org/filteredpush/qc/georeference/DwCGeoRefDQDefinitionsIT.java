/**
 * DwCGeoRefDQDefinitionsIT.java
 */
package org.filteredpush.qc.georeference;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.datakurator.ffdq.api.DQResponse;
import org.datakurator.ffdq.api.result.AmendmentValue;
import org.datakurator.ffdq.api.result.ComplianceValue;
import org.datakurator.ffdq.model.ResultState;
import org.filteredpush.qc.georeference.util.CountryLookup;
import org.filteredpush.qc.georeference.util.GEOUtil;
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
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#validationCountryFound(java.lang.String)}.
	 */
	@Test
	public void testValidationCountryFound() {
		
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCountryFound(null,null);
		logger.debug(result.getComment());
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET, result.getResultState());
		assertNull(result.getValue());
		assertFalse(GEOUtil.isEmpty(result.getComment()));
		
		result = DwCGeoRefDQ.validationCountryFound("Uganda",null);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		result = DwCGeoRefDQ.validationCountryFound("dwc:country",null);
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		result = DwCGeoRefDQ.validationCountryFound("Uganda","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		result = DwCGeoRefDQ.validationCountryFound("dwc:country","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		// check name with spaces
		result = DwCGeoRefDQ.validationCountryFound("République centrafricaine","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		result = DwCGeoRefDQ.validationCountryFound("Eswatini","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
		// Preferred name: Eswatini
		result = DwCGeoRefDQ.validationCountryFound("Swaziland","The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		
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
		assertFalse(GEOUtil.isEmpty(result.getComment()));;

		assertNotNull(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));
		assertTrue(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));

		country = "dwc:country";
		result = DwCGeoRefDQ.validationCountryFound(country,"The Getty Thesaurus of Geographic Names (TGN)");
		logger.debug(result.getComment());
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;

		assertNotNull(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));
		assertFalse(GeoUtilSingleton.getInstance().getTgnCountriesEntry(country));

	} 
	
	@Test
	public void testamendmentCountrycodeStandardized() { 
		String countryCode = null;
		DQResponse<AmendmentValue> result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET.getLabel(), result.getResultState().getLabel());
		
		countryCode="not a country code";
		result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.NOT_AMENDED.getLabel(), result.getResultState().getLabel());
		assertNull(result.getValue());
		
		List<String> codes = CountryLookup.getCountryCodes2();
		logger.debug(codes.size());
		Iterator<String> i = codes.iterator();
		while (i.hasNext()) { 
			countryCode = i.next();
			result = DwCGeoRefDQ.amendmentCountrycodeStandardized(countryCode.toLowerCase());
			logger.debug(result.getComment());
			assertFalse(GEOUtil.isEmpty(result.getComment()));;
			assertEquals(ResultState.AMENDED.getLabel(), result.getResultState().getLabel());
			assertEquals(1, result.getValue().getObject().size());
			assertEquals(countryCode, result.getValue().getObject().get("dwc:countryCode"));
		}
		
	}
	
	/**
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#validationCountryCountrycodeConsistent(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testValidationCountryCountrycodeConsistent() {
		
		String country="Uganda";
		String countryCode = "AT";
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
		country="USA";
		countryCode = "US";
		result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
        // Notes
        // The country code determination service should be able to match the name of a country 
        // in the original language. 
		
	 	logger.debug(GeoUtilSingleton.getInstance().isGettyCountryLookupItem(country));
	 	
		country="México";
		countryCode = "MX";
		result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		
	 	logger.debug(GeoUtilSingleton.getInstance().isGettyCountryLookupItem(country));
		
		country="México";
		countryCode = "MX";
		result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());
		
		// Notes
		// This test will fail if there is leading or trailing 
        // whitespace or there are leading or trailing non-printing characters.
		country=" Uganda"; // leading wspace in country
		countryCode = "UG";
		result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
		country="Uganda"; 
		countryCode = "UG ";  // trailing space in country code
		result = DwCGeoRefDQ.validationCountryCountrycodeConsistent(country, countryCode);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
	}
	
	@Test
	public void testvalidationStateprovinceFound() {
		
		String stateProvince ="Queensland";
		String sourceAuthority = null;
		DQResponse<ComplianceValue> result = DwCGeoRefDQ.validationStateprovinceFound(stateProvince, sourceAuthority);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.COMPLIANT.getLabel(), result.getValue().getLabel());

		stateProvince ="not a state province name";
		sourceAuthority = null;
		result = DwCGeoRefDQ.validationStateprovinceFound(stateProvince, sourceAuthority);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.RUN_HAS_RESULT.getLabel(), result.getResultState().getLabel());
		assertEquals(ComplianceValue.NOT_COMPLIANT.getLabel(), result.getValue().getLabel());
		
		stateProvince ="";
		sourceAuthority = null;
		result = DwCGeoRefDQ.validationStateprovinceFound(stateProvince, sourceAuthority);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET.getLabel(), result.getResultState().getLabel());
		assertNull(result.getValue());
		
		stateProvince ="Massachusetts";
		sourceAuthority = "https://invalid/";
		result = DwCGeoRefDQ.validationStateprovinceFound(stateProvince, sourceAuthority);
		logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));;
		assertEquals(ResultState.EXTERNAL_PREREQUISITES_NOT_MET.getLabel(), result.getResultState().getLabel());
		assertNull(result.getValue());
		
	} 
	
	/**
	 * Test method for {@link org.filteredpush.qc.georeference.DwCGeoRefDQ#amendmentCountrycodeFromCoordinates(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAmendmentCountrycodeFromCoordinates() {
		
		String latitude = "71.295556";
		String longitude = "-156.766389";
		String geodeticDatum = "EPSG:4326";
	    DQResponse<AmendmentValue> result = DwCGeoRefDQ.amendmentCountrycodeFromCoordinates(latitude, longitude, geodeticDatum, "US", "");
	    logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));
	    assertEquals(ResultState.INTERNAL_PREREQUISITES_NOT_MET.getLabel(), result.getResultState().getLabel());
	    
	    result = DwCGeoRefDQ.amendmentCountrycodeFromCoordinates(latitude, longitude, geodeticDatum, "", "");
	    logger.debug(result.getComment());
		assertFalse(GEOUtil.isEmpty(result.getComment()));
	    assertEquals(ResultState.FILLED_IN.getLabel(), result.getResultState().getLabel());
		assertEquals(1, result.getValue().getObject().size());
		assertEquals("US", result.getValue().getObject().get("dwc:countryCode"));
		
		try {
			String countryListUri = "https://raw.githubusercontent.com/mihai-craita/countries_center_box/master/countries.csv";
			InputStream inputStream;
			inputStream = new URL(countryListUri).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			CSVParser records = CSVFormat.DEFAULT.parse(reader);
			Iterator<CSVRecord> i = records.iterator();
			while (i.hasNext()) { 
				CSVRecord record = i.next();
				String countryCode = record.get(2);
				logger.debug(countryCode);
				latitude = record.get(3);
				longitude = record.get(4);
				String countryCode3 = CountryLookup.lookupCode3FromCodeName(countryCode);
				if (countryCode3==null || countryCode==null || 
						countryCode.equals("Antigua") || countryCode3.equals("CYP") || countryCode3.equals("GAB") ||
						countryCode3.equals("LIE") || countryCode3.equals("MCO") || countryCode3.equals("SMR") || 
						countryCode3.equals("VAT")) {
					countryCode=null; 
				} 
				// Has country rather than code for Antigua.
				// GAB, LIE have wrong sign in data set.  
				// CYP, MCO come up with multiple matches from buffers
				if (countryCode!=null) { 
				    result = DwCGeoRefDQ.amendmentCountrycodeFromCoordinates(latitude, longitude, geodeticDatum, "", "");
				    logger.debug(result.getComment());
					assertFalse(GEOUtil.isEmpty(result.getComment()));
				    assertEquals(ResultState.FILLED_IN.getLabel(), result.getResultState().getLabel());
					assertEquals(1, result.getValue().getObject().size());
					assertEquals(countryCode, result.getValue().getObject().get("dwc:countryCode"));
				}
			}
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
