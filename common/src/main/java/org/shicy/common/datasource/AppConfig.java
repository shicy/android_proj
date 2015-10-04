package org.shicy.common.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;

/**
 * 应用参数配置类，使用SharedPreferences存储数据
 * Created by Shicy on 2015/10/3.
 */
public class AppConfig {

    private static AppConfig _config;
    private Context context;
    private SharedPreferences configs;

    /**
     * 私有构造方法，禁止外部创建，请使用getInstance()方法获取实例对象
     */
    private AppConfig() {
        //
    }

    /**
     * 获取配置类的实例（单例）
     * @return 配置类实例对象
     */
    public static AppConfig getInstance() {
        if (_config == null) {
            _config = new AppConfig();
        }
        return _config;
    }

    /**
     * 设置应用配置的上下文信息
     * @param context 配置上下文信息，Application or Activity
     */
    public void setBaseContext(Context context) {
        this.context = context;
    }

    /**
     * 获取字符串型参数值，当配置不存在时返回null
     * @param key 参数名称
     * @return 参数值
     */
    public String getStringValue(String key) {
        return this.getStringValue(key, null);
    }

    /**
     * 获取字符串型参数值，当配置不存在或为null时返回默认值
     * @param key 参数名称
     * @param defValue 默认值
     * @return 参数值
     */
    public String getStringValue(String key, String defValue) {
        SharedPreferences cfgs = this.getConfigurationByException();
        return cfgs.getString(key, defValue);
    }

    /**
     * 获取整数型参数值，当配置不存在时返回0
     * @param key 参数名称
     * @return 参数值
     */
    public int getIntValue(String key) {
        return this.getIntValue(key, 0);
    }

    /**
     * 获取整数型参数值，当配置不存在时返回默认值
     * @param key 参数名称
     * @param defValue 默认值
     * @return 参数值
     */
    public int getIntValue(String key, int defValue) {
        SharedPreferences cfgs = this.getConfigurationByException();
        return cfgs.getInt(key, defValue);
    }

    /**
     * 获取布尔型参数值，当配置不存在时返回false
     * @param key 参数名称
     * @return 参数值
     */
    public boolean getBoolValue(String key) {
        return this.getBoolValue(key, false);
    }

    /**
     * 获取布尔型参数值，当配置不存在时返回默认值
     * @param key 参数名称
     * @param defValue 默认值
     * @return 参数值
     */
    public boolean getBoolValue(String key, boolean defValue) {
        SharedPreferences cfgs = this.getConfigurationByException();
        return cfgs.getBoolean(key, defValue);
    }

    /**
     * 获取浮点型参数值，当配置不存在时返回0
     * @param key 参数名称
     * @return 参数值
     */
    public float getFloatValue(String key) {
        return this.getFloatValue(key, 0.0f);
    }

    /**
     * 获取浮点型参数值，当配置不存在时返回默认值
     * @param key 参数名称
     * @param defValue 默认值
     * @return 参数值
     */
    public float getFloatValue(String key, float defValue) {
        SharedPreferences cfgs = this.getConfigurationByException();
        return cfgs.getFloat(key, defValue);
    }

    /**
     * 获取长整形参数值，当配置不存在时返回0
     * @param key 参数名称
     * @return 参数值
     */
    public long getLongValue(String key) {
        return this.getLongValue(key, 0L);
    }

    /**
     * 获取长整形参数值，当配置不存在时返回默认值
     * @param key 参数名称
     * @param defValue 默认值
     * @return 参数值
     */
    public long getLongValue(String key, long defValue) {
        SharedPreferences cfgs = this.getConfigurationByException();
        return cfgs.getLong(key, defValue);
    }

    /**
     * 设置一个字符串型的参数值
     * @param key 参数名称
     * @param value 参数值
     */
    public void setStringValue(String key, String value) {
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 设置一个整数型的参数值
     * @param key 参数名称
     * @param value 参数值
     */
    public void setIntValue(String key, int value) {
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 设置一个布尔型的参数值
     * @param key 参数名称
     * @param value 参数值
     */
    public  void setBoolValue(String key, boolean value) {
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 设置一个浮点型的参数值
     * @param key 参数名称
     * @param value 参数值
     */
    public void setFloatValue(String key, float value) {
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 设置一个长整型的参数值
     * @param key 参数名称
     * @param value 参数值
     */
    public void setLongValue(String key, long value) {
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 批量设置配置信息
     * @param values 相应的参数名和参数值
     */
    public void setValues(Map<String, Object> values) {
        Iterator<String> iterator = values.keySet().iterator();
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        while (iterator.hasNext()) {
            String key = iterator.next();
            this.setObjectWithoutCommit(editor, key, values.get(key));
        }
        editor.apply();
    }

    /**
     * 数组的形式批量设置配置信息
     * @param keys 所有参数名称
     * @param values 对应的参数值，必须与参数名称一一对应
     */
    public void setValues(String[] keys, Object[] values) {
        if (keys == null || values == null)
            return ;
        if (keys.length != values.length)
            throw new RuntimeException("the number of keys and values item not equal.");
        SharedPreferences.Editor editor = this.getConfigurationByException().edit();
        for (int i = 0; i < keys.length; i++) {
            this.setObjectWithoutCommit(editor, keys[i], values[i]);
        }
        editor.apply();
    }

    /**
     * 设置一个配置参数值，但不会提交保存。支持基本类型，如果是对象类型则转为字符串存储
     * @param editor 配置编辑器
     * @param key 参数名称
     * @param value 参数值
     */
    private void setObjectWithoutCommit(SharedPreferences.Editor editor, String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String)value);
        }
        else if (value instanceof Integer) {
            editor.putInt(key, (Integer)value);
        }
        else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean)value);
        }
        else if (value instanceof Float) {
            editor.putFloat(key, (Float)value);
        }
        else if (value instanceof Long) {
            editor.putLong(key, (Long)value);
        }
        else {
            editor.putString(key, value.toString());
        }
    }

    /**
     * 获取应用配置信息，即配置文件，如果配置不存在则会自动创建。配置只允许本应用读写
     * @return 配置信息
     */
    private SharedPreferences getConfigurationByException() {
        if (context == null)
            throw new RuntimeException("unknown AppConfig's context, setup use setBaseContext() function.");
        if (configs == null)
            configs = context.getSharedPreferences("configuration", Context.MODE_PRIVATE);
        return configs;
    }

}
