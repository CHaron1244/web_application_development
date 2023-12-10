<template>
  <div style="width: 100%;height: 100vh;background-color: powderblue; overflow: hidden" id="building">
    <div style="width: 600px;margin:40px auto">
      <img src="imgs\CentralPerk.png" alt="CENTRAL PERK">
      <div style="color: red ;font-size: 30px;text-align: center;padding: 30px 0">
        Welcome to GUNTHER'S CENTRAL PERK Registration Center
      </div>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120" class="demo-ruleForm">
        <el-form-item prop="userAccount" label="Input your user account">
          <el-input v-model="ruleForm.userAccount" type="text" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="userPassword" label="Enter your password">
          <el-input v-model="ruleForm.userPassword" type="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="userEmail" label="Enter your email">
          <el-input v-model="ruleForm.userEmail" type="email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="userRole" label="Choose your role">
          <el-radio v-model="ruleForm.userRole" :label="0">Customer</el-radio>
          <el-radio v-model="ruleForm.userRole" :label="1">Administrator</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">Register my account</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="danger" @click="backToLogin">back to login</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import { Message, MessageBox } from 'element-ui';
  import axios from 'axios'
  export default {
    name: "Register",
    data() {
      return {
        ruleForm: {
          userAccount: '',
          userPassword: '',
          userEmail: '',
          userRole: ''
        },
        rules: {
          userAccount: [
            { required: true, message: 'Please enter your user account,this cannot be an empty term ', trigger: 'blur' }
          ],
          userPassword: [
            { required: true, message: 'Please enter password,this cannot be an empty term', trigger: 'blur' }
          ],
          userEmail: [
            { required: true, message: 'Please enter email address,this cannot be an empty term', trigger: 'blur' },
            { type: 'email', message: 'Please enter the correct email address,this cannot be an empty term', trigger: ['blur', 'change'] }
          ],
          userRole: [
            { type: 'number', required: true, message: 'Please select your role,this cannot be an empty term', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      register() {
        this.$refs["ruleForm"].validate((valid) => {
          if (valid) {
            axios.post("/user/register", this.ruleForm).then(res => {
              if (res.data.code === '0') {
                this.$message({
                  message: 'Congratulations, you have registered successfully. Now click the button below to log in, and enjoy your afternoon tea!',
                  type: 'success'
                });
              } else {
                this.$message.error(res.data.msg);
              }
            }).catch(error => {
              this.$message.error(res.data.msg);
            })
          } else {
            this.$message.error('Error submit! Please check your input information!');
          }
        });
      },
      backToLogin() {
        this.$router.push("/login")// Redirect to login
      }
    }
  };
</script>
<style scoped>
  #building {
    background: url("../assets/imgs/friend.png") center center no-repeat;
    width: 50%;
    min-height: 100%;
    position: fixed;
    /* display: flex; */
    background-size: 100% 100%;
    margin: -60px;
  }
</style>
