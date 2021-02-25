package com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLsbsSearch {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<LSBlist> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LSBlist> getData() {
        return data;
    }

    public void setData(List<LSBlist> data) {
        this.data = data;
    }
}
