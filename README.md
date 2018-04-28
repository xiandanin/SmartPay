# SmartPay

### 特点
|特性|描述||
|:-|:-|:-|
|统一调用|无论哪个支付平台，都是一样的调用方式|
|RxJava|支持RxJava的调用方式|`.callAdapter(RxJavaCallAdapter.create(this))`
|RxJava2|支持RxJava2的调用方式|`.callAdapter(RxJava2CallAdapter.create(this))`
|自定义Model|支持对支付结果数据自定义Model|`.converterAdapter(adapter)`
|定制化|支持对平台支付自定义封装|`CostomCallAdapter implements SmartPayCallAdapter `
|测试|提供在线获取测试参数|

### Gradle
```
//必选
implementation 'com.dyhdyh.smartpay:smartpay:1.0.1'

//可选 如果需要RxJava1支持再引入
implementation 'com.dyhdyh.smartpay:smartpay-adapter-rxjava:1.0.1'

//可选 如果需要RxJava2支持再引入
implementation 'com.dyhdyh.smartpay:smartpay-adapter-rxjava2:1.0.1'

//可选 提供获取测试参数的接口，如果需要测试支持再引入
debugImplementation 'com.dyhdyh.smartpay:smartpay-test:1.0.1'
```


### 构造支付平台参数
```
//build方法里的参数由服务端提供
Map<String, Object> params = new HashMap<>();
if (PayType.ALIPAY == payType) {
	params = AliPayParams.build(orderStr);
} else if (PayType.WECHAT == payType) {
	params = WeChatPayParams.build(appid,partnerid,noncestr,timestamp,prepayid,sign);
}
```


### 普通调用方式
```
SmartPay.with(this)
        .payType(payType)
        .params(params)//可以是Map也可以是json,参考MainActivity
        .setOnPaymentListener(new OnSmartPaymentListener<SmartPayResult>() {
            @Override
            public void onResult(SmartPayResult result) {
                if (result.getStatus() == SmartPayResult.STATUS_SUCCESS) {
                    Toast.makeText(this,"支付成功", Toast.LENGTH_SHORT).show();
                } else if (result.getStatus() == SmartPayResult.STATUS_CANCEL) {
                    Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
```

### RxJava支持
通过创建`RxJavaCallAdapter`，转换成RxJava的调用方式

```
SmartPay.with(this)
        .payType(payType)
        .params(params)//可以是Map也可以是json,参考MainActivity
        .callAdapter(RxJavaCallAdapter.create(this))
        .as(Observable.class)
        .subscribe(new Action1<SmartPayResult>() {
            @Override
            public void call(SmartPayResult result) throws Exception {
                if (result.getStatus() == SmartPayResult.STATUS_SUCCESS) {
                    Toast.makeText(this,"支付成功", Toast.LENGTH_SHORT).show();
                } else if (result.getStatus() == SmartPayResult.STATUS_CANCEL) {
                    Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
```

### RxJava2调用方式
通过创建`RxJava2CallAdapter`，转换成RxJava2的调用方式

```
SmartPay.with(this)
        .payType(payType)
        .params(params)//可以是Map也可以是json,参考MainActivity
        .callAdapter(RxJava2CallAdapter.create(this))
        .as(Observable.class)
        .subscribe(new Consumer<SmartPayResult>() {
            @Override
            public void accept(SmartPayResult result) throws Exception {
                if (result.getStatus() == SmartPayResult.STATUS_SUCCESS) {
                    Toast.makeText(this,"支付成功", Toast.LENGTH_SHORT).show();
                } else if (result.getStatus() == SmartPayResult.STATUS_CANCEL) {
                    Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
```

### 自定义支付结果Model
参考[DefaultConverterAdapter](https://github.com/dengyuhan/SmartPay/blob/master/smartpay/src/main/java/com/dyhdyh/smartpay/DefaultConverterAdapter.java)

```
.converterAdapter(adapter)
```


### 测试支持
```
//从服务器获取测试json
PayTestServer.requestTestParams(payType, new okhttp3.Callback() {
    @Override
    public void onFailure(Call call, final IOException e) {
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        try {
            final String json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            String message = jsonObject.optString("message");
            boolean success = jsonObject.optBoolean("success");
            //平台参数
            String params = jsonObject.optString("params");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
});
```
