以下是使用 Element UI 实现分页功能的 Vue.js 和 Spring Boot 代码示例：

**Vue.js 代码：**

```vue
<template>
  <div>
    <el-table :data="tableData" border>
      <!-- 表格列定义 -->
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="pageSize"
      layout="sizes,total,prev,pager,next,jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      tableData: [], // 表格数据
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的条数
      total: 0 // 总条数
    };
  },
  mounted() {
    this.getTableData();
  },
  methods: {
    getTableData() {
      axios.get('/api/data', {
        params: {
          page: this.currentPage,
          size: this.pageSize
        }
      })
      .then(response => {
        this.tableData = response.data.list;
        this.total = response.data.total;
      })
      .catch(error => {
        console.error(error);
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getTableData();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getTableData();
    }
  }
};
</script>
```

在上述代码中，我们使用了 `el-table` 组件显示表格数据，通过 `@size-change` 和 `@current-change` 事件监听分页组件的页码和每页条数的变化。通过调用后端 API 获取分页数据，并在响应中更新表格数据、总条数等状态。

**Spring Boot 代码：**

```java
// DataController.java
@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping
    public Page<Data> getData(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return dataService.getData(page, size);
    }
}
```

在 Spring Boot 中，我们创建了一个名为 DataController 的控制器类，通过 `@GetMapping` 注解来指定 GET 请求的路径。在 `getData` 方法中，我们接收前端传递的页码和每页条数参数，并调用 DataService 获取相应的分页数据。
