package com.gipra.kelebek.Gene;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGene {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<GeneList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeneList> getData() {
        return data;
    }

    public void setData(List<GeneList> data) {
        this.data = data;
    }

}
