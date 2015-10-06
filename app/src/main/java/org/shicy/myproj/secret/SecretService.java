package org.shicy.myproj.secret;

import org.shicy.common.base.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Shicy on 2015/10/5.
 */
public class SecretService extends BaseService {

    /**
     * 获取所有保密项
     * @return 实例对象集
     */
    public List<SecretEntity> getAllSecretInfos() {
        List<SecretEntity> entityList = new ArrayList<>();
        entityList.add(createEntity("系佛教覅", "开卡"));
        entityList.add(createEntity("就哦飞机wife", "简介"));
        entityList.add(createEntity("喀麦隆的名声狼藉", "简介"));
        entityList.add(createEntity("囧分我发觉饿哦", "开卡"));
        entityList.add(createEntity("会few回复额我ifheu", "的身份的"));
        return entityList;
    }

    private SecretEntity createEntity(String title, String groupName) {
        SecretEntity entity = new SecretEntity();
        entity.setTitle(title);
        entity.setGroupName(groupName);
        return entity;
    }

}
