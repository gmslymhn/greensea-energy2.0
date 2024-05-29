import type { AppRouteModule } from '@/router/types';

import { getParentLayout, LAYOUT } from '@/router/constant';
import { t } from '@/hooks/web/useI18n';
import { RoleEnum } from '@/enums/roleEnum';

const fontManager: AppRouteModule = {
  path: '/userManager',
  name: 'userManager',
  component: LAYOUT,
  redirect: '/userManager/loginManage',
  meta: {
    orderNo: 500,
    icon: 'ion:bar-chart-outline',
    title: '用户管理',
    roles: [RoleEnum.SUPER],
  },
  children: [
    {
      path: 'loginManage',
      name: 'loginManage',
      meta: {
        title: t('登录管理'),
      },
      component: () => import('@/views/demo/userManage/loginManage.vue'),
    },
    {
      path: 'adminManage',
      name: 'adminManage',
      meta: {
        title: t('管理员用户管理'),
      },
      component: () => import('@/views/demo/userManage/adminManage.vue'),
    },
    {
      path: 'userManage',
      name: 'userManage',
      meta: {
        title: t('个人/企业用户管理'),
      },
      component: () => import('@/views/demo/userManage/userManage.vue'),
    },
    // {
    //   path: 'dataMap',
    //   name: 'dataMap',
    //   meta: {
    //     title: t('设备数据大屏'),
    //   },
    //   component: () => import('@/views/demo/dataScreen/dataScreen.vue'),
    // },
  ],
};

export default fontManager;
