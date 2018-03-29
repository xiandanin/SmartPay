package com.dyhdyh.smartpay.wechat;

import com.dyhdyh.smartpay.PayType;
import com.dyhdyh.smartpay.SmartPayResult;
import com.dyhdyh.smartpay.SmartPayResultConverter;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/29 11:22
 */
public class WeChatPayResultConverter implements SmartPayResultConverter<SmartPayResult> {
    @Override
    public SmartPayResult convert(Map<String, String> resultMap) {
        SmartPayResult result = new SmartPayResult();
        try {
            result.setPayType(PayType.WECHAT);
            result.setResult(resultMap);
            result.setMessage(resultMap.get("err_str"));
            result.setCode(Integer.parseInt(resultMap.get("err_code")));
            if (result.getCode() == 0) {
                result.setStatus(SmartPayResult.STATUS_SUCCESS);
            } else if (result.getCode() == -2) {
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
