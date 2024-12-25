/*
 Navicat Premium Data Transfer

 Source Server         : 云平台
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : 47.113.192.133:3306
 Source Schema         : greensea_device

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 10/06/2024 13:33:03
*/

    SET NAMES utf8mb4;
    SET FOREIGN_KEY_CHECKS = 0;

    -- ----------------------------
    -- Table structure for gre_dev_1
    -- ----------------------------
    DROP TABLE IF EXISTS `gre_dev_1`;
    CREATE TABLE `gre_dev_1`  (
      `id` int NOT NULL AUTO_INCREMENT,
      `sys_fault_lvl` int NULL DEFAULT NULL COMMENT '系统故障等级',
      `sys_fault_code` int NULL DEFAULT NULL COMMENT '系统故障码',
      `fc_status` int NULL DEFAULT NULL COMMENT '燃料电池状态',
      `fc_stack_power` float NULL DEFAULT NULL COMMENT '燃料电池电堆功率/Kw',
      `fc_stack_volt` float NULL DEFAULT NULL COMMENT '燃料电池电压/V',
      `fc_stack_cur` float NULL DEFAULT NULL COMMENT '燃料电池电流/A',
      `fc_dcdc_volt_out` float NULL DEFAULT NULL COMMENT '燃料电池DCDC输出电压/V',
      `fc_dcdc_cur_out` float NULL DEFAULT NULL COMMENT '燃料电池DCDC输出电流/A',
      `fc_sys_power` float NULL DEFAULT NULL COMMENT '燃料电池系统功率/kW',
      `fc_kwh` float NULL DEFAULT NULL COMMENT '燃料电池年累计发电量/kWh',
      `pemh2_status` int NULL DEFAULT NULL COMMENT '电解槽状态',
      `pemh2_dcdc_volt_out` float NULL DEFAULT NULL COMMENT '电解槽DCDC的输出电压/V',
      `pemh2_dcdc_cur_out` float NULL DEFAULT NULL COMMENT '电解槽DCDC的输出电流/A',
      `pemh2_pro` float NULL DEFAULT NULL COMMENT '电解槽年累计制氢量/Nm3',
      `lbms_volt` float NULL DEFAULT NULL COMMENT '大锂电池电压/V',
      `lbms_cur` float NULL DEFAULT NULL COMMENT '大锂电池电流/A',
      `lbms_soc` float NULL DEFAULT NULL COMMENT '大锂电池SOC',
      `sbms_volt` float NULL DEFAULT NULL COMMENT '小锂电池电压/V',
      `sbms_cur` float NULL DEFAULT NULL COMMENT '小锂电池电流/A',
      `sbms_soc` float NULL DEFAULT NULL COMMENT '大锂电池SOC',
      `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间精确到秒',
      PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 142 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

    SET FOREIGN_KEY_CHECKS = 1;
