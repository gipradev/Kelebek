package com.gipra.kelebek.ui.Report.My_Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProductListView {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("n_order_id")
    @Expose
    private String nOrderId;
    @SerializedName("c_otr_status")
    @Expose
    private String cOtrStatus;
    @SerializedName("c_order_status")
    @Expose
    private String cOrderStatus;
    @SerializedName("c_product")
    @Expose
    private String cProduct;
    @SerializedName("productid")
    @Expose
    private String productid;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("n_quantity")
    @Expose
    private String nQuantity;
    @SerializedName("n_subtotal")
    @Expose
    private String nSubtotal;
    @SerializedName("n_total_pv")
    @Expose
    private Object nTotalPv;
    @SerializedName("n_total_bv")
    @Expose
    private Object nTotalBv;
    @SerializedName("bv")
    @Expose
    private String bv;
    @SerializedName("c_product_type")
    @Expose
    private String cProductType;
    @SerializedName("ordereddate")
    @Expose
    private String ordereddate;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNOrderId() {
        return nOrderId;
    }

    public void setNOrderId(String nOrderId) {
        this.nOrderId = nOrderId;
    }

    public String getCOtrStatus() {
        return cOtrStatus;
    }

    public void setCOtrStatus(String cOtrStatus) {
        this.cOtrStatus = cOtrStatus;
    }

    public String getCOrderStatus() {
        return cOrderStatus;
    }

    public void setCOrderStatus(String cOrderStatus) {
        this.cOrderStatus = cOrderStatus;
    }

    public String getCProduct() {
        return cProduct;
    }

    public void setCProduct(String cProduct) {
        this.cProduct = cProduct;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getNAmount() {
        return nAmount;
    }

    public void setNAmount(String nAmount) {
        this.nAmount = nAmount;
    }

    public String getNQuantity() {
        return nQuantity;
    }

    public void setNQuantity(String nQuantity) {
        this.nQuantity = nQuantity;
    }

    public String getNSubtotal() {
        return nSubtotal;
    }

    public void setNSubtotal(String nSubtotal) {
        this.nSubtotal = nSubtotal;
    }

    public Object getNTotalPv() {
        return nTotalPv;
    }

    public void setNTotalPv(Object nTotalPv) {
        this.nTotalPv = nTotalPv;
    }

    public Object getNTotalBv() {
        return nTotalBv;
    }

    public void setNTotalBv(Object nTotalBv) {
        this.nTotalBv = nTotalBv;
    }

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public String getCProductType() {
        return cProductType;
    }

    public void setCProductType(String cProductType) {
        this.cProductType = cProductType;
    }

    public String getOrdereddate() {
        return ordereddate;
    }

    public void setOrdereddate(String ordereddate) {
        this.ordereddate = ordereddate;
    }

}
