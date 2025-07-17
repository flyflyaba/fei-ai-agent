import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../pages/Home.vue'
import TravelAppChat from '../pages/TravelAppChat.vue'
import ManusChat from '../pages/ManusChat.vue'

const routes: RouteRecordRaw[] = [
  { path: '/', component: Home },
  { path: '/travel', component: TravelAppChat },
  { path: '/manus', component: ManusChat }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 