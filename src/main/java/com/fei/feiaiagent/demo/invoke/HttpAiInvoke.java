package com.fei.feiaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * HTTP 方式调用 AI
 */
public class HttpAiInvoke {

    public static void main(String[] args) {

        String apiKey = TestApiKey.API_KEY; // 替换为你的实际 API Key

        // 构建请求URL
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        // 构建 messages 数组
        JSONArray messages = new JSONArray();
        // 添加系统消息
        messages.add(new JSONObject()
                .set("role", "system")
                .set("content", "You are a helpful assistant."));
        // 添加用户信息
        messages.add(new JSONObject()
                .set("role", "user")
                .set("content", "你是谁？"));

        // 构建请求体
        JSONObject json = new JSONObject();
        json.set("model", "qwen-plus");
        json.set("input", new JSONObject().set("messages", messages));
        json.set("parameters", new JSONObject().set("result_format", "message"));

        // 发送 POST 请求
        HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(json.toString())
                .execute();

        // 输出响应内容
        System.out.println(response.body());
    }
}
