package com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstPurchaselist {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_activation")
    @Expose
    private String dActivation;
    @SerializedName("order_id")
    @Expose
    private Object orderId;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("N_BV")
    @Expose
    private String nBV;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;
    @SerializedName("total_bv")
    @Expose
    private Integer totalBv;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDActivation() {
        return dActivation;
    }

    public void setDActivation(String dActivation) {
        this.dActivation = dActivation;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public String getNAmount() {
        return nAmount;
    }

    public void setNAmount(String nAmount) {
        this.nAmount = nAmount;
    }

    public String getNBV() {
        return nBV;
    }

    public void setNBV(String nBV) {
        this.nBV = nBV;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getTotalBv() {
        return totalBv;
    }

    public void setTotalBv(Integer totalBv) {
        this.totalBv = totalBv;
    }
}
