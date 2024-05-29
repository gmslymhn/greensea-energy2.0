import type { AppRouteModule } from '@/router/types';

import { LAYOUT } from '@/router/constant';
import { t } from '@/hooks/web/useI18n';
import {RoleEnum} from "@/enums/roleEnum";

const flow: AppRouteModule = {
  path: '/flow',
  name: 'FlowDemo',
  component: LAYOUT,
  redirect: '/flow/flowChart',
  meta: {
    orderNo: 5000,
    icon: 'tabler:chart-dots',
    title: t('安全设置'),
  },
  children: [
    {
      path: 'flowChart',
      name: 'flowChartDemo',
      component: () => import('@/views/demo/comp/flow-chart/index.vue'),
      meta: {
        title: t('账号登录安全'),
      },
    },
  ],
};

export default flow;
