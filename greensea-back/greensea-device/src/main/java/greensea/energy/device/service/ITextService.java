package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.GetTextDto;
import greensea.energy.device.domain.entity.TextEntity;
import greensea.energy.framework.domain.PageParam;

/**
 * @ClassName: ITextService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 17:27
 * @Version: 1.0
 **/
public interface ITextService {

    R getText(String textKey);

    R getText1(GetTextDto getTextDto);

    R getTextList(PageParam param);

    R updateText(TextEntity textEntity);
}
