package cmo.trading.binance.api_binance.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmo.trading.binance.api_binance.models.entity.Credential;
import cmo.trading.binance.api_binance.models.entity.Crypto;
import cmo.trading.binance.api_binance.models.services.IServices.Exchange;


@RestController
@CrossOrigin( "*" )
@RequestMapping("/binance_request")
public class BitsoController {

    @Autowired
    Credential credentials;

    @Autowired
    @Qualifier("binance")
    Exchange exchangeBinance;

    @GetMapping("/login")
    public Boolean LoginBinance( @RequestParam(value = "apiKey") String apiKey, @RequestParam(value = "apiSecret") String apiSecret){
        try {
            credentials.createBitsoCredentials(apiKey,apiSecret); 
            return true;   
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/getLastPrice")
    public Crypto getLastPrice(@RequestParam(value = "symbol") String symbol) {
        try {
            if(credentials.isLogin()){
                Crypto result = new Crypto();
                JSONObject payload = exchangeBinance.getLastPrice(symbol).getJSONObject("payload");
                result.setSymbol(payload.getString("book"));
                result.setPrice(payload.getDouble("last"));
                return result;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
