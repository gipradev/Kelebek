package com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLeaderLevelBv {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<LeaderLevelBv> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LeaderLevelBv> getData() {
        return data;
    }

    public void setData(List<LeaderLevelBv> data) {
        this.data = data;
    }


}
