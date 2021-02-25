package com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseBvReport {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<RepurchaseBvReportlist> data = null;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;
    @SerializedName("total_bv")
    @Expose
    private Integer totalBv;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RepurchaseBvReportlist> getData() {
        return data;
    }

    public void setData(List<RepurchaseBvReportlist> data) {
        this.data = data;
    }


    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getTotalBv() {
        return totalBv;
    }

    public void setTotalBv(Integer totalBv) {
        this.totalBv = totalBv;
    }
}
