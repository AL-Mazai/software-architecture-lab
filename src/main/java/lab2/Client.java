package lab2;

import java.util.Map;
import java.util.Objects;

public class Client {
    private IPay iPay;

    //注入
    public void setIPay(IPay iPay){
        this.iPay = iPay;
    }

    public String isAvailable() {
        return iPay.isAvailable();
    }

    public String pay(String mchid, String appid, double amount) {
       return iPay.pay(mchid, appid, amount);
    }

    public Map<String, Object> query(String orderid) {
        return iPay.query(orderid);
    }

    public String refunds(String mchid, String appid, double amount) {
        return iPay.refunds(mchid, appid, amount);
    }

    public Map<String, Object> queryRefunds(String refundid) {
        return iPay.queryRefunds(refundid);
    }
}
