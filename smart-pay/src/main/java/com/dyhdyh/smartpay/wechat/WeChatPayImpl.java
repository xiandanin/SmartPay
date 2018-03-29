package com.dyhdyh.smartpay.wechat;

import android.content.Context;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:32
 */
public class WeChatPayImpl extends WeChatPayBaseCall<Void> {

    public WeChatPayImpl(Context context) {
        super(context);
    }

    @Override
    public Void call(Map<String, Object> params) {
        callPay(params);
        return null;
    }
}
