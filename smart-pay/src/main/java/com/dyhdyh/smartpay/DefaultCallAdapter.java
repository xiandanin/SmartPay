package com.dyhdyh.smartpay;

import android.app.Activity;

import com.dyhdyh.smartpay.alipay.AliPayCallImpl;
import com.dyhdyh.smartpay.wechat.WeChatPayImpl;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class DefaultCallAdapter implements SmartPayCallAdapter {
    private Activity mActivity;

    public DefaultCallAdapter(Activity activity) {
        this.mActivity = activity;
    }


    @Override
    public SmartPayCall adapt(PayType payType) {
        SmartPayCall call = null;
        if (payType == PayType.WECHAT) {
            call = new WeChatPayImpl(mActivity);
        } else if (payType == PayType.ALIPAY) {
            call = new AliPayCallImpl(mActivity);
        }
        return call;
    }
}
