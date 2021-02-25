package com.gipra.kelebek.ui.PinManagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePinProduct {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<PinProductList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PinProductList> getData() {
        return data;
    }

    public void setData(List<PinProductList> data) {
        this.data = data;
    }

}
