package com.gipra.kelebek.ui.Report.My_Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetProduct {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<GetProductListView> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetProductListView> getData() {
        return data;
    }

    public void setData(List<GetProductListView> data) {
        this.data = data;
    }

}
