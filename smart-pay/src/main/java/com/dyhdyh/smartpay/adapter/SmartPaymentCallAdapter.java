package com.dyhdyh.smartpay.adapter;

import com.dyhdyh.smartpay.SmartPayCall;
import com.dyhdyh.smartpay.SmartPayParams;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 15:51
 */
public interface SmartPaymentCallAdapter<A> {

    A adapt(SmartPayCall payment, SmartPayParams params, Map<String, Object> extras);

}
