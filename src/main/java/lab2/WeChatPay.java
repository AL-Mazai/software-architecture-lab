package lab2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付类
 */
public class WeChatPay implements IPay {
    //接口地址
    private static final String BASE_URL = "http://121.36.106.217:9005/wechat";
    //token
    private static String token = null;
    //OKHttp进行网络请求
    private final OkHttpClient client;

    //初始化WeChatPay对象，创建OkHttpClient实例
    public WeChatPay() {
        this.client = new OkHttpClient();
    }



    @Override
    public String isAvailable() {
        Request request = new Request.Builder()
                .url(BASE_URL + "/isRunning")
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                //获取返回结果中的data数据
                Map<String, Object> data = Util.getData(body);
                // 获取token
                token = (String) data.get("token");
            } else {
                System.out.println("接口不可用.......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return token;
    }


    @Override
    public String pay(String mchid, String appid, double amount) {
        String orderId = null;

        RequestBody requestBody = new FormBody.Builder()
                .add("mchid", mchid)
                .add("appid", appid)
                .add("amount", String.valueOf(amount))
                .add("content", "支付测试")
                .add("token", token)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/transaction/pay")
                .post(requestBody)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("支付成功......");
                String body = response.body().string();
                System.out.println(body);

                Map<String, Object> data = Util.getData(body);
                orderId = (String) data.get("orderid");
            } else {
                System.out.println("支付失败......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }

        return orderId;
    }


    @Override
    public Map<String, Object> query(String orderId) {
        Map<String, Object> result = null;
        Request request = new Request.Builder()
                .url(BASE_URL + "/order/query?orderID=" + orderId + "&token=" + token)
                .get()
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("查询订单成功......");
                String body = response.body().string();
                Map<String, Object> data = Util.getData(body);
                result = data;
            } else {
                System.out.println("查询订单失败......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        return result;
    }


    @Override
    public String refunds(String mchid, String appid, double amount) {
        String refundId = null;

        RequestBody requestBody = new FormBody.Builder()
                .add("mchid", mchid)
                .add("appid", appid)
                .add("amount", String.valueOf(amount))
                .add("content", "退款测试")
                .add("token", token)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/transaction/refunds")
                .post(requestBody)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("退款成功......");
                String body = response.body().string();
                System.out.println(body);

                Map<String, Object> data = Util.getData(body);
                refundId = (String) data.get("orderid");
            } else {
                System.out.println("退款失败......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        return refundId;
    }


    @Override
    public Map<String, Object> queryRefunds(String refundId) {
        Map<String, Object> result = null;
        Request request = new Request.Builder()
                .url(BASE_URL + "/order/queryRefunds?orderID=" + refundId + "&token=" + token)
                .get()
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("查询退款订单成功......");
                String body = response.body().string();
                Map<String, Object> data = Util.getData(body);
                result = data;
            } else {
                System.out.println("查询退款订单失败......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        return result;
    }
}
