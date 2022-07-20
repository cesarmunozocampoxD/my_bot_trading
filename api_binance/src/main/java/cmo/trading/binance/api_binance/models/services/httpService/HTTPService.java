package cmo.trading.binance.api_binance.models.services.httpService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPService {
    
    String baseUrl;
    Signature sign = new Signature();

    public HTTPService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected String printResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    protected String printError(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getErrorStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    protected String getTimeStamp() {
        long timestamp = System.currentTimeMillis();
        return "timestamp=" + timestamp;
    }

    // concatenate query parameters
    protected String joinQueryParameters(HashMap<String, String> parameters) {
        String urlPath = "";
        boolean isFirst = true;

        for (Map.Entry mapElement : parameters.entrySet()) {
            if (isFirst) {
                isFirst = false;
                urlPath += mapElement.getKey() + "=" + mapElement.getValue();
            } else {
                urlPath += "&" + mapElement.getKey() + "=" + mapElement.getValue();
            }
        }
        return urlPath;
    }

    private String send(URL obj, String httpMethod) throws Exception {
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        if (httpMethod != null) {
            con.setRequestMethod(httpMethod);
        }
        // add API_KEY to header content
        // con.setRequestProperty("X-MBX-APIKEY", apiKey);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            return printResponse(con);
        } else {
            return printError(con);
        }
    }

    public String sendPublicRequest(HashMap<String, String> parameters, String urlPath) throws Exception {
        String queryPath = joinQueryParameters(parameters);
        URL obj = new URL(baseUrl + urlPath + "?" + queryPath);
        return send(obj, "GET");
    }

    public String sendSignedRequest(HashMap<String, String> parameters, String urlPath, String httpMethod,String apiSecret)
            throws Exception {
        String queryPath = "";
        String signature = "";
        if (!parameters.isEmpty()) {
            queryPath += joinQueryParameters(parameters) + "&" + getTimeStamp();
        } else {
            queryPath += getTimeStamp();
        }
        try {
            signature = sign.getSignature(queryPath, apiSecret);
        } catch (Exception e) {
            System.out.println("Please Ensure Your Secret Key Is Set Up Correctly! " + e);
            System.exit(0);
        }
        queryPath += "&signature=" + signature;

        URL obj = new URL(baseUrl + urlPath + "?" + queryPath);
        return send(obj, httpMethod);
    }
}
