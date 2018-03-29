package com.dyhdyh.smartpay;

import android.app.Activity;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:16
 */
public class SmartPay {
    private Activity mActivity;
    private PayType mPayType;
    private SmartPayCallAdapter mCallAdapter;
    private static SmartPay mInstance;
    private Map<String, Object> mParams;

    private SmartPay(Activity activity) {
        this.mActivity = activity;
        this.callAdapter(new DefaultCallAdapter(mActivity));
        this.converterAdapter(new DefaultConverterAdapter());
    }

    public static SmartPay with(Activity activity) {
        synchronized (SmartPay.class) {
            if (mInstance == null) {
                mInstance = new SmartPay(activity);
            }
        }
        return mInstance;
    }

    public SmartPay payType(PayType payType) {
        this.mPayType = payType;
        return this;
    }

    public SmartPay callAdapter(SmartPayCallAdapter adapter) {
        if (adapter == null) {
            return this;
        }
        this.mCallAdapter = adapter;
        return this;
    }


    public SmartPay converterAdapter(SmartPayConverterAdapter adapter) {
        SmartPayGlobalController.getInstance().setConverterAdapter(adapter);
        return this;
    }

    public SmartPay params(Map<String, Object> params) {
        this.mParams = params;
        SmartPayGlobalController.getInstance().putExtras(mParams);
        return this;
    }

    public SmartPay setOnPaymentListener(OnSmartPaymentListener listener) {
        SmartPayGlobalController.getInstance().register(new DefaultResultSubscriber(listener));
        return this;
    }

    public void start() {
        if (!(mCallAdapter instanceof DefaultCallAdapter)) {
            callAdapter(new DefaultCallAdapter(mActivity));
        }
        final SmartPayCall call = mCallAdapter.adapt(mPayType);
        if (call != null) {
            call.call(mParams);
        }
    }

    public <T> T as(Class<T> observable) {
        final SmartPayCall call = mCallAdapter.adapt(mPayType);
        if (call != null) {
            return (T) call.call(mParams);
        }
        return null;
    }


    public void release() {
        SmartPayGlobalController.getInstance().release();
        mActivity = null;
        mInstance = null;
    }

}
