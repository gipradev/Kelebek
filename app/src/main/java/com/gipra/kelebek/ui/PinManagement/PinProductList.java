package com.gipra.kelebek.ui.PinManagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinProductList {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("c_product")
    @Expose
    private String cProduct;
    @SerializedName("n_quantity")
    @Expose
    private String nQuantity;
    @SerializedName("bv")
    @Expose
    private String bv;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("n_sub_total")
    @Expose
    private String nSubTotal;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCProduct() {
        return cProduct;
    }

    public void setCProduct(String cProduct) {
        this.cProduct = cProduct;
    }

    public String getNQuantity() {
        return nQuantity;
    }

    public void setNQuantity(String nQuantity) {
        this.nQuantity = nQuantity;
    }

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public String getNAmount() {
        return nAmount;
    }

    public void setNAmount(String nAmount) {
        this.nAmount = nAmount;
    }

    public String getNSubTotal() {
        return nSubTotal;
    }

    public void setNSubTotal(String nSubTotal) {
        this.nSubTotal = nSubTotal;
    }
}
