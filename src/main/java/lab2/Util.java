package lab2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class Util {

    /**
     * 获取返回json字符串中的data数据
     * @param json
     * @return
     */
    public static Map<String, Object> getData(String json){
        // 将返回的JSON字符串转换为JSONObject对象
        JSONObject jsonObject = JSON.parseObject(json);
        // 将JSONObject对象转换为Map对象
        Map<String, Object> map = jsonObject.toJavaObject(Map.class);
        Map<String, Object> data = (Map) map.get("data");

        return data;
    }
}
