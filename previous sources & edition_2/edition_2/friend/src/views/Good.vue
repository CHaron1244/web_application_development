<template>
  <div>
    <div style="margin:10px 0">
      <el-input v-model="search" placeholder="Please enter the name of goods to find what you like!"
        style="width: 50%;border-bottom-color: black" clearable></el-input>
      <el-button type="primary" style="margin-left:5px;background-color: black; color: white;border-color: white;"
        @click="load">search</el-button>
      <el-button type="success"
        style="margin-left:10px ;background-color: rgb(121, 121, 190); color: white;border-color: white;"
        @click="showAddGoodDialog">add a good</el-button>
      <el-dialog title="Now you can add a good you want!" :visible="addDialogVisible" @close="addDialogVisible = false">
        <el-form :model="addGoodForm" ref="addGoodForm" label-width="120px">
          <el-form-item label="Name">
            <el-input v-model="addGoodForm.goodName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Price">
            <el-input v-model="addGoodForm.goodPrice" type="number" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Picture">
            <el-input v-model="addGoodForm.goodImg" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Information">
            <el-input v-model="addGoodForm.goodInfo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Type">
            <el-input v-model="addGoodForm.goodType" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">Cancel</el-button>
          <el-button type="danger" @click="addGood">Confirm</el-button>
        </div>
      </el-dialog>
    </div>
    <el-table :data="readTableData" style="width: 100%;" border height="1000">
      <el-table-column prop="goodName" label="GOOD NAME" align="center"></el-table-column>
      <el-table-column prop="goodPrice" label="GOOD PRICE" align="center"></el-table-column>
      <el-table-column prop="" label="GOOD PICTURE" align="center">
        <template slot-scope="scope">
          <img :src="require(`@/assets/imgs/${scope.row.goodName}.png`)" alt="hello" width="100%"></img> <!--TODO,图片路径未处理好-->
        </template>
      </el-table-column>
      <el-table-column prop="goodSale" label="GOOD SALE" align="center"></el-table-column>
      <el-table-column prop="goodInfo" label="GOOD INFORMATION" align="center"></el-table-column>
      <el-table-column prop="goodType" label="GOOD TYPE" align="center"></el-table-column>
      <el-table-column label="OPERATIONS" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="success"
            @click="showEditGoodDialog(scope.$index, scope.row)">EDIT</el-button>&nbsp;
          <el-dialog title="Now you can edit the information of this good!" :visible="editDialogVisible"
            @close="editDialogVisible = false">
            <el-form :model="editGoodForm" ref="editGoodForm" label-width="120px">
              <!-- <el-form-item label="Name">
                <el-input v-model="editGoodForm.goodName" autocomplete="off"></el-input>
              </el-form-item> -->
              <el-form-item label="Price">
                <el-input v-model="editGoodForm.goodPrice" type="number" autocomplete="off"></el-input>
                <!--TODO:Double类型也能用number吗？-->
              </el-form-item>
              <el-form-item label="Picture">
                <el-input v-model="editGoodForm.goodImg" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="Information">
                <el-input v-model="editGoodForm.goodInfo" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="Type">
                <el-input v-model="editGoodForm.goodType" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="editDialogVisible = false">Cancel</el-button>
              <el-button type="danger" @click="editGood">Confirm</el-button>
            </div>
          </el-dialog>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">DELETE</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Customer',
    data() {
      return {
        index: 0,
        row: {},
        tableData: [], // 所有商品信息
        search: '',
        user: {},
        editGoodForm: {
          goodName: '',
          goodPrice: null,
          goodImg: '',
          goodSale: 0,
          goodInfo: '',
          goodType: ''
        },
        addGoodForm: {
          goodName: '',
          goodPrice: null,
          goodImg: '',
          goodSale: 0,
          goodInfo: '',
          goodType: ''
        },
        editDialogVisible: false,
        addDialogVisible: false
      };
    },
    created() {
      let str = sessionStorage.getItem("user"); // str is a JSON object
      this.user = JSON.parse(str); // user is a js object (this.user.userAccount)访问
      this.load(); // 加载所有商品信息
    },
    methods: {
      load() {
        axios.get("/good/selectAllGoods").then((res) => {
          this.tableData = res.data.data;
        });
      },
      showEditGoodDialog(index, row) {
        this.editDialogVisible = true
        this.index = index
        this.row = row
      },
      showAddGoodDialog() {
        this.addDialogVisible = true
      },
      editGood() { // console.log(this.index, this.row);
        const role = this.user.userRole
        if (role == '1') {
          this.editGoodForm.goodName = this.row.goodName
          this.editGoodForm.goodSale = this.row.goodSale
          console.log(this.editGoodForm)
          axios.post('/good/updateGood', this.editGoodForm).then(res => {
            const ans = res.data
            if (ans.code === '0') {
              this.$message({
                message: `Successfully edit your commodity int the list of goods!`,
                type: 'success'
              })
            } else {
              this.$message({
                type: "error",
                message: 'Sorry,fail to edit this item!!'
              })
            }
          }).catch(error => {
            this.$message({
              type: "error",
              message: 'Sorry,fail to edit this item!!'
            })
          })
          this.load() // 数据更新就要加载
          this.editDialogVisible = false
          this.editGoodForm = {
            goodName: '',
            goodPrice: null, // 设置为空是为了下一次点击edit弹出dialog时Price栏为空,不出现上次输入遗留下的结果
            goodImg: '',
            goodSale: 0,
            goodInfo: '',
            goodType: ''
          }
        }
      },
      addGood() {
        const role = this.user.userRole
        this.addGoodForm.goodSale = 0
        console.log(this.addGoodForm)
        axios.post('/good/addGood', this.addGoodForm).then(res => {
          const ans = res.data
          if (ans.code == '0') {
            this.$message({
              message: `Successfully add your commodity int the list of goods!`,
              type: 'success'
            })
          } else {
            this.$message({
              type: "error",
              message: 'Sorry,fail to add this item!!'
            })
          }
        }).catch(error => {
          this.$message({
            type: "error",
            message: 'Sorry,fail to add this item!!'
          })
        })
        this.load() // 数据更新就要加载
        this.addDialogVisible = false
        this.addGoodForm = {
          goodName: '',
          goodPrice: null, // 设置为空是为了下一次点击edit弹出dialog时Price栏为空,不出现上次输入遗留下的结果
          goodImg: '',
          goodSale: 0,
          goodInfo: '',
          goodType: ''
        }
      },
      handleDelete(index, row) {
        this.$confirm('DO you really want to delete this commodity, so people cannot enjoy the dish!', 'REMOVE THE DELICACY', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          const goodName = row.goodName
          axios.post(`/good/deleteGoodByName/${goodName}`).then(res => {
            if (res.data.code === '0') {
              this.$message({
                message: `Successfully delete ${goodName}`,
                type: 'success'
              })
            } else {
              this.$message({
                message: 'Something wrong happened!Good is not successfully deleted!',
                type: 'error'
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'You have chosen to not delete! So people can enjoy one more treat'
          })
        })
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
        return this.tableData.filter(item => {
          if (this.search === '')
            return item.goodName.toLowerCase().includes('');
          else
            return item.goodName.toLowerCase().includes(this.search.toLowerCase());
        });
      }
    }
  };
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
