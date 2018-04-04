package com.dyhdyh.smartpay.adapter.rxjava.alipay;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.dyhdyh.smartpay.adapter.rxjava.RxJavaResultSubscriber;
import com.dyhdyh.smartpay.alipay.AliPayBaseCall;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * @author dengyuhan
 *         created 2018/3/28 20:08
 */
public class AliPayCallRxJavaImpl<SmartPayResult> extends AliPayBaseCall<Observable<SmartPayResult>> {

    public AliPayCallRxJavaImpl(Activity activity) {
        super(activity);
    }


    @Override
    public Observable<SmartPayResult> call(final Map<String, Object> params) {
        return Observable.create(new Observable.OnSubscribe<SmartPayResult>() {
            @Override
            public void call(Subscriber<? super SmartPayResult> subscriber) {
                try {
                    SmartPayGlobalController.getInstance().register(new RxJavaResultSubscriber<>(subscriber));

                    Map<String, String> result = callPay(params);

                    SmartPayGlobalController.getInstance().notify(PayType.ALIPAY, result);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
