package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:47
 */
public interface SmartPayConverterFactory<T> {

    SmartPayResultConverter<T> create(PayType payType);
}
