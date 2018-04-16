package com.dyhdyh.smartpay;

import com.dyhdyh.smartpay.alipay.AliPayResultConverter;
import com.dyhdyh.smartpay.wechat.WeChatPayResultConverter;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:48
 */
public class DefaultConverterAdapter implements SmartPayResultConverterAdapter {

    @Override
    public SmartPayResultConverter adapt(PayType payType) {
        SmartPayResultConverter converter = null;
        if (payType == PayType.WECHAT) {
            converter = new WeChatPayResultConverter();
        } else if (payType == PayType.ALIPAY) {
            converter = new AliPayResultConverter();
        }
        return converter;
    }
}
