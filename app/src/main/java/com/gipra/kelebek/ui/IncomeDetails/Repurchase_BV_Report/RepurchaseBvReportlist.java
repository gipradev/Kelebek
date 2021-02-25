package com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepurchaseBvReportlist {
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
    @SerializedName("N_PV")
    @Expose
    private String nPV;

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

    public String getNPV() {
        return nPV;
    }

    public void setNPV(String nPV) {
        this.nPV = nPV;
    }
}
