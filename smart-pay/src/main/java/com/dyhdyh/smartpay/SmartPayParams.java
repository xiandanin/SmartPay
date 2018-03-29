package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/29 10:58
 */
public abstract class SmartPayParams {

    public static String getKey(PayType payType, String key) {
        return payType.name().toLowerCase() + "_" + key;
    }

}
