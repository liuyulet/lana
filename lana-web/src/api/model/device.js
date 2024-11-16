import config from "@/config"
import http from "@/utils/request"

export default {

	/**
	 * 产品类型
	 */
	productType: {
		list: {
			url: `${config.API_URL}/devices/deviceProductType/list`,
			name: "获取部门列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/devices/deviceProductType/save`,
			name: "新增部门",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/devices/deviceProductType/update`,
			name: "修改部门",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/deviceProductType/delete`,
			name: "删除部门",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		getProductMode: {
			url: `${config.API_URL}/devices/deviceProductMode/list`,
			name: "获取产品物模型",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		saveProductMode: {
			url: `${config.API_URL}/devices/deviceProductMode/save`,
			name: "保存产品物模型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	},
	/**
	 * 设备类型
	 */
	deviceItem: {
		page: {
			url: `${config.API_URL}/devices/deviceItem/page`,
			name: "获取设备列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/devices/deviceItem/save`,
			name: "新增设备",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/devices/deviceItem/update`,
			name: "修改设备",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/deviceItem/delete`,
			name: "删除设备",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		getDeviceMode: {
			url: `${config.API_URL}/devices/deviceMode/list`,
			name: "获取设备物模型",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		saveDeviceModes: {
			url: `${config.API_URL}/devices/deviceMode/save`,
			name: "保存设备物模型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	},
	/**
	 * 设备分组
	 */
	deviceGroup: {
		page: {
			url: `${config.API_URL}/devices/deviceGroup/page`,
			name: "获取分组列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		groupDeviceItemPage: {
			url: `${config.API_URL}/devices/deviceGroup/groupDeviceItemPage`,
			name: "获取已经分组的设备列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		groupDeviceItemList: {
			url: `${config.API_URL}/devices/deviceGroup/groupDeviceItemList`,
			name: "获取未分组的设备列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		saveBindingDevice: {
			url: `${config.API_URL}/devices/deviceGroup/saveBindingDevice`,
			name: "保存绑定的分组设备",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		deleteBindingDevice: {
			url: `${config.API_URL}/devices/deviceGroup/deleteBindingDevice`,
			name: "删除绑定的分组设备，解绑",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		save: {
			url: `${config.API_URL}/devices/deviceGroup/save`,
			name: "新增设备分组",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/devices/deviceGroup/update`,
			name: "修改设备分组",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/deviceGroup/delete`,
			name: "删除设备分组",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},

	},

}
