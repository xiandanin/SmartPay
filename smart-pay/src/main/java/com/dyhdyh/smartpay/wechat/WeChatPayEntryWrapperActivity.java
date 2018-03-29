package com.dyhdyh.smartpay.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/29 11:14
 */
public abstract class WeChatPayEntryWrapperActivity extends Activity {

    private IWXAPI api;
    private IWXAPIEventHandler mEventHandler = new IWXAPIEventHandler() {
        @Override
        public void onReq(BaseReq baseReq) {

        }

        @Override
        public void onResp(BaseResp resp) {
            Map<String, String> result = new HashMap<>();
            result.put("err_str", resp.errStr);
            result.put("err_code", String.valueOf(resp.errCode));
            result.put("type", String.valueOf(resp.getType()));
            result.put("open_id", resp.openId);
            result.put("transaction", resp.transaction);
            SmartPayGlobalController.getInstance().notify(PayType.WECHAT, result);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Object appIdObj = SmartPayGlobalController.getInstance().getExtras().get(WeChatPayParams.KEY_APPID);
        if (appIdObj != null && appIdObj instanceof String) {
            api = WXAPIFactory.createWXAPI(this, (String) appIdObj);
            api.handleIntent(getIntent(), mEventHandler);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, mEventHandler);
    }


}
