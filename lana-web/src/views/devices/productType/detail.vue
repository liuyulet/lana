<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-alert title="如果要关闭该页面，请点击右上角的 X ，该页面的保存操作不会关闭页面" type="warning" style="margin-bottom: 15px;"></el-alert>
		<el-tabs tab-position="top">
			<el-tab-pane label="产品物模型">
				<el-card shadow="never">
					<el-form ref="ruleForm" :model="form" :rules="rules" label-width="100px">
<!--					<el-form-item label="表格" prop="list">-->
						<sc-form-table ref="table" v-model="productTypeModel.ruleFormForm.list" :addTemplate="productTypeModel.addTemplate" drag-sort placeholder="请添加属性信息">
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
										<el-option v-for="item in productTypeModel.dataTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
									</el-select>
								</template>
							</el-table-column>
							<el-table-column prop="val" label="单位" min-width="180" align="center">
								<template #default="scope">
									<el-input v-model="scope.row.modeRemark" placeholder="请输入内容"></el-input>
								</template>
							</el-table-column>
						</sc-form-table>
						<dev  class="custom-margin-top align-right">
							<el-button @click="resetForm">重置</el-button>
							<el-button type="primary" v-auth="'devices:deviceProductType:model'" @click="saveProductTypeMode">保存</el-button>
						</dev>
					</el-form>
				</el-card>
			</el-tab-pane>


			<el-tab-pane label="接入管理" class="custom-padding-left">
				<el-alert title="因为边缘计算参数较多，接入管理页面的配置参数将会保留至下次重新打开边界页面之前" type="warning" style="margin-bottom: 15px;"></el-alert>
				<el-form :inline="true">
					<el-form-item label="产品连接方式" >
						<div class="mb-2 ml-4" >
							<el-radio-group v-model="abutment.link.deviceType">
								<el-radio :label="1">直连设备</el-radio>
								<el-radio :label="2">边缘计算设备</el-radio>
								<el-radio :label="3">边缘计算子设备</el-radio>
							</el-radio-group>
						</div>
					</el-form-item>
				</el-form>
				<el-form :inline="true" v-show="abutment.link.deviceType == 3">
					<el-form-item label="所属边缘计算设备" >
						<el-select  v-model="abutment.protocolsMqtt.edgeIdOpt" placeholder="请选择所属的边缘计算实例信息"   class="custom-width">
							<el-option
								v-for="item in abutment.protocolsMqtt.edgeIdOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value"
							/>
						</el-select >
					</el-form-item>
				</el-form>

				<el-form :inline="true" v-show="abutment.link.deviceType != 3" >
					<el-form-item label="连接协议" >
						<el-select  v-model="abutment.protocolsMqtt.agreementId" placeholder="请选择要使用的协议信息"  style="width: 100%;">
							<el-option
								v-for="item in abutment.protocolsMqtt.options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select >
					</el-form-item>
				</el-form>
				<el-form :inline="true" v-show="abutment.link.deviceType == 2" >
					<el-form-item label="边缘计算信息：" >
					</el-form-item>
				</el-form>
				<el-form :model="form" :rules="rules"  v-show="abutment.link.deviceType == 2"  label-width="150px" class="custom-form">
					<el-row :gutter="24"> <!-- 使用 el-row 设置列间距 -->
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->
							<el-form-item label="边缘实例名称" prop="edgeName">
								<el-input v-model="form.edgeName" placeholder="请输入边缘实例名称" clearable></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->
							<el-form-item label="边缘编码" prop="edgeSn">
								<el-input v-model="form.edgeSn" :options="groups" placeholder="请输入边缘实例名称" :props="groupsProps" :show-all-levels="false" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="20"> <!-- 使用 el-row 设置列间距 -->
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->
							<el-form-item label="秘钥key" prop="edgeSecretKey">
								<el-input v-model="form.edgeSecretKey" :options="groups"  placeholder="请输入秘钥key" :props="groupsProps" :show-all-levels="false" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->
							<el-form-item label="秘钥id" prop="edgeSecretId">
								<el-input v-model="form.edgeSecretId" placeholder="请输入秘钥id" clearable></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="20"> <!-- 使用 el-row 设置列间距 -->
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->

							<el-form-item label="经度" prop="edgeLon">
								<el-input v-model="form.edgeLon" :options="groups" placeholder="请输入经度" :props="groupsProps" :show-all-levels="false" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12"> <!-- 每个 el-col 占据 12 列，共 24 列 -->
							<el-form-item label="维度" prop="edgeLat">
								<el-input v-model="form.edgeLat" placeholder="请输入维度" clearable></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
				<!--//1:直连设备,2:边缘计算设备,3:边缘计算子设备-->
				<dev  class="custom-margin-top align-right" v-show="abutment.link.deviceType == 1">
					<el-button type="primary" v-auth="'devices:deviceProductType:abutment'" @click="saveDevice" >保存</el-button>
				</dev>
				<dev  class="custom-margin-top align-right" v-show="abutment.link.deviceType == 2">
					<el-button type="primary" v-auth="'devices:deviceProductType:abutment'" @click="saveEdgeDevice" >保存</el-button>
				</dev>
				<dev  class="custom-margin-top align-right" v-show="abutment.link.deviceType == 3">
					<el-button type="primary" v-auth="'devices:deviceProductType:abutment'" @click="saveEdgeSubDevice" >保存</el-button>
				</dev>

			</el-tab-pane>
		</el-tabs>
	</sc-dialog>
