package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.AddGmDto;
import greensea.energy.framework.domain.dto.GmLoginDto;
import greensea.energy.framework.domain.dto.GmUpdatePasswordDto;
import greensea.energy.framework.domain.dto.UpdateGmDto;
import greensea.energy.framework.domain.dto.param.GmParam;

/**
 * @ClassName: IGmService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:37
 * @Version: 1.0
 **/
public interface IGmService {
    R loginGm(GmLoginDto gmLoginDto);

    R addGm(AddGmDto addGmDto);

    R logoutGm();

    R logoutBytoken(String token);

    R getGmSelfMsg();

    R gmList(GmParam gmParam);

    R getGmMsg(Integer gmId);

    R updateGmMsg(UpdateGmDto updateGmDto);

    R updateGmPassword(GmUpdatePasswordDto gmUpdatePasswordDto);

    R daleteGmById(Integer gmId);
}
