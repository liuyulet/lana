<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1500" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-alert title="如果要关闭该页面，请点击右上角的 X ，该页面的保存操作不会关闭页面" type="warning" style="margin-bottom: 15px;"></el-alert>
		<el-tabs tab-position="top">
			<el-tab-pane label="设备物模型">
				<el-card shadow="never">
					<el-alert title="‘属性值’由‘属性信息’决定，如果是控制属性，则属性值为控制设备所需要发送的值，如果是事件属性，则属性值为采集字段对应的数据解析信息" type="warning" style="margin-bottom: 15px;"></el-alert>
					<el-form ref="ruleForm" :model="form" :rules="rules" label-width="100px">
						<!--					<el-form-item label="表格" prop="list">-->
						<sc-form-table ref="table" v-model="deviceItemModel.deviceForm.list" :addTemplate="deviceItemModel.addTemplate" drag-sort placeholder="请添加属性信息">
							<el-table-column prop="type" label="属性名称" width="200" align="center">
								<template #default="scope">
									<el-input v-model="scope.row.modeName" placeholder="请输入内容"></el-input>
								</template>
							</el-table-column>
							<el-table-column prop="type" label="属性标志" width="200" align="center">
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
							<el-table-column prop="type" label="属性信息" width="200" align="center">
								<template #default="scope">
									<el-select v-model="scope.row.modeControlAtt" placeholder="请选择">
										<el-option v-for="item in deviceItemModel.controlTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
									</el-select>
								</template>
							</el-table-column>

							<el-table-column prop="type" label="属性值" width="200" align="center">
								<template #default="scope">
									<el-input v-model="scope.row.defultValue" placeholder="请输入属性值"></el-input>
								</template>
							</el-table-column>
							<el-table-column prop="val" label="补充说明" min-width="180" align="center">
								<template #default="scope">
									<el-input v-model="scope.row.modeRemark" placeholder="请输入内容"></el-input>
								</template>
							</el-table-column>

							<el-table-column prop="type" label="属性来源" width="100" align="center">
								<template #default="scope">
									<el-tag v-if="scope.row.modeDataSource==1" type="success">产品</el-tag>
									<el-tag v-if="scope.row.modeDataSource==0" type="danger">设备</el-tag>
								</template>
							</el-table-column>
						</sc-form-table>
						<dev  class="custom-margin-top align-right">
							<el-button @click="resetForm">重置</el-button>
							<el-button type="primary" @click="saveDeviceMode">保存</el-button>
						</dev>
					</el-form>
				</el-card>
			</el-tab-pane>

			<el-tab-pane label="规则事件" >
				<div class="mb-2 ml-4">
					<el-radio-group v-model="rules.ruletype">
						<el-radio :label="1">规则脚本</el-radio>
						<el-radio :label="2">规则表达式</el-radio>
						<el-radio :label="3">规则配置</el-radio>
					</el-radio-group>
				</div>

				<el-card shadow="never" v-show="rules.ruletype==1" header="开发中....">

					<sc-code-editor v-model="rules.aviatorjs" mode="javascript" theme="darcula">
					</sc-code-editor>
					<dev  class="custom-margin-top align-right">
						<el-button @click="rulesAsScriptReset">重置</el-button>
						<el-button type="primary" @click="rulesAsScriptSave">保存</el-button>
					</dev>
				</el-card>

				<el-card shadow="never" v-show="rules.ruletype==2" header="开发中....">

					<dev  class="custom-margin-top align-right">
						<el-button @click="rulesAsExpressionReset">重置</el-button>
						<el-button type="primary" @click="rulesAsExpressionSave">保存</el-button>
					</dev>
				</el-card>

				<el-card v-show="rules.ruletype==3" shadow="never" header="开发中....">

					<dev  class="custom-margin-top align-right">
						<el-button @click="rulesAllocateReset">重置</el-button>
						<el-button type="primary" @click="rulesAllocate">保存</el-button>
					</dev>
				</el-card>
			</el-tab-pane>

		</el-tabs>
<!--		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>-->
	</sc-dialog>
</template>

<script>

import { defineAsyncComponent } from 'vue';
const scCodeEditor = defineAsyncComponent(() => import('@/components/scCodeEditor'));

