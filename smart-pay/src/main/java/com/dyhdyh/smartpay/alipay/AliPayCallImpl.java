package com.dyhdyh.smartpay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayResultObserver;

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
                    SmartPayResultObserver.notify(PayType.ALIPAY, (Map<String, String>) msg.obj);
                    break;
                }
            }
        }
    };

    public AliPayCallImpl(Activity activity) {
        super(activity);
    }


    @Override
    public Void call(final AliPayParams params, Map<String, Object> extras) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Map<String, String> result = getPayTask().payV2(params.getOrderStr(), params.isShowPayLoading());
                mHandler.sendMessage(mHandler.obtainMessage(SDK_PAY_FLAG, result));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return null;
    }
}
