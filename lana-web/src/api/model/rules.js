import config from "@/config"
import http from "@/utils/request"

export default {

	/**
	 * 规则管理
	 */
	rules: {
		updateAndSave: {
			url: `${config.API_URL}/rules/rulesItem/updateAndSave`,
			name: "新增规则",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		getRules: {
			url: `${config.API_URL}/rules/rulesItem/getRules`,
			name: "查询规则",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	}

}
