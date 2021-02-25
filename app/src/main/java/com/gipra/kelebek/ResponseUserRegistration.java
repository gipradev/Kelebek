package com.gipra.kelebek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUserRegistration {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("c_username")
    @Expose
    private String cUsername;
    @SerializedName("c_name")
    @Expose
    private String name;
    @SerializedName("n_mobile")
    @Expose
    private String mobile;
    @SerializedName("c_email")
    @Expose
    private String email;
    @SerializedName("'c_address'")
    @Expose
    private String address;
    @SerializedName("c_country")
    @Expose
    private String country;
    @SerializedName("C_STATE")
    @Expose
    private String state;
    @SerializedName("C_DISTRICT")
    @Expose
    private String district;
    @SerializedName("n_pincode")
    @Expose
    private String pin;
    @SerializedName("c_dob")
    @Expose
    private String dob;
    @SerializedName("c_sponsor_name")
    @Expose
    private String cSponsorName;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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

    public String getCSponsorName() {
        return cSponsorName;
    }

    public void setCSponsorName(String cSponsorName) {
        this.cSponsorName = cSponsorName;
    }
}