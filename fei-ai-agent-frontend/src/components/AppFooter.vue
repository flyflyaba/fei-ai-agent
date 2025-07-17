<template>
  <footer class="footer" :class="footerClass">
    <div class="footer-col logo-col">
      <div class="footer-title">AI智能助手应用平台</div>
      <div class="footer-links">
        <a href="#" class="footer-link">《用户协议》</a>
        <a href="#" class="footer-link">《隐私政策》</a>
      </div>
    </div>
    <div class="footer-col links-col">
      <div class="footer-title">友情链接</div>
      <a href="https://github.com/flyflyaba/fei-ai-agent" target="_blank" class="footer-link">GitHub</a>
    </div>
    <div class="footer-col contact-col">
      <div class="footer-title">联系我们</div>
      <div class="footer-link">商务合作</div>
    </div>
  </footer>
</template>

<script setup lang="ts">
const props = defineProps<{ color?: string }>()
const footerClass = props.color === 'white' ? 'footer-white' : ''
</script>

<style scoped>
.footer {
  width: 100vw;
  background: transparent;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 60px;
  padding: 38px 0 28px 0;
  border-top: none;
  font-family: 'JetBrains Mono', 'Consolas', 'Menlo', monospace;
  color: inherit;
  font-size: 1rem;
  flex-wrap: wrap;
}
.footer-white {
  color: #fff !important;
}
.footer-col {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 160px;
  max-width: 220px;
  flex: 1 1 180px;
  margin-bottom: 12px;
  text-align: center;
}
.footer-title {
  font-weight: bold;
  font-size: 1.1rem;
  margin-bottom: 10px;
  color: inherit;
}
.footer-links {
  display: flex;
  flex-direction: column;
  gap: 2px;
  align-items: center;
}
.footer-link {
  color: inherit;
  text-decoration: none;
  margin-bottom: 6px;
  font-size: 0.98rem;
  transition: color 0.2s;
}
.footer-link:hover {
  color: #1aaa55;
}
@media (max-width: 900px) {
  .footer {
    gap: 24px;
    padding: 28px 0 18px 0;
  }
}
@media (max-width: 600px) {
  .footer {
    flex-direction: column;
    align-items: center;
    gap: 0;
    padding: 18px 0 10px 0;
  }
  .footer-col {
    align-items: center !important;
    margin-bottom: 18px;
    min-width: 0;
    max-width: 100vw;
    text-align: center;
  }
  .footer-title {
    font-size: 1rem;
  }
}
</style> 