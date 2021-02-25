package com.gipra.kelebek.ui.PinManagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePinRequest {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<PinListView> data = null;

    public List<PinListView> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setData(List<PinListView> data) {
        this.data = data;
    }
}