</template>

<script>
export default {
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			titleMap: {
				add: '新增产品详情',
				edit: '产品详情',
				show: '查看产品详情'
			},
			productTypeId: '',
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
			//边缘信息表格信息
			form: {
				id:"",
				edgeSn: "",
				edgeName: "",
				edgeSecretKey: "",
				edgeSecretId: "",
				edgeLon: "",
				edgeLat: "",
				edgeProductTypeId: ""
			},
			//边缘信息验证规则
			rules: {
				edgeName: [
					{required: true, message: '请输入边缘计算的名称'}
				],
				edgeSecretKey: [
					{required: true, message: '请输入边缘计算的秘钥key'}
				],
				edgeSecretId: [
					{required: true, message: '请输入边缘计算的秘钥id'}
				]
			},
			//物模型消息提醒
			msg: {
				open: true,
				appKey: "",
				secretKey: ""
			},
			//产品类型 信息
			productTypeModel: {
				ruleFormForm: {
					list: []
				},
				attributeTypeDic: [
					{
						label: "否",
						value: 0
					},
					{
						label: "是",
						value: 1
					}
				],
				controlTypeDic: [
					{
						label: "否",
						value: 0
					},
					{
						label: "是",
						value: 1
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
					modeDataSource: '',
					modeControlAtt: ''
				},
			},
			//接入信息
			abutment:{
				protocolsMqtt:{
					//关联协议
					options: [],
					//关联边缘计算实例
					edgeIdOptions: [],
					//边缘子设备
					edgeIdOpt:'',
					edgeId:'',
					agreementId:''

				},
				link: {
					deviceType: 1
				},
			},

		}
	},

	mounted() {

	},
	methods: {

		//获取协议
		async getProtocolsMqtt(deviceItemId){
			this.isSaveing = true;
			var res = await this.$API.device.productType.getDeviceProtocolsMode.get({"id":deviceItemId});
			if (res.code == 200) {
				this.abutment.protocolsMqtt.options = res.data.rotocolResult;
				this.abutment.protocolsMqtt.edgeIdOptions = res.data.edgesItemData;
				this.abutment.protocolsMqtt.agreementId = res.data.id
				this.abutment.link.deviceType = res.data.deviceType
				if(res.data.deviceType == 3){
					this.abutment.protocolsMqtt.edgeIdOpt = res.data.edgeId
				}else {
					this.abutment.protocolsMqtt.edgeId = res.data.edgeId
				}

				Object.assign(this.form, res.data.deviceEdgesItemResult)
			}
			this.isSaveing = false;
		},

		//保存边缘计算
		async saveEdgeDevice(){
			this.isSaveing = true;
			//保存边缘实例
			this.form.edgeProductTypeId = this.productTypeId;
			var saveEdgesItemRes = {};
			if(this.form.id==''){
				saveEdgesItemRes = await this.$API.device.edgesItem.saveEdgesItem.post(this.form);
			}else {
				saveEdgesItemRes = await this.$API.device.edgesItem.updateEdgesItem.post(this.form);
			}
			if (saveEdgesItemRes.code == 200) {
				this.abutment.protocolsMqtt.edgeId = saveEdgesItemRes.data.id;
				let SaveDeviceAbutmentSave = {
					'productTypeId': this.productTypeId,
					'agreementId': this.abutment.protocolsMqtt.agreementId,
					'deviceType': this.abutment.link.deviceType,
					'edgeId':  this.abutment.protocolsMqtt.edgeId
				}
				var res = await this.$API.device.productType.saveDeviceProtocolsMode.post(SaveDeviceAbutmentSave);
				this.isSaveing = false;
				if (res.code == 200) {
					this.$message.success("操作成功");
				}
			}
		},
		//保存设备
		async saveDevice() {
			this.isSaveing = true;
			//保存边缘实例
			this.form.edgeProductTypeId = this.productTypeId;
			let SaveDeviceAbutmentSave = {
				'productTypeId': this.productTypeId,
				'agreementId': this.abutment.protocolsMqtt.agreementId,
				'deviceType': this.abutment.link.deviceType
			}
			var res = await this.$API.device.productType.saveDeviceProtocolsMode.post(SaveDeviceAbutmentSave);
			console.log("结果：" + res.code)
			this.isSaveing = false;
			if (res.code == 200) {
				this.$message.success("操作成功");
			}

		},
		//保存边缘子设备
		async saveEdgeSubDevice(){
			this.isSaveing = true;
			//保存边缘实例
			this.form.edgeProductTypeId = this.productTypeId;
			let SaveDeviceAbutmentSave = {
				'productTypeId': this.productTypeId,
				'agreementId': this.abutment.protocolsMqtt.agreementId,
				'deviceType': this.abutment.link.deviceType,
				'edgeId': this.abutment.protocolsMqtt.edgeIdOpt
			}
			var res = await this.$API.device.productType.saveDeviceProtocolsMode.post(SaveDeviceAbutmentSave);
			this.isSaveing = false;
			if (res.code == 200) {
				this.$message.success("操作成功");
			} else {
				this.$message.error("操作失败");
			}
		},

		//保存产品物模型
		async saveProductTypeMode() {
			try {
				this.isSaveing = true;
				const tableData = this.productTypeModel.ruleFormForm.list;

				// 转换前端数据为后端所需格式
				const deviceProductModeSave = this.convertToBackendFormat(tableData);
				var res = {};
				if(tableData.length > 0) {
					// 发送数据到后端
					res = await this.$API.device.productType.saveProductMode.post(deviceProductModeSave);
				}else {
					//操作
					this.$confirm(`物模型数据会被清空，确定保存吗？`, '提示', {
						type: 'warning'
					}).then(async () => {
						//获取选中id集合列表
						res = await this.$API.device.productType.saveProductMode.post(deviceProductModeSave);
					}).catch(() => {
					})
				}
				if (res.code == 200) {
					this.$message.success("操作成功");
				}


			} catch (error) {
				console.error(error);
			} finally {
				this.isSaveing = false;
			}
		},
		//获取产品物模型数据
		async getProductMode(productTypeId){
			this.isSaveing = true;
			var res = await this.$API.device.productType.getProductMode.get({"id":productTypeId});
			if (res.code == 200) {
				this.productTypeModel.ruleFormForm.list = res.data;
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.isSaveing = false;
		},

		convertToBackendFormat(listData) {
			return {
				deviceProductModeListSave: listData.map(item => ({
					modeName: item.modeName,
					modeSigns: item.modeSigns,
					modeDataType: item.modeDataType,
					modeDataSource: item.modeDataSource,
					modeControlAtt: item.modeControlAtt,
					modeRemark: item.modeRemark
				})),
				productTypeId: this.productTypeId // 设置 productTypeId
			};
		},
		//重置产品物模型
		resetForm(){
			this.productTypeModel.ruleFormForm.list = []
		},

		table_add(){
			var newRow = {
				key: "",
				value: "",
				title: "",
				isSet: true
			}
			this.setting.push(newRow)
		},
		table_edit(row){
			if(row.isSet){
				row.isSet = false
			}else{
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
			this.productTypeId = data.id
			//获取物模型
			this.getProductMode(data.id)
			//获取协议
			this.getProtocolsMqtt(data.id)
		}
	}
}
</script>

<style>
.custom-form {
	border: 1px solid #000; /* 黑色边框 */
	padding: 20px; /* 内边距，可以根据需要调整 */
	border-radius: 8px; /* 圆角，可以根据需要调整 */
}
.custom-margin-top{
	margin-top: 20px;
	justify-content: flex-end;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
.custom-padding-left {
	padding-left: 20px;
	padding-right: 20px;
}
.custom-width {
	width: 400px;
}
</style>
