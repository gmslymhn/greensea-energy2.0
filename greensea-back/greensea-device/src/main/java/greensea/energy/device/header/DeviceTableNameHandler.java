package greensea.energy.device.header;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: TableNameHandler
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 02:01
 * @Version: 1.0
 **/
public class DeviceTableNameHandler implements TableNameHandler {
    //用于记录哪些表可以使用该动态表名处理器（即哪些表需要分表）
    private List<String> tableNames;
    //构造函数，构造动态表名处理器的时候，传递tableNames参数
    public DeviceTableNameHandler(String ...tableNames) {
        this.tableNames = Arrays.asList(tableNames);
    }
    //每个请求线程维护一个day数据，避免多线程数据冲突。所以使用ThreadLocal
    private static final ThreadLocal<String> DEV_ID = new ThreadLocal<>();
    //设置请求线程的day数据
    public static void setData(String id) {
        DEV_ID.set(id);
    }
    //删除当前请求线程的day数据
    public static void removeData() {
        DEV_ID.remove();
    }
    //动态表名接口实现方法
    @Override
    public String dynamicTableName(String sql, String tableName) {
        if (this.tableNames.contains(tableName)){
            return tableName + "_" + DEV_ID.get();  //表名增加后缀
        }else{
            return tableName;   //表名原样返回
        }
    }
}
