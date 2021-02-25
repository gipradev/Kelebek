package com.gipra.kelebek.ui.Report.LeftSideSales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesListView {
    @SerializedName("count1")
    @Expose
    private Integer count1;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("childusername")
    @Expose
    private String childusername;
    @SerializedName("actvatedddate")
    @Expose
    private String actvatedddate;

    public String getActvatedddate() {
        return actvatedddate;
    }

    public void setActvatedddate(String actvatedddate) {
        this.actvatedddate = actvatedddate;
    }

    @SerializedName("n_coupon_pv")
    @Expose
    private Integer nCouponPv;

    public Integer getCount() {
        return count1;
    }

    public void setCount(Integer count1) {
        this.count1 = count1;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getChildusername() {
        return childusername;
    }

    public void setChildusername(String childusername) {
        this.childusername = childusername;
    }


    public Integer getNCouponPv() {
        return nCouponPv;
    }

    public void setNCouponPv(Integer nCouponPv) {
        this.nCouponPv = nCouponPv;
    }


}
