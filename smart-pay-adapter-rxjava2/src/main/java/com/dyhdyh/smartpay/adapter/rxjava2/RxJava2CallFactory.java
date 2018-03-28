package com.dyhdyh.smartpay.adapter.rxjava2;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayCall;
import com.dyhdyh.smartpay.SmartPayCallAdapter;
import com.dyhdyh.smartpay.wechat.WeChatPayImpl;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class RxJava2CallFactory implements SmartPayCallAdapter {
    private Activity mActivity;

    public RxJava2CallFactory(Activity activity) {
        this.mActivity = activity;
    }


    @Override
    public SmartPayCall adapt(PayType payType) {
        SmartPayCall call = null;
        if (payType == PayType.WECHAT) {
            call = new WeChatPayImpl();
        } else if (payType == PayType.ALIPAY) {
            call = new AliPayCallRxJava2Impl(mActivity);
        }
        return call;
    }
}
