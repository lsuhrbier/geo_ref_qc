
package org.geolocate;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebService(name = "geolocatesvcSoap", targetNamespace = "http://geo-locate.org/webservices/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GeolocatesvcSoap {


    /**
     * returns the names of all waterbodies found with a locality description. <br>*U.S. localities only, county required*
     * 
     * @param localityDescription
     * @return
     *     returns org.geolocate.ArrayOfString
     */
    @WebMethod(operationName = "FindWaterBodiesWithinLocality", action = "http://geo-locate.org/webservices/FindWaterBodiesWithinLocality")
    @WebResult(name = "FindWaterBodiesWithinLocalityResult", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "FindWaterBodiesWithinLocality", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.FindWaterBodiesWithinLocality")
    @ResponseWrapper(localName = "FindWaterBodiesWithinLocalityResponse", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.FindWaterBodiesWithinLocalityResponse")
    public ArrayOfString findWaterBodiesWithinLocality(
        @WebParam(name = "LocalityDescription", targetNamespace = "http://geo-locate.org/webservices/")
        LocalityDescription localityDescription);

    /**
     * Georeferences a locality description. returns a 'Georef_Result_Set' given a 'LocalityDescription' and boolean georeferencing options. <br>Language key refers to an integer representing different languages libraries. Will default to basic english (0) if invalid key is provided. <br>*GLOBAL*
     * 
     * @param doPoly
     * @param displacePoly
     * @param doUncert
     * @param polyAsLinkID
     * @param restrictToLowestAdm
     * @param findWaterbody
     * @param languageKey
     * @param localityDescription
     * @param hwyX
     * @return
     *     returns org.geolocate.GeorefResultSet
     */
    @WebMethod(operationName = "Georef", action = "http://geo-locate.org/webservices/Georef")
    @WebResult(name = "Result", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "Georef", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef")
    @ResponseWrapper(localName = "GeorefResponse", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.GeorefResponse")
    public GeorefResultSet georef(
        @WebParam(name = "LocalityDescription", targetNamespace = "http://geo-locate.org/webservices/")
        LocalityDescription localityDescription,
        @WebParam(name = "HwyX", targetNamespace = "http://geo-locate.org/webservices/")
        boolean hwyX,
        @WebParam(name = "FindWaterbody", targetNamespace = "http://geo-locate.org/webservices/")
        boolean findWaterbody,
        @WebParam(name = "RestrictToLowestAdm", targetNamespace = "http://geo-locate.org/webservices/")
        boolean restrictToLowestAdm,
        @WebParam(name = "doUncert", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doUncert,
        @WebParam(name = "doPoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doPoly,
        @WebParam(name = "displacePoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean displacePoly,
        @WebParam(name = "polyAsLinkID", targetNamespace = "http://geo-locate.org/webservices/")
        boolean polyAsLinkID,
        @WebParam(name = "LanguageKey", targetNamespace = "http://geo-locate.org/webservices/")
        int languageKey);

    /**
     * Georeferences a locality description. returns a 'Georef_Result_Set' given Country, State, County, LocalityString and boolean georeferencing options.  <br><b>Use this one if you are unsure of which to use.</b> <br>Language key refers to an integer representing different languages libraries. Will default to basic english (languagekey=0) if invalid key is provided. <br>*GLOBAL*
     * 
     * @param country
     * @param doPoly
     * @param displacePoly
     * @param county
     * @param localityString
     * @param doUncert
     * @param polyAsLinkID
     * @param restrictToLowestAdm
     * @param state
     * @param findWaterbody
     * @param languageKey
     * @param hwyX
     * @return
     *     returns org.geolocate.GeorefResultSet
     */
    @WebMethod(operationName = "Georef2", action = "http://geo-locate.org/webservices/Georef2")
    @WebResult(name = "Result", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "Georef2", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef2")
    @ResponseWrapper(localName = "Georef2Response", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef2Response")
    public GeorefResultSet georef2(
        @WebParam(name = "Country", targetNamespace = "http://geo-locate.org/webservices/")
        String country,
        @WebParam(name = "State", targetNamespace = "http://geo-locate.org/webservices/")
        String state,
        @WebParam(name = "County", targetNamespace = "http://geo-locate.org/webservices/")
        String county,
        @WebParam(name = "LocalityString", targetNamespace = "http://geo-locate.org/webservices/")
        String localityString,
        @WebParam(name = "HwyX", targetNamespace = "http://geo-locate.org/webservices/")
        boolean hwyX,
        @WebParam(name = "FindWaterbody", targetNamespace = "http://geo-locate.org/webservices/")
        boolean findWaterbody,
        @WebParam(name = "RestrictToLowestAdm", targetNamespace = "http://geo-locate.org/webservices/")
        boolean restrictToLowestAdm,
        @WebParam(name = "doUncert", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doUncert,
        @WebParam(name = "doPoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doPoly,
        @WebParam(name = "displacePoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean displacePoly,
        @WebParam(name = "polyAsLinkID", targetNamespace = "http://geo-locate.org/webservices/")
        boolean polyAsLinkID,
        @WebParam(name = "LanguageKey", targetNamespace = "http://geo-locate.org/webservices/")
        int languageKey);

    /**
     * Georeferences a locality description. returns a 'Georef_Result_Set' given vLocality, vGeorgraphy and boolean georeferencing options.  VLocality and VGeogrpahy are fields specific to BioGeomancer. <br>Language key refers to an integer representing different languages libraries. Will default to basic english (0) if invalid key is provided <br>*North American Localities Only*
     * 
     * @param vGeography
     * @param doPoly
     * @param displacePoly
     * @param vLocality
     * @param doUncert
     * @param polyAsLinkID
     * @param restrictToLowestAdm
     * @param findWaterbody
     * @param languageKey
     * @param hwyX
     * @return
     *     returns org.geolocate.GeorefResultSet
     */
    @WebMethod(operationName = "Georef3", action = "http://geo-locate.org/webservices/Georef3")
    @WebResult(name = "Result", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "Georef3", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef3")
    @ResponseWrapper(localName = "Georef3Response", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef3Response")
    public GeorefResultSet georef3(
        @WebParam(name = "vLocality", targetNamespace = "http://geo-locate.org/webservices/")
        String vLocality,
        @WebParam(name = "vGeography", targetNamespace = "http://geo-locate.org/webservices/")
        String vGeography,
        @WebParam(name = "HwyX", targetNamespace = "http://geo-locate.org/webservices/")
        boolean hwyX,
        @WebParam(name = "FindWaterbody", targetNamespace = "http://geo-locate.org/webservices/")
        boolean findWaterbody,
        @WebParam(name = "RestrictToLowestAdm", targetNamespace = "http://geo-locate.org/webservices/")
        boolean restrictToLowestAdm,
        @WebParam(name = "doUncert", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doUncert,
        @WebParam(name = "doPoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doPoly,
        @WebParam(name = "displacePoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean displacePoly,
        @WebParam(name = "polyAsLinkID", targetNamespace = "http://geo-locate.org/webservices/")
        boolean polyAsLinkID,
        @WebParam(name = "LanguageKey", targetNamespace = "http://geo-locate.org/webservices/")
        int languageKey);

    /**
     * Snaps given point to nearest water body found from given locality description. <br>*U.S. localities only, county required*
     * 
     * @param wgs84Coordinate
     * @param localityDescription
     */
    @WebMethod(operationName = "SnapPointToNearestFoundWaterBody", action = "http://geo-locate.org/webservices/SnapPointToNearestFoundWaterBody")
    @RequestWrapper(localName = "SnapPointToNearestFoundWaterBody", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.SnapPointToNearestFoundWaterBody")
    @ResponseWrapper(localName = "SnapPointToNearestFoundWaterBodyResponse", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.SnapPointToNearestFoundWaterBodyResponse")
    public void snapPointToNearestFoundWaterBody(
        @WebParam(name = "LocalityDescription", targetNamespace = "http://geo-locate.org/webservices/")
        LocalityDescription localityDescription,
        @WebParam(name = "WGS84Coordinate", targetNamespace = "http://geo-locate.org/webservices/", mode = WebParam.Mode.INOUT)
        Holder<GeographicPoint> wgs84Coordinate);

    /**
     * Snaps given point to nearest water body found from given locality description terms. <br>*U.S. localities only, county required*
     * 
     * @param wgs84Latitude
     * @param country
     * @param wgs84Longitude
     * @param county
     * @param localityString
     * @param state
     * @return
     *     returns org.geolocate.GeographicPoint
     */
    @WebMethod(operationName = "SnapPointToNearestFoundWaterBody2", action = "http://geo-locate.org/webservices/SnapPointToNearestFoundWaterBody2")
    @WebResult(name = "WGS84Coordinate", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "SnapPointToNearestFoundWaterBody2", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.SnapPointToNearestFoundWaterBody2")
    @ResponseWrapper(localName = "SnapPointToNearestFoundWaterBody2Response", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.SnapPointToNearestFoundWaterBody2Response")
    public GeographicPoint snapPointToNearestFoundWaterBody2(
        @WebParam(name = "Country", targetNamespace = "http://geo-locate.org/webservices/")
        String country,
        @WebParam(name = "State", targetNamespace = "http://geo-locate.org/webservices/")
        String state,
        @WebParam(name = "County", targetNamespace = "http://geo-locate.org/webservices/")
        String county,
        @WebParam(name = "LocalityString", targetNamespace = "http://geo-locate.org/webservices/")
        String localityString,
        @WebParam(name = "WGS84Latitude", targetNamespace = "http://geo-locate.org/webservices/")
        double wgs84Latitude,
        @WebParam(name = "WGS84Longitude", targetNamespace = "http://geo-locate.org/webservices/")
        double wgs84Longitude);

    /**
     * Returns an uncertainty polygon given the unique id used to generate it.
     * 
     * @param polyGenerationKey
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CalcUncertaintyPoly", action = "http://geo-locate.org/webservices/CalcUncertaintyPoly")
    @WebResult(name = "CalcUncertaintyPolyResult", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "CalcUncertaintyPoly", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.CalcUncertaintyPoly")
    @ResponseWrapper(localName = "CalcUncertaintyPolyResponse", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.CalcUncertaintyPolyResponse")
    public String calcUncertaintyPoly(
        @WebParam(name = "PolyGenerationKey", targetNamespace = "http://geo-locate.org/webservices/")
        String polyGenerationKey);

    /**
     * Georeferences a locality description. returns a 'Georef_Result_Set' given Country, State, County, LocalityString and boolean georeferencing options.  Also adds results from Biogeomancer to the mix. May take a long time to get results back from BG. <br>Language key refers to an integer representing different languages libraries. Will default to basic english (languagekey=0) if invalid key is provided. <br>*GLOBAL*
     * 
     * @param country
     * @param doPoly
     * @param displacePoly
     * @param county
     * @param localityString
     * @param doUncert
     * @param polyAsLinkID
     * @param restrictToLowestAdm
     * @param state
     * @param findWaterbody
     * @param languageKey
     * @param hwyX
     * @return
     *     returns org.geolocate.GeorefResultSet
     */
    @WebMethod(operationName = "Georef2plusBG", action = "http://geo-locate.org/webservices/Georef2plusBG")
    @WebResult(name = "Result", targetNamespace = "http://geo-locate.org/webservices/")
    @RequestWrapper(localName = "Georef2plusBG", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef2PlusBG")
    @ResponseWrapper(localName = "Georef2plusBGResponse", targetNamespace = "http://geo-locate.org/webservices/", className = "org.geolocate.Georef2PlusBGResponse")
    public GeorefResultSet georef2PlusBG(
        @WebParam(name = "Country", targetNamespace = "http://geo-locate.org/webservices/")
        String country,
        @WebParam(name = "State", targetNamespace = "http://geo-locate.org/webservices/")
        String state,
        @WebParam(name = "County", targetNamespace = "http://geo-locate.org/webservices/")
        String county,
        @WebParam(name = "LocalityString", targetNamespace = "http://geo-locate.org/webservices/")
        String localityString,
        @WebParam(name = "HwyX", targetNamespace = "http://geo-locate.org/webservices/")
        boolean hwyX,
        @WebParam(name = "FindWaterbody", targetNamespace = "http://geo-locate.org/webservices/")
        boolean findWaterbody,
        @WebParam(name = "RestrictToLowestAdm", targetNamespace = "http://geo-locate.org/webservices/")
        boolean restrictToLowestAdm,
        @WebParam(name = "doUncert", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doUncert,
        @WebParam(name = "doPoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean doPoly,
        @WebParam(name = "displacePoly", targetNamespace = "http://geo-locate.org/webservices/")
        boolean displacePoly,
        @WebParam(name = "polyAsLinkID", targetNamespace = "http://geo-locate.org/webservices/")
        boolean polyAsLinkID,
        @WebParam(name = "LanguageKey", targetNamespace = "http://geo-locate.org/webservices/")
        int languageKey);

}
