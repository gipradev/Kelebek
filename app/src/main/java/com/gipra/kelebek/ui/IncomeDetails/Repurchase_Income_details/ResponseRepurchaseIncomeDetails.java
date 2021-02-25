package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseIncomeDetails {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<RepurchaseIncomeDetailList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RepurchaseIncomeDetailList> getData() {
        return data;
    }

    public void setData(List<RepurchaseIncomeDetailList> data) {
        this.data = data;
    }
}
