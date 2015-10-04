package org.shicy.common.app;

import android.app.Application;

import org.shicy.common.datasource.AppConfig;

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

    public MyApplication() {
        super();
        // 一个应用只会实例化一个Application，所以它是唯一的
        instance = this;
        // 初始化应用配置信息
        AppConfig.getInstance().setBaseContext(this);
    }

}
