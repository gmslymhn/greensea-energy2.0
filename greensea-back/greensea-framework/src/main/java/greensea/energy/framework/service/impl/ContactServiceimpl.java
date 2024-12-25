package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.http.IpUtil;
import greensea.energy.common.utils.http.ServletUtils;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.dto.AddContactDto;
import greensea.energy.framework.domain.entity.ContactEntity;
import greensea.energy.framework.mapper.ContactMapper;
import greensea.energy.framework.service.IContactService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ContactServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-19 20:26
 * @Version: 1.0
 **/
@Service
public class ContactServiceimpl implements IContactService{

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public R addContactUs(AddContactDto addContactDto){
        ContactEntity contactEntity =new ContactEntity(addContactDto);
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        contactEntity.setContactIp(ip);
        contactMapper.insert(contactEntity);
        return R.success("添加成功！");
    }
    @Override
    public R getContactUsList(PageParam param){
        Page<ContactEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        QueryWrapper<ContactEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("contact_id");
        IPage<ContactEntity> contactEntityIPage = contactMapper.selectPage(page, queryWrapper);
        return R.success(contactEntityIPage);
    }
}
