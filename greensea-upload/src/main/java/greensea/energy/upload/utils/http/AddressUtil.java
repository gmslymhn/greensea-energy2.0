package greensea.energy.upload.utils.http;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import greensea.energy.upload.domain.model.Address;

/**
 * @ClassName: AddressUtil
 * @Description:获取地址工具类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 01:34
 * @Version: 1.0
 **/
public class AddressUtil {
    /**
     * 根据IP地址获取地理位置
     * @param ip ip地址
     * @return 地址
     */
    public static Address getAddressByIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        if ("127.0.0.1".equals(ip)) {
            return null;
        }
//        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&format=json&query=" + ip;
        String url = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ip;
        JSONObject resJson = JSONObject.parseObject(HttpClientUtils.get(url));
//        System.out.println(resJson.getJSONArray("data").get(0));
        JSONObject data = (JSONObject) resJson.get("data");
//        String location = data.getString("prov")+data.getString("city")+data.getString("district");
        Address address = new Address();
        address.setAreacode(data.getString("areacode"));
        address.setCountry(data.getString("country"));
        address.setCity(data.getString("city"));
        address.setContinent(data.getString("continent"));
        address.setLat(data.getString("lat"));
        address.setLng(data.getString("lng"));
        address.setProv(data.getString("prov"));
        address.setDistrict(data.getString("district"));
        address.setRadius(data.getString("radius"));
        return address;
    }

    public static void main(String[] args) {
        System.out.println(getAddressByIP("27.155.195.218"));
    }
}
