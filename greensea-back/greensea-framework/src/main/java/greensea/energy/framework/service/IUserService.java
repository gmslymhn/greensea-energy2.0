package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.AddUserDto;
import greensea.energy.framework.domain.dto.UpdateUserDto;
import greensea.energy.framework.domain.dto.UserLoginDto;
import greensea.energy.framework.domain.dto.UserUpdatePasswordDto;
import greensea.energy.framework.domain.dto.param.UserParam;

/**
 * @ClassName: IUserService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:39
 * @Version: 1.0
 **/
public interface IUserService {
    R loginUser(UserLoginDto userLoginDto);

    R addUser(AddUserDto addUserDto);

    R verifyRegister(String userAccoount, String userEmail);

    R logoutUser();

    R getUserSelfMsg();

    R userList(UserParam userParam);

    R getUserMsg(Integer userId);

    R updateUserMsg(UpdateUserDto updateUserDto);

    R updateUserPassword(UserUpdatePasswordDto userUpdatePasswordDto);

    R daleteUserById(Integer userId);
}
