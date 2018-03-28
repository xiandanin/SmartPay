package com.dyhdyh.smartpay;

import com.dyhdyh.smartpay.OnSmartPaymentListener;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPayResultSubscriber;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class DefaultResultSubscriber implements SmartPayResultSubscriber<SmartPayResult> {
    private OnSmartPaymentListener<SmartPayResult> mListener;

    public DefaultResultSubscriber(OnSmartPaymentListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onNotifyResult(SmartPayResult result) {
        if (mListener != null) {
            mListener.onResult(result);
        }
    }
}
