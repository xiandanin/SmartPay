package com.dyhdyh.smartpay;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局控制器
 *
 * @author dengyuhan
 *         created 2018/3/28 16:52
 */
public class SmartPayGlobalController {
    private static SmartPayGlobalController mInstance;

    private SmartPayGlobalController() {
    }

    public static SmartPayGlobalController getInstance() {
        synchronized (SmartPayGlobalController.class) {
            if (mInstance == null) {
                mInstance = new SmartPayGlobalController();
            }
        }
        return mInstance;
    }

    private Map<String, Object> mExtras = new LinkedHashMap<>();
    private SmartPayResultSubscriber mSubscriber;
    private SmartPayConverterAdapter mConverterFactory;

    public void register(SmartPayResultSubscriber subscriber) {
        mSubscriber = subscriber;
    }

    /**
     * 全局可访问的参数
     *
     * @param extras
     */
    public void putExtras(Map<String, Object> extras) {
        this.mExtras.putAll(extras);
    }

    public Map<String, Object> getExtras() {
        return mExtras;
    }

    public void setConverterAdapter(SmartPayConverterAdapter adapter) {
        mConverterFactory = adapter;
    }

    public void notify(PayType payType, Map<String, String> result) {
        Log.d("------------------->", mSubscriber + " " + result);
        if (mSubscriber != null) {
            mSubscriber.onNotifyResult(mConverterFactory.adapt(payType).convert(result));
        }
    }

    public void release() {
        mInstance = null;
    }
}
