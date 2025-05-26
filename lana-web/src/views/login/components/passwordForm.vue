<template>
	<el-form ref="loginForm" :model="form" :rules="rules" label-width="0" size="large" @keyup.enter="login">
		<el-form-item prop="user">
			<el-input v-model="form.user" prefix-icon="el-icon-user" clearable :placeholder="$t('login.userPlaceholder')">
<!--				<template #append>
					<el-select v-model="userType" style="width: 130px;">
						<el-option :label="$t('login.admin')" value="admin"></el-option>
						<el-option :label="$t('login.user')" value="user"></el-option>
					</el-select>
				</template>-->
			</el-input>
		</el-form-item>
		<el-form-item prop="password">
			<el-input v-model="form.password" prefix-icon="el-icon-lock" clearable show-password :placeholder="$t('login.PWPlaceholder')"></el-input>
		</el-form-item>

		<el-form-item v-if="form.captchaEnabled" prop="captcha">
			<el-col :span="14">
				<el-input v-model="form.captcha" prefix-icon="el-icon-key" clearable :placeholder="$t('login.captchaPlaceholder')" ></el-input>
			</el-col>
			<el-col :span="9">
				<img :src="form.captchaData" @click="getCaptcha()"/>
			</el-col>
		</el-form-item>

		<el-form-item  style="margin-bottom: 10px;">
			<el-col :span="12">
				<el-checkbox :label="$t('login.rememberMe')" v-model="form.autologin"></el-checkbox>
			</el-col>
			<el-col :span="12" class="login-forgot">
				<router-link to="/reset_password">{{ $t('login.forgetPassword') }}？</router-link>
			</el-col>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" style="width: 100%;" :loading="islogin" round @click="login">{{ $t('login.signIn') }}</el-button>
		</el-form-item>
		<div class="login-reg">
			{{$t('login.noAccount')}} <router-link to="/user_register">{{$t('login.createAccount')}}</router-link>
		</div>
	</el-form>
</template>

<script>
	export default {
		data() {
			return {
				//: 'admin',
				form: {
					user: "",
					password: "",
					captchaEnabled: false,
					captchaData: "",
					key: "",
					captcha: "",
					autologin: true
				},
				rules: {
					user: [
						{required: true, message: this.$t('login.userError'), trigger: 'blur'}
					],
					password: [
						{required: true, message: this.$t('login.PWError'), trigger: 'blur'}
					]
				},
				islogin: false,
			}
		},
		watch:{
/*			userType(val){
				if(val == 'admin'){
					this.form.user = 'admin'
					this.form.password = 'admin'
				}else if(val == 'user'){
					this.form.user = 'user'
					this.form.password = 'user'
				}
			}*/
		},
		mounted() {
			this.getCaptcha()
		},
		methods: {
			async login(){

				var validate = await this.$refs.loginForm.validate().catch(()=>{})
				if(!validate){ return false }

				this.islogin = true
				var data = {
					username: this.form.user,
					//password: this.$TOOL.crypto.MD5(this.form.password)
					password: this.form.password,
					captcha: this.form.captcha,
					key: this.form.key
				}
				//获取token
				var user = await this.$API.auth.token.post(data)
				if(user.code == 200){
					// todo 后续做token刷新机制，不在做具体的时间限制
					this.$TOOL.cookie.set("TOKEN", user.data.token, {
						expires: this.form.autologin? 24*60*60 : 0
					})
					this.$TOOL.data.set("USER_INFO", user.data.userInfo)
				}else{
					this.islogin = false
					this.$message.warning(user.message)
					return false
				}
				//获取菜单
				var menu = null
				if(this.form.user == 'admin'){
					menu = await this.$API.system.menu.myMenus.get()
				}else{
					menu = await this.$API.system.menu.myMenus.get()
				}
				if(menu.code == 200){
					if(menu.data.menu.length==0){
						this.islogin = false
						this.$alert("当前用户无任何菜单权限，请联系系统管理员", "无权限访问", {
							type: 'error',
							center: true
						})
						return false
					}
					this.$TOOL.data.set("MENU", menu.data.menu)
					this.$TOOL.data.set("PERMISSIONS", menu.data.permissions)
					this.$TOOL.data.set("DASHBOARDGRID", menu.data.dashboardGrid)
				}else{
					this.islogin = false
					this.$message.warning(menu.message)
					return false
				}

				this.$router.replace({
					path: '/'
				})
				this.$message.success("欢迎回来")
				this.islogin = false
			},
			async getCaptcha(){
				var res = await this.$API.auth.captcha.get()
				if(res.code == 200){
						this.form.captchaEnabled = res.data.captchaEnabled
						this.form.captchaData = res.data.image
						this.form.key = res.data.key
				}else{
					this.$message.warning(res.message)
				}
			}
		}
	}
</script>

<style>
input,img {
	vertical-align:middle;
}
</style>
