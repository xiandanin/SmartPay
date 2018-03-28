package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:18
 */
public interface SmartPayCall<Params extends SmartPayParams,R> {

    /**
     * @param params 支付平台需要的参数
     * @param extras 开发者额外的参数
     */
    R call(Params params, Map<String, Object> extras);
}
