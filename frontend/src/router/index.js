import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Bank from '../views/Bank.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/bank/:numero',
    name: 'Bank',
    component: Bank,
    props: true
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router