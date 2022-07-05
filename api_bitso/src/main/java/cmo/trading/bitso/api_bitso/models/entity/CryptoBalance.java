package cmo.trading.bitso.api_bitso.models.entity;

public class CryptoBalance {
    String currecy;
    Double total;
    Double available;
    Double locked;
    Double pendingDeposit;

    public String getCurrecy() {
        return currecy;
    }

    public void setCurrecy(String currecy) {
        this.currecy = currecy;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getLocked() {
        return locked;
    }

    public void setLocked(Double locked) {
        this.locked = locked;
    }

    public Double getPendingDeposit() {
        return pendingDeposit;
    }

    public void setPendingDeposit(Double pendingDeposit) {
        this.pendingDeposit = pendingDeposit;
    }
}
