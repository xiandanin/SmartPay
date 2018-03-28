package com.dyhdyh.smartpay.alipay;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPayResultConverter;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:21
 */
public class AliPayResultConverter implements SmartPayResultConverter<SmartPayResult> {
    @Override
    public SmartPayResult convert(Map<String, String> resultMap) {
        SmartPayResult result = new SmartPayResult();
        try {
            result.setPayType(PayType.ALIPAY);
            result.setResult(resultMap);
            result.setMessage(resultMap.get("memo"));
            result.setStatusCode(Integer.parseInt(resultMap.get("resultStatus")));
            result.setSuccess(result.getStatusCode() == 9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
