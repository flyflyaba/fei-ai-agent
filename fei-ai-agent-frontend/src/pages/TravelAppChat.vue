<template>
  <div class="page-wrapper">
    <div class="header-area">
      <div class="header-bar">
        <div class="header-left">
          <router-link to="/">
            <button class="back-btn">返回</button>
          </router-link>
        </div>
        <div class="header-center">
          <div class="chat-title">AI旅游大师</div>
        </div>
        <div class="header-right">
          会话ID：{{ chatId }}
        </div>
      </div>
    </div>
    <div class="main-content">
      <ChatRoom :chatId="chatId" :onSend="handleSend" aiAvatar="/travel-ai-avatar.png" userAvatar="/me-avatar.png" buttonColor="#17643a" buttonHoverColor="#1aaa55" />
      <div class="input-float">
        <ChatInput :chatId="chatId" />
      </div>
    </div>
    <div class="footer-area">
      <AppFooter />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import ChatRoom from '../components/ChatRoom.vue'
import AppFooter from '../components/AppFooter.vue'
import { v4 as uuidv4 } from 'uuid'

// SEO优化
if (typeof document !== 'undefined') {
  document.title = 'AI旅游大师 - AI智能助手应用平台';
  const metaDesc = document.querySelector('meta[name="description"]') || document.createElement('meta');
  metaDesc.setAttribute('name', 'description');
  metaDesc.setAttribute('content', 'AI旅游大师，智能规划行程、推荐景点、解答旅游相关问题的AI助手。');
  document.head.appendChild(metaDesc);
}

interface Message {
  role: 'user' | 'ai'
  content: string
  time?: number
}

const chatId = ref<string>(uuidv4())

const handleSend = (message: string, messages: Message[]) => {
  const eventSource = new EventSource(
    `http://localhost:8123/api/ai/travel_app/chat/sse?message=${encodeURIComponent(message)}&chatId=${chatId.value}`
  )
  let aiMsg = ''
  eventSource.onmessage = (event: MessageEvent) => {
    aiMsg += event.data
    if (messages[messages.length - 1]?.role === 'ai') {
      messages[messages.length - 1].content = aiMsg
      messages[messages.length - 1].time = Date.now()
    } else {
      messages.push({ role: 'ai', content: aiMsg, time: Date.now() })
    }
  }
  eventSource.onerror = () => eventSource.close()
}
</script>

<style scoped>
.page-wrapper {
  width: 100vw;
  min-height: 100vh;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  background: #f6f7fa;
}
.header-area {
  background: linear-gradient(90deg, #17643a 60%, #1aaa55 100%);
  width: 100vw;
  min-height: 56px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: none;
  border-radius: 0;
  margin: 0 0 18px 0;
  position: relative;
  z-index: 2;
}
.header-bar {
  min-height: 56px;
  width: 100vw;
  max-width: 1000px;
  margin: 0 auto;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}
.main-content {
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
  min-height: 0;
  margin: 0 auto 18px auto;
  width: 100vw;
  max-width: 1000px;
  background: #fff;
  border-radius: 0 0 16px 16px;
  box-shadow: 0 2px 16px #0001;
  overflow: hidden;
  align-items: stretch;
  justify-content: flex-start;
  max-height: 70vh;
  height: 70vh;
  position: relative;
}
.footer-area {
  background: #fff;
  width: 100vw;
  min-height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
  position: relative;
  border-radius: 0;
  margin: 24px 0 0 0;
  box-shadow: none;
  border-top: 1.5px solid #ececec;
}
.header-left {
  position: absolute;
  left: 0px;
  top: 0;
  height: 100%;
  display: flex;
  align-items: center;
}
.header-center {
  flex: 0 1 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: 100%;
}
.header-right {
  position: absolute;
  right: 0px;
  top: 0;
  height: 100%;
  display: flex;
  align-items: center;
  color: #fff;
  font-size: 14px;
}
.back-btn {
  padding: 8px 22px;
  font-size: 16px;
  border-radius: 8px;
  border: 2px solid #fff;
  background: #17643a;
  color: #fff;
  cursor: pointer;
  transition: background 0.2s, border 0.2s;
  z-index: 2;
}
.back-btn:hover {
  background: #218c54;
  border: 2px solid #1aaa55;
}
.chat-title {
  color: #fff;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 0;
}
.chat-id {
  color: #fff;
  font-size: 14px;
  margin-bottom: 0;
}
.footer {
  flex-shrink: 0;
  height: 70px;
  width: 100vw;
  text-align: center;
  color: #b7e7c7;
  font-size: 1rem;
  padding: 32px 0 18px 0;
  position: absolute;
  left: 0;
  bottom: 0;
  background: transparent;
  font-family: 'JetBrains Mono', 'Consolas', 'Menlo', monospace;
  letter-spacing: 1px;
}
.input-float {
  position: absolute;
  left: 50%;
  bottom: 24px;
  transform: translateX(-50%);
  width: calc(100% - 48px);
  z-index: 10;
}
@media (max-width: 1000px) {
  .header-bar, .main-content, .footer-area {
    max-width: 100vw;
  }
  .header-area {
    margin-bottom: 10px;
  }
  .main-content {
    margin-bottom: 10px;
  }
  .footer-area {
    margin-top: 14px;
  }
}
@media (max-width: 600px) {
  .header-bar {
    min-height: 40px;
  }
  .main-content {
    border-radius: 0 0 8px 8px;
    margin-bottom: 6px;
  }
  .footer-area {
    min-height: 120px;
    margin-top: 8px;
  }
}
</style>