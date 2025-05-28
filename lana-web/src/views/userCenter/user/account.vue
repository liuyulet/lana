<template>
<!--
	<el-alert title="异步组件动态加载使用了正处于试验阶段的<Suspense>组件, 其API和使用方式可能会改变. <Suspense> is an experimental feature and its API will likely change." type="warning" show-icon style="margin-bottom: 15px;"/>
-->

	<el-card shadow="never" header="个人信息">
		<el-form ref="form" :model="form" label-width="120px" style="margin-top:20px;">
			<el-form-item label="账号">
				<el-input v-model="form.userName" disabled></el-input>
				<div class="el-form-item-msg">账号信息用于登录，系统不允许修改</div>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input v-model="form.realName"></el-input>
			</el-form-item>
			<el-form-item label="性别">
				<el-select v-model="form.gender" placeholder="请选择">
					<el-option label="保密" value="0"></el-option>
					<el-option label="男" value="1"></el-option>
					<el-option label="女" value="2"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="手机号">
				<el-input v-model="form.mobile"></el-input>
			</el-form-item>
			<el-form-item label="邮箱">
				<el-input v-model="form.email"></el-input>
			</el-form-item>
			<el-form-item label="个性签名">
				<el-input v-model="form.signature" type="textarea"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" v-auth="'serve:user:save'" @click="save()">保存</el-button>
			</el-form-item>
		</el-form>
	</el-card>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					realName: "",
					userName: "",
					gender: "",
					signature: "",
					mobile: "",
					email: ""
				}
			}
		},
		created() {
			var userInfo = this.$TOOL.data.get("USER_INFO");
			this.form.userName = userInfo.userName;
			this.form.realName = userInfo.realName;
			this.form.gender = userInfo.gender.toString();
			this.form.signature = userInfo.signature;
			this.form.mobile = userInfo.mobile;
			this.form.email = userInfo.email;
		},
		methods: {
			async save() {
				this.isSaveing = true;
				var params = {
					username: this.form.userName,
					realName: this.form.realName,
					gender: this.form.gender,
					signature: this.form.signature,
					mobile: this.form.mobile,
					email: this.form.email
				}
				var res = await this.$API.system.user.updateMySelf.post(params);
				this.isSaveing = false;
				if(res.code == 200){
					this.$emit('success', this.form, this.mode)
					this.visible = false;
					this.$message.success("操作成功，退出后再次登陆即可生效")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			}
		}
	}
</script>

<style>
</style>
