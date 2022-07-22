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
    public static final String SYMBOL_BTC_USD = "btc_usd";
    public static final String SYMBOL_XRP_USD = "xrp_usd";
    public static final String SYMBOL_AAVE_USD = "aave_usd";

    @Autowired
    HTTPServiceBinance httpServiceBinance;

    @Override
    public JSONObject getLastPrice(String symbol) throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("book", symbol);
        String result = httpServiceBinance.GETMethod(parameters, "/v3/ticker");
        JSONObject obj = new JSONObject(result);
        return obj;
    }

    @Override
    public List<CryptoBalance> getMyBalance() throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        String result = httpServiceBinance.GETMethod(parameters, "/v3/balance/");
        JSONObject payload = new JSONObject(result);
        JSONArray myBalances = (payload.getJSONObject("payload").getJSONArray("balances"));
        List<CryptoBalance> listBalances = new ArrayList<>();
        for (int index = 0; index < myBalances.length(); index++) {
            JSONObject obj = myBalances.getJSONObject(index);
            CryptoBalance myBitsoBalance = new CryptoBalance();
            if (obj.getDouble("available") > 0) {
                myBitsoBalance.setTotal(obj.getDouble("total"));
                myBitsoBalance.setAvailable(obj.getDouble("available"));
                myBitsoBalance.setCurrecy(obj.getString("currency"));
                myBitsoBalance.setLocked(obj.getDouble("locked"));
                myBitsoBalance.setPendingDeposit(obj.getDouble("pending_deposit"));
                listBalances.add(myBitsoBalance);
            }
        }
        return listBalances;
    }

    @Override
    public List<CryptoTrades> getMyTrades() throws Exception {
        HashMap<String, String> parameters = new HashMap<>();
        String result = httpServiceBinance.GETMethod(parameters, "/v3/user_trades/");
        JSONObject payload = new JSONObject(result);
        JSONArray myTrades = (payload.getJSONArray("payload"));
        List<CryptoTrades> listTrades = new ArrayList<>();
        for (int index = 0; index < myTrades.length(); index++) {
            JSONObject obj = myTrades.getJSONObject(index);
            CryptoTrades myCryptoTrades = new CryptoTrades();

            myCryptoTrades.setSymbol(obj.getString("book"));
            myCryptoTrades.setMajor(obj.getDouble("major"));
            myCryptoTrades.setCreatedAt(obj.getString("created_at"));
            myCryptoTrades.setMinor(obj.getDouble("minor"));
            myCryptoTrades.setFeesAmount(obj.getDouble("fees_amount"));
            myCryptoTrades.setFeesCurrency(obj.getString("fees_currency"));
            myCryptoTrades.setPrice(obj.getDouble("price"));
            myCryptoTrades.setTid(obj.getString("tid"));
            myCryptoTrades.setOid(obj.getString("oid"));
            myCryptoTrades.setSide(obj.getString("side"));
            myCryptoTrades.setMakerSide(obj.getString("maker_side"));
            listTrades.add(myCryptoTrades);
        }
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
