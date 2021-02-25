package com.gipra.kelebek.ui.PinManagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseOtrView {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("c_otr_status")
    @Expose
    private String cOtrStatus;
    @SerializedName("c_otrno")
    @Expose
    private String cOtrno;
    @SerializedName("d_otr_date")
    @Expose
    private String dOtrDate;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCOtrStatus() {
        return cOtrStatus;
    }

    public void setCOtrStatus(String cOtrStatus) {
        this.cOtrStatus = cOtrStatus;
    }

    public String getCOtrno() {
        return cOtrno;
    }

    public void setCOtrno(String cOtrno) {
        this.cOtrno = cOtrno;
    }

    public String getDOtrDate() {
        return dOtrDate;
    }

    public void setDOtrDate(String dOtrDate) {
        this.dOtrDate = dOtrDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
