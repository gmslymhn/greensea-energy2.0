package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.entity.AbnormalEntity;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.mapper.AbnormalMapper;
import greensea.energy.device.service.IAbnormalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AbnormalLogServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 13:49
 * @Version: 1.0
 **/
@Service
public class AbnormalLogServiceimpl implements IAbnormalLogService {
    @Autowired
    private AbnormalMapper abnormalMapper;


    @Override
    public R getAbnormalLogList(DeviceParam deviceParam){
        Page<AbnormalEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<AbnormalEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .orderByDesc("abnormal_id");
        IPage<AbnormalEntity> abnormalEntityIPage = abnormalMapper.selectPage(page, queryWrapper);
        return R.success(abnormalEntityIPage);
    }


}
