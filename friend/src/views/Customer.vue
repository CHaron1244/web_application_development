<template>
  <div>
    <div style="margin:10px 0">
      <el-input v-model="search" placeholder="Please enter the name of goods to find what you like!"
                style="width: 50%;border-bottom-color: black" clearable></el-input>
      <el-button type="primary" style="margin-left:  5px ;
        background-color: black; color: white;border-color: white;" @click="load">search
      </el-button>
    </div>
    <!-- 在el-table上加上height属性就可以固定表头，先用着再看要不要不需要这个功能 -->
    <el-table :data="readTableData" style="width: 100%;font-size: 15px;font-weight: bold" border height="1000">
      <el-table-column prop="goodName" label="GOOD NAME" align="center"></el-table-column>
      <el-table-column prop="goodPrice" label="GOOD PRICE" align="center">
        <template slot-scope="scope">
          $&nbsp;{{scope.row.goodPrice}}
        </template>
      </el-table-column>
      <el-table-column prop="" label="GOOD PICTURE" align="center">
        <template slot-scope="scope">
          <!-- <img src="../../public/imgs/Americano.png" alt="hello" width="100%"></img> TODO,图片路径未处理好 -->
          <img :src="require(`@/assets/imgs/${scope.row.goodName}.png`)" alt="hello" width="100%"></img>
        </template>
      </el-table-column>
      <el-table-column prop="goodSale" label="GOOD SALE" align="center"></el-table-column>
      <el-table-column prop="goodInfo" label="GOOD INFORMATION" align="center"></el-table-column>
      </el-table-column>
      <el-table-column prop="goodType" label="GOOD TYPE" align="center"></el-table-column>
      <el-table-column label="OPERATION" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="showAddToCartDialog(scope.$index, scope.row)">Add to
            Cart
          </el-button>
          <el-dialog title="Now Add this refreshment to your cart!" :visible="addToCartDialogVisible"
                     @close="addToCartDialogVisible = false">
            <el-form :model="addToCartForm" ref="addToCartForm" label-width="80px">
              <el-form-item label="Quantity">
                <el-input v-model="addToCartForm.quantity" type="number" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="addToCartDialogVisible = false">Cancel</el-button>
              <el-button type="primary" @click="AddToCart">Confirm</el-button>
            </div>
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column label="FURTHER" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="info" @click="generateBrowseLog(scope.$index, scope.row)">WANT TO KNOW MORE?
          </el-button>
          <el-dialog title="NOTICE" :visible.sync="dialogVisible" width="40%">
            <span>We will notify you if there's more information, and this will produce a browse log. Good luck!</span>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="dialogVisible = false">Confirm</el-button>
            </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
import dayjs from 'dayjs'

export default {
  name: 'Customer',
  data () {
    return {
      dialogVisible: false,
      index: 0,
      row: {},
      addToCartDialogVisible: false, // 弹出框默认不出现
      addToCartForm: {
        quantity: 1 // 默认数量为1
      },
      dialogFormVisible: false,
      form: {},
      tableData: [], // 所有商品信息
      search: '',
      user: {},
      currentTime: ''
    }
  },
  created () {
    const str = sessionStorage.getItem('user') // str is a JSON object
    this.user = JSON.parse(str) // user is a js object (this.user.userAccount)访问
    this.load() // 加载所有商品信息
  },
  methods: {
    showAddToCartDialog (index, row) {
      this.addToCartDialogVisible = true
      this.index = index
      this.row = row
    },
    load () {
      axios.get('/good/selectAllGoods').then((res) => {
        this.tableData = res.data.data
      })
    },
    AddToCart () { // 自己想的方法：用data里面的index row来存点击按钮返回过来的index row
      const role = this.user.userRole
      if (role == '0') { // 规定只有customer才能购物，管理员只能管理商品
        // let str = sessionStorage.getItem('user') // str is a JSON object
        const userAccount = this.user.userAccount
        const goodName = this.row.goodName
        const goodPrice = this.row.goodPrice
        const goodNumber = this.addToCartForm.quantity
        const form = {
          userAccount,
          goodName,
          goodPrice,
          goodNumber
        }
        axios.post('/cart/addItem', form).then(res => {
          const ans = res.data
          if (ans.code == '0') {
            this.$message({
              message: 'Successfully add your commodity to your cart!',
              type: 'success'
            })
          } else {
            this.$message({
              type: 'error',
              message: 'Sorry,fail to add this item failed!!'
            })
          }
        }).catch(error => {
          this.$message({
            type: 'error',
            message: 'Sorry,fail to add this item!!'
          })
        })
        this.load() // 数据更新就要加载
        this.addToCartForm.quantity = 1 // 重置为1每次
        this.addToCartDialogVisible = false
      }
    },
    handleDelete (index, row) { // console.log(index, row); index是这一行数据索引(from 0)，row是这一行数据，可以直接获取元素
      const goodName = row.goodName
      axios.post(`/good/deleteGoodByName/${goodName}`).then(res => {
        if (res.data.code == '0') {
          this.$message({
            message: `Successfully delete ${goodName}`,
            type: 'success'
          })
        } else {
          this.$message({
            message: 'Something wrong happened!',
            type: 'error'
          })
        }
      })
    },
    generateBrowseLog (index, row) {
      const account = this.user.userAccount
      const goodName = row.goodName
      this.currentTime = dayjs().format('YYYY-MM-DD HH:mm:ss')
      const form = {
        userAccount: account,
        goodName: goodName,
        browseTime: this.currentTime
      }
      axios.post('/browseLog/addBrowseLog', form).then(res => {
        if (res.data.code == '0') {
          this.$message({
            type: 'success',
            message: 'Successfully add this browse log!'
          })
        } else {
          this.$message({
            type: 'error',
            message: 'Fail to add this browse log!'
          })
        }
      }).catch(error => {
        this.$message({
          type: 'error',
          message: 'Oops! Something wrong happened!'
        })
      })
      setTimeout(() => {
        this.dialogVisible = true // 提醒用户会产生browse log
      }, 1000)
    }
  },
  watch: {
    tableData () {
      deep: true,
      this.load()
    }
  },
  computed: {
    readTableData () {
      return this.tableData.filter(item => {
        if (this.search === '') {
          return item.goodName.toLowerCase().includes('')
        } else {
          return item.goodName.toLowerCase().includes(this.search.toLowerCase())
        }
      })
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-col {
  border-radius: 4px;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
</style>
