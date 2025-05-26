import API from "@/api";

//审批工作流人员/组织选择器配置
// todo 因为是基于审批结构进行修改，有很多方法名称，需要转变为设备的规则名册，但是不影响使用。后面再优化，先加上注释能明白是什么方法就行。
export default {
	//配置接口正常返回代码
	successCode: 200,
	//弃用
	group: {
		//请求接口对象
		apiObj: API.device.productType.list,
		//接受数据字段映射
		parseData: function (res) {
			return {
				rows: res.data,
				msg: res.msg,
				code: res.code
			}
		},
		//显示数据字段映射
		props: {
			key: 'id',
			label: 'label',
			children: 'children'
		}
	},
	//设备实例
	user: {
		apiObj: API.device.deviceItem.page,
		pageSize: 20,
		parseData: function (res) {
			return {
				rows: res.data.rows,
				total: res.data.total,
				msg: res.msg,
				code: res.code
			}
		},
		props: {
			key: 'id',
			label: 'name',
		},
		request: {
			page: 'page',
			pageSize: 'pageSize',
			groupId: 'groupId',
			keyword: 'keyword'
		}
	},
	//分组选择
	role: {
		//请求接口对象
		apiObj: API.device.deviceGroup.getDeviceGroupList,
		//接受数据字段映射
		parseData: function (res) {
			return {
				rows: res.data,
				msg: res.msg,
				code: res.code
			}
		},
		//显示数据字段映射
		props: {
			key: 'id',
			label: 'label',
			children: 'children'
		}
	},
	//配置产品
	protocol: {
		//请求接口对象
		apiObj: API.abutment.protocols.roleLabelList,
		//接受数据字段映射
		parseData: function (res) {
			return {
				rows: res.data,
				msg: res.msg,
				code: res.code
			}
		},
		//显示数据字段映射
		props: {
			key: 'id',
			label: 'label',
			children: 'children'
		}
	}
}
