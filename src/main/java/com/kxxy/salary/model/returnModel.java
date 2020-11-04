package com.kxxy.salary.model;

public class returnModel {
    private int status;
    private String msg;
    private workModel rdata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public workModel getRdata() {
        return rdata;
    }

    public void setRdata(workModel rdata) {
        this.rdata = rdata;
    }
}
