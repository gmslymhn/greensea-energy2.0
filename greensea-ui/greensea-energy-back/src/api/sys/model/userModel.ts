/**
 * @description: Login interface parameters
 */
export interface LoginParams {
  userAccount: string;
  userPassword: string;
}

export interface SuperLoginParams {
  gmAccount: string;
  gmPassword: string;
}

export interface smsCodeParams {
  userAccount: string;
  userEmail: string;
}

export interface SignParams {
  userAccount: string;
  userPassword: string;
  userNickname: string;
  userEmail: string;
  verificationCode: string;
}

export interface RoleInfo {
  roleName: string;
  value: string;
}

/**
 * @description: Login interface return value
 */
// export interface LoginResultModel {
//   lastLoginIp: string;
//   lastLoginLocation: string;
//   lastLoginTime: string;
//   token: string;
// }

export interface LoginResultModel {
  code: number;
  message: string;
  data: any;
}

/**
 * @description: Get user information return value
 */
export interface GetUserInfoModel {
  roles: RoleInfo[];
  // 用户id
  userId: string | number;
  // 用户名
  username: string;
  // 真实名字
  realName: string;
  // 头像
  avatar: string;
  // 介绍
  desc?: string;
}
