import config from "@/config"
import http from "@/utils/request"

export default {
	/**
	 * 菜单
	 */
	menu: {
		myMenus: {
			url: `${config.API_URL}/sys/menus/nav`,
			name: "获取我的菜单",
			get: async function(){
				return await http.get(this.url);
			}
		},
		list: {
			url: `${config.API_URL}/sys/menus/list`,
			name: "菜单列表",
			get: async function(){
				return await http.get(this.url);
			}
		},
		roleList: {
			url: `${config.API_URL}/sys/menus/roleList`,
			name: "角色分配菜单列表",
			get: async function(params){
				return await http.get(this.url,params);
			}
		},
		save: {
			url: `${config.API_URL}/sys/menus/save`,
			name: "新增菜单类型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},

		update: {
			url: `${config.API_URL}/sys/menus/update`,
			name: "菜单类型修改",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/sys/menus/delete`,
			name: "删除菜单上下级",
			post: async function(data){
				return await http.post(this.url,data, {});
			}
		}
	},
	/**
	 * 字典
	 */
	dic: {
		tree: {
			url: `${config.API_URL}/sys/dict/list`,
			name: "获取字典树",
			get: async function(){
				return await http.get(this.url);
			}
		},
		list: {
			url: `${config.API_URL}/sys/dic/page`,
			name: "字典明细",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		get: {
			url: `${config.API_URL}/sys/dic/get`,
			name: "获取字典数据",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		update: {
			url: `${config.API_URL}/sys/dict/update`,
			name: "字典类型修改",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		save: {
			url: `${config.API_URL}/sys/dict/save`,
			name: "新增字典类型",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		deletDicType: {
			url: `${config.API_URL}/sys/dict/deletDicType`,
			name: "删除字典类型",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		deletDicData: {
			url: `${config.API_URL}/sys/dic/deletDicData`,
			name: "删除字典子项",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		updateDicData: {
			url: `${config.API_URL}/sys/dic/updateDicData`,
			name: "修改字典子项",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		saveDicData: {
			url: `${config.API_URL}/sys/dic/saveDicData`,
			name: "新增字典子项",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	},
	/**
	 * 角色
	 */
	role: {
		list: {
			url: `${config.API_URL}/sys/role/page`,
			name: "获取角色列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		roleLinkMenus: {
			url: `${config.API_URL}/sys/role/roleLinkMenus`,
			name: "维护角色权限",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		save: {
			url: `${config.API_URL}/sys/role/save`,
			name: "新增角色",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/sys/role/update`,
			name: "修改角色",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/sys/role/delete`,
			name: "删除角色",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		role: {
			url: `${config.API_URL}/sys/role/list`,
			name: "删除角色",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	},
	/**
	 * 部门
	 */
	dept: {
		list: {
			url: `${config.API_URL}/sys/org/list`,
			name: "获取部门列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/sys/org/save`,
			name: "新增部门",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/sys/org/update`,
			name: "修改部门",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/sys/org/delete`,
			name: "删除字典类型",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	},
	/**
	 * 用户
	 */
	user: {
		list: {
			url: `${config.API_URL}/sys/user/page`,
			name: "获取用户列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		save: {
			url: `${config.API_URL}/sys/user/save`,
			name: "保存用户",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		update: {
			url: `${config.API_URL}/sys/user/update`,
			name: "修改用户用户",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		updateMySelf: {
			url: `${config.API_URL}/sys/user/updateMySelf`,
			name: "修改个人信息数据",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		updateMySelfPassword: {
			url: `${config.API_URL}/sys/user/updateMySelfPassword`,
			name: "修改个人密码",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		delete: {
			url: `${config.API_URL}/sys/user/delete`,
			name: "修改用户用户",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		resetPassword: {
			url: `${config.API_URL}/sys/user/resetPassword`,
			name: "重置用户密码",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		}
	},
	app: {
		list: {
			url: `${config.API_URL}/system/app/list`,
			name: "应用列表",
			get: async function(){
				return await http.get(this.url);
			}
		}
	},
	log: {
		list: {
			url: `${config.API_URL}/sys/log/operate/page`,
			name: "个人日志列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		syslist: {
			url: `${config.API_URL}/sys/log/operate/sysPage`,
			name: "系统日志列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		sysRulesPage: {
			url: `${config.API_URL}/sys/log/operate/sysRulesPage`,
			name: "情景模式日志分页查询",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		sysRulesWarnings: {
			url: `${config.API_URL}/sys/log/operate/sysRulesWarnings`,
			name: "预警消息日志分页查询",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	},
	table: {
		list: {
			url: `${config.API_URL}/system/table/list`,
			name: "表格列管理列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		info: {
			url: `${config.API_URL}/system/table/info`,
			name: "表格列管理详情",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	},
	tasks: {
		list: {
			url: `${config.API_URL}/system/tasks/list`,
			name: "系统任务管理",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	}
}
