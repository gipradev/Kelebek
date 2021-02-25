package com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LSBlist {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("n_gross_amt")
    @Expose
    private String nGrossAmt;
    @SerializedName("N_DEDUCTION_AMT")
    @Expose
    private String nDEDUCTIONAMT;
    @SerializedName("N_NET_PAYABLE")
    @Expose
    private String nNETPAYABLE;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getNGrossAmt() {
        return nGrossAmt;
    }

    public void setNGrossAmt(String nGrossAmt) {
        this.nGrossAmt = nGrossAmt;
    }

    public String getNDEDUCTIONAMT() {
        return nDEDUCTIONAMT;
    }

    public void setNDEDUCTIONAMT(String nDEDUCTIONAMT) {
        this.nDEDUCTIONAMT = nDEDUCTIONAMT;
    }

    public String getNNETPAYABLE() {
        return nNETPAYABLE;
    }

    public void setNNETPAYABLE(String nNETPAYABLE) {
        this.nNETPAYABLE = nNETPAYABLE;
    }


}
