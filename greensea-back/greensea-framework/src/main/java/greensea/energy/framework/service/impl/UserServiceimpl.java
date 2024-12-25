package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.common.utils.http.ServletUtils;
import greensea.energy.framework.domain.dto.*;
import greensea.energy.framework.domain.dto.param.UserParam;
import greensea.energy.framework.domain.entity.*;
import greensea.energy.framework.domain.model.LoginUser;
import greensea.energy.framework.domain.model.LoginUserToken;
import greensea.energy.framework.domain.vo.MsgVo;
import greensea.energy.framework.jwt.security.AuthenticationContextHolder;
import greensea.energy.framework.mapper.RoleMapper;
import greensea.energy.framework.mapper.UserGmMapper;
import greensea.energy.framework.mapper.UserMapper;
import greensea.energy.framework.mapper.UserMsgMapper;
import greensea.energy.framework.service.IUserService;
import greensea.energy.framework.web.SecurityUtils;
import greensea.energy.framework.web.service.LoginService;
import greensea.energy.framework.web.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:40
 * @Version: 1.0
 **/
@Service
public class UserServiceimpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMsgMapper userMsgMapper;
    @Autowired
    private UserGmMapper userGmMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private FileServiceImpl fileServicel;

    @Override
    public R loginUser(UserLoginDto userLoginDto){
        Authentication authentication = null;
        try{
//            // 创建一个认证令牌，包含用户名和密码
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUserAccount(), userLoginDto.getUserPassword());
//            // 调用 AuthenticationManager 进行身份验证
            AuthenticationContextHolder.setContext(authenticationToken);
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return loginService.addLoginFailFrequency(userLoginDto.getUserAccount());
        }
        // 如果身份验证成功，可以从认证对象中获取用户详细信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if (loginUser.getUserType().equals("B")){
            Map<String, Object> map = getMap(loginUser);
            return R.success(map);
        }
        return R.error("账号异常！");
    }
    private Map<String, Object> getMap(LoginUser loginUser){
        String token = tokenService.createToken(loginUser);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        UserEntity userEntity = userMapper.selectById(loginUser.getUserId());
        map.put("lastLoginTime",userEntity.getLastLoginTime());
        map.put("lastLoginIp",userEntity.getLastLoginIp());
        map.put("lastLoginLocation",userEntity.getLastLoginLocation());
        userEntity.setLoginTotal(userEntity.getLoginTotal()+1);
        LoginUserToken loginUserToken =tokenService.getLoginUserToken1(token);
        userEntity.setLastLoginIp(loginUserToken.getIpaddr());
        userEntity.setLastLoginLocation(loginUserToken.getLoginLocation());
        userEntity.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(userEntity);
        return map;
    }

    @Override
    public R addUser(AddUserDto addUserDto){
        QueryWrapper<UserGmEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", addUserDto.getUserAccount());
        UserGmEntity userGmEntity = userGmMapper.selectOne(queryWrapper);
        QueryWrapper<UserGmEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("email", addUserDto.getUserEmail());
        UserGmEntity userGmEntity1 = userGmMapper.selectOne(queryWrapper1);
        if (ObjectUtils.isNotNull(userGmEntity)) {
            return R.error("账号已注册！");
        }
        if(ObjectUtils.isNotNull(userGmEntity1)){
            return R.error("邮箱已注册！");
        }
        userGmEntity = get1(addUserDto);
        userGmMapper.insert(userGmEntity);
        UserGmEntity userGmEntity2 = userGmMapper.selectOne(queryWrapper);
        UserEntity userEntity = get2(addUserDto,userGmEntity2);
        userMapper.insert(userEntity);
        UserMsgEntity userMsgEntity =get3(addUserDto,userGmEntity2);
        userMsgMapper.insert(userMsgEntity);
        return R.success("注册成功！");
    }
    @Override
    public R verifyRegister(String userAccoount, String userEmail){
        QueryWrapper<UserGmEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userEmail);
        UserGmEntity userGmEntity = userGmMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(userGmEntity)){
            return R.error("该邮箱已注册！");
        }
        QueryWrapper<UserGmEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("account", userAccoount);
        UserGmEntity userGmEntity1 = userGmMapper.selectOne(queryWrapper1);
        if (ObjectUtils.isNotNull(userGmEntity1)){
            return R.error("该账号已注册！");
        }
        return loginService.addRegisterVerify(userAccoount,userEmail);

    }
    @Override
    public R logoutUser(){
        HttpServletRequest request = ServletUtils.getRequest();
        LoginUserToken loginUserToken =  tokenService.getLoginUserToken(request);
        tokenService.deleteToken(loginUserToken);
        return R.success("退出成功！");
    }
    private UserGmEntity get1(AddUserDto addUserDto){
        UserGmEntity userGmEntity = new UserGmEntity();
        userGmEntity.setAccount(addUserDto.getUserAccount());
        userGmEntity.setEmail(addUserDto.getUserEmail());
        userGmEntity.setType("B");
        userGmEntity.setState(true);
        userGmEntity.setDelFlag(0);
        return userGmEntity;
    }
    private UserEntity get2(AddUserDto addUserDto,UserGmEntity userGmEntity){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userGmEntity.getId());
        userEntity.setUserAccount(addUserDto.getUserAccount());
        userEntity.setUserNickname(addUserDto.getUserNickname());
        userEntity.setUserType("4");
        userEntity.setUserState(true);
        userEntity.setDelFlag(0);
        userEntity.setLoginTotal(0);
        userEntity.setUserPassword(SecurityUtils.encryptPassword(addUserDto.getUserPassword()));
        return userEntity;
    }
    private UserMsgEntity get3(AddUserDto addUserDto,UserGmEntity userGmEntity){
        UserMsgEntity userMsgEntity = new UserMsgEntity();
        userMsgEntity.setUserId(userGmEntity.getId());
        userMsgEntity.setUserEmail(addUserDto.getUserEmail());
        userMsgEntity.setDelFlag(0);
//        userMsgEntity.setUserPhone(addUserDto.getUserPhone());
        return userMsgEntity;
    }
    @Override
    public R getUserSelfMsg(){
        Integer id = SecurityUtils.getUserId();
        UserEntity userEntity = userMapper.selectById(id);
        UserMsgEntity userMsgEntity = userMsgMapper.selectById(id);
        if (ObjectUtils.isNull(userMsgEntity)||ObjectUtils.isNull(userEntity)){
            return R.error("用户异常");
        }
        MsgVo msgVo = get1(userEntity,userMsgEntity);
        return R.success(msgVo);
    }
    private MsgVo get1(UserEntity userEntity,UserMsgEntity userMsgEntity){
        MsgVo msgVo = new MsgVo();
        msgVo.setAccount(userEntity.getUserAccount());
        msgVo.setNickname(userEntity.getUserNickname());
        msgVo.setLastLoginIp(userEntity.getLastLoginIp());
        msgVo.setLastLoginTime(userEntity.getLastLoginTime());
        msgVo.setPhone(userMsgEntity.getUserPhone());
        msgVo.setEmail(userMsgEntity.getUserEmail());
        msgVo.setLastLoginLocation(userEntity.getLastLoginLocation());
        RoleEntity roleEntity = roleMapper.selectById(userEntity.getUserType());
        msgVo.setRole(roleEntity.getRoleName());
        if (ObjectUtils.isNotNull(userMsgEntity.getUserAvatar())){
            msgVo.setAvatarUrl(fileServicel.getTemporaryUrl(userMsgEntity.getUserAvatar()));
        } else {
            msgVo.setAvatarUrl(fileServicel.getTemporaryUrl(1));
        }
        return msgVo;
    }

    @Override
    public R userList(UserParam userParam){
        Page<UserEntity> page = new Page<>(userParam.getPageNum(),userParam.getPageSize());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userParam.getUserAccount()),"user_account",userParam.getUserAccount())
                .eq(ObjectUtils.isNotNull(userParam.getUserType()),"user_type",userParam.getUserType());
        IPage<UserEntity> userIPage = userMapper.selectPage(page, queryWrapper);
        return R.success(userIPage);
    }
    @Override
    public R getUserMsg(Integer userId){
        UserEntity userEntity = userMapper.selectById(userId);
        UserMsgEntity userMsgEntity = userMsgMapper.selectById(userId);
        MsgVo msgVo = get1(userEntity,userMsgEntity);
        return R.success(msgVo);
    }
    @Override
    public R updateUserMsg(UpdateUserDto updateUserDto){
        UserGmEntity userGmEntity = userGmMapper.selectById(updateUserDto.getUserId());
        if (ObjectUtils.isNull(userGmEntity)){
            return R.error("用户不存在！");
        }
        UserMsgEntity userMsgEntity = new UserMsgEntity();
        userMsgEntity.setUserId(updateUserDto.getUserId());
        if (StringUtils.isNotBlank(updateUserDto.getUserEmail())||ObjectUtils.isNotNull(updateUserDto.getState())){
            userGmEntity.setEmail(updateUserDto.getUserEmail());
            userGmEntity.setState(updateUserDto.getState());
            userMsgEntity.setUserEmail(updateUserDto.getUserEmail());
            userGmMapper.updateById(userGmEntity);
            userMsgMapper.updateById(userMsgEntity);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(updateUserDto.getUserId());
        if (StringUtils.isNotBlank(updateUserDto.getUserPassword())){
            userEntity.setUserPassword(SecurityUtils.encryptPassword(updateUserDto.getUserPassword()));
            userMapper.updateById(userEntity);
        }else if(StringUtils.isNotBlank(updateUserDto.getUserNickname())||ObjectUtils.isNotNull(updateUserDto.getState())){
            userEntity.setUserNickname(updateUserDto.getUserNickname());
            userEntity.setUserState(updateUserDto.getState());
            userMapper.updateById(userEntity);
        }
        if(ObjectUtils.isNotNull(updateUserDto.getAvatarId())||ObjectUtils.isNotNull(updateUserDto.getUserPhone())){
            userMsgEntity.setUserAvatar(updateUserDto.getAvatarId());
            userMsgEntity.setUserPhone(updateUserDto.getUserPhone());
            userMsgMapper.updateById(userMsgEntity);
        }
        return R.success("修改成功！");
    }

    @Override
    public R updateUserPassword(UserUpdatePasswordDto userUpdatePasswordDto){
        UserEntity userEntity = userMapper.selectById(userUpdatePasswordDto.getUserId());
        if (ObjectUtils.isNull(userEntity)){
            return R.error("用户不存在！");
        }
        if(SecurityUtils.matchesPassword(userUpdatePasswordDto.getOldPassword(),userEntity.getUserPassword())){
            userEntity.setUserPassword(SecurityUtils.encryptPassword(userUpdatePasswordDto.getNewPassword()));
            userMapper.updateById(userEntity);
            logoutUser();
            return R.success("修改成功！");
        }
        return R.error("原密码错误！");
    }

    @Override
    public R daleteUserById(Integer userId){
        UserGmEntity userGmEntity = userGmMapper.selectById(userId);
        if (ObjectUtils.isNull(userGmEntity)){
            return R.error("管理员不存在！");
        }
        userMsgMapper.deleteById(userId);
        userMapper.deleteById(userId);
        userGmMapper.deleteById(userId);
        return R.success("删除成功！");
    }
}
