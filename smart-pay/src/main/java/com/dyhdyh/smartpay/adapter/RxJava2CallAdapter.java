package com.dyhdyh.smartpay.adapter;

import com.dyhdyh.smartpay.SmartPayCall;
import com.dyhdyh.smartpay.SmartPayParams;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * @author dengyuhan
 *         created 2018/3/28 15:51
 */
public final class RxJava2CallAdapter<T> implements SmartPaymentCallAdapter<Observable<T>> {

    @Override
    public Observable<T> adapt(final SmartPayCall payment, final SmartPayParams params, final Map<String, Object> extras) {
        return Observable.create(new ObservableOnSubscribe<Map<String, String>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, String>> emitter) throws Exception {
                payment.call(params, extras);
                //SmartPayResultObserver.register();
            }
        }).map(new Function<Map<String, String>, T>() {
            @Override
            public T apply(Map<String, String> result) throws Exception {
                return null;
            }
        });
    }
}
