package com.dyhdyh.smartpay.alipay;

import com.dyhdyh.smartpay.SmartPayParams;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:25
 */
public class AliPayParams implements SmartPayParams {
    private boolean mShowPayLoading;
    private String mOrderStr;

    /**
     * @param orderStr 该字段由服务端提供
     */
    public AliPayParams(String orderStr) {
        this.mOrderStr = orderStr;
        this.mShowPayLoading = true;
    }

    public boolean isShowPayLoading() {
        return mShowPayLoading;
    }

    public AliPayParams setShowPayLoading(boolean showPayLoading) {
        this.mShowPayLoading = showPayLoading;
        return this;
    }

    public String getOrderStr() {
        return mOrderStr;
    }

    public AliPayParams setOrderStr(String orderStr) {
        this.mOrderStr = orderStr;
        return this;
    }
}
