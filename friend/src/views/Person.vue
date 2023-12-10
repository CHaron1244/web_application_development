<template>
  <div>
    <span style="font-size: 40px;color:blue;text-align: center;line-height: 80px;">{{this.user_form.userAccount}}'s
      PERSONAL INFORMATION</span>
    <el-card style="width: 100%; margin-left: 5px;margin-top: 10px;margin-right: 30px;">
      <el-form ref="form" :model="user_form" label-width="150px">
        <el-form-item label="USER ACCOUNT">
          <el-tooltip class="item" effect="dark" :visible-arrow="false" :open-delay="100"
            content="You know you cannot change your user account!Oops!" placement="top-start">
            <el-input v-model="user_form.userAccount" disabled></el-input>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="USER PASSWORD">
          <el-input v-model="user_form.userPassword" :disabled="disabledEdit"></el-input>
        </el-form-item>
        <el-form-item label="USER EMAIL">
          <el-input v-model="user_form.userEmail" :disabled="disabledEdit"></el-input>
        </el-form-item>
        <el-form-item label="USER ROLE">
          <el-input v-model="user_form.userRole" :disabled="disabledEdit"></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="editPersonInfo">EDIT YOUR INFORMATION</el-button>
        <el-button type="primary" @click="closeEdit">CLOSE EDIT</el-button>
        <el-button type="info" @click="savePersonInfo">SAVE YOUR EDITED INFORMATION</el-button>
        <el-button type="danger" @click="cancelPersonInfo">CANCEL YOUR INFORMATION</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "Person",
    data() {
      return {
        user_form: {},
        disabledEdit: true
      }
    },
    created() {
      let str = sessionStorage.getItem("user") || "{}"
      this.user_form = JSON.parse(str)
    },
    methods: {
      editPersonInfo() {
        this.disabledEdit = false
      },
      closeEdit() {
        this.disabledEdit = true
      },
      savePersonInfo() {
        this.$confirm('Do you really want to save your edited personal information?', 'TIP', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          axios.post('/user/updateUser', this.user_form).then(res => {
            const data = res.data
            if (data.code == '0') {
              this.$message({
                type: 'success',
                message: 'Successfully edit your personal information!'
              })
            } else {
              this.$message({
                type: 'danger',
                message: 'fail to edit!'
              })
            }
          }).catch(error => {
            this.$message({
              type: 'danger',
              message: 'fail to edit!'
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Cancel the edited information!'
          })
        })
      },
      cancelPersonInfo() {
        this.$confirm('Do you really want to cancel your edited personal information?', 'TIP', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          axios.post(`/user/deleteUserByAccount/${this.user_form.userAccount}`).then(res => {
            if (res.data.code == '0') {
              this.$message({
                type: "success",
                message: `Good bye ${this.user_form.userAccount}, thanks for your coming! Wish you a good day!`
              })
              sessionStorage.setItem("user", JSON.stringify(null))
              this.$router.push('/login')
            } else {
              this.$message({
                type: "info",
                message: 'Sorry,fail to cancel your personal information!'
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: "info",
            message: 'Terminate your account cancellation!'
          })
        })
      }
    }
  }
</script>
