package com.dyhdyh.smartpay.alipay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.dyhdyh.smartpay.SmartPayCall;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:12
 */
public abstract class AliPayBaseCall<R> implements SmartPayCall<R> {

    private PayTask mPayTask;

    public AliPayBaseCall(Activity activity) {
        this.mPayTask = new PayTask(activity);
    }

    public PayTask getPayTask() {
        return mPayTask;
    }

    protected Map<String, String> callPay(Map<String, Object> params) {
        String orderStr = (String) params.get(AliPayParams.KEY_ORDER_STR);
        boolean showPayLoading = (boolean) params.get(AliPayParams.KEY_SHOW_PAY_LOADING);
        return getPayTask().payV2(orderStr, showPayLoading);
    }

}
