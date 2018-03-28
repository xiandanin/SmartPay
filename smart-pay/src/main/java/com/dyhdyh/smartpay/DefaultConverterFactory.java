package com.dyhdyh.smartpay;

import com.dyhdyh.smartpay.alipay.AliPayResultConverter;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class DefaultConverterFactory implements SmartPayConverterAdapter {

    @Override
    public SmartPayResultConverter adapt(PayType payType) {
        SmartPayResultConverter converter = null;
        if (payType == PayType.WECHAT) {

        } else if (payType == PayType.ALIPAY) {
            converter = new AliPayResultConverter();
        }
        return converter;
    }
}
