<template>
  <div style="height:80px; line-height: 50px;border-bottom: 1px solid black;padding-top:15px;padding-left:250px;display: flex">
    <div class="left-image">
      <img src="../../src/assets/imgs/CentralPerk.png" />
    </div>
    <div style="width: 1000px; font-weight: bold;color: red; font-size: 50px">GUNTHER'S CENTRAL PERK</div>
    <div class="right-image"><img src="../../src/assets/imgs/CentralPerk.png" /></div>
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown style="color:blueviolet" @command="handleCommand">
        <span class="el-dropdown-link" style="font-size:13px;">
          {{this.user.userAccount}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu split-button slot="dropdown">
          <el-dropdown-item command="a">personal infomation</el-dropdown-item>
          <el-dropdown-item command="b">log out the perk!</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Header",
    props: ['user'],
    data() {
      return {
        user: {}
      }
    },
    created() {
      let str = sessionStorage.getItem("user") || "{}";
      this.user = JSON.parse(str);
    },
    methods: {
      handleCommand(command){
        if(command === "a"){
            this.$message({
              type:'success',
              message:'Welcome to your personal centre!Note that you cannot edit if you don\'t push the edit button!'
            })
            this.showPersonInfo()
          }else{
            this.logout()
          }
      },
      showPersonInfo(){
        this.$router.push('/person')
      },
      logout() {
        sessionStorage.setItem("user", JSON.stringify(null));
        this.$message({
          type: 'success',
          message: 'Welcome to Gunther\'s CENTRAL PERK next time!'
        });

        // 在这里设置一个延迟，例如3秒（3000毫秒）
        setTimeout(() => {
          this.$router.push('/login');
        }, 3000);
      }
    }
  }
</script>

<style>
  .left-image,
  .right-image {
    flex: 1;
    /* 填充剩余空间，使图片高度与标题高度一致 */
    display: flex;
    justify-content: center;
    /* 水平居中对齐 */
    align-items: center;
    /* 垂直居中对齐 */
    height: 50%;
    padding-bottom: 20px;
  }
</style>
