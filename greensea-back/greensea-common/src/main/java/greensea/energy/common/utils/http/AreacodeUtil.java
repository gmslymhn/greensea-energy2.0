package greensea.energy.common.utils.http;

import com.alibaba.fastjson2.JSONObject;
import greensea.energy.common.utils.StringUtils;

/**
 * @ClassName: AreacodeUtil
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-11 11:09
 * @Version: 1.0
 **/
public class AreacodeUtil {
    /**
     * 根据IP地址获取地理位置
     * @param ip ip地址
     * @return 地址
     */
    public static String getAreacodeByIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        if ("127.0.0.1".equals(ip)) {
            return "局域网，无法获取位置";
        }
        String url = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ip;
        JSONObject resJson = JSONObject.parseObject(HttpClientUtils.get(url));
//        System.out.println(resJson.getJSONArray("data").get(0));
        JSONObject data = (JSONObject) resJson.get("data");
        String location = data.getString("areacode");
        return location;
    }

    public static void main(String[] args) {
        System.out.println(getAreacodeByIP("110.182.135.225"));
    }
}
