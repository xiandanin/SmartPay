package com.dyhdyh.smartpay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayGlobalController;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:18
 */
public class AliPayCallImpl extends AliPayBaseCall<Void> {
    private static final int SDK_PAY_FLAG = 1;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    SmartPayGlobalController.getInstance().requestHandler(PayType.ALIPAY, (Map<String, String>) msg.obj);
                    break;
                }
            }
        }
    };

    public AliPayCallImpl(Activity activity) {
        super(activity);
    }


    @Override
    public Void call(final Map<String, Object> params) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Map<String, String> result = callPay(params);
                mHandler.sendMessage(mHandler.obtainMessage(SDK_PAY_FLAG, result));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return null;
    }
}
