package com.fei.feiaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSearchToolTest {

    @Value("${search-api.api-key}")
    private String searchApiKey;

    @Test
    void searchWeb() {
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
//        String query = "哔哩哔哩 (゜-゜)つロ 干杯~ bilibili.com";
        String query = "携程旅游攻略 https://you.ctrip.com";
        String result = webSearchTool.searchWeb(query);
        Assertions.assertNotNull(result);
    }
}