package com.gipra.kelebek.ui.Report.SponsorList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSponsorSearch {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SponsorListView> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SponsorListView> getData() {
        return data;
    }

    public void setData(List<SponsorListView> data) {
        this.data = data;
    }
}
