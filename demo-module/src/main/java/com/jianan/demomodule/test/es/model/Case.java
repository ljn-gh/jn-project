package com.jianan.demomodule.test.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/6/27
 * @description
 **/
@Data
@Document(indexName = "caseinfoall3")
@AllArgsConstructor
@NoArgsConstructor
public class Case {
    @Id
    @Field(type = FieldType.Keyword)
    private String caseid;
    
    @Field(type = FieldType.Date)
    private String regtime;
    
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String casename;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String casecon;
    
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String casereason;
    
    @Field(type = FieldType.Keyword,name = "c_citycode")
    private String citycode;

    @Field(name = "t_law_punish_illegal_info", type = FieldType.Nested)
    private List<IllegalInfo> t_law_punish_illegal_info;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IllegalInfo{
        @Field(type = FieldType.Double)
        private Double penam;
        
        @Field(type = FieldType.Text, analyzer = "ik_max_word")
        private String illegfact;
        
        @Field(type = FieldType.Text, analyzer = "ik_max_word")
        private String penbasis;
        
        private String pendecissdate;
    }
}
