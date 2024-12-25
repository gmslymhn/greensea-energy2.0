package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.GetImgDto;
import greensea.energy.device.domain.entity.ImgEntity;
import greensea.energy.framework.domain.PageParam;

/**
 * @ClassName: IimgService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 13:44
 * @Version: 1.0
 **/
public interface IImgService {
    R getImg(GetImgDto getImgDto);

    R getImgList(PageParam param);

    R updateImg(ImgEntity imgEntity);
}
