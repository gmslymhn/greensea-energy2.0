package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.dto.AddContactDto;

/**
 * @ClassName: IContactService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-19 20:26
 * @Version: 1.0
 **/
public interface IContactService {
    R addContactUs(AddContactDto addContactDto);

    R getContactUsList(PageParam param);
}
