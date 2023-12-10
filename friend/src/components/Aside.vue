<template>
  <div>
    <el-menu style="height:100%" default-active="path" router class="el-menu-vertical-demo" :router="false"
      @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
      <el-menu-item index="cart"> <!--index里面内容点击之后会跟在url后面-->
        <i class="el-icon-shopping-cart-2"></i>
        <span slot="title">CART</span>
      </el-menu-item>
      <el-menu-item index="bill">
        <i class="el-icon-s-order"></i>
        <span slot="title">ORDERS</span>
      </el-menu-item>
      <el-menu-item index="browseLog">
        <i class="el-icon-s-data"></i>
        <span slot="title">BROWSELOGS</span>
      </el-menu-item>
      <el-menu-item index="buyLog">
        <i class="el-icon-s-data"></i>
        <span slot="title">BUYLOGS</span>
      </el-menu-item>
      <el-menu-item index="allPersonInfo">
        <i class="el-icon-user-solid"></i>
        <span slot="title">AllPERSONINFO</span>
      </el-menu-item>
      <el-menu-item index="good">
        <i class="el-icon-s-goods"></i>
        <span slot="title">GOOD</span>
      </el-menu-item>
      <el-menu-item index="customer">
        <i class="el-icon-user-solid"></i>
        <span slot="title">CUSTOMER</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  import Vue from "vue";
  import ElementUI from 'element-ui'
  Vue.use(ElementUI)

  export default {
    data() {
      return {
        user: {},
        path: '' // 获取当前路由地址，并去掉第一个字符 "/"
      }
    },
    created() {
      let str = sessionStorage.getItem("user") || "{}"
      this.user = JSON.parse(str)
      this.path = this.$route.path.substr(1)
    },
    methods: {
      handleSelect(index) {
        if (index == 'good') { // 只有admin可以看
          const role = this.user.userRole
          if (role == '1') {
            this.$message({
              type: 'success',
              message: 'You have access to good!'
            })
            this.$router.push('/good')
          } else {
            this.$message({
              type: 'error',
              message: 'You have no access to good!'
            })
          }
        } else if (index == 'cart') {  // 都可以看
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
        } else if (index == 'browseLog') { // 只有admin可以看
          const role = this.user.userRole
          if (role == '1') {
            this.$message({
              type: 'success',
              message: 'You have access to browseLog!'
            })
            this.$router.push('/browseLog')
          } else {
            this.$message({
              type: 'error',
              message: 'You have no access to browseLog!'
            })
          }
        } else if (index == 'buyLog') { // 只有admin可以看
          const role = this.user.userRole
          if (role == '1') {
            this.$message({
              type: 'success',
              message: 'You have access to buyLog!'
            })
            this.$router.push('/buyLog')
          } else {
            this.$message({
              type: 'error',
              message: 'You have no access to buyLog!'
            })
          }
        } else if (index == 'allPersonInfo') { // 只有admin可以看
          const role = this.user.userRole
          if (role == '1') {
            this.$message({
              type: 'success',
              message: 'You have access to ALLPERSONINFO!'
            })
            this.$router.push('/allPersonInfo')
          } else {
            this.$message({
              type: 'error',
              message: 'You have no access to ALLPERSONINFO!'
            })
          }
        } else if (index == 'customer') {
          const role = this.user.userRole
          if (role == '0') {
            this.$message({
              type: 'success',
              message: 'You have successfully gone to customer!'
            })
            this.$router.push('/customer')
          } else {
            this.$message({
              type: 'error',
              message: 'You have no access to customer!'
            })
          }
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
