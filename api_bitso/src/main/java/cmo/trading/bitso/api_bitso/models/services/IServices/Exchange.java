package cmo.trading.bitso.api_bitso.models.services.IServices;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cmo.trading.bitso.api_bitso.models.entity.CryptoBalance;
import cmo.trading.bitso.api_bitso.models.entity.CryptoTrades;

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
}
