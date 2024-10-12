package com.jianan.demomodule.test.json;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ArrayToStringserializer
 * @description
 * @author lijianan
 * @date 2024/3/18 15:10
 * @version TODO
 */
public class ArrayToStringserializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        
        SerializeWriter out = serializer.out;
        if(object == null){
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
        }else{
            if (object instanceof List) {
                List list = (List) object;
                if(list.size() == 0){
                    serializer.writeNull();
                    return;
                }
                StringJoiner sj = new StringJoiner(" ");
                for (int i = 0; i < list.size(); i++) {
                    sj.add(list.get(i).toString());
                }
                out.writeString(sj.toString());
            }
        }
    }
}