package com.dyhdyh.smartpay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/28 19:20
 */
public interface SmartPayResultConverter<T> {

    T convert(Map<String, String> resultMap);

}
