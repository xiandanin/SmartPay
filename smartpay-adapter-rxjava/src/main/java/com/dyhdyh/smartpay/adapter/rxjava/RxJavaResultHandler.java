package com.dyhdyh.smartpay.adapter.rxjava;

import com.dyhdyh.smartpay.SmartPayResultHandler;

import rx.Subscriber;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class RxJavaResultHandler<T> implements SmartPayResultHandler<T> {
    private Subscriber<T> mSubscriber;

    public RxJavaResultHandler(Subscriber<T> subscriber) {
        this.mSubscriber = subscriber;
    }

    @Override
    public void onHandlerResult(T result) {
        if (mSubscriber != null) {
            mSubscriber.onNext(result);
            mSubscriber.onCompleted();
        }
    }
}
