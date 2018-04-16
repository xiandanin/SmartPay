package com.dyhdyh.smartpay.adapter.rxjava2.alipay;

import android.app.Activity;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.dyhdyh.smartpay.adapter.rxjava2.RxJava2ResultHandler;
import com.dyhdyh.smartpay.alipay.AliPayBaseCall;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:08
 */
public class AliPayCallRxJava2Impl<SmartPayResult> extends AliPayBaseCall<Observable<SmartPayResult>> {

    public AliPayCallRxJava2Impl(Activity activity) {
        super(activity);
    }


    @Override
    public Observable<SmartPayResult> call(final Map<String, Object> params) {
        return Observable.create(new ObservableOnSubscribe<SmartPayResult>() {
            @Override
            public void subscribe(ObservableEmitter<SmartPayResult> emitter) throws Exception {
                try {
                    SmartPayGlobalController.getInstance().register(new RxJava2ResultHandler<>(emitter));

                    Map<String, String> result = callPay(params);

                    SmartPayGlobalController.getInstance().requestHandler(PayType.ALIPAY, result);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
