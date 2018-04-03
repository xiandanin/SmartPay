package com.dyhdyh.smartpay.adapter.rxjava2;

import com.dyhdyh.smartpay.SmartPayResultSubscriber;

import rx.Subscriber;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class RxJavaResultSubscriber<T> implements SmartPayResultSubscriber<T> {
    private Subscriber<T> mSubscriber;

    public RxJavaResultSubscriber(Subscriber<T> subscriber) {
        this.mSubscriber = subscriber;
    }

    @Override
    public void onNotifyResult(T result) {
        if (mSubscriber != null) {
            mSubscriber.onNext(result);
            mSubscriber.onCompleted();
        }
    }
}
