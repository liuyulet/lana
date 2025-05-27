<template >
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1400" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-container>
			<el-alert title="这里仅展示没有绑定所选分组的设备" type="warning" show-icon :closable="false"/>
			<el-header>
				<div class="left-panel">
				</div>
				<div class="right-panel">
					<div class="right-panel-search">
						<el-input v-model="search.name" placeholder="设备名称" clearable></el-input>
						<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
					</div>
				</div>
			</el-header>

			<el-main class="nopadding">
				<scTable ref="table" :data="apiData" @selection-change="selectionChange"  stripe height="500" paginationLayout="">
					<el-table-column type="selection" width="50"></el-table-column>
					<el-table-column label="ID" prop="id" width="100" sortable='custom'></el-table-column>
					<el-table-column label="图片" width="80" column-key="filterAvatar" :filters="[{text: '已上传', value: '1'}, {text: '未上传', value: '0'}]">
						<template #default="scope">
							<el-avatar :src="scope.row.imgs" size="small"></el-avatar>
						</template>
					</el-table-column>
					<el-table-column label="设备名称" prop="name" width="180" sortable='custom' ></el-table-column>
					<el-table-column label="设备编码" prop="deviceNumber" width="160" sortable='custom'></el-table-column>
					<el-table-column label="设备状态" prop="status" width="100" sortable='custom'>
						<template #default="scope">
							<el-tag v-if="scope.row.status==1" type="success">正常</el-tag>
							<el-tag v-if="scope.row.status==0" type="danger">停用</el-tag>
						</template>
					</el-table-column>
					<el-table-column label="设备描述" prop="remark" width="180" sortable='custom' ></el-table-column>
					<el-table-column label="创建时间" prop="createTime" width="180" sortable='custom'></el-table-column>
					<el-table-column label="创建人员" prop="creatorName" width="180" sortable='custom'></el-table-column>
				</scTable>
			</el-main>

		</el-container>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" v-auth="'devices:deviceGroup:bindSava'" @click="submit()">保 存</el-button>
		</template>
	</sc-dialog>
</template>

<script>
export default {
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			titleMap: {
				add: '新增',
				edit: '编辑绑定设备',
				show: '查看'
			},
			groupId: null,
			visible: false,
			isSaveing: false,
			//表单数据
			dialog: {
				save: false
			},
			showGrouploading: false,
			groupFilterText: '',
			group: [],
			apiData: [],
			selection: [],
			search: {
				name: null
			}

		}
	},

	methods: {
		setData(data){
			this.groupId = data.id
			this.gteDeviceItemGroupList()
		},
		//显示
		open(mode='add'){
			this.mode = mode;
			this.visible = true;
			return this
		},
		async gteDeviceItemGroupList(){
			const params = {
				groupId: this.groupId
			};
			var res = await this.$API.device.deviceGroup.groupDeviceItemList.get(params);
			if(res.code == 200){
				this.apiData = res.data
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},

		//保存数据
		async submit(){
			this.pamras = {
				groupId: this.groupId,
				deviceIds: this.selection
			}
			this.isSaveing = true;
			var res = await this.$API.device.deviceGroup.saveBindingDevice.post(this.pamras);
			this.isSaveing = false;
			if(res.code == 200){
				this.$emit('success', this.form, this.mode)
				this.visible = false;
				this.$message.success("操作成功")
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},
		//表格选择后回调事件
		selectionChange(selection){
			this.selection = selection.map(selection => selection.id);
			//this.selection = selection;
		}
		//表单注入数据

	}
}
</script>

<style>
</style>
