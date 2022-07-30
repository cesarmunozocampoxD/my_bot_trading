package cmo.trading.binance.api_binance.models.services.IServices;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cmo.trading.binance.api_binance.models.entity.CryptoBalance;
import cmo.trading.binance.api_binance.models.entity.CryptoTrades;

public interface Exchange {
    public static final String OPERATION_SELL = "SELL";
    public static final String OPERATION_BUY = "BUY";

    JSONObject getLastPrice(String symbol) throws Exception;

    List<CryptoBalance> getMyBalance() throws Exception;

    List<CryptoTrades> getMyTrades() throws Exception;

    JSONArray haveOpenOrder(String symbol, String typeOperation) throws Exception;

    JSONObject getLastOrder(String symbol) throws Exception;

    String makeOrder(String symbol, String side, String type, String timeInForce, String quantity, String price)
            throws Exception;

    ArrayList<ArrayList<String>> getKLines(String symbol, String interval, String limit) throws Exception;

    public Float getMediaMovil(String symbol, String interval, String limit) throws Exception;
}
