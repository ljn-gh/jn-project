package com.jianan.demomodule.test.service;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jn
 * @Date: 2024/6/26
 * @description
 **/
@Service
public class EsService {
    //@Autowired
    private RestHighLevelClient restClient;
    
}
