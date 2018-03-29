package com.dyhdyh.smartpay.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dyhdyh.smartpay.OnSmartPaymentListener;
import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPay;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.adapter.rxjava2.RxJava2CallAdapter;
import com.dyhdyh.smartpay.alipay.AliPayParams;
import com.dyhdyh.smartpay.test.alipay.AliPayTest;
import com.dyhdyh.smartpay.test.wechat.WeChatPayTest;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeChatPayTest.requestGetTestParams();
    }

    public void clickAliPay(View view) {
        startDefaultPay(PayType.ALIPAY);
    }

    public void clickWeChatPay(View view) {
        startDefaultPay(PayType.WECHAT);
    }

    public void clickWeChatPayRxJava(View view) {
        startRxJava2Pay(PayType.WECHAT);
    }

    public void clickAliPayRxJava(View view) {
        startRxJava2Pay(PayType.ALIPAY);
    }


    public void startRxJava2Pay(final PayType payType) {
        SmartPay.with(this)
                .payType(payType)
                .params(buildParams(payType))
                .callAdapter(RxJava2CallAdapter.create(this))
                .as(Observable.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SmartPayResult>() {
                    @Override
                    public void accept(SmartPayResult result) throws Exception {
                        Log.d("------------->", result.getResult() + " " + Thread.currentThread().getName());
                        if (result.getStatus() == SmartPayResult.STATUS_SUCCESS) {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "支付成功", Toast.LENGTH_SHORT).show();
                        } else if (result.getStatus() == SmartPayResult.STATUS_CANCEL) {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "取消支付", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void startDefaultPay(final PayType payType) {
        SmartPay.with(this)
                .payType(payType)
                .params(buildParams(payType))
                .setOnPaymentListener(new OnSmartPaymentListener<SmartPayResult>() {
                    @Override
                    public void onResult(SmartPayResult result) {
                        Log.d("------------->", result.getResult() + " " + Thread.currentThread().getName());
                        if (result.getStatus() == SmartPayResult.STATUS_SUCCESS) {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "支付成功", Toast.LENGTH_SHORT).show();
                        } else if (result.getStatus() == SmartPayResult.STATUS_CANCEL) {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "取消支付", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
    }

    public Map<String, Object> buildParams(PayType payType) {
        Map<String, Object> params = new HashMap<>();
        if (payType == PayType.ALIPAY) {
            params.putAll(AliPayParams.build(AliPayTest.generateOrderStr(AliPayTest.TEST_APPID, AliPayTest.TEST_RSA2_PRIVATE)));
        } else if (payType == PayType.WECHAT) {
            params.putAll(WeChatPayTest.getTestParams());
        }
        return params;
    }


}
