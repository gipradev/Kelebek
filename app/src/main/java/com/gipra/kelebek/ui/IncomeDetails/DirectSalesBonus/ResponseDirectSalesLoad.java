package com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDirectSalesLoad {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DataSalesListView> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataSalesListView> getData() {
        return data;
    }

    public void setData(List<DataSalesListView> data) {
        this.data = data;
    }
}
