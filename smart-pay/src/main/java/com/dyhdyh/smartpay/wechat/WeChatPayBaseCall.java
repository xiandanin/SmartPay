package com.dyhdyh.smartpay.wechat;

import android.content.Context;

import com.dyhdyh.smartpay.SmartPayCall;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:12
 */
public abstract class WeChatPayBaseCall<R> implements SmartPayCall<R> {

    private IWXAPI mWXApi;
    private Context mContext;

    public WeChatPayBaseCall(Context context) {
        this.mContext = context;
    }

    protected void callPay(Map<String, Object> params) {
        String appid = (String) params.get(WeChatPayParams.KEY_APPID);
        if (mWXApi == null) {
            mWXApi = WXAPIFactory.createWXAPI(mContext, null);
            mWXApi.registerApp(appid);
        }
        PayReq req = new PayReq();
        req.appId = appid;
        req.partnerId = (String) params.get(WeChatPayParams.KEY_PARTNERID);
        req.prepayId = (String) params.get(WeChatPayParams.KEY_PREPAYID);
        req.nonceStr = (String) params.get(WeChatPayParams.KEY_NONCESTR);
        req.timeStamp = (String) params.get(WeChatPayParams.KEY_TIMESTAMP);
        req.packageValue = (String) params.get(WeChatPayParams.KEY_PACKAGE);
        req.sign = (String) params.get(WeChatPayParams.KEY_SIGN);
        //req.extData = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        mWXApi.sendReq(req);
    }

}
