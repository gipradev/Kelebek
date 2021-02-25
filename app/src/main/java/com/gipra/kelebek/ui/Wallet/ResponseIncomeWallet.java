package com.gipra.kelebek.ui.Wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseIncomeWallet {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("messge")
    @Expose
    private String messge;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
