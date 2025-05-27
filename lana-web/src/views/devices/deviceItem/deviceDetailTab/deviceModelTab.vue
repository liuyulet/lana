<template>
		<el-card shadow="never">
			<el-form ref="ruleForm" :model="form" :rules="rules" label-width="100px" v-loading="loading">
				<sc-form-table ref="table" v-model="deviceItemModel.deviceForm.list" :addTemplate="deviceItemModel.addTemplate" drag-sort placeholder="请添加属性信息">
					<el-table-column prop="type" label="属性名称" width="220" align="center">
						<template #default="scope">
							<el-input v-model="scope.row.modeName" placeholder="请输入内容"></el-input>
						</template>
					</el-table-column>
					<el-table-column prop="type" label="属性标志" width="220" align="center">
						<template #default="scope">
							<el-input v-model="scope.row.modeSigns" placeholder="请输入内容"></el-input>
						</template>
					</el-table-column>
					<el-table-column prop="type" label="数据类型" width="200" align="center">
						<template #default="scope">
							<el-select v-model="scope.row.modeDataType" placeholder="请选择">
								<el-option v-for="item in deviceItemModel.dataTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
							</el-select>
						</template>
					</el-table-column>
<!--					<el-table-column prop="type" label="属性信息" width="130" align="center">
						<template #default="scope">
							<el-select v-model="scope.row.modeControlAtt" placeholder="请选择">
								<el-option v-for="item in deviceItemModel.controlTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
							</el-select>
						</template>
					</el-table-column>
					<el-table-column prop="type" label="属性值" width="180" align="center">
						<template #default="scope">
							<el-input v-model="scope.row.defultValue" placeholder="请输入属性值"></el-input>
						</template>
					</el-table-column>-->
					<el-table-column prop="val" label="单位" min-width="180" align="center">
						<template #default="scope">
							<el-input v-model="scope.row.modeRemark" placeholder="请输入单位"></el-input>
						</template>
					</el-table-column>
					<el-table-column prop="type" label="属性来源" width="160" align="center">
						<template #default="scope">
							<el-tag v-if="scope.row.modeDataSource==1" type="success">产品</el-tag>
							<el-tag v-if="scope.row.modeDataSource==0" type="danger">设备</el-tag>
						</template>
					</el-table-column>
				</sc-form-table>
				<div class="custom-margin-top align-right">
					<el-button @click="resetForm">重置</el-button>
					<el-button type="primary" v-auth="'devices:deviceDetail:modelSave'" @click="saveDeviceMode">保存</el-button>
				</div>
			</el-form>
		</el-card>

</template>

<script>export default {
	name: 'deviceModelTab',
	props: {
		deviceItemId: Number,
	},
	data() {
		return {
			loading: false,
			//物模型
			deviceItemModel: {
				deviceForm: {
					list: []
				},

				controlTypeDic: [
					{
						label: "无属性",
						value: 0
					},
					{
						label: "控制属性",
						value: 1
					},
					{
						label: "采集属性",
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
					}
				],
				addTemplate: {
					modeName: '',
					modeSigns: '',
					modeDataType: '',
					modeDataSource: 0,
					modeControlAtt: '',
					defultValue: ''
				},
			},
		}
	},

	mounted() {
		this.getDeviceMode(this.deviceItemId);
	},

/*		watch: {
			deviceItemId(newVal) {
				console.log('deviceItemId changed to:', newVal); // 监听 deviceItemId 的变化
			}
		},*/

	methods: {
//获取物模型
		async getDeviceMode(deviceItemId){
			this.loading = true;
			//this.deviceItemId = deviceItemId
			var res = await this.$API.device.deviceItem.getDeviceMode.get({"id":deviceItemId});
			if (res.code === 200) {
				this.deviceItemModel.deviceForm.list = res.data;
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},

		//保存产品物模型
		async saveDeviceMode() {
			try {
				this.loading = true;
				const tableData = this.deviceItemModel.deviceForm.list;
				// 转换前端数据为后端所需格式
				const deviceModeSave = this.convertToBackendFormat(tableData);
				var res = {};
				if(tableData.length > 0) {
					// 发送数据到后端
					res = await this.$API.device.deviceItem.saveDeviceModes.post(deviceModeSave);
				}else {
					//操作
					this.$confirm(`物模型数据会被清空，确定保存吗？`, '提示', {
						type: 'warning'
					}).then(async () => {
						//获取选中id集合列表
						res = await this.$API.device.deviceItem.saveDeviceModes.post(deviceModeSave);
					}).catch(() => {
					})
				}
				if (res.code === 200) {
					this.$message.success("操作成功");
				}
			} catch (error) {
				console.error(error);
			} finally {
				this.loading = false;
			}
		},
		convertToBackendFormat(listData) {
			return {
				deviceModeListSave: listData.map(item => ({
					modeName: item.modeName,
					modeSigns: item.modeSigns,
					modeDataType: item.modeDataType,
					modeDataSource: item.modeDataSource,
					modeControlAtt: item.modeControlAtt,
					defultValue: item.defultValue,
					modeRemark: item.modeRemark
				})),
				deviceItemId: this.deviceItemId // 设置 deviceItemId
			};
		},
		//重置产品物模型
		resetForm(){
			this.deviceItemModel.deviceForm.list = []
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
