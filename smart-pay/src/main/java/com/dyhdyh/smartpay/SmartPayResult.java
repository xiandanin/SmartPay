package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:18
 */
public class SmartPayResult {
    private PayType payType;
    private boolean success;
    private int statusCode;
    private String message;
    private Map<String, String> result;

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
