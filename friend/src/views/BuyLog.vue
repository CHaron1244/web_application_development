<template>
  <div>
    <div styple="padding-top: 20px;">
      <span style="font-size: 30px;color:blue;margin-top: 20px;">BUY LOG INFORMATION</span>
    </div>
    <div style="margin:10px 0">
      <el-input v-model="search" placeholder="Search your order information! Find something by your account"
        style="width: 50%;border-bottom-color: black" clearable></el-input>
      <el-button type="primary" style="margin-left:  5px ;
        background-color: black; color: white;border-color: white;" @click="load">search</el-button>
    </div>
    <!-- 在el-table上加上height属性就可以固定表头，先用着再看要不要不需要这个功能 -->
    <el-table :data="readTableData" style="width: 100%;font-size: 15px;font-weight: bold" border height="500">
      <el-table-column prop="userAccount" label="USER ACCOUNT" align="center"></el-table-column>
      <el-table-column prop="goodName" label="GOOD NAME" align="center"></el-table-column>
      <el-table-column prop="goodNumber" label="GOOD NUMBER" align="center"></el-table-column>
      <el-table-column prop="buyTime" label="BUY TIME" align="center"></el-table-column>
    </el-table>
  </div>
</template>
<script>
  import axios from 'axios'
  import dayjs from 'dayjs'
  export default {
    name: 'Bill',
    data() {
      return {
        tableData: [], // 所有bill信息
        search: '',
        user: {},
        currentTime: ''
      }
    },
    created() {
      let str = sessionStorage.getItem("user"); // str is a JSON object
      this.user = JSON.parse(str); // user is a js object (this.user.userAccount)访问
      this.load();
    },
    methods: {
      load() { // 加载所有bill信息
        axios.get('/buyLog/selectAllBuyLogs').then(res => {
          this.tableData = res.data.data;
        })
      },
    },
    watch: {
      tableData() {
        deep: true,
          this.load()
      }
    },
    computed: {
      readTableData() { // 只展示当前用户的账单信息
        return this.tableData.filter(item => {
          if (this.search == '')
            return item.userAccount.toLowerCase().includes('')
          else
            return item.userAccount.toLowerCase().includes(this.search.toLowerCase())
        });
      }
    }
  }
</script>
