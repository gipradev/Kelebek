package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepurchaseIncomelist {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_date")
    @Expose
    private String dDate;
    @SerializedName("c_username")
    @Expose
    private String cusername;
    @SerializedName("n_bv")
    @Expose
    private String nbv;
    @SerializedName("n_level")
    @Expose
    private String nlevel;
    @SerializedName("n_precentage")
    @Expose
    private String nprecentage;
    @SerializedName("gross_amt")
    @Expose
    private String grossamt;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public String getNbv() {
        return nbv;
    }

    public void setNbv(String nbv) {
        this.nbv = nbv;
    }

    public String getNlevel() {
        return nlevel;
    }

    public void setNlevel(String nlevel) {
        this.nlevel = nlevel;
    }

    public String getNprecentage() {
        return nprecentage;
    }

    public void setNprecentage(String nprecentage) {
        this.nprecentage = nprecentage;
    }

    public String getGrossamt() {
        return grossamt;
    }

    public void setGrossamt(String grossamt) {
        this.grossamt = grossamt;
    }
}
