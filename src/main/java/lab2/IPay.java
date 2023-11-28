package lab2;

import java.util.Map;
import java.util.Objects;

public interface IPay {
    /**
     * 查询接口是否可用：由于其他方法要使用token，所以根据返回的token是否为空来判断
     * @return
     */
    String isAvailable();
    /**
     * 支付成功返回订单编号
     * @param mchid
     * @param appid
     * @param amount
     */
    String pay(String mchid, String appid, double amount);
    /**
     * 查询支付订单
     * @param orderId
     * @return
     */
    Map<String, Object> query(String orderId);
    /**
     * 退款成功返回退款订单编号
     * @param mchid
     * @param appid
     * @param amount
     * @return
     */
    String refunds(String mchid, String appid, double amount);
    /**
     * 查询退款订单
     * @param refundId
     * @return
     */
    Map<String, Object> queryRefunds(String refundId);
}
