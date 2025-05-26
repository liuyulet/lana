<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogForm" label-width="100px" label-position="left">
			<el-form-item label="产品名称(不允许操作)" prop="name" >
				<el-input v-model="form.edgeProductTypeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="边缘实例名称" prop="edgeName">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="边缘编码" prop="edgeSn">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="秘钥Key" prop="edgeSecretKey">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="秘钥Id" prop="edgeSecretId">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="经度" prop="edgeLon">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="维度" prop="edgeLat">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
			<el-form-item label="固件版本" prop="edgeFirmwareVersion">
				<el-input v-model="form.edgeName" clearable></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script>
	export default {
		emits: ['success', 'closed'],
		data() {
			return {
				mode: "add",
				titleMap: {
					add: '新增',
					edit: '编辑',
					show: '查看'
				},
				visible: false,
				isSaveing: false,
				//表单数据
				form: {
					id:"",
					edgeSn: "",
					edgeName: "",
					edgeFirmwareVersion: "",
					edgeSecretKey: "",
					edgeSecretId: "",
					edgeLon: "",
					edgeLat: "",
					edgeProductTypeId: "",
					edgeProductTypeName: ""
				},
				//验证规则
				rules: {
					sort: [
						{required: true, message: '请输入排序', trigger: 'change'}
					],
					name: [
						{required: true, message: '请输入角色名称'}
					],
					roleCode: [
						{required: true, message: '请输入角色别名'}
					]
				}
			}
		},
		mounted() {

		},
		methods: {
			//显示
			open(mode='add'){
				this.mode = mode;
				this.visible = true;
				return this
			},
			//表单提交方法

			//表单注入数据
			setData(data){
				//可以和上面一样单个注入，也可以像下面一样直接合并进去
				Object.assign(this.form, data)
			}
		}
	}
</script>

<style>
</style>
