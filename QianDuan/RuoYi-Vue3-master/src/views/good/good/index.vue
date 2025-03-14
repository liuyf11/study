<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodName">
        <el-input
          v-model="queryParams.goodName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入价格"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      
      <!-- <el-form-item label="类型ID" prop="typeId">
        <el-input
          v-model="queryParams.typeId"
          placeholder="请输入类型ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->

      <!-- 改为下拉选项框，选择分类名称 -->
      <el-form-item label="类型名称" prop="typeId" >
        <el-select v-model="queryParams.typeId" placeholder="请选择类型" 
        clearable @keyup.enter="handleQuery" style="width: 150px;">
          <el-option v-for="item in typeList" :key="item.typeId" 
          :label="item.typeName" :value="item.typeId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['good:good:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['good:good:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['good:good:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['good:good:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增ID" align="center" prop="goodId" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="图片链接" align="center" prop="imageUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.imageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="类型ID" align="center" prop="typeId" /> -->
      <el-table-column label="类型" align="center" prop="typeName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['good:good:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['good:good:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="goodRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="goodName">
          <el-input v-model="form.goodName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="图片链接" prop="imageUrl">
          <image-upload v-model="form.imageUrl"/>
        </el-form-item>
        <!-- <el-form-item label="类型ID" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入类型ID" />
        </el-form-item> -->
        <el-form-item label="类型" prop="typeId">
          <el-select v-model="form.typeId" placeholder="请选择类型">
            <el-option v-for="item in typeList" :key="item.typeId" 
                      :label="item.typeName" :value="item.typeId" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Good">
import { listGood, getGood, delGood, addGood, updateGood } from "@/api/good/good";
import { listType } from "@/api/good/type";
const { proxy } = getCurrentInstance();

const goodList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    goodName: null,
    price: null,
    imageUrl: null,
    typeId: null
  },
  rules: {
    goodName: [
      { required: true, message: "商品名称不能为空", trigger: "blur" }
    ],
    price: [
      { required: true, message: "价格不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商品信息列表 */
function getList() {
  loading.value = true;
  listGood(queryParams.value).then(response => {
    goodList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    goodId: null,
    goodName: null,
    price: null,
    imageUrl: null,
    typeId: null
  };
  proxy.resetForm("goodRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.goodId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商品信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _goodId = row.goodId || ids.value
  getGood(_goodId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商品信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["goodRef"].validate(valid => {
    if (valid) {
      if (form.value.goodId != null) {
        updateGood(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addGood(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _goodIds = row.goodId || ids.value;
  proxy.$modal.confirm('是否确认删除商品信息编号为"' + _goodIds + '"的数据项？').then(function() {
    return delGood(_goodIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('good/good/export', {
    ...queryParams.value
  }, `good_${new Date().getTime()}.xlsx`)
}


//查询所有分类数据的请求对象，一般分类不会超过1000条
const typeQueryParams = reactive({
  pageNum: 1,
  pageSize: 1000,
});
//定义分类列表的引用
const typeList = ref([]);
function getTypeList() {
  listType(typeQueryParams).then(response => {
    typeList.value = response.rows;
    console.log("************");
    console.log(typeList); // 在这里输出typeList的值
    console.log("************");
  });
}

//调用方法
getTypeList();

getList();
</script>
