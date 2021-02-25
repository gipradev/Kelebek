package com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFirstPurchaseSearch {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<FirstPurchaselist> data = null;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;
    @SerializedName("total_bv")
    @Expose
    private Integer totalBv;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FirstPurchaselist> getData() {
        return data;
    }

    public void setData(List<FirstPurchaselist> data) {
        this.data = data;
    }
    public Integer gettotalamount() {
        return totalamount;
    }

    public void settotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer gettotalBv() {
        return totalBv;
    }

    public void settotalBv(Integer totalBv) {
        this.totalBv = totalBv;
    }
}