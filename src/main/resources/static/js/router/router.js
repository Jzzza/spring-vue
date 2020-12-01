import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'components/messages/MessageList.vue'

Vue.use(VueRouter)

const routes = [
  {path:'/',component: MessagesList}
]

export default new VueRouter({
  routes
})