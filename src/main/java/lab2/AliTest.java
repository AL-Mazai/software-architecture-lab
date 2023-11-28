package lab2;

import lab1.A;

import java.util.Map;

public class AliTest {
    public static void main(String[] args) {
        // 创建IPay实例
        IPay aliPay = new AliPay();

        // 创建Client对象
        Client client = new Client();
        //注入接口实现类
        client.setIPay(aliPay);

        // 调用支付接口是否可用
        String token = client.isAvailable();
        if(token == null){
            System.out.println("接口调用失败......");
        }else {
            System.out.println("接口调用成功......");
        }
        System.out.println("token：" + token);
        System.out.println();

        // 支付
        String orderId = client.pay("20170100", "20211120270", 10);
        System.out.println("订单编号：" + orderId);
        System.out.println();

        // 查询支付订单
        Map<String, Object> orderResult = client.query(orderId);
        System.out.println("订单内容：");
        System.out.println(orderResult);
        System.out.println();

        // 调用退款接口
        String refundId = client.refunds("20170100", "20211120270", 50);
        System.out.println("退款订单编号:" + refundId);
        System.out.println();

        // 查询退款单
        Map<String, Object> refundResult = client.queryRefunds(refundId);
        System.out.println("退款单内容：" + refundResult);
        System.out.println();
    }
}
