package com.jianan.demomodule.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.erhlc.AbstractElasticsearchConfiguration;

//@Configuration
//@Data
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    //重写父类方法
    @Override
    //@Bean
    public RestHighLevelClient elasticsearchClient() {

        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
        
    }
}
