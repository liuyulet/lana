<template>
	<el-card shadow="never" header="修改密码">
		<el-alert title="密码更新成功后，您将被重定向到登录页面，您可以使用新密码重新登录。" type="info" show-icon style="margin-bottom: 15px;"/>
		<el-form ref="form" :model="form" :rules="rules" label-width="120px" style="margin-top:20px;">
			<el-form-item label="当前密码" prop="password">
				<el-input v-model="form.password" type="password" show-password placeholder="请输入当前密码"></el-input>
<!--				<div class="el-form-item-msg">必须提供当前登录用户密码才能进行更改</div>-->
			</el-form-item>
			<el-form-item label="新密码" prop="newPassword">
				<el-input v-model="form.newPassword" type="password" show-password placeholder="请输入新密码"></el-input>
				<sc-password-strength v-model="form.newPassword"></sc-password-strength>
				<div class="el-form-item-msg">请输入包含英文、数字的8位以上密码</div>
			</el-form-item>
			<el-form-item label="确认新密码" prop="confirmNewPassword">
				<el-input v-model="form.confirmNewPassword" type="password" show-password placeholder="请再次输入新密码"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" v-auth="'serve:password:save'" @click="save">保存密码</el-button>
			</el-form-item>
		</el-form>
	</el-card>
</template>

<script>
	import scPasswordStrength from '@/components/scPasswordStrength'

	export default {
		components: {
			scPasswordStrength
		},
		data() {
			return {
				form: {
					password: "",
					newPassword: "",
					confirmNewPassword: ""
				},
				rules: {
					password: [
						{ required: true, message: '请输入当前密码'}
					],
					newPassword: [
						{ required: true, message: '请输入新密码'}
					],
					confirmNewPassword: [
						{ required: true, message: '请再次输入新密码'},
						{validator: (rule, value, callback) => {
							if (value !== this.form.newPassword) {
								callback(new Error('两次输入密码不一致'));
							}else{
								callback();
							}
						}}
					]
				}
			}
		},
		methods: {
			async save() {
				var userInfo = this.$TOOL.data.get("USER_INFO");
				var params = {
					username: userInfo.userName,
					password: this.form.password,
					newPassword: this.form.newPassword,
				}
				var res = await this.$API.system.user.updateMySelfPassword.post(params);
				this.isSaveing = false;
				if (res.code == 200) {
					this.$emit('success', this.form, this.mode)
					this.visible = false;
					this.$alert("密码修改成功，是否跳转至登录页使用新密码登录", "修改成功", {
						type: 'success',
						center: true
					}).then(() => {
						this.$router.replace({
							path: '/login'
						})
					}).catch(() => {
					})
				} else {
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			}
		}
	}
</script>

<style>
</style>
