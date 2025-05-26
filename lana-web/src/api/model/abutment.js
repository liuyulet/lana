import config from "@/config"
import http from "@/utils/request"

export default {

	/**
	 * 接入协议
	 */
	protocols: {
		list: {
			url: `${config.API_URL}/abutment/mqtt/page`,
			name: "协议分页查询",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/abutment/mqtt/save`,
			name: "新增协议",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/abutment/mqtt/update`,
			name: "修改协议",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/abutment/mqtt/delete`,
			name: "删除协议",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		roleLabelList: {
			url: `${config.API_URL}/abutment/mqtt/list`,
			name: "协议分页查询",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},

	},
	edges:{
		list: {
			url: `${config.API_URL}/devices/edgesItem/page`,
			name: "获取边缘实例列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/devices/edgesItem/save`,
			name: "新增边缘实例",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/devices/edgesItem/update`,
			name: "修改边缘实例",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/edgesItem/delete`,
			name: "删除边缘实例",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	},
	directives:{
		pushData: {
			url: `${config.API_URL}/abutment/directives/pushData`,
			name: "指令发送",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	}

}
