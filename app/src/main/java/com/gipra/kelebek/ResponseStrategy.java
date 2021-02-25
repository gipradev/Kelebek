package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStrategy {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("c_strategy")
    @Expose
    private String cStrategy;
    @SerializedName("update_strategy")
    @Expose
    private String updateStrategy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCStrategy() {
        return cStrategy;
    }

    public void setCStrategy(String cStrategy) {
        this.cStrategy = cStrategy;
    }

    public String getUpdateStrategy() {
        return updateStrategy;
    }

    public void setUpdateStrategy(String updateStrategy) {
        this.updateStrategy = updateStrategy;
    }
}
