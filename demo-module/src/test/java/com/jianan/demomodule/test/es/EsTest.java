package com.jianan.demomodule.test.es;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jianan.demomodule.test.es.model.Case;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/6/27
 * @description
 **/
public class EsTest {
    private RestHighLevelClient client;
    
    @BeforeEach
    public void bofore(){
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://localhost:9200")));
    }
    
    @Test
    public void test_matchall() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        List<Case> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            list.add(JSON.parseObject(sourceAsString, Case.class));
        }
        System.out.println(ArrayUtils.toString(list));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_multimatch() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        request.source().query(QueryBuilders.multiMatchQuery("德教天下","casecon","casereason"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        JSONArray ja = new JSONArray();
        for (SearchHit hit : hits) {
            ja.add(hit.getSourceAsMap());
        }
        System.out.println(ja.toJSONString());
    }

    @Test
    public void test_term() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        request.source().query(QueryBuilders.termQuery("c_citycode","110100"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        JSONArray ja = new JSONArray();
        for (SearchHit hit : hits) {
            ja.add(hit.getSourceAsMap());
        }
        System.out.println(ja.toJSONString());
    }

    @Test
    public void test_range() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        request.source().query(QueryBuilders.rangeQuery("regtime").gt("2023-05-21"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        JSONArray ja = new JSONArray();
        for (SearchHit hit : hits) {
            ja.add(hit.getSourceAsMap());
        }
        System.out.println(ja.toJSONString());
    }

    @Test
    public void test_bool() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        BoolQueryBuilder bool = new BoolQueryBuilder();
        bool.should(QueryBuilders.termQuery("c_citycode","440100"))
                        .must(QueryBuilders.termQuery("c_citycode","110100"));
        request.source().query(bool).size(100);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        JSONArray ja = new JSONArray();
        for (SearchHit hit : hits.getHits()) {
            ja.add(hit.getSourceAsMap());
        }
        System.out.println(hits.getTotalHits().value);
        System.out.println(ja.toJSONString());
    }

    @Test
    public void test_agg() throws IOException {
        SearchRequest request = new SearchRequest("caseinfoall3");
        SearchSourceBuilder sourceBuilder = request.source().size(0).query(QueryBuilders.rangeQuery("regtime").gt("2023-01-01"));
        sourceBuilder.aggregation(AggregationBuilders.terms("citycount").field("c_citycode").size(100).order(BucketOrder.count(false)));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        ParsedStringTerms aggregation = response.getAggregations().get("citycount");
        List<? extends Terms.Bucket> buckets = aggregation.getBuckets();
        JSONArray ja = new JSONArray();
        for (Terms.Bucket bucket : buckets) {
            ja.add(MapUtil.of(Pair.of(bucket.getKeyAsString(), bucket.getDocCount())));
        }
        System.out.println(ja.toJSONString());
    }
    
    @Test
    public void init() throws Exception {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://localhost:9200")));
        String data = Files.readString(Paths.get("E:\\project\\lijianan\\文档\\aj.txt"));
        JSONArray array = JSON.parseArray(data);
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i).getJSONObject("_source");
            request.add(new IndexRequest("caseinfoall3").id(object.getString("caseid")).source(object.toJSONString(), XContentType.JSON));
        }
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        restHighLevelClient.close();
    }
    
    @AfterEach
    public void after() throws IOException {
        this.client.close();
    }
}
