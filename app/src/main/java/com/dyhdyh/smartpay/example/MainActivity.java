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
import com.dyhdyh.smartpay.test.PayTestServer;
import com.dyhdyh.widget.loading.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Response;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 获取测试参数
     *
     * @param payType
     */
    public void requestTestParams(PayType payType, final Action1<String> action1) {
        LoadingDialog.make(this).show();
        PayTestServer.requestTestParams(payType, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                LoadingDialog.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                LoadingDialog.cancel();
                try {
                    final String json = response.body().string();
                    Log.d("--------->", json);
                    //{"success":true,"message":"微信支付","params":"{\"appid\":\"wxb4ba3c02aa476ea1\",\"partnerid\":\"1900006771\",\"package\":\"Sign=WXPay\",\"noncestr\":\"ac7dbb4d2ec31fd041553d1bda3ebab4\",\"timestamp\":1523444552,\"prepayid\":\"wx111902324287646fa29a4ad02645578292\",\"sign\":\"512F69E5989877DCD0E20F49F4527A07\"}"}
                    //{"success":true,"message":"支付宝支付","params":"app_id=2014100900013222&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95%22%2C%22body%22%3A%22SmartPay%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95%22%2C%22out_trade_no%22%3A%221523444636%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-04-11+07%3A04%3A56&version=1.0&sign=Sz5G%2BkD1%2Bh1dWAw6cBrX%2BbRlIX2z5tn9mLouCUSdAAtXhZJXlgg%2F6Xp0LcFKld%2BXgk3PS4JDJCY8NTTX1MijdCE6yBWsDKa8%2BsKiau%2Bt3yjQhVHx73PjBi7pG%2F05xT086yXhTAABNCrAdzS3FDJ9NM%2B6DOihycdHpbe19k1bBBtigLQULlveYr3AaYV22M0LnxBNrp21HDwk3bajOztluKu60oSYsz810qzkjE6Mr0qX1eM9ybcjZA3S9kcCED3EwpGAbSVQLOuDHiyfFjthNJ8BWfqocLRRJOxC97KjXCzg2wO%2BujnITp8qOZFqc1wMolczhIgmzcZcyI6BStqGpg%3D%3D"}
                    JSONObject jsonObject = new JSONObject(json);
                    String message = jsonObject.optString("message");
                    boolean success = jsonObject.optBoolean("success");
                    String params = jsonObject.optString("params");
                    if (success) {
                        action1.call(params);
                    } else {
                        Toast.makeText(MainActivity.this, "获取测试数据失败---->" + message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "获取测试数据失败---->", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void clickAliPay(View view) {
        requestTestParams(PayType.ALIPAY, new Action1<String>() {
            @Override
            public void call(String params) {
                startDefaultPay(PayType.ALIPAY, params);
            }
        });
    }

    public void clickWeChatPay(View view) {
        requestTestParams(PayType.WECHAT, new Action1<String>() {
            @Override
            public void call(String params) {
                startDefaultPay(PayType.WECHAT, params);
            }
        });
    }

    public void clickWeChatPayRxJava(View view) {
        requestTestParams(PayType.WECHAT, new Action1<String>() {
            @Override
            public void call(String params) {
                startRxJava2Pay(PayType.WECHAT, params);
            }
        });
    }

    public void clickAliPayRxJava(View view) {
        requestTestParams(PayType.ALIPAY, new Action1<String>() {
            @Override
            public void call(String params) {
                startRxJava2Pay(PayType.ALIPAY, params);
            }
        });
    }


    public void startRxJava2Pay(final PayType payType, String paramsString) {
        SmartPay.with(this)
                .payType(payType)
                .params(paramsString)
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
                            Toast.makeText(MainActivity.this, payType.name().toLowerCase() + "支付失败-->" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void startDefaultPay(final PayType payType, String paramsString) {
        SmartPay.with(this)
                .payType(payType)
                .params(paramsString)
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


}
