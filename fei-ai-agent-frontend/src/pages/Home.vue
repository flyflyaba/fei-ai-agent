<template>
  <div class="home">
    <div class="main-title">AI智能助手应用平台</div>
    <div class="sub-title">/ 选择下方应用开始体验 /</div>
    <div class="app-cards">
      <router-link to="/travel" class="app-card">
        <div class="icon travel-icon">🗺️</div>
        <div class="app-title">AI旅游大师</div>
        <div class="app-desc">智能规划行程，解答旅游相关问题。</div>
      </router-link>
      <router-link to="/manus" class="app-card">
        <div class="icon manus-icon">🤖</div>
        <div class="app-title">AI超级智能体</div>
        <div class="app-desc">全能型AI助手，解决各类专业问题。</div>
      </router-link>
    </div>
    <AppFooter color="white" />
  </div>
</template>

<script setup lang="ts">
import AppFooter from '../components/AppFooter.vue'
// SEO优化
if (typeof document !== 'undefined') {
  document.title = 'AI智能助手应用平台';
  const metaDesc = document.querySelector('meta[name="description"]') || document.createElement('meta');
  metaDesc.setAttribute('name', 'description');
  metaDesc.setAttribute('content', 'AI智能助手应用平台，支持AI旅游大师与AI超级智能体，体验AI对话。');
  document.head.appendChild(metaDesc);
}
</script>

<style scoped>
.home {
  min-height: 90vh;
  background: linear-gradient(135deg, #10151a 60%, #1aaa55 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 70px;
  position: relative;
  font-family: 'JetBrains Mono', 'Consolas', 'Menlo', monospace;
}
.main-title {
  font-size: 2.6rem;
  font-weight: bold;
  margin-bottom: 12px;
  letter-spacing: 2px;
  color: #1aaa55;
  text-shadow: 0 4px 24px #1aaa5555, 0 1px 0 #222;
  font-family: inherit;
}
.sub-title {
  font-size: 1.15rem;
  color: #b7e7c7;
  margin-bottom: 38px;
  font-family: inherit;
}
.app-cards {
  display: flex;
  gap: 48px;
  margin-bottom: 60px;
  flex-wrap: wrap;
  justify-content: center;
}
.app-card {
  width: 260px;
  height: 260px;
  background: #181c20;
  border-radius: 22px;
  box-shadow: 0 4px 32px #1aaa5522, 0 1.5px 0 #222;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  transition: box-shadow 0.25s, transform 0.22s, border 0.22s;
  cursor: pointer;
  border: 2.5px solid #23272e;
  position: relative;
  overflow: hidden;
}
.app-card:hover {
  box-shadow: 0 12px 48px #1aaa55cc, 0 2px 0 #1aaa55;
  border: 2.5px solid #1aaa55;
  transform: translateY(-8px) scale(1.045) rotate(-1deg);
  z-index: 2;
}
.icon {
  font-size: 3.6rem;
  margin-bottom: 18px;
  filter: drop-shadow(0 2px 8px #1aaa55cc);
}
.app-title {
  font-size: 1.35rem;
  font-weight: bold;
  margin-bottom: 8px;
  color: #1aaa55;
  font-family: inherit;
  letter-spacing: 1px;
}
.app-desc {
  font-size: 1.05rem;
  color: #b7e7c7;
  text-align: center;
  padding: 0 10px;
  font-family: inherit;
}
.footer {
  width: 100vw;
  text-align: center;
  color: #b7e7c7;
  font-size: 1rem;
  padding: 32px 0 18px 0;
  position: absolute;
  left: 0;
  bottom: 0;
  background: transparent;
  font-family: inherit;
  letter-spacing: 1px;
}
@media (max-width: 900px) {
  .app-cards {
    gap: 24px;
  }
  .app-card {
    width: 200px;
    height: 200px;
  }
}
@media (max-width: 600px) {
  .main-title {
    font-size: 1.3rem;
  }
  .app-cards {
    gap: 12px;
    flex-direction: column;
    align-items: center;
  }
  .app-card {
    width: 92vw;
    height: 120px;
    border-radius: 14px;
    margin-bottom: 10px;
    flex-direction: row;
    justify-content: flex-start;
    padding-left: 18px;
  }
  .icon {
    font-size: 2.1rem;
    margin-bottom: 0;
    margin-right: 18px;
  }
  .app-title {
    font-size: 1.1rem;
    margin-bottom: 2px;
  }
  .app-desc {
    font-size: 0.95rem;
    padding: 0 2px;
  }
  .footer {
    font-size: 0.92rem;
    padding: 18px 0 10px 0;
  }
}
</style>