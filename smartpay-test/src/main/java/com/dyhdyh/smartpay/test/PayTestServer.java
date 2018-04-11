package com.dyhdyh.smartpay.test;

import android.os.Handler;

import com.dyhdyh.smartpay.PayType;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 测试服务
 *
 * @author dengyuhan
 *         created 2018/4/11 15:38
 */
public class PayTestServer {
    private static Handler mHandler = new Handler();


    /**
     * 去服务器获取动态的支付参数
     *
     * @param payType
     */
    public static void requestTestParams(PayType payType, Callback callback) {
        String url = "http://sample.dyhdyh.com/smartpay/payment.php?pay_type=" + payType.name().toLowerCase();
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        new OkHttpClient.Builder().build()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(call, e);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    callback.onResponse(call, response);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

    }
}
