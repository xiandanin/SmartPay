package com.dyhdyh.smartpay.rxjava;

import com.dyhdyh.smartpay.SmartPayCallAdapter;

import io.reactivex.Observable;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:33
 */
public class RxJava2Adapter<T> implements SmartPayCallAdapter<Observable<T>> {

    @Override
    public Observable<T> adapt() {
        return null;
    }
}
