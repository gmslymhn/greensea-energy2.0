package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.framework.domain.dto.param.LoginLogParam;
import greensea.energy.framework.domain.dto.param.SysLogParam;
import greensea.energy.framework.domain.entity.LoginLogEntity;
import greensea.energy.framework.domain.entity.SysLogEntity;
import greensea.energy.framework.mapper.LoginLogMapper;
import greensea.energy.framework.mapper.SysLogMapper;
import greensea.energy.framework.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LogServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 21:24
 * @Version: 1.0
 **/
@Service
public class LogServiceimpl implements ILogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public R getLoginLog(LoginLogParam loginLogParam){
        Page<LoginLogEntity> page = new Page<>(loginLogParam.getPageNum(),loginLogParam.getPageSize());
        QueryWrapper<LoginLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(loginLogParam.getLoginIp()),"login_ip",loginLogParam.getLoginIp())
                .eq(StringUtils.isNotBlank(loginLogParam.getUserAccount()),"user_account",loginLogParam.getUserAccount())
                .eq("login_type",loginLogParam.getLoginType());
        IPage<LoginLogEntity> loginLogIPage = loginLogMapper.selectPage(page, queryWrapper);
        return R.success(loginLogIPage);
    }
    @Override
    public R getSysLog(SysLogParam sysLogParam){
        Page<SysLogEntity> page = new Page<>(sysLogParam.getPageNum(),sysLogParam.getPageSize());
        QueryWrapper<SysLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(sysLogParam.getLoginIp()),"login_ip",sysLogParam.getLoginIp())
                .eq(StringUtils.isNotBlank(sysLogParam.getUserAccount()),"user_account",sysLogParam.getUserAccount());
        IPage<SysLogEntity> sysLogIPage = sysLogMapper.selectPage(page, queryWrapper);
        return R.success(sysLogIPage);
    }
}
