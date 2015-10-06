package org.shicy.myproj.secret;

import org.shicy.common.base.BaseEntity;

/**
 * 密保实例
 * Created by Shicy on 2015/10/4.
 */
public class SecretEntity extends BaseEntity {

    private String title;
    private String groupName;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
