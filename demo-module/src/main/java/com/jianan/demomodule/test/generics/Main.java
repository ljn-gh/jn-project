package com.jianan.demomodule.test.generics;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @Author: jn
 * @Date: 2024/7/30
 * @description
 **/
public class Main {
    public static void main(String[] args) {
        List<Map<String, Lock>> list = new ArrayList<>();
        List<Map<String, Lock>> maps = JSONObject.parseObject(JSON.toJSONString(list), new TypeReference<>() {});
        List<Map<String, Lock>> maps2 = JSONObject.parseObject(JSON.toJSONString(list), List.class);
        Type genericSuperclass = list.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }
}
