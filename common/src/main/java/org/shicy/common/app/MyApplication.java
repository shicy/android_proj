package org.shicy.common.app;

import android.app.Application;

import org.shicy.common.datasource.AppConfig;
import org.shicy.common.datasource.db.DsManager;
import org.shicy.common.utils.DeviceUtils;

/**
 * 扩展Application，定义全局参数
 * Created by Shicy on 2015/10/3.
 */
public class MyApplication extends Application {

    // 当前应用实例
    private static MyApplication instance;

    /**
     * 获取应用实例
     * @return 应用实例对象
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 全局应用程序构造方法，应该由系统创建，禁止私自创建
     */
    public MyApplication() {
        super();
        // 一个应用只会实例化一个Application，所以它是唯一的
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化设置度量信息
        DeviceUtils.initWithContext(this);
        // 初始化应用配置信息
        AppConfig.getInstance().setBaseContext(this);
        // 初始化数据库管理类
        DsManager.getInstance().setBaseContext(this);
        DsManager.getInstance().config("default.db", null);
    }
}
