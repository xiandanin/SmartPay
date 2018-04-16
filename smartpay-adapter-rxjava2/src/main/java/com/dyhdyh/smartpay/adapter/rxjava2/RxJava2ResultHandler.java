package com.dyhdyh.smartpay.adapter.rxjava2;

import com.dyhdyh.smartpay.SmartPayResultHandler;

import io.reactivex.ObservableEmitter;

/**
 * @author dengyuhan
 *         created 2018/3/28 17:05
 */
public class RxJava2ResultHandler<T> implements SmartPayResultHandler<T> {
    private ObservableEmitter<T> mEmitter;

    public RxJava2ResultHandler(ObservableEmitter<T> emitter) {
        this.mEmitter = emitter;
    }

    @Override
    public void onHandlerResult(T result) {
        if (mEmitter != null) {
            mEmitter.onNext(result);
            mEmitter.onComplete();
        }
    }
}
