import config from "@/config"
import http from "@/utils/request"

export default {
	token: {
		url: `${config.API_URL}/sys/auth/login`,
		name: "登录获取TOKEN",
		post: async function(data={}){
			return await http.post(this.url, data);
		}
	},

	logout: {
		url: `${config.API_URL}/sys/auth/logout`,
		post: async function(){
			return await http.post(this.url);
		}
	},

	captcha: {
		url: `${config.API_URL}/sys/captcha/getCaptcha`,
		get: async function(){
			return await http.get(this.url);
		}
	},

	captchaEnabled: {
		url: `${config.API_URL}/sys/captcha/enabled`,
		get: async function(){
			return await http.get(this.url);
		}
	}


}
