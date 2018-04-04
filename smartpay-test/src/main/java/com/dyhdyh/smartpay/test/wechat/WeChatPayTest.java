package com.dyhdyh.smartpay.test.wechat;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dengyuhan
 * created 2018/3/29 10:27
 */
public class WeChatPayTest {

    public static String testJson;

    public static Map<String, String> getTestParams() throws NullPointerException {
        Map<String, String> map = new HashMap<>();
        try {
            if (TextUtils.isEmpty(testJson)) {
                throw new NullPointerException();
            }
            JSONObject jsonObject = new JSONObject(testJson);
            map.put("wechat_appid", jsonObject.optString("appid"));
            map.put("wechat_partnerid", jsonObject.optString("partnerid"));
            map.put("wechat_package", jsonObject.optString("package"));
            map.put("wechat_noncestr", jsonObject.optString("noncestr"));
            map.put("wechat_timestamp", jsonObject.optString("timestamp"));
            map.put("wechat_prepayid", jsonObject.optString("prepayid"));
            map.put("wechat_sign", jsonObject.optString("sign"));

            WeChatPayTest.requestGetTestParams();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 从服务器上获取最新的测试参数
     */
    public static void requestGetTestParams() {
        Request request = new Request.Builder()
                .get()
                .url("http://wxpay.wxutil.com/pub_v2/app/app_pay.php").build();
        new OkHttpClient.Builder().build()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        testJson = response.body().string();
                    }
                });

    }
}
