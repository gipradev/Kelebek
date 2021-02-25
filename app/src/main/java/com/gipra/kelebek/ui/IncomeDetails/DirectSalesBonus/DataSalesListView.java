package com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSalesListView {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("todate")
    @Expose
    private String todate1;
    @SerializedName("dsb")
    @Expose
    private String dsb;
    @SerializedName("n_gross_amt")
    @Expose
    private String nGrossAmt;
    @SerializedName("deduction_amount")
    @Expose
    private String deductionAmount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTodate() {
        return todate1;
    }

    public void setTodate(String todate) {
        this.todate1 = todate;
    }

    public String getDsb() {
        return dsb;
    }

    public void setDsb(String dsb) {
        this.dsb = dsb;
    }

    public String getNGrossAmt() {
        return nGrossAmt;
    }

    public void setNGrossAmt(String nGrossAmt) {
        this.nGrossAmt = nGrossAmt;
    }

    public String getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        this.deductionAmount = deductionAmount;
    }
}