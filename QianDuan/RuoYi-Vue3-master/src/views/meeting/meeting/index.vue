<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会议题目" prop="meetingTitle">
        <el-input
          v-model="queryParams.meetingTitle"
          placeholder="请输入会议题目"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会议地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入会议地点"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm"
          placeholder="请选择会议开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm"
          placeholder="请选择会议结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截止时间" prop="deadlineForRegistration">
        <el-date-picker clearable
          v-model="queryParams.deadlineForRegistration"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm"
          placeholder="请选择截止报名时间">
        </el-date-picker>
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
          v-hasPermi="['meeting:meeting:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['meeting:meeting:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['meeting:meeting:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['meeting:meeting:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="meetingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="会议唯一标识" align="center" prop="meetingID" />
      <el-table-column label="会议题目" align="center" prop="meetingTitle" />
      <el-table-column label="会议地点" align="center" prop="location" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会议结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止报名时间" align="center" prop="deadlineForRegistration" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会议介绍" align="center" prop="departmentRequirement" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['meeting:meeting:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['meeting:meeting:remove']">删除</el-button>
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

    <!-- 添加或修改会议管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="meetingRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会议题目" prop="meetingTitle">
          <el-input v-model="form.meetingTitle" placeholder="请输入会议题目" />
        </el-form-item>
        <el-form-item label="会议地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入会议地点" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm"
            placeholder="请选择会议开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm"
            placeholder="请选择会议结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止时间" prop="deadlineForRegistration">
          <el-date-picker clearable
            v-model="form.deadlineForRegistration"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm"
            placeholder="请选择截止报名时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="会议介绍" prop="departmentRequirement">
          <el-input v-model="form.departmentRequirement" type="textarea" placeholder="请输入内容" />
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

<script setup name="Meeting">
import { listMeeting, getMeeting, delMeeting, addMeeting, updateMeeting } from "@/api/meeting/meeting";

const { proxy } = getCurrentInstance();

const meetingList = ref([]);
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
    meetingTitle: null,
    location: null,
    startTime: null,
    endTime: null,
    deadlineForRegistration: null,
    departmentRequirement: null
  },
  rules: {
    meetingTitle: [
      { required: true, message: "会议题目不能为空", trigger: "blur" }
    ],
    startTime: [
      { required: true, message: "会议开始时间，具体到年月日几点几分不能为空", trigger: "blur" }
    ],
    deadlineForRegistration: [
      { required: true, message: "截止报名时间，具体到年月日几点几分不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询会议管理列表 */
function getList() {
  loading.value = true;
  listMeeting(queryParams.value).then(response => {
    meetingList.value = response.rows;
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
    meetingID: null,
    meetingTitle: null,
    location: null,
    startTime: null,
    endTime: null,
    deadlineForRegistration: null,
    departmentRequirement: null
  };
  proxy.resetForm("meetingRef");
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
  ids.value = selection.map(item => item.meetingID);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加会议管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _meetingID = row.meetingID || ids.value
  getMeeting(_meetingID).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改会议管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["meetingRef"].validate(valid => {
    if (valid) {
      if (form.value.meetingID != null) {
        updateMeeting(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMeeting(form.value).then(response => {
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
  const _meetingIDs = row.meetingID || ids.value;
  proxy.$modal.confirm('是否确认删除会议管理编号为"' + _meetingIDs + '"的数据项？').then(function() {
    return delMeeting(_meetingIDs);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('meeting/meeting/export', {
    ...queryParams.value
  }, `meeting_${new Date().getTime()}.xlsx`)
}

getList();
</script>
