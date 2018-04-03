package com.dyhdyh.smartpay.adapter.rxjava2;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayCall;
import com.dyhdyh.smartpay.SmartPayCallAdapter;
import com.dyhdyh.smartpay.adapter.rxjava2.alipay.AliPayCallRxJava2Impl;
import com.dyhdyh.smartpay.adapter.rxjava2.wechat.WeChatPayCallRxJava2Impl;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class RxJava2CallAdapter implements SmartPayCallAdapter {
    private Activity mActivity;

    private RxJava2CallAdapter(Activity activity) {
        this.mActivity = activity;
    }

    public static RxJava2CallAdapter create(Activity activity) {
        return new RxJava2CallAdapter(activity);
    }


    @Override
    public SmartPayCall adapt(PayType payType) {
        SmartPayCall call = null;
        if (payType == PayType.WECHAT) {
            call = new WeChatPayCallRxJava2Impl(mActivity);
        } else if (payType == PayType.ALIPAY) {
            call = new AliPayCallRxJava2Impl(mActivity);
        }
        return call;
    }
}
