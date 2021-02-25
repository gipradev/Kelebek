package com.gipra.kelebek.ui.Report.My_Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyProduct {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<MyProductListView> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MyProductListView> getData() {
        return data;
    }

    public void setData(List<MyProductListView> data) {
        this.data = data;
    }

}
