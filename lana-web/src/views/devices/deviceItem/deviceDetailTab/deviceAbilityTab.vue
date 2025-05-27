<template>
	<el-alert title="功能控制、采集功能会根据物模型的编码进行数据消息的发送、代码解析功能。请准确填写" type="warning" style="margin-bottom: 15px;"></el-alert>
		<el-table :data="controlModel.controlForm.list" stripe  v-loading="loading">
			<el-table-column label="#" type="index" width="50"></el-table-column>
			<el-table-column label="功能名称"  width="170">
				<template #default="scope">
					<el-input v-if="scope.row.isSet" v-model="scope.row.controlName" placeholder="请输入内容"></el-input>
					<span v-else>{{scope.row.controlName}}</span>
				</template>
			</el-table-column>

			<el-table-column label="功能类型"  width="150">
				<template #default="scope">
					<el-select v-if="scope.row.isSet" v-model="scope.row.controlType" placeholder="请选择" >
						<el-option v-for="item in controlModel.controlTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
					</el-select>
					<span v-else>{{ scope.row.controlTypeLabel }}</span>
				</template>
			</el-table-column>
			<el-table-column label="属性标志"  width="170">
				<template #default="scope">
					<el-input v-if="scope.row.isSet" v-model="scope.row.controlMode" @input="handleInput" placeholder="请输入内容"></el-input>
					<span v-else>{{scope.row.controlMode}}</span>
				</template>
			</el-table-column>
			<el-table-column label="属性类型"  width="150">
				<template #default="scope">
					<el-select v-if="scope.row.isSet" v-model="scope.row.controlDataType" placeholder="请选择">
						<el-option v-for="item in controlModel.dataTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
					</el-select>
					<span v-else>{{scope.row.controlDataTypeLabel}}</span>
				</template>
			</el-table-column>
			<el-table-column label="属性值"  width="280">
				<template #default="scope">

					<el-input
						v-if="scope.row.isSet"
						v-model="scope.row.controlDefultValue"
						maxlength="999"
						style="width: 270px"
						type="textarea"
						show-word-limit
						placeholder="请输入内容"
					/>
					<span v-else>{{scope.row.controlDefultValue}}</span>
				</template>
			</el-table-column>


			<el-table-column min-width="1"></el-table-column>
			<el-table-column label="操作" fixed="right" width="200">
				<template #default="scope">
					<el-button-group>
						<el-button @click="table_edit(scope.row)" text type="primary" v-auth="'devices:deviceDetail:serveAdd'" size="small">{{scope.row.isSet?'保存':"修改"}}</el-button>
						<el-button v-if="scope.row.isSet" @click=cancels() text type="primary" size="small">取消</el-button>
						<el-popconfirm v-if="!scope.row.isSet" title="确定删除吗？" @confirm="table_del(scope.row, scope.$index)">
							<template #reference>
								<el-button text type="primary" v-auth="'devices:deviceDetail:serveDel'" size="small">删除</el-button>
							</template>
						</el-popconfirm>
					</el-button-group>
				</template>
			</el-table-column>
		</el-table>
	<el-button type="primary" icon="el-icon-plus" @click="table_add" style="margin-top: 20px;"></el-button>
</template>

<script>export default {
	name: 'deviceAbilityTab',
	props: {
		deviceItemId: Number,
	},
	data() {
		return {
			loading: false,
			controlModel: {
				controlForm: {
					list: []
				},
				controlTypeDic: [
					{
						label: "控制功能",
						value: 1
					},
					{
						label: "采集功能",
						value: 2
					}
				],
				dataTypeDic: [
					{
						label: "字符串",
						value: "NCHAR(255)"
					},
					{
						label: "整数",
						value: "int"
					},
					{
						label: "单精度",
						value: "float"
					},
					{
						label: "双精度",
						value: "double"
					},
					{
						label: "JSON",
						value: "json"
					},
					{
						label: "自定义指令",
						value: "customize"
					}
				],
			}
		}
	},

	mounted() {
		this.getDeviceAbility(this.deviceItemId)
	},


	methods: {
		//获取功能列表
		async getDeviceAbility(deviceItemId){
			this.loading = true;
			var res = await this.$API.device.deviceAbility.list.get({"deviceItemId":deviceItemId});
			if (res.code === 200) {
				this.controlModel.controlForm.list = res.data;
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},
		//保存设备功能
		async saveDeviceAbility(row){
			this.loading = true;
			var res = await this.$API.device.deviceAbility.save.post(row);
			if (res.code === 200) {
				this.getDeviceAbility(this.deviceItemId)
				this.$message.success("操作成功");
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},
		//删除
		async delDeviceAbility(row){
			this.loading = true;
			var res = await this.$API.device.deviceAbility.delete.get({"id": row.id});
			if (res.code === 200) {
				this.$message.success("操作成功");
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},


		table_add(){
			var newRow = {
				deviceItemId: this.deviceItemId,
				controlName: "",
				controlType: '',
				controlTypeLabel: "",
				controlMode: "",
				controlDataType: "",
				controlDataTypeLabel: "",
				controlDefultValue: "",
				isSet: true
			}
			this.controlModel.controlForm.list.push(newRow)
		},
		table_edit(row){
			if(row.isSet){
				row.controlTypeLabel = this.controlModel.controlTypeDic.find(item => item.value === row.controlType).label
				row.controlDataTypeLabel = this.controlModel.dataTypeDic.find(item => item.value === row.controlDataType).label
				this.saveDeviceAbility(row);
			}else{
				row.isSet = true
			}
		},
		cancels(){
			this.getDeviceAbility(this.deviceItemId)
		},
		table_del(row, index){
			this.delDeviceAbility(row);
			this.controlModel.controlForm.list.splice(index, 1)
		},
	}
}
</script>

<style scoped>
.custom-margin-top {
	margin-top: 20px;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
