package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 16:52
 */
public class SmartPayResultObserver {
    private static SmartPayResultSubscriber mSubscriber;
    private static SmartPayConverterAdapter mConverterFactory;

    public static void register(SmartPayResultSubscriber subscriber) {
        mSubscriber = subscriber;
    }

    public static void setConverterFactory(SmartPayConverterAdapter factory) {
        mConverterFactory = factory;
    }

    public static void notify(PayType payType, Map<String, String> result) {
        if (mSubscriber != null) {
            mSubscriber.onNotifyResult(mConverterFactory.adapt(payType).convert(result));
        }
        unregister();
    }

    private static void unregister() {
        mSubscriber = null;
        mConverterFactory = null;
    }
}
