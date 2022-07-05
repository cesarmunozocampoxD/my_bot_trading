package cmo.trading.bitso.api_bitso.controllers.bitso;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmo.trading.bitso.api_bitso.models.entity.Crypto;
import cmo.trading.bitso.api_bitso.models.entity.CryptoBalance;
import cmo.trading.bitso.api_bitso.models.entity.CryptoTrades;
import cmo.trading.bitso.api_bitso.models.services.IServices.Exchange;

import cmo.trading.bitso.api_bitso.models.entity.Credential;

@RestController
@CrossOrigin( "*" )
@RequestMapping("/bitso_requests")
public class BitsoController {

    @Autowired
    Credential credentials;

    @Autowired
    @Qualifier("Bitso")
    Exchange exchangeBitso;

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
                JSONObject payload = exchangeBitso.getLastPrice(symbol).getJSONObject("payload");
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

    @GetMapping("/getMyBalance")
    public List<CryptoBalance> getMyBalance() {
        try {
            List<CryptoBalance> listBalances = exchangeBitso.getMyBalance();
            return listBalances;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getMyTrades")
    public List<CryptoTrades> getMyTrades() {
        try {
            List<CryptoTrades> listTrades = exchangeBitso.getMyTrades();
            return listTrades;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
