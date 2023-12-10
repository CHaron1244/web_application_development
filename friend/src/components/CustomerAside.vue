<template>
  <div>
    <!--顾客只能看到侧栏里面的customer，cart，order-->
    <el-menu style="height:100%" default-active="path" router class="el-menu-vertical-demo" :router="false"
             @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
      <el-menu-item index="cart"> <!--index里面内容点击之后会跟在url后面(注意index包不包含/要看基url最后有没有/ )-->
        <i class="el-icon-shopping-cart-2"></i>
        <span slot="title">CART</span>
        <!--<router-link to="/cart">CART</router-link> 只放这个也能实现跳转页面-->
      </el-menu-item>
      <el-menu-item index="bill">
        <i class="el-icon-s-order"></i>
        <span slot="title">ORDERS</span>
      </el-menu-item>
      <el-menu-item index="customer">
        <i class="el-icon-user-solid"></i>
        <span slot="title">CUSTOMER</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import Vue from 'vue'
import ElementUI from 'element-ui'

Vue.use(ElementUI)

export default {
  name: 'CustomerAside',
  data () {
    return {
      user: {},
      path: '' // 获取当前路由地址，并去掉第一个字符 "/"
    }
  },
  created () {
    const str = sessionStorage.getItem('user') || '{}'
    this.user = JSON.parse(str)
    this.path = this.$route.path.substr(1)
  },
  methods: {
    handleSelect (index) {
      if (index == 'cart') { // 都可以看
        this.$message({
          type: 'success',
          message: 'You have successfully gone to cart!'
        })
        this.$router.push('/cart')
      } else if (index == 'bill') { // 都可以看
        this.$message({
          type: 'success',
          message: 'You have successfully gone to bill!'
        })
        this.$router.push('/bill')
      } else if (index == 'customer') {
        const role = this.user.userRole
        this.$message({
          type: 'success',
          message: 'You have successfully gone to customer!'
        })
        this.$router.push('/customer')
      }
    }
  }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
