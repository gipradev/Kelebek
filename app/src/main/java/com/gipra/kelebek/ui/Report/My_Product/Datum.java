package com.gipra.kelebek.ui.Report.My_Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("bv")
    @Expose
    private String bv;
    @SerializedName("n_quantity")
    @Expose
    private String nQuantity;
    @SerializedName("c_gst_type")
    @Expose
    private String cGstType;
    @SerializedName("n_cgst")
    @Expose
    private Integer nCgst;
    @SerializedName("n_sgst")
    @Expose
    private Integer nSgst;
    @SerializedName("n_igst")
    @Expose
    private Integer nIgst;
    @SerializedName("n_kfcs")
    @Expose
    private String nKfcs;
    @SerializedName("n_subtotal")
    @Expose
    private String nSubtotal;
    @SerializedName("n_total_bv")
    @Expose
    private Object nTotalBv;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getNAmount() {
        return nAmount;
    }

    public void setNAmount(String nAmount) {
        this.nAmount = nAmount;
    }

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public String getNQuantity() {
        return nQuantity;
    }

    public void setNQuantity(String nQuantity) {
        this.nQuantity = nQuantity;
    }

    public String getCGstType() {
        return cGstType;
    }

    public void setCGstType(String cGstType) {
        this.cGstType = cGstType;
    }

    public Integer getNCgst() {
        return nCgst;
    }

    public void setNCgst(Integer nCgst) {
        this.nCgst = nCgst;
    }

    public Integer getNSgst() {
        return nSgst;
    }

    public void setNSgst(Integer nSgst) {
        this.nSgst = nSgst;
    }

    public Integer getNIgst() {
        return nIgst;
    }

    public void setNIgst(Integer nIgst) {
        this.nIgst = nIgst;
    }

    public String getNKfcs() {
        return nKfcs;
    }

    public void setNKfcs(String nKfcs) {
        this.nKfcs = nKfcs;
    }

    public String getNSubtotal() {
        return nSubtotal;
    }

    public void setNSubtotal(String nSubtotal) {
        this.nSubtotal = nSubtotal;
    }

    public Object getNTotalBv() {
        return nTotalBv;
    }

    public void setNTotalBv(Object nTotalBv) {
        this.nTotalBv = nTotalBv;
    }
}
