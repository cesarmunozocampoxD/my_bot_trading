package cmo.trading.binance.api_binance.models.entity;

public class CryptoTrades extends Crypto {
    private Double major;
    private String createdAt;
    private Double minor;
    private Double feesAmount;
    private String feesCurrency;
    private String tid;
    private String oid;
    private String side;
    private String makerSide;

    public Double getMajor() {
        return major;
    }

    public void setMajor(Double major) {
        this.major = major;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getMinor() {
        return minor;
    }

    public void setMinor(Double minor) {
        this.minor = minor;
    }

    public Double getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(Double feesAmount) {
        this.feesAmount = feesAmount;
    }

    public String getFeesCurrency() {
        return feesCurrency;
    }

    public void setFeesCurrency(String feesCurrency) {
        this.feesCurrency = feesCurrency;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getMakerSide() {
        return makerSide;
    }

    public void setMakerSide(String makerSide) {
        this.makerSide = makerSide;
    }
}
