package com.jianan.demomodule.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.jianan.demomodule.model.User;

/**
 * @Author: jn
 * @Date: 2024/10/30
 * @description
 **/
public class MPgenerator {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\":\"213123\",\"name\":\\{\\},\"age\":0}";
        User user = mapper.readValue(json, User.class);
        System.out.println(user.getId());
    }
}
