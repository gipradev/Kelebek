package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TSBvMatchingList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_to_date")
    @Expose
    private String dToDate;
    @SerializedName("n_left_pv")
    @Expose
    private String nLeftPv;
    @SerializedName("n_right_pv")
    @Expose
    private String nRightPv;
    @SerializedName("matching_bv")
    @Expose
    private String matchingBv;
    @SerializedName("n_left_carry_pv")
    @Expose
    private String nLeftCarryPv;
    @SerializedName("n_right_carry_pv")
    @Expose
    private String nRightCarryPv;
    @SerializedName("n_lsb")
    @Expose
    private String nLsb;
    @SerializedName("n_tsb")
    @Expose
    private String nTsb;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDToDate() {
        return dToDate;
    }

    public void setDToDate(String dToDate) {
        this.dToDate = dToDate;
    }

    public String getNLeftPv() {
        return nLeftPv;
    }

    public void setNLeftPv(String nLeftPv) {
        this.nLeftPv = nLeftPv;
    }

    public String getNRightPv() {
        return nRightPv;
    }

    public void setNRightPv(String nRightPv) {
        this.nRightPv = nRightPv;
    }

    public String getMatchingBv() {
        return matchingBv;
    }

    public void setMatchingBv(String matchingBv) {
        this.matchingBv = matchingBv;
    }

    public String getNLeftCarryPv() {
        return nLeftCarryPv;
    }

    public void setNLeftCarryPv(String nLeftCarryPv) {
        this.nLeftCarryPv = nLeftCarryPv;
    }

    public String getNRightCarryPv() {
        return nRightCarryPv;
    }

    public void setNRightCarryPv(String nRightCarryPv) {
        this.nRightCarryPv = nRightCarryPv;
    }

    public String getNLsb() {
        return nLsb;
    }

    public void setNLsb(String nLsb) {
        this.nLsb = nLsb;
    }

    public String getNTsb() {
        return nTsb;
    }

    public void setNTsb(String nTsb) {
        this.nTsb = nTsb;
    }

}
