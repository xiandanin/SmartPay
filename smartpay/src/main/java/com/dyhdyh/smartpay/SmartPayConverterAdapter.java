package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:47
 */
public interface SmartPayConverterAdapter<T> {

    SmartPayResultConverter<T> adapt(PayType payType);
}
