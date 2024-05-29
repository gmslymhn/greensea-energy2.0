import type {AppRouteModule} from '@/router/types';

import {getParentLayout, LAYOUT} from '@/router/constant';
import {t} from '@/hooks/web/useI18n';
import {RoleEnum} from "@/enums/roleEnum";

const charts: AppRouteModule = {
  path: '/charts',
  name: 'Charts',
  component: LAYOUT,
  redirect: '/charts/echarts/map',
  meta: {
    orderNo: 500,
    icon: 'ion:bar-chart-outline',
    title: '设备管理',
  },
  children: [
    // {
    //   path: 'baiduMap',
    //   name: 'BaiduMap',
    //   meta: {
    //     title: t('routes.demo.charts.baiduMap'),
    //   },
    //   component: () => import('@/views/demo/charts/map/Baidu.vue'),
    // },
    // {
    //   path: 'aMap',
    //   name: 'AMap',
    //   meta: {
    //     title: t('routes.demo.charts.aMap'),
    //   },
    //   component: () => import('@/views/demo/charts/map/Gaode.vue'),
    // },
    {
      path: 'googleMap',
      name: 'GoogleMap',
      meta: {
        title: t('设备列表'),
      },
      component: () => import('@/views/demo/charts/map/Google.vue'),
    },
    // {
    //   path: 'dataMap',
    //   name: 'dataMap',
    //   meta: {
    //     title: t('设备数据大屏'),
    //   },
    //   component: () => import('@/views/demo/dataScreen/dataScreen.vue'),
    // },

    {
      path: 'echarts',
      name: 'Echarts',
      component: getParentLayout('Echarts'),
      meta: {
        title: '设备详情',
      },
      redirect: '/charts/echarts/map',
      children: [
        {
          path: 'map',
          name: 'Map',
          component: () => import('@/views/demo/charts/Map.vue'),
          meta: {
            title: t('routes.demo.charts.map'),
          },
        },
        {
          path: 'line',
          name: 'Line',
          component: () => import('@/views/demo/charts/Line.vue'),
          meta: {
            title: t('routes.demo.charts.line'),
          },
        },
        {
          path: 'pie',
          name: 'Pie',
          component: () => import('@/views/demo/charts/Pie.vue'),
          meta: {
            title: t('routes.demo.charts.pie'),
            roles: [RoleEnum.SUPER],
          },
        },
      ],
    },
  ],
};

export default charts;
