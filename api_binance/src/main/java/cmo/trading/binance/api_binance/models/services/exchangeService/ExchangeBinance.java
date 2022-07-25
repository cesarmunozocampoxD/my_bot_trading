package cmo.trading.binance.api_binance.models.services.exchangeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmo.trading.binance.api_binance.models.entity.CryptoBalance;
import cmo.trading.binance.api_binance.models.entity.CryptoTrades;
import cmo.trading.binance.api_binance.models.services.IServices.Exchange;
import cmo.trading.binance.api_binance.models.services.httpService.HTTPServiceBinance;


@Service("Binance")
public class ExchangeBinance implements Exchange {
    
    public static final String SYMBOL_ADAUSDT = "ADAUSDT";
    public static final String SYMBOL_XRPUSDT = "XRPUSDT";

    @Autowired
    HTTPServiceBinance httpServiceBinance;

    @Override
    public JSONObject getLastPrice(String symbol) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("symbol", symbol);
        String result = httpServiceBinance.GETMethod(parameters, "/api/v3/ticker/price");
        JSONObject obj = new JSONObject(result);
        return obj;
    }

    @Override
    public List<CryptoBalance> getMyBalance() throws Exception {
        List<CryptoBalance> listBalances = new ArrayList<>();
        
        return listBalances;
    }

    @Override
    public List<CryptoTrades> getMyTrades() throws Exception {
        List<CryptoTrades> listTrades = new ArrayList<>();

        
        return listTrades;
    }

    @Override
    public JSONArray haveOpenOrder(String symbol, String typeOperation) throws Exception {
        return null;
    }

    @Override
    public JSONObject getLastOrder(String symbol) throws Exception {
        return null;
    }

    @Override
    public String makeOrder(String symbol, String side, String type, String timeInForce, String quantity, String price)
            throws Exception {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> getKLines(String symbol, String interval, String limit) throws Exception {
        return null;
    }
}
