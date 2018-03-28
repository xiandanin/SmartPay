package com.dyhdyh.smartpay;

/**
 * @author dengyuhan
 *         created 2018/3/28 20:35
 */
public interface SmartPayCallAdapter<A> {

    /** call执行的代理方法 */
    A adapt();
}
