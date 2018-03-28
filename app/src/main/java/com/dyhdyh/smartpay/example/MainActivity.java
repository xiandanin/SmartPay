package com.dyhdyh.smartpay.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPay;
import com.dyhdyh.smartpay.SmartPayParams;
import com.dyhdyh.smartpay.SmartPaymentParamsFactory;
import com.dyhdyh.smartpay.alipay.AliPayParams;
import com.dyhdyh.smartpay.rxjava.RxJava2Adapter;
import com.dyhdyh.smartpay.test.SmartPayTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAliPay(View view) {
        SmartPay.with(this)
                .payType(PayType.ALIPAY)
                .params(new SmartPaymentParamsFactory() {
                    @Override
                    public SmartPayParams build(PayType payType) {
                        if (payType == PayType.ALIPAY) {
                            return new AliPayParams(SmartPayTest.AliPayTest.generateOrderStr(SmartPayTest.AliPayTest.TEST_APPID, SmartPayTest.AliPayTest.TEST_RSA2_PRIVATE));
                        }
                        return null;
                    }
                }).asObservable2(new RxJava2Adapter<String>());
    }


}
