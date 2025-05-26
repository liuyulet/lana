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
/*		getRules: {
			url: `${config.API_URL}/rules/rulesItem/getRules`,
			name: "查询规则",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},*/
		getPage: {
			url: `${config.API_URL}/rules/rulesItem/page`,
			name: "分页查询规则",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/rules/rulesItem/save`,
			name: "新增规则实例",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/rules/rulesItem/update`,
			name: "修改规则实例",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/rules/rulesItem/delete`,
			name: "删除规则实例",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		rulesSeting: {
			url: `${config.API_URL}/rules/rulesItemNode/saveAndUpdate`,
			name: "规则信息设置",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		getRulesItem: {
			url: `${config.API_URL}/rules/rulesItemNode/getRulesItem`,
			name: "获取规则详情",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	}

}
