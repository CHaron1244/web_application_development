<template>
  <!-- 定义登录页面的样式 -->
  <div style="width: 100%;height: 100vh;background-color: silver; overflow: hidden" id="building">
    <!-- 设置登录框的宽度和居中 -->
    <div style="width: 600px;margin:80px auto">
      <img src="../../src/assets/imgs/CentralPerk.png" alt="CENTRAL PERK">
      <!-- 显示标题 -->
      <div style="text-align: center; font-weight: bold;color: red; font-size: 40px;padding-bottom: 10px;">WELCOME TO
      </div>
      <div style="text-align: center; font-weight: bold;color: blue; font-size: 40px;padding-bottom: 40px;">GUNTHER'S
        CENTRAL PERK</div>
      <!-- 定义表单 -->
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" size="normal" label-width="120" class="demo-ruleForm">
        <!-- 输入用户名的表单项 -->
        <el-form-item prop="userAccount" label="Please enter your account">
          <el-input prefix-icon="el-icon-user-solid" v-model="ruleForm.userAccount"
            :style="{color: 'white', borderColor: 'white'}" type="text" autocomplete="off"></el-input>
        </el-form-item>
        <!-- 输入密码的表单项 -->
        <el-form-item prop="userPassword" label="Please enter your password">
          <el-input prefix-icon="el-icon-lock" v-model="ruleForm.userPassword"
            :style="{color: 'white', borderColor: 'white'}" type="password" autocomplete="off">
          </el-input>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button style="width: 100%;text-align: center;background-color: red;color: white;border-color: white"
            type="primary" @click="login">log in</el-button>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button style="width: 100%;text-align: center;background-color: black;color: white;border-color: white"
            type="primary" @click="register">register</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "Login", // 定义组件名称
    data() {
      return {
        ruleForm: {
          userAccount: '',
          userPassword: ''
        }, // 表单数据对象
        rules: { // 表单验证规则
          userAccount: [
            {
              required: true,
              message: 'Please enter your user account,this cannot be an empty term',
              trigger: 'blur',
            },
          ],
          userPassword: [
            {
              required: true,
              message: 'Please enter password,this cannot be an empty term',
              trigger: 'blur',
            },
          ]
        }
      }
    },
    methods: {
      register() { // 注册方法，跳转到注册页面
        this.$router.push("/register")
      },
      login() { // 登录方法
        axios.get(`/user/selectByAccount/${this.ruleForm.userAccount}`).then(res => {
          const data = res.data.data
          this.ruleForm.userEmail = data.userEmail
          this.ruleForm.userRole = data.userRole
        }).catch(error => {
          this.$message({
            type: "error",
            message: 'Illegal User!No such user account!'
          })
        })
        let str = sessionStorage.getItem("user") || "{}"
        this.$refs["ruleForm"].validate((valid) => { // 对表单进行验证
          if (valid) { // 如果验证通过
            axios.post("/user/login", this.ruleForm).then(res => { // 向服务器发送登录请求 返回的是Result,里面data是null res.data才是Result
              if (res.data.code == '0') { // 如果登录成功
                this.$message({
                  type: "success",
                  message: `login in successfully!Welcome to Gunther's Central Perk,${res.data.data.userAccount}!`
                })
                sessionStorage.setItem("user", JSON.stringify(this.ruleForm))  // 缓存用户信息
                if (this.ruleForm.userRole == 0) {
                  this.$router.push("/customer")//跳转到用户
                } else if (this.ruleForm.userRole == 1) {
                  this.$router.push("/good")//跳转到商品管理
                }
              } else {
                this.$message({
                  type: "error",
                  message: 'Wrong account or wrong password, fail to login to!'
                })
              }
            })
          } else { // 如果验证不通过
            this.$message({
              type: "error",
              message: 'Sorry, validation failed!!'
            })
          }
        })
      }
    },
  }
</script>

<style scoped>
  .el-message--success,
  .el-message--error {
    font-size: 30px
  }

  #building {
    background: url("../assets/imgs/friend.png");
    width: 50%;
    min-height: 100%;
    position: fixed;
    background-size: 100% 100%;
    margin: -60px;
  }
</style>
