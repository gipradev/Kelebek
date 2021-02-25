package com.gipra.kelebek.ui.Genealogy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSponsorTree {
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("c_username1")
    @Expose
    private String cUsername1;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getcUsername1() {
        return cUsername1;
    }

    public void setcUsername1(String cUsername1) {
        this.cUsername1 = cUsername1;
    }

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SponsortreeList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SponsortreeList> getData() {
        return data;
    }

    public void setData(List<SponsortreeList> data) {
        this.data = data;
    }



}
