package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:47
 */
public interface SmartPayResultConverterAdapter<T> {

    SmartPayResultConverter<T> adapt(PayType payType);
}
