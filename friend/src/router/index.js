import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../layout/Layout'
import Bill from '../views/Bill' // 账单界面
import BrowseLog from '../views/BrowseLog' // 浏览日志界面
import BuyLog from '../views/BuyLog' // 购买日志界面
import Cart from '../views/Cart' // 购物车界面
import Customer from '../views/Customer' // 顾客商品页面
import Good from '../views/Good' // 所有商品页面，商品管理
import Login from '../views/Login' // 登录页面
import Person from '../views/Person' // 个人主页
import Register from '../views/Register' // 注册页面
import allPersonInfo from '../views/AllPersonInfo'
Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) { // 解决控制台navigation冗余报错的问题
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/login',
    children: [{
      path: '/good',
      name: 'Good',
      component: Good
    },
    {
      path: '/person',
      name: 'Person',
      component: Person
    },
    {
      path: '/allPersonInfo',
      name: 'AllPersonInfo',
      component: allPersonInfo
    },
    {
      path: '/customer',
      name: 'Customer',
      component: Customer
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/bill',
      name: 'Bill',
      component: Bill
    },
    {
      path: '/browseLog',
      name: 'BrowseLog',
      component: BrowseLog
    },
    {
      path: '/buyLog',
      name: 'BuyLog',
      component: BuyLog
    }]
  }, {
    path: '/login', // 登录页面
    name: 'Login',
    component: Login
  }, {
    path: '/register', // 注册页面
    name: 'Register',
    component: Register
  },{
    path: '*',
    component: Login
  }
]
const router = new VueRouter({
  mode: 'hash', // modify (history)
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  if (to.path == '/alipay/pay') {
    window.location.href = 'http://114.132.219.217:9090/alipay/pay'// 修改跳转的目标地址为9090端口 modify
  } else {
    // 其他路径保持不变
    next()
  }
})
export default router
