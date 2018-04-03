package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:18
 */
public interface SmartPayCall<R> {

    /**
     * @param params 支付平台需要的参数
     */
    R call(Map<String, Object> params);
}
