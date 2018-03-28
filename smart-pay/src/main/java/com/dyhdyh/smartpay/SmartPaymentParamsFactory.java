package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:53
 */
public interface SmartPaymentParamsFactory {

    SmartPayParams build(PayType payType);
}
