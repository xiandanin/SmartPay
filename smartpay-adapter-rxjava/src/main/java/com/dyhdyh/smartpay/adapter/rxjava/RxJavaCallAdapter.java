package com.dyhdyh.smartpay.adapter.rxjava;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayCall;
import com.dyhdyh.smartpay.SmartPayCallAdapter;
import com.dyhdyh.smartpay.adapter.rxjava.alipay.AliPayCallRxJavaImpl;
import com.dyhdyh.smartpay.adapter.rxjava.wechat.WeChatPayCallRxJavaImpl;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class RxJavaCallAdapter implements SmartPayCallAdapter {
    private Activity mActivity;

    private RxJavaCallAdapter(Activity activity) {
        this.mActivity = activity;
    }

    public static RxJavaCallAdapter create(Activity activity) {
        return new RxJavaCallAdapter(activity);
    }


    @Override
    public SmartPayCall adapt(PayType payType) {
        SmartPayCall call = null;
        if (payType == PayType.WECHAT) {
            call = new WeChatPayCallRxJavaImpl(mActivity);
        } else if (payType == PayType.ALIPAY) {
            call = new AliPayCallRxJavaImpl(mActivity);
        }
        return call;
    }
}
