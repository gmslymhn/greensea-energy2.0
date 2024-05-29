import { defHttp } from '@/utils/http/axios';
import {
  LoginParams,
  LoginResultModel,
  GetUserInfoModel,
  SignParams,
  smsCodeParams,
  SuperLoginParams,
} from './model/userModel';

import { ErrorMessageMode } from '#/axios';

enum Api {
  Login = '/background/user/login',
  Logout = '/logout',
  SuperLogin = '/background/gm/login',
  GetUserInfo = '/background/user/getselfmag',
  GetSuperUserInfo = '/background/gm/getselfmag',
  GetPermCode = '/getPermCode',
  TestRetry = '/testRetry',
  Sign = '/background/user/register',
  GetSmsCode = '/background/user/register/verify',
}

/**
 * @description: user login api
 */
export function loginApi(params: LoginParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post<LoginResultModel>(
    {
      url: Api.Login,
      params,
    },
    {
      errorMessageMode: mode,
    },
  );
}
export function adminLoginApi(params: SuperLoginParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post<LoginResultModel>(
    {
      url: Api.SuperLogin,
      params,
    },
    {
      errorMessageMode: mode,
    },
  );
}

/**
 * @description: getUserInfo
 */
export function getUserInfo() {
  return defHttp.post<GetUserInfoModel>({ url: Api.GetUserInfo }, { errorMessageMode: 'none' });
}

/**
 * @description: 获取超管的用户信息
 */

export function getSuperUserInfo() {
  return defHttp.post<GetUserInfoModel>(
    { url: Api.GetSuperUserInfo },
    { errorMessageMode: 'none' },
  );
}

export function getPermCode() {
  return defHttp.get<string[]>({ url: Api.GetPermCode });
}

export function doLogout() {
  return defHttp.get({ url: Api.Logout });
}

export function testRetry() {
  return defHttp.get(
    { url: Api.TestRetry },
    {
      retryRequest: {
        isOpenRetry: true,
        count: 5,
        waitTime: 1000,
      },
    },
  );
}

//注册按钮
export function SignApi(params: SignParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post(
    {
      url: Api.Sign,
      params,
    },
    {
      errorMessageMode: mode,
    },
  );
}

// 获取验证码
export function getSmsCode(params: smsCodeParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post(
    {
      url: Api.GetSmsCode,
      params,
    },
    { errorMessageMode: mode },
  );
}
