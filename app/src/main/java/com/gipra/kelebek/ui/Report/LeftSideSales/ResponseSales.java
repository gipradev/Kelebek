package com.gipra.kelebek.ui.Report.LeftSideSales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSales {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SalesListView> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SalesListView> getData() {
        return data;
    }

    public void setData(List<SalesListView> data) {
        this.data = data;
    }
}
