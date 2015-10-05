package org.shicy.common.base;

import java.io.Serializable;

/**
 * 实例基本类
 * Created by Shicy on 2015/10/4.
 */
public class BaseEntity implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
