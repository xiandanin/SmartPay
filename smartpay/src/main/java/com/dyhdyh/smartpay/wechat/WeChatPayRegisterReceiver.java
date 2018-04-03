package com.dyhdyh.smartpay.wechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author dengyuhan
 *         created 2018/3/29 14:21
 */
public class WeChatPayRegisterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object appIdObj = SmartPayGlobalController.getInstance().getExtras().get(WeChatPayParams.KEY_APPID);
        if (appIdObj != null && appIdObj instanceof String) {
            String appid = (String) appIdObj;
            final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);

            msgApi.registerApp(appid);
        }
    }
}
