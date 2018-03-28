package com.dyhdyh.smartpay.adapter.rxjava2;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPayResultObserver;
import com.dyhdyh.smartpay.alipay.AliPayBaseCall;
import com.dyhdyh.smartpay.alipay.AliPayParams;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:08
 */
public class AliPayCallRxJava2Impl extends AliPayBaseCall<Observable<SmartPayResult>> {

    public AliPayCallRxJava2Impl(Activity activity) {
        super(activity);
    }

    @Override
    public Observable<SmartPayResult> call(final AliPayParams params, final Map<String, Object> extras) {
        return Observable.create(new ObservableOnSubscribe<SmartPayResult>() {
            @Override
            public void subscribe(ObservableEmitter<SmartPayResult> emitter) throws Exception {
                SmartPayResultObserver.register(new RxJava2ResultSubscriber<>(emitter));

                Map<String, String> result = getPayTask().payV2(params.getOrderStr(), params.isShowPayLoading());

                SmartPayResultObserver.notify(PayType.ALIPAY, result);
            }
        }).subscribeOn(Schedulers.io());
    }
}
