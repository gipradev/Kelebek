package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseIncome {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<RepurchaseIncomelist> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RepurchaseIncomelist> getData() {
        return data;
    }

    public void setData(List<RepurchaseIncomelist> data) {
        this.data = data;
    }
}
