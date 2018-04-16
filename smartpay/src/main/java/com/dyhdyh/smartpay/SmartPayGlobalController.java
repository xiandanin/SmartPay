package com.dyhdyh.smartpay;

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

    private Map<String, Object> mExtras = new LinkedHashMap<>();
    private SmartPayResultHandler mHandler;
    private SmartPayResultConverterAdapter mConverterAdapter;


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

    public void register(SmartPayResultHandler handler) {
        mHandler = handler;
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

    public void setConverterAdapter(SmartPayResultConverterAdapter adapter) {
        mConverterAdapter = adapter;
    }

    public void requestHandler(PayType payType, Map<String, String> result) {
        if (mHandler != null) {
            mHandler.onHandlerResult(mConverterAdapter.adapt(payType).convert(result));
        }
    }

    public void release() {
        mInstance = null;
    }
}
