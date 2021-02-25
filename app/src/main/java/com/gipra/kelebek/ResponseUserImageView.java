package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUserImageView {

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
