package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 15:46
 */
public interface OnSmartPaymentListener<T> {
    void onResult(T result);
}
