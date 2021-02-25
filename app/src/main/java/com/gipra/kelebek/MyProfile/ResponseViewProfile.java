package com.gipra.kelebek.MyProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseViewProfile {

    @SerializedName("c_username")
    @Expose
    private String cUsername;
    @SerializedName("sponsor_username")
    @Expose
    private String sponsorUsername;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("C_FNAME")
    @Expose
    private String cFNAME;
    @SerializedName("C_LNAME")
    @Expose
    private String cLNAME;
    @SerializedName("C_GENDER")
    @Expose
    private String cGENDER;
    @SerializedName("C_DOB")
    @Expose
    private String cDOB;
    @SerializedName("C_MOBILE")
    @Expose
    private String cMOBILE;
    @SerializedName("C_EMAIL")
    @Expose
    private String cEMAIL;
    @SerializedName("C_ADDRESS")
    @Expose
    private String cADDRESS;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("C_ZIP_CODE")
    @Expose
    private String cZIPCODE;
    @SerializedName("C_PAN")
    @Expose
    private String cPAN;
    @SerializedName("n_kyc_number")
    @Expose
    private String nKycNumber;

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getSponsorUsername() {
        return sponsorUsername;
    }

    public void setSponsorUsername(String sponsorUsername) {
        this.sponsorUsername = sponsorUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCFNAME() {
        return cFNAME;
    }

    public void setCFNAME(String cFNAME) {
        this.cFNAME = cFNAME;
    }

    public String getCLNAME() {
        return cLNAME;
    }

    public void setCLNAME(String cLNAME) {
        this.cLNAME = cLNAME;
    }

    public String getCGENDER() {
        return cGENDER;
    }

    public void setCGENDER(String cGENDER) {
        this.cGENDER = cGENDER;
    }

    public String getCDOB() {
        return cDOB;
    }

    public void setCDOB(String cDOB) {
        this.cDOB = cDOB;
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

    public String getCADDRESS() {
        return cADDRESS;
    }

    public void setCADDRESS(String cADDRESS) {
        this.cADDRESS = cADDRESS;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCZIPCODE() {
        return cZIPCODE;
    }

    public void setCZIPCODE(String cZIPCODE) {
        this.cZIPCODE = cZIPCODE;
    }

    public String getCPAN() {
        return cPAN;
    }

    public void setCPAN(String cPAN) {
        this.cPAN = cPAN;
    }

    public String getNKycNumber() {
        return nKycNumber;
    }

    public void setNKycNumber(String nKycNumber) {
        this.nKycNumber = nKycNumber;
    }
}