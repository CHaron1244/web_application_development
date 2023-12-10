<template>
  <div>
    <div style="padding-top: 20px;">
      <span style="font-size: 30px;color:blue;margin-top: 20px;">CART INFORMATION</span>
    </div>
    <div style="margin:10px 0">
      <el-input v-model="search" placeholder="Search your shopping information! Find something by good's name"
        style="width: 50%;border-bottom-color: black" clearable></el-input>
      <el-button type="primary" style="margin-left:  5px ;
        background-color: black; color: white;border-color: white;" @click="load">search</el-button>
    </div>
    <!-- 在el-table上加上height属性就可以固定表头，先用着再看要不要不需要这个功能 -->
    <el-table :data="readTableData" style="width: 100%;font-size: 15px;font-weight: bold" border height="500">
      <el-table-column prop="userAccount" label="USER ACCOUNT" align="center"></el-table-column>
      <el-table-column prop="goodName" label="GOOD NAME" align="center"></el-table-column>
      <el-table-column prop="goodPrice" label="GOOD PRICE" align="center"></el-table-column>
      <el-table-column prop="goodNumber" label="GOOD NUMBER" align="center"></el-table-column>
      <el-table-column label="CHECKOUT" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="checkout(scope.$index, scope.row)">ADD A ORDER</el-button>
        </template>
      </el-table-column>
      <el-table-column label="PAYBILL" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="submitToAlipay(scope.$index, scope.row)">PAY THE BILL</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
  import axios from 'axios'
  import dayjs from 'dayjs'
  export default {
    name: 'AllPersonInfo',
    data() {
      return {
        tableData: [], // 所有人员信息
        search: '',
        user: {},
        currentTime: dayjs().format("YYYY-MM-DD HH:mm:ss")
      }
    },
    created() {
      let str = sessionStorage.getItem("user"); // str is a JSON object
      this.user = JSON.parse(str); // user is a js object (this.user.userAccount)访问
      this.load();
      this.currentTime = dayjs().format("YYYY-MM-DD HH:mm:ss")
    },
    methods: {
      load() { // 加载所有购物车商品信息
        axios.get('/cart/selectAllItemsInCart').then(res => {
          this.tableData = res.data.data;
        })
      },
      generateBuyLog(index, row) { // 产生buy log
        const buyLog_form = {
          userAccount: row.userAccount,
          goodName: row.goodName,
          goodNumber: row.goodNumber,
          buyTime: this.currentTime
        }
        axios.post('/buyLog/addBuyLog', buyLog_form).then(res => {
          if (res.data.code == '0') {
            this.$message({
              type: 'success',
              message: 'Successfully add this buy log!'
            })
          } else {
            this.$message({
              type: 'error',
              message: 'Fail to add this buy log!'
            })
          }
        }).catch(res => {
          this.$message({
            type: 'error',
            message: 'Oops! Something wrong happened!'
          })
        })
      },
      checkout(index, row) {
        const form = {
          userAccount: row.userAccount,
          goodName: row.goodName,
          goodNumber: row.goodNumber,
          orderTime: this.currentTime
        }
        // console.log('this.dayjs().format("YYYY-MM-DD HH:mm:ss")  '+dayjs().format("YYYY-MM-DD HH:mm:ss"))
        this.$confirm('Are you sure to check out the bill?', 'NOTICE', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.currentTime = dayjs().format("YYYY-MM-DD HH:mm:ss")
          axios.post('/bill/addBill', form).then(res => {
            this.$message({
              type: 'success',
              message: 'Successfully check out the bill!'
            })
            // 将商品的goodSale加上对应的销量
            axios.post(`/good/updateGoodByGoodName/${row.goodName}/${row.goodNumber}`).then(res => { // 再进行更新
              if (res.data.code == '0') {
                this.$message({
                  type: 'success',
                  message: 'Successfully update the sale of this good!'
                })
              } else {
                this.$message({
                  type: 'error',
                  message: 'Fail to update the sale of this good!'
                })
              }
            })
            const send_form = {
              userAccount: row.userAccount,
              goodName: row.goodName,
              goodNumber: row.goodNumber,
              buyTime: this.currentTime
            }
            // 发送邮件给user
            axios.post('/email/send', send_form).then(res => {
              const data = res.data
              if (data.code == '0') {
                this.$message({
                  type: 'success',
                  message: 'Successfully send  the email!'
                })
              } else {
                this.$message({
                  type: 'error',
                  message: 'Oops! Fail to send  the email!'
                })
              }
            }).catch(error => {
              this.$message({
                type: 'error',
                message: 'Fail to send  the email!'
              })
            })
            // 把cart中的这一项删除掉，因为已经加入到order里面了
            axios.post(`/cart/deleteItemByUserAccountAndGoodName/${row.userAccount}/${row.goodName}`).then(res => {
              const data = res.data
              if (data.code == '0') {
                this.$message({
                  type: 'success',
                  message: 'Successfully delete this item from the cart!'
                })
                this.generateBuyLog(index, row) // 在这里产生buy log记录
              } else {
                this.$message({
                  type: 'error',
                  message: 'Something wrong happened! Fail to check out the bill!'
                })
              }
            }).catch(error => {
              this.$message({
                type: 'error',
                message: 'Something wrong happened! Fail to check out the bill!'
              })
            })
          }).catch(error => {
            this.$message({
              type: 'error',
              message: 'Fail to check out the bill!'
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'You cancel to check out the bill!'
          })
        })
      },
      submitToAlipay(index, row) {
        this.$router.push('/alipay/pay')
      }
    },
    watch: {
      tableData() {
        deep: true,
          this.load()
      }
    },
    computed: {
      readTableData() {
        const account = this.user.userAccount
        return this.tableData.filter(item => {
          if (this.search == '')
            return item.goodName.toLowerCase().includes('') && item.userAccount === account;
          else
            return item.goodName.toLowerCase().includes(this.search.toLowerCase()) && item.userAccount === account;
        });
      }
    }
  }
</script>
