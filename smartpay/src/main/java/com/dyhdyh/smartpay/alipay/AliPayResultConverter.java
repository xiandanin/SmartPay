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
            result.setCode(Integer.parseInt(resultMap.get("resultStatus")));
            if (result.getCode() == 9000) {
                result.setStatus(SmartPayResult.STATUS_SUCCESS);
            } else if (result.getCode() == 6001) {
                result.setStatus(SmartPayResult.STATUS_CANCEL);
            } else {
                result.setStatus(SmartPayResult.STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
