package greensea.energy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.mapper.DeviceUpload1Mapper;
import greensea.energy.framework.domain.dto.param.LoginTokenParam;
import greensea.energy.framework.domain.model.LoginUserToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreenseaEnergyApplicationTests {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;

    @Test
    public void test() {
//        deviceUploadMapper.createNewTable("3216");
//        ResourceEntity resourceEntity = new ResourceEntity()
        System.out.println( redisUtils.keys("login_token:"));
        LoginTokenParam loginTokenParam = new LoginTokenParam();
        Page<LoginUserToken> page = new Page<>(loginTokenParam.getPageNum(),loginTokenParam.getPageSize());
        Set<String> keys = redisUtils.keys("login_token:");
        List<LoginUserToken> loginUserTokenList = new ArrayList<>();
        for (String key:keys){
            LoginUserToken loginUserToken = (LoginUserToken) redisUtils.getCacheObject(key);
            loginUserTokenList.add(loginUserToken);
        }
        // 计算分页的起始索引和结束索引
        int start = Math.toIntExact(loginTokenParam.getPageNum() - 1) * loginTokenParam.getPageSize();
        int end = Math.min(start + loginTokenParam.getPageSize(), loginUserTokenList.size());

// 获取分页数据
        List<LoginUserToken> pageList = loginUserTokenList.subList(start, end);
        Page<LoginUserToken>loginUserTokenPage =new Page<>(loginTokenParam.getPageNum(),loginTokenParam.getPageSize(),loginUserTokenList.size());
        loginUserTokenPage.setRecords(pageList);
        System.out.println(pageList);
        System.out.println(loginUserTokenPage);
//        resourceEntity.setResourceDescription("3213123");
//        resourceEntity.setResourceName("321");
//        redisUtils.setCacheObject("resourceKey",resourceEntity);
//        redisUtils.setCacheObject("yourKey", 321, 15, TimeUnit.MINUTES);
//        redisUtils.setCacheObject("yourKey", 3211, 15, TimeUnit.MINUTES);
//        System.out.println(redisUtils.getExpirationTime("login_user:10"));
//        redisUtils.deleteObject("321");
//        System.out.println(redisUtils.getCacheObject("yourKey"));
//        LoginUserToken loginUserToken = (LoginUserToken) redisUtils.getCacheObject("login_tokens:FEHZwC5k3a");
//
//        System.out.println(loginUserToken);
//        System.out.println(redisUtils.getCacheObject("login_user:1761687939071496194"));
//        System.out.println(redisUtils.hasKey("login_tokens:FEHZwC5k3a"));
    }

}
