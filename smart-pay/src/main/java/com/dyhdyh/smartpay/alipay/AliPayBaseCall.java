package com.dyhdyh.smartpay.alipay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.dyhdyh.smartpay.SmartPayCall;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:12
 */
public abstract class AliPayBaseCall<R> implements SmartPayCall<AliPayParams,R> {

    private PayTask mPayTask;

    public AliPayBaseCall(Activity activity) {
        this.mPayTask = new PayTask(activity);
    }

    public PayTask getPayTask() {
        return mPayTask;
    }
}
