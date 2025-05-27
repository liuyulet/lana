import config from "@/config"
import http from "@/utils/request"

export default {

	/**
	 * 产品类型
	 */
	productType: {
		list: {
			url: `${config.API_URL}/devices/deviceProductType/list`,
			name: "获取产品类型列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/devices/deviceProductType/save`,
			name: "新增产品类型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/devices/deviceProductType/update`,
			name: "修改产品类型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/deviceProductType/delete`,
			name: "删除产品类型",
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
		},
		getDeviceProtocolsMode: {
			url: `${config.API_URL}/devices/deviceProductType/getDeviceProtocolsMode`,
			name: "查询所属协议",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		saveDeviceProtocolsMode: {
			url: `${config.API_URL}/devices/deviceProductType/saveDeviceProtocolsMode`,
			name: "设备绑定协议",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	},
	edgesItem: {
		saveEdgesItem: {
			url: `${config.API_URL}/devices/edgesItem/save`,
			name: "新增边缘实例",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		updateEdgesItem: {
			url: `${config.API_URL}/devices/edgesItem/update`,
			name: "修改边缘实例",
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

		getContoleList: {
			url: `${config.API_URL}/devices/deviceMode/contoleList`,
			name: "获取设备物模型控制属性列表",
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
		},
		saveContoleModel: {
			url: `${config.API_URL}/devices/deviceMode/saveContoleModel`,
			name: "保存设备物模型控制属性默认值",
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
		getDeviceGroupList: {
			url: `${config.API_URL}/devices/deviceGroup/getDeviceGroupList`,
			name: "分组列表查询，规则编排使用",
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
	/**
	 * 设备功能
	 */
	deviceAbility: {
		list: {
			url: `${config.API_URL}/devices/deviceControlData/list`,
			name: "获取设备功能列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/devices/deviceControlData/saveOrUpdate`,
			name: "保存设备功能数据",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/devices/deviceControlData/delete`,
			name: "删除设备分组",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},

		listReshow: {
			url: `${config.API_URL}/devices/deviceControlData/listReshow`,
			name: "规则引起设备控制功能--回显使用",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},

	}

}
