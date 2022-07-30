package cmo.trading.binance.api_binance.controllers;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmo.trading.binance.api_binance.models.entity.Credential;
import cmo.trading.binance.api_binance.models.entity.Crypto;
import cmo.trading.binance.api_binance.models.services.IServices.Exchange;

@RestController
@CrossOrigin("*")
@RequestMapping("/binance_request")
public class BinanceController {

    @Autowired
    Credential credentials;

    @Autowired
    @Qualifier("binance")
    Exchange exchangeBinance;

    @GetMapping("/login")
    public Boolean LoginBinance(@RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "apiSecret") String apiSecret) {
        try {
            credentials.createBitsoCredentials(apiKey, apiSecret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/getLastPrice")
    public ResponseEntity<Crypto> getLastPrice(@RequestParam(value = "symbol") String symbol) {
        Crypto result = new Crypto();
        try {
            if (credentials.isLogin()) {
                JSONObject payload = exchangeBinance.getLastPrice(symbol);
                result.setSymbol(payload.getString("symbol"));
                result.setPrice(payload.getDouble("price"));
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getklines")
    public ResponseEntity<ArrayList<ArrayList<String>>> getKLines(@RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "inverval") String interval, @RequestParam(value = "limit") String limit) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            if (credentials.isLogin()) {
                result = exchangeBinance.getKLines(symbol, interval, limit);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    

    @GetMapping("/getMediaMovil")
    public ResponseEntity<Float> getSimpleMediaMovil(@RequestParam(value = "symbol") String symbol,@RequestParam(value = "inverval") String interval,@RequestParam(value = "limit") String limit){        
        Float resultMediaMovil = 0f;    
        try {
            if(!credentials.isLogin())
                return new ResponseEntity<>(resultMediaMovil, HttpStatus.BAD_REQUEST);
                
                resultMediaMovil = exchangeBinance.getMediaMovil(symbol, interval, limit);
                return new ResponseEntity<>(resultMediaMovil, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(resultMediaMovil, HttpStatus.BAD_REQUEST);
        }
    }
}
