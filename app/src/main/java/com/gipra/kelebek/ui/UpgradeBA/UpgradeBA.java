package com.gipra.kelebek.ui.UpgradeBA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpgradeBA {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("c_username")
    @Expose
    private String cUsername;
    @SerializedName("C_FNAME")
    @Expose
    private String cFNAME;
    @SerializedName("D_JOIN")
    @Expose
    private String dJOIN;
    @SerializedName("c_registration_type")
    @Expose
    private String cRegistrationType;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getCFNAME() {
        return cFNAME;
    }

    public void setCFNAME(String cFNAME) {
        this.cFNAME = cFNAME;
    }

    public String getDJOIN() {
        return dJOIN;
    }

    public void setDJOIN(String dJOIN) {
        this.dJOIN = dJOIN;
    }

    public String getCRegistrationType() {
        return cRegistrationType;
    }

    public void setCRegistrationType(String cRegistrationType) {
        this.cRegistrationType = cRegistrationType;
    }
}
