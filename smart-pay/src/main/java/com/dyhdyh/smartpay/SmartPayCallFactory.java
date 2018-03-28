package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:47
 */
public interface SmartPayCallFactory {

    SmartPayCall create(PayType payType);
}
