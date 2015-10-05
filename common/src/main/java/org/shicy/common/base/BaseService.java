package org.shicy.common.base;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Shicy on 2015/10/5.
 */
public abstract class BaseService {

    private static Map<String, BaseService> instances = null;

    /**
     * 获取相关服务类实例
     * @param cls 服务类对象
     * @param <T> 类
     * @return 返回实例对象
     */
    public static <T extends BaseService> T getService(Class<T> cls) {
        if (instances == null)
            instances = new HashMap<>();

        String clsName = cls.getName();
        BaseService service = instances.get(clsName);

        if (service == null) {
            try {
                service = cls.newInstance();
                instances.put(clsName, service);
            }
            catch (Exception e) {
                // no Exception
            }
        }

        if (service != null) {
            try {
                return (T)service;
            }
            catch (Exception e) {
                // no Exception
            }
        }

        return null;
    }

}
