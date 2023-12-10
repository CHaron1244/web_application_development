import Vue from 'vue'
import ElementUI from 'element-ui' // 官方是将这两条element-ui放在Vue和App之间的
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios' // 引入axios
import App from './App.vue'
import router from './router'
import store from './store' // 引入store
import locale from 'element-ui/lib/locale/lang/en' // 使用英文组件
import './assets/css/global.css'
import dayjs from 'dayjs'

Vue.prototype.$dayjs = dayjs

Vue.use(ElementUI, { locale }) // 使用英文组件
Vue.config.productionTip = false

Vue.use(ElementUI)
axios.defaults.baseURL = 'http://114.132.219.217:9090/'
Vue.prototype.$axios = axios
Vue.prototype.$http = axios
new Vue({
  render: h => h(App),
  store,
  router,
  beforeCreate () {
    Vue.prototype.$bus = this // 安装全局事件总线，$bus就是当前应用的vm
  }
}).$mount('#app')
