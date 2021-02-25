package com.gipra.kelebek.ui.Report.My_Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetBill {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("n_order_id")
    @Expose
    private String nOrderId;
    @SerializedName("invdate")
    @Expose
    private String invdate;
    @SerializedName("C_FNAME")
    @Expose
    private String cFNAME;
    @SerializedName("C_ADDRESS1")
    @Expose
    private String cADDRESS1;
    @SerializedName("districtame")
    @Expose
    private String districtame;
    @SerializedName("statename")
    @Expose
    private String statename;
    @SerializedName("C_MOBILE")
    @Expose
    private String cMOBILE;
    @SerializedName("C_EMAIL")
    @Expose
    private String cEMAIL;
    @SerializedName("grand_total")
    @Expose
    private Integer grandTotal;
    @SerializedName("d_date")
    @Expose
    private String dDate;
    @SerializedName("ordereddate")
    @Expose
    private String ordereddate;
    @SerializedName("grand_total_pv")
    @Expose
    private Integer grandTotalPv;


    public String getNOrderId() {
        return nOrderId;
    }

    public void setNOrderId(String nOrderId) {
        this.nOrderId = nOrderId;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public String getCFNAME() {
        return cFNAME;
    }

    public void setCFNAME(String cFNAME) {
        this.cFNAME = cFNAME;
    }

    public String getCADDRESS1() {
        return cADDRESS1;
    }

    public void setCADDRESS1(String cADDRESS1) {
        this.cADDRESS1 = cADDRESS1;
    }

    public String getDistrictame() {
        return districtame;
    }

    public void setDistrictame(String districtame) {
        this.districtame = districtame;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getCMOBILE() {
        return cMOBILE;
    }

    public void setCMOBILE(String cMOBILE) {
        this.cMOBILE = cMOBILE;
    }

    public String getCEMAIL() {
        return cEMAIL;
    }

    public void setCEMAIL(String cEMAIL) {
        this.cEMAIL = cEMAIL;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getOrdereddate() {
        return ordereddate;
    }

    public void setOrdereddate(String ordereddate) {
        this.ordereddate = ordereddate;
    }

    public Integer getGrandTotalPv() {
        return grandTotalPv;
    }

    public void setGrandTotalPv(Integer grandTotalPv) {
        this.grandTotalPv = grandTotalPv;
    }


    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
