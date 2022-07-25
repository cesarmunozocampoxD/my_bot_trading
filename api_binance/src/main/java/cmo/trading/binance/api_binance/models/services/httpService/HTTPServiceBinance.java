package cmo.trading.binance.api_binance.models.services.httpService;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmo.trading.binance.api_binance.models.entity.Credential;
import cmo.trading.binance.api_binance.models.services.IServices.IHTTPMethods;


@Service
public class HTTPServiceBinance extends HTTPService implements IHTTPMethods {
    
    private static final String BASE_URL = "https://api.binance.com";

    @Autowired
    private Credential credential;
    
    public HTTPServiceBinance() {
        super(BASE_URL);
    }

    @Override
    public String GETMethod(HashMap<String, String> parameters, String urlPath) throws Exception {
        
        String queryPath = (parameters.size() > 0) ? "?" + joinQueryParameters(parameters) : "";
        long nonce = System.currentTimeMillis();
        String message = nonce + "GET" + urlPath + queryPath;
        String strURL = baseUrl + urlPath + queryPath;
        String signature = "";
        byte[] secretBytes = credential.API_SECRET().getBytes();
        SecretKeySpec localMac = new SecretKeySpec(secretBytes, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(localMac);
        byte[] arrayOfByte = mac.doFinal(message.getBytes());
        BigInteger localBigInteger = new BigInteger(1, arrayOfByte);
        signature = String.format("%0" + (arrayOfByte.length << 1) + "x", new Object[] { localBigInteger });

        String authHeader = String.format("Bitso %s:%s:%s", credential.API_KEY(), nonce, signature);
        URL obj = new URL(strURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Bitso get Balance");
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", authHeader);
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            return printResponse(con);
        } else {
            return printError(con);
        }
    }

    @Override
    public String SETMethod(HashMap<String, String> parameters, String urlPath) throws Exception {
        return "";
    }

}
