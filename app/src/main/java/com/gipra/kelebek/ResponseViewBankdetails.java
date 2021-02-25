package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseViewBankdetails {


    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("C_BANK")
    @Expose
    private String cBANK;
    @SerializedName("C_BRANCH")
    @Expose
    private String cBRANCH;
    @SerializedName("C_ACC_NO")
    @Expose
    private String cACCNO;
    @SerializedName("C_IFC_CODE")
    @Expose
    private String cIFCCODE;
    @SerializedName("status")
    @Expose
    private String status;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCBANK() {
        return cBANK;
    }

    public void setCBANK(String cBANK) {
        this.cBANK = cBANK;
    }

    public String getCBRANCH() {
        return cBRANCH;
    }

    public void setCBRANCH(String cBRANCH) {
        this.cBRANCH = cBRANCH;
    }

    public String getCACCNO() {
        return cACCNO;
    }

    public void setCACCNO(String cACCNO) {
        this.cACCNO = cACCNO;
    }

    public String getCIFCCODE() {
        return cIFCCODE;
    }

    public void setCIFCCODE(String cIFCCODE) {
        this.cIFCCODE = cIFCCODE;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}


