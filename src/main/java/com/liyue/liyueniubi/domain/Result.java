package com.liyue.liyueniubi.domain;

import java.io.Serializable;

public class Result implements Serializable {

    private  Object object;
    private  int  code;
    private  String  value;
    private  boolean  success;

    public Result(Object object, int code, String value, boolean success) {
        this.object = object;
        this.code = code;
        this.value = value;
        this.success = success;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
