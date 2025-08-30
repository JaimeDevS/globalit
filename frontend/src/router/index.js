import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Bank from '../views/bank.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/bank',
    name: 'Bank',
    component: Bank
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router