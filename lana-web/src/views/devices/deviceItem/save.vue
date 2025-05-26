<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="800" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogForm" label-width="100px" label-position="left">
			<el-form-item label="头像" prop="imgs">
				<sc-upload v-model="form.imgs" title="上传设备照片"></sc-upload>
			</el-form-item>
			<el-form-item label="设备名称" prop="name">
				<el-input v-model="form.name" placeholder="请输入设备名称" clearable></el-input>
			</el-form-item>
			<el-form-item label="设备编码" prop="deviceNumber">
				<el-input v-model="form.deviceNumber" placeholder="请输入设备编码/sn码" clearable></el-input>
			</el-form-item>
			<el-form-item label="设备描述" prop="remark">
				<el-input v-model="form.remark" placeholder="请输入设备描述" clearable></el-input>
			</el-form-item>
			<el-form-item label="所属产品" prop="productTypeShow">
				<el-cascader v-model="form.productTypeShow" :options="productTypeShows" :props="productTypeShowsProps" clearable style="width: 100%;"></el-cascader>
			</el-form-item>
			<el-form-item label="是否启用" prop="status">
				<el-switch v-model="form.status" :active-value=1 :inactive-value=0></el-switch>
			</el-form-item>
			<el-form-item label="物模型" prop="productTypeMode" v-show="mode=='add'">
				<el-switch
					v-model="form.productTypeMode"
					class="ml-2"
					inline-prompt
					style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
					active-text="继承产品物模型"
					inactive-text="不继承产品物模型"
				></el-switch>
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
					add: '新增设备',
					edit: '编辑设备',
					show: '查看设备'
				},
				visible: false,
				isSaveing: false,
				//表单数据
				form: {
					id:"",
					name: "",
					deviceNumber: "",
					imgs: "",
					dept: "",
					remark: "",
					productTypeShow: "",
					productTypeId: "",
					productTypeMode: true,
					status: 1
				},
				//验证规则
				rules: {
					imgs:[
						{required: true, message: '请上传设备图片'}
					],
					name: [
						{required: true, message: '请输入设备名称'}
					],
					productTypeShow: [
						{required: true, message: '请选择所属产品'}
					],

				},

				productTypeShows: [],
				productTypeShowsProps: {
					value: "id",
					checkStrictly: true
				}
			}
		},
		mounted() {
			this.getproductTypeShow()

		},
		methods: {
			//显示
			open(mode='add'){
				this.mode = mode;
				this.visible = true;
				return this
			},

			async getproductTypeShow(){
				var res = await this.$API.device.productType.list.get();
				this.productTypeShows = res.data;
			},
			//表单提交方法
			submit(){
				this.$refs.dialogForm.validate(async (valid) => {
					if (valid) {
						this.isSaveing = true;
						var res = {};
						if(this.mode === 'add'){
							//新增用户
							res = await this.$API.device.deviceItem.save.post(this.form);
						}else {
							//修改用户
							res = await this.$API.device.deviceItem.update.post(this.form);
						}
						this.isSaveing = false;
						if(res.code == 200){
							this.$emit('success', this.form, this.mode)
							this.visible = false;
							this.$message.success("操作成功")
						}else{
							this.$alert(res.msg, "提示", {type: 'error'})
						}
					}else{
						return false;
					}
				})
			},
			//表单注入数据
			setData(data){
				this.form.id = data.id
				this.form.name = data.name
				this.form.deviceNumber = data.deviceNumber
				this.form.imgs = data.imgs
				this.form.status = data.status
				this.form.remark = data.remark
				//所属部门
				if(data.productTypeShow.length>0){
					this.form.productTypeShow = data.productTypeShow
				}
				//可以和上面一样单个注入，也可以像下面一样直接合并进去
				//Object.assign(this.form, data)
			}
		}
	}
</script>

<style>
</style>
