package cmo.trading.bitso.api_bitso.models.services.IServices;

import java.util.HashMap;

public interface IHTTPMethods {

    String GETMethod(HashMap<String, String> parameters, String urlPath) throws Exception;

    String SETMethod(HashMap<String, String> parameters, String urlPath) throws Exception;

}
