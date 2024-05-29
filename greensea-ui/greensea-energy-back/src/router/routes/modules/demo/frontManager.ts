import type { AppRouteModule } from '@/router/types';

import { getParentLayout, LAYOUT } from '@/router/constant';
import { t } from '@/hooks/web/useI18n';
import { RoleEnum } from '@/enums/roleEnum';

const fontManager: AppRouteModule = {
  path: '/frontManager',
  name: 'frontManager',
  component: LAYOUT,
  redirect: '/frontManager/leadingEnd',
  meta: {
    orderNo: 500,
    icon: 'ion:bar-chart-outline',
    title: '前台管理',
    roles: [RoleEnum.SUPER],
  },
  children: [
    {
      path: 'leadingEnd',
      name: 'leadingEnd',
      meta: {
        title: t('产品管理'),
      },
      component: () => import('@/views/demo/frontManager/frontManager.vue'),
    },
    {
      path: 'changeImg',
      name: 'changeImg',
      meta: {
        title: t('修改前端图片'),
      },
      component: () => import('@/views/demo/frontManager/changeImg.vue'),
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
