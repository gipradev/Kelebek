package com.gipra.kelebek.ui.UpgradeBA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUpgradeBA {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<UpgradeBA> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UpgradeBA> getData() {
        return data;
    }

    public void setData(List<UpgradeBA> data) {
        this.data = data;
    }
}
