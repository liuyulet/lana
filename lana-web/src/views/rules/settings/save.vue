<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogForm" label-width="100px" label-position="left">
			<el-form-item label="规则名称" prop="asName">
				<el-input v-model="form.asName" clearable></el-input>
			</el-form-item>
			<el-form-item label="配置方式" prop="asType">
				<el-select  v-model="form.asType" placeholder="请选择" >
					<el-option v-for="item in asTypeMeun" :key="item.value" :label="item.label" :value="item.value"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="是否启用" prop="asEnable">
				<el-select  v-model="form.asEnable" placeholder="请选择" >
					<el-option v-for="item in asEnableMeun" :key="item.value" :label="item.label" :value="item.value"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="规则说明" prop="asRemark">
				<el-input v-model="form.asRemark"
						  maxlength="255"
						  type="textarea"
						  show-word-limit
						  placeholder="请输入规则说明"
				></el-input>
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
					asName: "",
					asType: "",
					asEnable: '',
					asRemark: "",
					asContent: "",
					asPath: ""
				},
				asTypeMeun: [
		/*			{
						label: "规则脚本(弃用)",
						value: 1
					},
					{
						label: "表达式(弃用)",
						value: 2
					},*/
					{
						label: "图形化配置",
						value: 3
					}
				],
				asEnableMeun: [
					{
						label: "不启用",
						value: 0
					},
					{
						label: "启用",
						value: 1
					}
				],
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
			submit(){
				this.$refs.dialogForm.validate(async (valid) => {
					if (valid) {
						this.isSaveing = true;
						var res = {};
						if(this.mode === 'add'){
							res = await this.$API.rules.rules.save.post(this.form);
						}else {
							res = await this.$API.rules.rules.update.post(this.form);
						}
						this.isSaveing = false;
						if(res.code == 200){
							this.$emit('success', this.form, this.mode)
							this.visible = false;
							this.$message.success("操作成功")
						}else{
							this.$alert(res.msg, "提示", {type: 'error'})
						}
					}
				})
			},
			//表单注入数据
			setData(data){
				Object.assign(this.form, data)
			}
		}
	}
</script>

<style>
</style>
