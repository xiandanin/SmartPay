package com.dyhdyh.smartpay.adapter.rxjava2.wechat;

import android.content.Context;

import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPayGlobalController;
import com.dyhdyh.smartpay.adapter.rxjava2.RxJava2ResultHandler;
import com.dyhdyh.smartpay.wechat.WeChatPayBaseCall;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author dengyuhan
 *         created 2018/3/29 13:55
 */
public class WeChatPayCallRxJava2Impl extends WeChatPayBaseCall<Observable<SmartPayResult>> {
    public WeChatPayCallRxJava2Impl(Context context) {
        super(context);
    }

    @Override
    public Observable<SmartPayResult> call(final Map<String, Object> params) {
        return Observable.create(new ObservableOnSubscribe<SmartPayResult>() {
            @Override
            public void subscribe(ObservableEmitter<SmartPayResult> emitter) throws Exception {
                try {
                    SmartPayGlobalController.getInstance().register(new RxJava2ResultHandler<>(emitter));

                    callPay(params);

                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }
}
