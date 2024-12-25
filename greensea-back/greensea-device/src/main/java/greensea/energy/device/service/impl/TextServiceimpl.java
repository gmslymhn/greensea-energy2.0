package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.dto.GetTextDto;
import greensea.energy.device.domain.entity.TextEntity;
import greensea.energy.device.mapper.TextMapper;
import greensea.energy.device.service.ITextService;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.web.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TextServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 17:28
 * @Version: 1.0
 **/
@Service
public class TextServiceimpl implements ITextService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TextMapper textMapper;

    @Override
    public R getText(String textKey){
        String privacyPolicy = (String) redisUtils.getCacheObject(textKey);
        if (StringUtils.isNotBlank(privacyPolicy)){
            return R.success(privacyPolicy);
        }
        QueryWrapper<TextEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("text_key",textKey);
        TextEntity textEntity = textMapper.selectOne(queryWrapper);
        if (StringUtils.isNotBlank(textEntity.getTextContent())){
            redisUtils.setCacheObject(textKey,textEntity.getTextContent());
            return R.success(textEntity.getTextContent());
        }
        return R.error("文字异常！");
    }

    @Override
    public R getText1(GetTextDto getTextDto){
        String privacyPolicy = (String) redisUtils.getCacheObject(getTextDto.getTextKey()+getTextDto.getTextType());
        if (StringUtils.isNotBlank(privacyPolicy)){
            return R.success(privacyPolicy);
        }
        QueryWrapper<TextEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("text_key",getTextDto.getTextKey())
                .eq("text_type",getTextDto.getTextType());
        TextEntity textEntity = textMapper.selectOne(queryWrapper);
        if (StringUtils.isNotBlank(textEntity.getTextContent())){
            redisUtils.setCacheObject(getTextDto.getTextKey()+textEntity.getTextType(),textEntity.getTextContent());
            return R.success(textEntity.getTextContent());
        }
        return R.error("文字异常！");
    }


    @Override
    public R getTextList(PageParam param){
        Page<TextEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        IPage<TextEntity> textEntityIPage = textMapper.selectPage(page, null);
        return R.success(textEntityIPage);
    }

    @Override
    public R updateText(TextEntity textEntity){
        TextEntity textEntity1 = textMapper.selectById(textEntity.getTexId());
        if (ObjectUtils.isNull(textEntity1)){
            return R.error("系统异常！");
        }
        textEntity.setTextKey(null);
        textEntity.setUpdateTime(null);
        textEntity.setUpdateGm(SecurityUtils.getUserAccount());
        textMapper.updateById(textEntity);
        redisUtils.setCacheObject(textEntity1.getTextKey()+textEntity1.getTextType(),textEntity.getTextContent());
        return R.success("修改成功！");
    }
}
