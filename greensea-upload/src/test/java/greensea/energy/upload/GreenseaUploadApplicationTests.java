package greensea.energy.upload;

import greensea.energy.upload.domain.model.Device;
import greensea.energy.upload.mapper.UploadMapper;
import greensea.energy.upload.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GreenseaUploadApplicationTests {

    @Autowired
    private UploadMapper uploadMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Test
    void contextLoads() {
//        UploadEntity2 uploadEntity2 = new UploadEntity2(1.11F,1.11F,1.11F, 1.11F, 1F, 1F, 1F, 1F, 1F, 1F,1.11F, 1F, 1F, 1F, 1F, 1F, 1F, 1F, 1F, 1F,1.11F,1.11F,1.11F,1.11F,1.11F,1.11F,1.11F);
//        uploadMapper.uplpad2("inv_1",uploadEntity2);
//        System.out.println(redisUtils.getCacheObject(getTokenKey(deviceKey));
//        JSONObject json =JSONObject.parseObject((String) redisUtils.getCacheObject("device_number:FZFC-2241-2211-02"));
//        System.out.println((Device) redisUtils.getCacheObject("device_number:FZFC-2241-2211-02"));
    }

}