export default {
	components: {
		scCodeEditor
	},
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			titleMap: {
				add: '新增设备详情',
				edit: '设备详情',
				show: '查看设备详情'
			},

			deviceItemId: '',
			visible: false,
			isSaveing: false,
			//表单数据
			sys: {
				name: "SCUI",
				logoUrl: "",
				login: true,
				passwordRules: "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$",
				copyright: "@SCUI"
			},
			msg: {
				open: true,
				appKey: "",
				secretKey: ""
			},

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
						label: "事件属性",
						value: 2
					}
				],
				dataTypeDic: [
					{
						label: "字符串",
						value: "string"
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
			//规则事件
			rules: {
				ruletype: 1,
				aviatorjs:'// 统一入参默认为：dataList',
				aviatorjsName:'',
				aviatorjsId:'',
				aviatorjsPath:'',
			},

			controlModel: {
				controlForm: {
					list: []
				},
			}
		}
	},

	methods: {


		//获取物模型
		async getDeviceMode(deviceItemId){
			this.isSaveing = true;
			var res = await this.$API.device.deviceItem.getDeviceMode.get({"id":deviceItemId});

			if (res.code === 200) {
				this.deviceItemModel.deviceForm.list = res.data;

			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.isSaveing = false;
		},

		//保存产品物模型
		async saveDeviceMode() {
			try {
				this.isSaveing = true;
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
				this.isSaveing = false;
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


		//获取脚本
		async getRules(id){
			this.isSaveing = true;
			let RulesItemQuery = {
				'deviceId': id,
			}
			var res = await this.$API.rules.rules.getRules.post(RulesItemQuery);
			if (res.code === 200) {
				if(res.data!=null){
					this.rules.aviatorjs = res.data.asContent;
					this.rules.aviatorjsId = res.data.id;
					this.rules.aviatorjsPath = res.data.asPath;
					this.rules.aviatorjsName = res.data.asName;
				}

			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.isSaveing = false;
		},
		//重置产品物模型
		rulesAsScriptReset(){
			this.rules.aviatorjs = "// 统一入参默认为：dataList"
		},
		//保存脚本
		async rulesAsScriptSave(){
			if(this.rules.aviatorjsName!=null && this.rules.aviatorjsName !== ''){
				let value = '';
				this.$prompt('请输入规则名称,不输入则不做修改', '规则名称', {
				}).then(async ({ value: inputValue }) => {
					value = inputValue || this.rules.aviatorjsName;
					this.isSaveing = true;
					let RulesItemSave = {
						'id': this.rules.aviatorjsId,
						'asName': value,
						'asType': 1,
						'asContent': this.rules.aviatorjs,
						'asPath': this.rules.aviatorjsPath,
						'deviceItemId': this.deviceItemId
					}
					var res = await this.$API.rules.rules.updateAndSave.post(RulesItemSave);
					if (res.code === 200) {
						this.$message.success("操作成功");
					} else {
						this.$alert(res.msg, "提示", { type: 'error' });
					}
				});
			}else {
				this.$prompt('请输入规则名称', '规则名称', {
					inputPlaceholder: '请输入识别度较高的常用过滤名称',
					inputPattern: /\S/,
					inputErrorMessage: '规则名称不能为空'
				}).then(async ({ value }) =>{
					this.isSaveing = true;
					let RulesItemSave = {
						'asName': value,
						'asType': 1,
						'asContent': this.rules.aviatorjs,
						'asPath': this.rules.aviatorjsPath,
						'deviceItemId': this.deviceItemId
					}
					var res = await this.$API.rules.rules.updateAndSave.post(RulesItemSave);
					if (res.code === 200) {
						this.$message.success("操作成功");
					} else {
						this.$alert(res.msg, "提示", { type: 'error' });
					}
				})
			}
		},



		async table_edit(row) {
			if (row.isSet) {
				row.isSet = false
				if (row.defultValue !== '') {
					this.isSaveing = true;
					var res = await this.$API.device.deviceItem.saveContoleModel.post(row);
					this.isSaveing = false;
					if (res.code === 200) {
						this.$message.success("操作成功")
					}
				}
			} else {
				row.isSet = true
			}
		},
		table_del(row, index){
			this.setting.splice(index, 1)
		},
		open(mode='add'){
			this.mode = mode;
			this.visible = true;
			return this
		},
		setData(data){
			this.deviceItemId = data.id
			//获取物模型
			this.getDeviceMode(data.id)
			//this.getDeviceControlMode(data.id)
			//获取规则脚本
			this.getRules(data.id)
		}
	}
}
</script>

<style>
.custom-margin-top{
	margin-top: 20px;
	justify-content: flex-end;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
