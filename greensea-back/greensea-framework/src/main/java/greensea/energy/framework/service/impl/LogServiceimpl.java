package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.framework.domain.dto.param.LoginLogParam;
import greensea.energy.framework.domain.dto.param.LoginTokenParam;
import greensea.energy.framework.domain.dto.param.SysLogParam;
import greensea.energy.framework.domain.entity.LoginLogEntity;
import greensea.energy.framework.domain.entity.SysLogEntity;
import greensea.energy.framework.domain.model.LoginUserToken;
import greensea.energy.framework.mapper.LoginLogMapper;
import greensea.energy.framework.mapper.SysLogMapper;
import greensea.energy.framework.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public R getLoginLog(LoginLogParam loginLogParam){
        Page<LoginLogEntity> page = new Page<>(loginLogParam.getPageNum(),loginLogParam.getPageSize());
        QueryWrapper<LoginLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(loginLogParam.getLoginIp()),"login_ip",loginLogParam.getLoginIp())
                .eq(StringUtils.isNotBlank(loginLogParam.getUserAccount()),"user_account",loginLogParam.getUserAccount())
                .eq("login_type",loginLogParam.getLoginType())
                .orderByDesc("log_id");
        IPage<LoginLogEntity> loginLogIPage = loginLogMapper.selectPage(page, queryWrapper);
        return R.success(loginLogIPage);
    }
    @Override
    public R getSysLog(SysLogParam sysLogParam){
        Page<SysLogEntity> page = new Page<>(sysLogParam.getPageNum(),sysLogParam.getPageSize());
        QueryWrapper<SysLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(sysLogParam.getLoginIp()),"login_ip",sysLogParam.getLoginIp())
                .eq(StringUtils.isNotBlank(sysLogParam.getUserAccount()),"user_account",sysLogParam.getUserAccount())
                .orderByDesc("log_id");
        IPage<SysLogEntity> sysLogIPage = sysLogMapper.selectPage(page, queryWrapper);
        return R.success(sysLogIPage);
    }

    @Override
    public R getLoginToken(LoginTokenParam loginTokenParam){
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
        return R.success(loginUserTokenPage);
    }
}
