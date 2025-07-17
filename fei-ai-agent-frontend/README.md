# fei-ai-agent-frontend

基于 Vue3 + TypeScript 的 AI 应用前端

## 目录结构

```
fei-ai-agent-frontend/
├── index.html
├── public/
├── src/
│   ├── api/
│   │   └── chat.ts
│   ├── components/
│   │   └── ChatRoom.vue
│   ├── pages/
│   │   ├── Home.vue
│   │   ├── ManusChat.vue
│   │   └── TravelAppChat.vue
│   ├── router/
│   │   └── index.ts
│   ├── App.vue
│   ├── main.ts
│   └── shims-vue.d.ts
├── package.json
├── tsconfig.json
├── vite.config.ts
└── README.md
```

## 启动方式

```bash
npm install
npm run dev -- --host
```

访问：http://localhost:5173

> 注意：本项目的 `index.html` 需放在项目根目录（与 `package.json` 同级），而不是 `public/` 目录。 