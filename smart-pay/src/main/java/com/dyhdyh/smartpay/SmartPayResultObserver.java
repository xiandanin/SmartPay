package com.dyhdyh.smartpay;

import android.util.Log;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 16:52
 */
public class SmartPayResultObserver {
    private static SmartPayResultSubscriber mSubscriber;
    private static SmartPayConverterFactory mConverterFactory;

    public static void register(SmartPayResultSubscriber subscriber) {
        mSubscriber = subscriber;
    }

    public static void setConverterFactory(SmartPayConverterFactory factory) {
        mConverterFactory = factory;
    }

    public static void notify(PayType payType, Map<String, String> result) {
        if (mSubscriber != null) {
            Log.d("支付结果--------------->", payType + " " + result + " " + Thread.currentThread().getName());
            mSubscriber.onNotifyResult(mConverterFactory.create(payType).convert(result));
        }
        unregister();
    }

    private static void unregister() {
        mSubscriber = null;
        mConverterFactory = null;
    }
}
