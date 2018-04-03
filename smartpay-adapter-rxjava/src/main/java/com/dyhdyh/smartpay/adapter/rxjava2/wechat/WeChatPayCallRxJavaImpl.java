package com.dyhdyh.smartpay.adapter.rxjava2.wechat;

import android.content.Context;

import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.adapter.rxjava2.RxJavaResultSubscriber;
import com.dyhdyh.smartpay.wechat.WeChatPayBaseCall;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author dengyuhan
 *         created 2018/3/29 13:55
 */
public class WeChatPayCallRxJavaImpl extends WeChatPayBaseCall<Observable<SmartPayResult>> {
    public WeChatPayCallRxJavaImpl(Context context) {
        super(context);
    }

    @Override
    public Observable<SmartPayResult> call(final Map<String, Object> params) {
        return Observable.create(new Observable.OnSubscribe<SmartPayResult>() {
            @Override
            public void call(Subscriber<? super SmartPayResult> subscriber) {
                try {
                    SmartPayGlobalController.getInstance().register(new RxJavaResultSubscriber<>(subscriber));

                    callPay(params);

                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }
}
