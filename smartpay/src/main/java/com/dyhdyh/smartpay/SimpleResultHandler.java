package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class SimpleResultHandler implements SmartPayResultHandler<SmartPayResult> {
    private OnSmartPaymentListener<SmartPayResult> mListener;

    public SimpleResultHandler(OnSmartPaymentListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onHandlerResult(SmartPayResult result) {
        if (mListener != null) {
            mListener.onResult(result);
        }
    }
}
