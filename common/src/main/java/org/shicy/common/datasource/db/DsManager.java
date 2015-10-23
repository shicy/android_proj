package org.shicy.common.datasource.db;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库管理类
 * Created by Shicy on 2015/10/16.
 */
public class DsManager {

    private static DsManager instance;
    private Context context;
    private String defaultDsName;
    private Map<String, DbHelper.ScriptAdapter> configs;
    private Map<String, DbHelper> dbHelplers;

    // 获取数据库管理对象实例
    public static DsManager getInstance() {
        if (instance == null) {
            instance = new DsManager();
        }
        return instance;
    }

    // 单例模式，使用getInstance()获取实例对象
    private DsManager() {
        configs = new HashMap<>();
        dbHelplers = new HashMap<>();
    }

    // 设置应用程序上下文信息，仅第一次设置有效，以后不允许修改
    public void setBaseContext(Context context) {
        if (this.context == null)
            this.context = context;
    }

    public void config(String dsName, DbHelper.ScriptAdapter adapter) {
        configs.put(dsName, adapter);
        if (defaultDsName == null)
            defaultDsName = dsName;
    }

    public void setDefaultDs(String name) {
        defaultDsName = name;
    }
}
