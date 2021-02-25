package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegBA {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("c_username")
    @Expose
    private String cUsername;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("GENDER")
    @Expose
    private String gENDER;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("Country")
    @Expose
    private Integer country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("c_nominee_name")
    @Expose
    private String cNomineeName;
    @SerializedName("C_PAN")
    @Expose
    private String cPAN;
    @SerializedName("c_nominee_relation")
    @Expose
    private String cNomineeRelation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGENDER() {
        return gENDER;
    }

    public void setGENDER(String gENDER) {
        this.gENDER = gENDER;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCNomineeName() {
        return cNomineeName;
    }

    public void setCNomineeName(String cNomineeName) {
        this.cNomineeName = cNomineeName;
    }

    public String getCPAN() {
        return cPAN;
    }

    public void setCPAN(String cPAN) {
        this.cPAN = cPAN;
    }

    public String getCNomineeRelation() {
        return cNomineeRelation;
    }

    public void setCNomineeRelation(String cNomineeRelation) {
        this.cNomineeRelation = cNomineeRelation;
    }
}
