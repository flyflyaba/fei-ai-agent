package com.fei.feiaiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeiManusTest {

    @Resource
    private FeiManus feiManus;

    @Test
    public void run() {
        String userPrompt = """
                我的另一半居住在上海静安区，请帮我找到5公里内合适的约会地点，
                并结合一些网络图片，制定一份详细的约会计划，
                并以PDF格式输出
                """;
        String answer = feiManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }
}