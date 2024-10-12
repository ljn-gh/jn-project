package com.jianan.demomodule.test.tokenizer;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/4/28
 * @description
 **/
public class Jieba {
    public static void main(String[] args) {
        JiebaSegmenter jieba = new JiebaSegmenter();
        List<SegToken> process = jieba.process("你好,今天的天气怎么样?", JiebaSegmenter.SegMode.SEARCH);
        process.forEach(segToken -> {
            System.out.println(segToken.word);
        });
    }
}
