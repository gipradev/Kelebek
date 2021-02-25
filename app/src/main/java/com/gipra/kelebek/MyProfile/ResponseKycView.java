package com.gipra.kelebek.MyProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseKycView {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("path")
    @Expose
    private String path;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
