package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_Bonus_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTeamSalesBonus {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<TSBList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TSBList> getData() {
        return data;
    }

    public void setData(List<TSBList> data) {
        this.data = data;
    }
}
