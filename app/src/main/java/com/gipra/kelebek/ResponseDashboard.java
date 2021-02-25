package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDashboard {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("messge")
    @Expose
    private String messge;
    @SerializedName("n_Incomeamount")
    @Expose
    private String nIncomeamount;
    @SerializedName("ShoppingAmount")
    @Expose
    private String shoppingAmount;
    @SerializedName("n_left_count")
    @Expose
    private String nLeftCount;
    @SerializedName("n_right_count")
    @Expose
    private String nRightCount;
    @SerializedName("n_left_active")
    @Expose
    private String nLeftActive;
    @SerializedName("n_right_active")
    @Expose
    private String nRightActive;
    @SerializedName("n_left_pv")
    @Expose
    private String nLeftPv;
    @SerializedName("n_right_pv")
    @Expose
    private String nRightPv;
    @SerializedName("total_income")
    @Expose
    private Integer totalIncome;
    @SerializedName("c_rank")
    @Expose
    private String cRank;

    public String getCRank() {
        return cRank;
    }

    public void setCRank(String cRank) {
        this.cRank = cRank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public String getNIncomeamount() {
        return nIncomeamount;
    }

    public void setNIncomeamount(String nIncomeamount) {
        this.nIncomeamount = nIncomeamount;
    }

    public String getShoppingAmount() {
        return shoppingAmount;
    }

    public void setShoppingAmount(String shoppingAmount) {
        this.shoppingAmount = shoppingAmount;
    }

    public String getNLeftCount() {
        return nLeftCount;
    }

    public void setNLeftCount(String nLeftCount) {
        this.nLeftCount = nLeftCount;
    }

    public String getNRightCount() {
        return nRightCount;
    }

    public void setNRightCount(String nRightCount) {
        this.nRightCount = nRightCount;
    }

    public String getNLeftActive() {
        return nLeftActive;
    }

    public void setNLeftActive(String nLeftActive) {
        this.nLeftActive = nLeftActive;
    }

    public String getNRightActive() {
        return nRightActive;
    }

    public void setNRightActive(String nRightActive) {
        this.nRightActive = nRightActive;
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

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }}
