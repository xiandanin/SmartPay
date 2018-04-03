package com.dyhdyh.smartpay.alipay;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayParams;

import java.util.HashMap;
import java.util.Map;

public class AliPayParams extends SmartPayParams {
    public static final String KEY_ORDER_STR = getKey(PayType.ALIPAY, "order_str");
    public static final String KEY_SHOW_PAY_LOADING = getKey(PayType.ALIPAY, "show_pay_loading");


    public static Map<String, Object> build(String orderStr) {
        return build(orderStr, true);
    }

    public static Map<String, Object> build(String orderStr, boolean showPayLoading) {
        Map<String, Object> params = new HashMap<>();
        params.put(KEY_ORDER_STR, orderStr);
        params.put(KEY_SHOW_PAY_LOADING, showPayLoading);
        return params;
    }
}
