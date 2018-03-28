package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:15
 */
public interface SmartPayResultSubscriber<T> {

    void onNotifyResult(T result);
}
