package com.jianan.demomodule.test.json;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
 * Param
 * @description
 * @author lijianan
 * @date 2024/3/18 15:08
 * @version TODO
 */
public class Param {
    private List<String> name;

    @JSONField(serializeUsing = ArrayToStringserializer.class)
    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}