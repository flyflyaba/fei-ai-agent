<template>
  <div class="chat-room">
    <div class="chat-header">
      <slot name="header"></slot>
    </div>
    <div class="chat-history" ref="historyRef">
      <template v-if="messages.length === 0">
        <div class="empty-tip">快来与AI对话吧！</div>
      </template>
      <template v-else>
        <div v-for="(msg, idx) in messages" :key="idx" :class="['msg-row', msg.role]">
          <div v-if="msg.role === 'ai'" class="avatar ai-avatar">
            <img src="/ai-avatar.png" alt="AI头像" />
          </div>
          <div v-else class="avatar user-avatar">
            <img src="/user-avatar.png" alt="用户头像" />
          </div>
          <div class="bubble-wrap">
            <div class="bubble">
              <div class="bubble-content">{{ msg.content }}</div>
              <div class="bubble-meta">{{ msg.time ? formatTime(msg.time) : '' }}</div>
            </div>
          </div>
        </div>
      </template>
    </div>
    <div class="chat-input">
      <input v-model="input" @keyup.enter="send" :placeholder="placeholder || '请输入消息...'" />
      <button @click="send">发送</button>
    </div>
    <div class="chat-footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, nextTick } from 'vue'

interface Message {
  role: 'user' | 'ai'
  content: string
  time?: number
}

const props = defineProps<{
  chatId: string
  onSend: (message: string, messages: Message[]) => void
  placeholder?: string
  buttonColor?: string
  buttonHoverColor?: string
}>()

const input = ref<string>('')
const messages = ref<Message[]>([])
const historyRef = ref<HTMLElement | null>(null)

const send = () => {
  if (!input.value.trim()) return
  messages.value.push({ role: 'user', content: input.value, time: Date.now() })
  props.onSend(input.value, messages.value)
  input.value = ''
}

const addAiMessage = (content: string) => {
  messages.value.push({ role: 'ai', content, time: Date.now() })
}

const formatTime = (t: number) => {
  const d = new Date(t)
  return d.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', second: '2-digit' })
}

watch(messages, async () => {
  await nextTick()
  if (historyRef.value) {
    historyRef.value.scrollTop = historyRef.value.scrollHeight
  }
}, { deep: true })

defineExpose({ addAiMessage, messages })
</script>

<style scoped>
.chat-room {
  width: 100%;
  margin: 0 auto 0 auto;
  border: none;
  border-radius: 0;
  background: #fff;
  box-shadow: none;
  display: flex;
  flex-direction: column;
  height: 100%;
  min-height: 0;
  position: relative;
  align-items: stretch;
  justify-content: flex-start;
}
.chat-header {
  display: none;
}
.chat-history {
  flex: 1;
  overflow-y: auto;
  background: #f6f7fa;
  padding: 18px 8px 18px 8px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
.msg-row {
  display: flex;
  align-items: flex-end;
  gap: 10px;
  max-width: 900px;
  width: 100%;
}
.msg-row.user {
  flex-direction: row-reverse;
}
.avatar {
  width: 38px;
  height: 38px;
  border-radius: 50%;
  overflow: hidden;
  background: #222;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  box-shadow: 0 2px 8px #0002;
}
.avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.bubble-wrap {
  max-width: 70vw;
  min-width: 0;
  display: flex;
  flex-direction: column;
}
.bubble {
  background: #23272e;
  color: #fff;
  border-radius: 12px;
  padding: 12px 16px 8px 16px;
  font-size: 1rem;
  text-align: left;
  box-shadow: 0 2px 8px #0002;
  word-break: break-word;
  min-width: 60px;
  max-width: 100%;
}
.ai .bubble {
  background: #f5f6fa;
  color: #222;
}
.user .bubble {
  background: #1a9aff;
  color: #fff;
}
.bubble-content {
  text-align: left;
}
.bubble-meta {
  font-size: 12px;
  color: #b7e7c7;
  text-align: right;
  margin-top: 2px;
}
.chat-input {
  display: flex;
  gap: 8px;
  padding: 6px 12px;
  border-top: 1px solid #e0e0e0;
  background: #fff;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 18px;
}
input {
  flex: 1;
  padding: 6px 12px;
  font-size: 1rem;
  border-radius: 8px;
  border: 1.5px solid #222;
  background: #fff;
  color: #222;
  outline: none;
  transition: border 0.2s;
}
input:focus {
  border: 1.5px solid #1aaa55;
}
button {
  padding: 6px 22px;
  font-size: 1rem;
  border-radius: 8px;
  border: none;
  background: v-bind('props.buttonColor || "#17643a"');
  color: #fff;
  cursor: pointer;
  font-weight: bold;
  transition: background 0.2s, box-shadow 0.2s;
  box-shadow: 0 2px 8px #0002;
}
button:active, button:hover {
  background: v-bind('props.buttonHoverColor || "#1aaa55"');
}
.chat-footer {
  padding: 8px 0 8px 0;
  text-align: center;
  color: #888;
  font-size: 0.95rem;
  background: transparent;
}
.empty-tip {
  color: #bbb;
  font-size: 1.3rem;
  text-align: center;
  margin-top: 18vh;
  letter-spacing: 2px;
}
@media (max-width: 900px) {
  .chat-room {
    max-width: 98vw;
    height: 90vh;
    min-height: 320px;
  }
}
@media (max-width: 600px) {
  .chat-room {
    max-width: 100vw;
    border-radius: 0;
    height: 100vh;
    min-height: 0;
  }
  .chat-header {
    border-radius: 0;
    padding: 14px 4px 10px 4px;
  }
  .chat-history {
    padding: 10px 2px 10px 2px;
  }
  .bubble {
    font-size: 0.98rem;
    padding: 10px 10px 6px 10px;
  }
  .avatar {
    width: 32px;
    height: 32px;
  }
}
</style>