
package com.jianan.demomodule.test.json;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.*;
import com.jianan.demomodule.test.es.model.Case;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main
 * @description
 * @author lijianan
 * @date 2024/3/18 15:20
 * @version TODO
 */
public class Main {
    public static void main(String[] args)
    {
        Param param = new Param();
        param.setName(new ArrayList<>());
        System.out.println(JSON.toJSONString(param));
    }
    
    @Test
    public void test() {
        List<Map<String,String>> list = List.of(Map.of("name","1"),Map.of("name","2"),Map.of("name","3"));

        String str = JSON.toJSONString(list);
        System.out.println(str);

        List<Map<String,String>> parsed1 = JSON.parseObject(str, new TypeReference<>() {
        });

        List<String> parsed2 = JSON.parseObject(str,List.class);

        System.out.println("111");
    }
    
    @Test
    public void test2(){
        Case aCase = new Case();
        Case.IllegalInfo info1 = new Case.IllegalInfo();
        info1.setIllegfact("case1-111");
        Case.IllegalInfo info2 = new Case.IllegalInfo();
        info2.setIllegfact("case1-222");
        aCase.setCaseid(IdUtil.fastUUID());
        aCase.setT_law_punish_illegal_info(List.of(info1,info2));


        Case aCase2 = new Case();
        Case.IllegalInfo info21 = new Case.IllegalInfo();
        info21.setIllegfact("case2-111");
        Case.IllegalInfo info22 = new Case.IllegalInfo();
        info22.setIllegfact("case2-222");
        aCase2.setCaseid(IdUtil.fastUUID());
        aCase2.setT_law_punish_illegal_info(List.of(info21,info22));

        List<Case> list = List.of(aCase, aCase2);
        JSONObject object1 = new JSONObject();
        object1.put("data",list);
        JSONObject object = JSON.parseObject(JSON.toJSONString(object1));

        System.out.println("");

        Map<String, Object> paths = JSONPath.paths(object);
        System.out.println(paths);
        
        Object o = JSONPath.eval(object1, "/data");
        System.out.println();
    }
}