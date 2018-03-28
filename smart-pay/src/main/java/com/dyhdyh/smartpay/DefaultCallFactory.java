package com.dyhdyh.smartpay;

import android.app.Activity;

import com.dyhdyh.smartpay.alipay.AliPayCallImpl;
import com.dyhdyh.smartpay.wechat.WeChatPayImpl;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class DefaultCallFactory implements SmartPayCallFactory {
    private Activity mActivity;

    public DefaultCallFactory(Activity activity) {
        this.mActivity = activity;
    }


    @Override
    public SmartPayCall create(PayType payType) {
        SmartPayCall call = null;
        if (payType == PayType.WECHAT) {
            call = new WeChatPayImpl();
        } else if (payType == PayType.ALIPAY) {
            call = new AliPayCallImpl(mActivity);
        }
        return call;
    }
}
