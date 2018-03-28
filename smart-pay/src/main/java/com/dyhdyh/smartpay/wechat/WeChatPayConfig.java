package com.dyhdyh.smartpay.wechat;

import com.dyhdyh.smartpay.SmartPaymentConfig;

/**
 * @author dengyuhan
 *         created 2018/3/27 17:25
 */
public class WeChatPayConfig implements SmartPaymentConfig {
    private boolean mShowPayLoading;
    private String mOrderStr;

    /**
     * @param orderStr 该字段由服务端提供
     */
    public WeChatPayConfig() {
        this.mShowPayLoading = true;
    }

    public boolean isShowPayLoading() {
        return mShowPayLoading;
    }

    public WeChatPayConfig setShowPayLoading(boolean showPayLoading) {
        this.mShowPayLoading = showPayLoading;
        return this;
    }

    public String getOrderStr() {
        return mOrderStr;
    }

    public WeChatPayConfig setOrderStr(String orderStr) {
        this.mOrderStr = orderStr;
        return this;
    }
}
