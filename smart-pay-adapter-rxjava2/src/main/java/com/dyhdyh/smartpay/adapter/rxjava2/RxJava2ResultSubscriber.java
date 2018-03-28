package com.dyhdyh.smartpay.adapter.rxjava2;

import com.dyhdyh.smartpay.SmartPayResultSubscriber;

import io.reactivex.ObservableEmitter;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class RxJava2ResultSubscriber<T> implements SmartPayResultSubscriber<T> {
    private ObservableEmitter<T> mEmitter;

    public RxJava2ResultSubscriber(ObservableEmitter<T> emitter) {
        this.mEmitter = emitter;
    }

    @Override
    public void onNotifyResult(T result) {
        if (mEmitter != null) {
            mEmitter.onNext(result);
        }
    }
}
