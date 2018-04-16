package com.dyhdyh.smartpay;

/**
 * 支付结果分发处理
 * @author dengyuhan
 *         created 2018/3/28 17:15
 */
public interface SmartPayResultHandler<T> {

    void onHandlerResult(T result);
}
