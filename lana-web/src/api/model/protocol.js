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
		getDeviceProtocolsMode: {
			url: `${config.API_URL}/abutment/mqtt/getDeviceProtocolsMode`,
			name: "查询所属协议",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		saveDeviceProtocolsMode: {
			url: `${config.API_URL}/abutment/mqtt/saveDeviceProtocolsMode`,
			name: "设备绑定协议",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	}

}