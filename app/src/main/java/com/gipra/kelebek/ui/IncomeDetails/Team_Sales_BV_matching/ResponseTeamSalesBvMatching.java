package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTeamSalesBvMatching {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<TSBvMatchingList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TSBvMatchingList> getData() {
        return data;
    }

    public void setData(List<TSBvMatchingList> data) {
        this.data = data;
    }
}
