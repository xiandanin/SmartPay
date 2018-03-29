package com.dyhdyh.smartpay.wechat;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/29 10:37
 */
public class WeChatPayParams extends SmartPayParams {
    public static final String KEY_APPID = getKey(PayType.WECHAT, "appid");
    public static final String KEY_PARTNERID = getKey(PayType.WECHAT, "partnerid");
    public static final String KEY_PACKAGE = getKey(PayType.WECHAT, "package");
    public static final String KEY_NONCESTR = getKey(PayType.WECHAT, "noncestr");
    public static final String KEY_TIMESTAMP = getKey(PayType.WECHAT, "timestamp");
    public static final String KEY_PREPAYID = getKey(PayType.WECHAT, "prepayid");
    public static final String KEY_SIGN = getKey(PayType.WECHAT, "sign");

    public static Map<String, Object> build(String appid, String partnerid, String noncestr, String timestamp, String prepayid, String sign) {
        return build(appid, partnerid, "Sign=WXPay", noncestr, timestamp, prepayid, sign);
    }

    public static Map<String, Object> build(String appid, String partnerid, String packageValue, String noncestr, String timestamp, String prepayid, String sign) {
        Map<String, Object> params = new HashMap<>();
        params.put(KEY_APPID, appid);
        params.put(KEY_PARTNERID, partnerid);
        params.put(KEY_PACKAGE, packageValue);
        params.put(KEY_NONCESTR, noncestr);
        params.put(KEY_TIMESTAMP, timestamp);
        params.put(KEY_PREPAYID, prepayid);
        params.put(KEY_SIGN, sign);
        return params;
    }
}
