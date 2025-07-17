package com.fei.feiaiagent.controller;

import com.fei.feiaiagent.agent.FeiManus;
import com.fei.feiaiagent.app.TravelApp;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private TravelApp travelApp;

    @Resource
    private ToolCallback[] allTools;

    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * 同步調用 AI 旅游大师应用
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping("/travel_app/chat/sync")
    public String doChatWithTravelAppSync(String message, String chatId) {
        return travelApp.doChat(message, chatId);
    }

    /**
     * SSE 流式调用 AI 旅游大师应用（方法一）
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/travel_app/chat/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithTravelAppSSE(String message, String chatId) {
        return travelApp.doChatByStream(message, chatId);
    }

    /**
     * SSE流式调用 AI 旅游大师应用（方法二）
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/travel_app/chat/server_sent_event")
    public Flux<ServerSentEvent<String>> doChatWithTravelAppServerSentEvent(String message, String chatId) {
        return travelApp.doChatByStream(message, chatId)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }

    /**
     * SSE流式调用 AI 旅游大师应用（方法三）
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping("/travel_app/chat/sse_emitter")
    public SseEmitter doChatWithTravelAppSseEmitter(String message, String chatId) {
        // 创建一个超时时间较长的 SseEmitter
        SseEmitter sseEmitter = new SseEmitter(180000L); // 3分钟超时
        // 获取 Flux 响应式数据流并且直接通过订阅推送给 SseEmitter
        travelApp.doChatByStream(message, chatId)
                .subscribe(
                        // 处理每条消息
                        chunk -> {
                            try {
                                sseEmitter.send(chunk);
                            } catch (IOException e) {
                                sseEmitter.completeWithError(e);
                            }
                        },
                        // 处理错误
                        sseEmitter::completeWithError,
                        // 处理完成
                        sseEmitter::complete
                );
        // 返回emitter
        return sseEmitter;
    }

    /**
     * 流式调用 Manus 超级智能体
     * @param message
     * @return
     */
    @GetMapping("/manus/chat")
    public SseEmitter doChatWithManus(String message) {
        FeiManus feiManus = new FeiManus(allTools, dashscopeChatModel);
        return feiManus.runStream(message);
    }
}
