package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.param.LoginLogParam;
import greensea.energy.framework.domain.dto.param.SysLogParam;

/**
 * @ClassName: LogService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 21:25
 * @Version: 1.0
 **/
public interface ILogService {
    R getLoginLog(LoginLogParam loginLogParam);

    R getSysLog(SysLogParam sysLogParam);
}
