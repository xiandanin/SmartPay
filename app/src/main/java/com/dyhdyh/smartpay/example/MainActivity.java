package com.dyhdyh.smartpay.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dyhdyh.smartpay.OnSmartPaymentListener;
import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPay;
import com.dyhdyh.smartpay.SmartPayParams;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPaymentParamsFactory;
import com.dyhdyh.smartpay.alipay.AliPayParams;
import com.dyhdyh.smartpay.rxjava2.RxJava2CallFactory;
import com.dyhdyh.smartpay.test.SmartPayTest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAliPay(View view) {
        startRxJava2Pay(PayType.ALIPAY);
    }


    public void startRxJava2Pay(PayType payType) {
        SmartPay.with(this)
                .payType(payType)
                .params(new SmartPaymentParamsFactory() {
                    @Override
                    public SmartPayParams build(PayType payType) {
                        if (payType == PayType.ALIPAY) {
                            return new AliPayParams(SmartPayTest.AliPayTest.generateOrderStr(SmartPayTest.AliPayTest.TEST_APPID, SmartPayTest.AliPayTest.TEST_RSA2_PRIVATE));
                        }
                        return null;
                    }
                })
                .callFactory(new RxJava2CallFactory(this))
                .call(Observable.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SmartPayResult>() {
                    @Override
                    public void accept(SmartPayResult result) throws Exception {
                        Log.d("------------->", result.getResult() + " "+Thread.currentThread().getName());
                    }
                });
    }

    public void startDefaultPay(PayType payType) {
        SmartPay.with(this)
                .payType(payType)
                .params(new SmartPaymentParamsFactory() {
                    @Override
                    public SmartPayParams build(PayType payType) {
                        if (payType == PayType.ALIPAY) {
                            return new AliPayParams(SmartPayTest.AliPayTest.generateOrderStr(SmartPayTest.AliPayTest.TEST_APPID, SmartPayTest.AliPayTest.TEST_RSA2_PRIVATE));
                        }
                        return null;
                    }
                }).setOnPaymentListener(new OnSmartPaymentListener<SmartPayResult>() {
            @Override
            public void onResult(SmartPayResult result) {
                Log.d("------------->", result.getResult() + "");
            }
        }).start();
    }


}
