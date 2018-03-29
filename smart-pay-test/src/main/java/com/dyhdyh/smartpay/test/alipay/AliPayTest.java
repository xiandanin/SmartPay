package com.dyhdyh.smartpay.test.alipay;

import java.util.Map;

/**
 * @author dengyuhan
 *         created 2018/3/29 10:27
 */
public class AliPayTest {
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String TEST_APPID = "2014100900013222";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String TEST_RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC98aoOKirBjPTKN4vLTi8vpYwWTLG6WJh701BnCl3C5gT3lQQGeXf+ZphXBwD9e3fRyJai9cpxiis7eUbonrqrmUoIvbyiAudoYpqnXVKTcaO/rSisr9aa7IOfMqFHEOS61nIlceJja2txCJfUNanS89aghMcvqHLcJ5m0YKN4cimbUUYPELMVI1PsCJyJUNKFiYorQq1/TWWqbvaKRaDRk8NHJgHsKtZFlyhbnm73NsP69NOdKvjQvTlLfpFZE+l8I61sy8Cz4V2E67bwJ2r9/G4f0FVt+F/U4YVegax4w8+w5Y55veazDJrm2gJ4abNk0EETZwyBLPgsbFR2/y3hAgMBAAECggEAe9iVQ7UUuaxZc3wyJvYsaAmtxGBvRYw8qAgJFZY5ujlWJcPAoyQSLAri62OCrsQRRPRf25MdU1h+hcG2jTfpiLdjAT4NPylbjsE0C0oa7E4dMX4K1kW0TMFHtMZDR93o9TWbqXSO4roIjOPIczImL4iTeYf5g8Z2VbtwSZ71FzNgmuAKLy7eobLfewXo1daqBe0ZDGk75RkQ2NR6cJmbLLMbBlTqAbQyBA8KIdue3gR5rruecgsdoMEEBnBPTrZ07U26l1+m3k2lHjE3EsztsQBMDpUwdFKuq4kk7gRBXolj8zqYoKRiOVWy88bvPz8Vx8Vo6XGa0X0S/yBnM5t3kQKBgQDopuLxmxS/3MWcJsIKRYLKUlnWe/So0HbFsV6ij/So/8rfcWkzh9k+qnnSBHtvkQFsnv0+GLd1Hy8Wad9vCyFDessvhuzQRY2Imk9FotaTkYbrchrz0reJUYY6nUklyCTxEAz2UxyLLEIQO0JAhXH8S18YNuh6hVEphmKT2NxpbQKBgQDRAY8vGqJzvznIcCdXXoJx4Ic65dnPRwoEB1abQHnpYqmf8VH/Styk7By1Ap+luP7X3T6QX4gSR4RqC5krJBoz9nAsucgo+t9aUXrhLV83hOoKWD7znX/C1+0b/osRPoexlBmaaf+n+RNIQ3hxU9uIl6WHT01daBL4GVBanxchxQKBgES/e/R1JS6E6If6FADBBaMPrqhovKVd5JsKjLJw45VE8QgSFUo67IFOEu1ykZ8oNEmKub6twxiC/IEdC/9eRJgSIxSKRFRPGUGyh5ZGRi4ZJMtSTpCaRc34HzgW3lShzfjGC26GpLqje2oceLlkNYieJR2crBn4Z0FkCqExxgAJAoGBAMdW/2NjudE/bzMWlM8lmrBV/2RTWPvyu0DAZv/H7P6FVVbw6M3ebrb1YyPZDr8WxCjKISO9maAlictCqKGW2074GmDuCFPdgi04TUR667eeE0IujEv5yaLiIolyqtyVkQHzSMAXnPht/NANWdBstJOAXyXAov8VhhIOwq7L0VopAoGAeZBY2TXgCvvD/7sDQ8hQjxEI0IVORT35bGoNtSce2QDa9lG5cwXOn775TcAhCiWAmLexRKo+1Q/aYVAYTTB6ImEvE5DVb+3vz7a+1Wgw8yz+pijwabVRT0oggBYRWFyNRVjIrrwg3Tg5Me+P65/p64ztOhDotbQl6mtF0CshC44=";

    public static String generateOrderStr(String appId, String rsa2Private) {
        String title = "支付测试";
        String desc = "SmartPay支付测试";
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(appId, title, desc, true);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String sign = OrderInfoUtil2_0.getSign(params, rsa2Private, true);
        final String orderInfo = orderParam + "&" + sign;
        return orderInfo;
    }
}
