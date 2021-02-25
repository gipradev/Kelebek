package com.gipra.kelebek.ui.PinManagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinListView {
    @SerializedName("count")
    @Expose
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @SerializedName("n_id")
    @Expose
    private String nId;
    @SerializedName("n_order_id")
    @Expose
    private String nOrderId;
    @SerializedName("c_payment_type")
    @Expose
    private Object cPaymentType;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("c_pin_request")
    @Expose
    private String cPinRequest;
    @SerializedName("pin_status")
    @Expose
    private String pinStatus;
    @SerializedName("n_total_bv")
    @Expose
    private String nTotalBv;
    @SerializedName("n_total_pv")
    @Expose
    private Object nTotalPv;
    @SerializedName("n_grand_total")
    @Expose
    private String nGrandTotal;
    @SerializedName("d_date")
    @Expose
    private String dDate;

    public String getNId() {
        return nId;
    }

    public void setNId(String nId) {
        this.nId = nId;
    }

    public String getNOrderId() {
        return nOrderId;
    }

    public void setNOrderId(String nOrderId) {
        this.nOrderId = nOrderId;
    }

    public Object getCPaymentType() {
        return cPaymentType;
    }

    public void setCPaymentType(Object cPaymentType) {
        this.cPaymentType = cPaymentType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCPinRequest() {
        return cPinRequest;
    }

    public void setCPinRequest(String cPinRequest) {
        this.cPinRequest = cPinRequest;
    }

    public String getPinStatus() {
        return pinStatus;
    }

    public void setPinStatus(String pinStatus) {
        this.pinStatus = pinStatus;
    }

    public String getNTotalBv() {
        return nTotalBv;
    }

    public void setNTotalBv(String nTotalBv) {
        this.nTotalBv = nTotalBv;
    }

    public Object getNTotalPv() {
        return nTotalPv;
    }

    public void setNTotalPv(Object nTotalPv) {
        this.nTotalPv = nTotalPv;
    }

    public String getNGrandTotal() {
        return nGrandTotal;
    }

    public void setNGrandTotal(String nGrandTotal) {
        this.nGrandTotal = nGrandTotal;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

}