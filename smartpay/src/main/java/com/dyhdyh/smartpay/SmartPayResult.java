package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:18
 */
public class SmartPayResult {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAIL = 2;
    public static final int STATUS_CANCEL = 3;

    private PayType payType;
    private int status;
    private int code;
    private String message;
    private Map<String, String> result;

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
