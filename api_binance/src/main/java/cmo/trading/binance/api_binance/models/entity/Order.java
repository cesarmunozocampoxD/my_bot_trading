package cmo.trading.binance.api_binance.models.entity;

public class Order extends Crypto {
    private double cunmulativeQuoteQty;
    private String side;
    private int orderListId;
    private double executedQty;
    private int orderId;
    private double origQty;
    private String clientOrderId;
    private int updateTime;
    private String type;
    private double icebergQty;
    private double stopPrice;
    private double origQuoteOrderQty;
    private int time;
    private String timeInForce;
    private boolean isWorking;
    private String status;

    public double getCunmulativeQuoteQty() {
        return cunmulativeQuoteQty;
    }

    public void setCunmulativeQuoteQty(double cunmulativeQuoteQty) {
        this.cunmulativeQuoteQty = cunmulativeQuoteQty;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(int orderListId) {
        this.orderListId = orderListId;
    }

    public double getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(double executedQty) {
        this.executedQty = executedQty;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrigQty() {
        return origQty;
    }

    public void setOrigQty(double origQty) {
        this.origQty = origQty;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getIcebergQty() {
        return icebergQty;
    }

    public void setIcebergQty(double icebergQty) {
        this.icebergQty = icebergQty;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    public double getOrigQuoteOrderQty() {
        return origQuoteOrderQty;
    }

    public void setOrigQuoteOrderQty(double origQuoteOrderQty) {
        this.origQuoteOrderQty = origQuoteOrderQty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
