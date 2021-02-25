package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepurchaseIncomeDetailList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("dsb")
    @Expose
    private String dsb;
    @SerializedName("tsb")
    @Expose
    private String tsb;
    @SerializedName("n_lsb")
    @Expose
    private String nLsb;
    @SerializedName("gross_amount")
    @Expose
    private String grossAmount;
    @SerializedName("deduction_amount")
    @Expose
    private String deductionAmount;
    @SerializedName("net_amount")
    @Expose
    private String netAmount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getDsb() {
        return dsb;
    }

    public void setDsb(String dsb) {
        this.dsb = dsb;
    }

    public String getTsb() {
        return tsb;
    }

    public void setTsb(String tsb) {
        this.tsb = tsb;
    }

    public String getNLsb() {
        return nLsb;
    }

    public void setNLsb(String nLsb) {
        this.nLsb = nLsb;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }
}
