<template>
	<sc-dialog :title="规则编排" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-card shadow="never">
	<!--	<el-alert title="设备的功能页面" type="warning" style="margin-bottom: 15px;"></el-alert>-->
<!--			<div class="mb-2 ml-4" >
				<el-radio-group v-model="serveData.actionType">
					<el-radio :label=1>规则脚本</el-radio>
					<el-radio :label=2>表达式</el-radio>
					<el-radio :label=3>图形化配置</el-radio>
				</el-radio-group>
			</div>-->
			<el-container>
				<el-header style="display: flex; justify-content: space-between; align-items: center;">
					<span><h3>规则编排</h3></span>
					<el-button type="primary" :loading="isSaveing"    @click="saveRule">保存规则信息</el-button>
				</el-header>
				<el-main>
					<sc-workflow v-model="data.nodeConfig"></sc-workflow>
				</el-main>
			</el-container>
		</el-card>
	</sc-dialog>

</template>


<script>
import scWorkflow from '@/components/scWorkflow/index.vue'

export default {
	name: 'workflow',
	components: {
		scWorkflow
	},
	props: {
		rulesId:Number,
	},
	data() {
		return {
			visible: false,
			isSaveing: false,
			initRule:{
				"rulesId": this.rulesId,
				"name": "",
				"nodeConfig": {
					"nodeName": "开始",
					"type": 0,
					"nodeDeviceList": [],
				}
			},
			form: {
				id:"",
				asName: "",
				asType: "",
				asEnable: '',
				asRemark: "",
				asContent: "",
				asPath: ""
			},
			data: {},
			saveData: {}
		}
	},
	mounted() {
		this.getRuleDetail()
	},
	methods: {

		//加载树数据
		async getRuleDetail(){
			this.isSaveing = true;
			var res = await this.$API.rules.rules.getRulesItem.post({"rulesId":this.rulesId});
			if (res.code === 200) {
				if (res.data && res.data !== undefined){
					this.isSaveing = false;
					this.data.rulesId = res.data.rulesId;
					this.data.name = res.data.name;
					if(res.data.nodeConfig !== undefined){
						if(res.data.nodeConfig !== undefined){
							this.data.nodeConfig = JSON.parse(res.data.nodeConfig);
						}
					}else {
						this.isSaveing = false;
						this.data = this.initRule
					}
				}else {
					this.isSaveing = false;
					this.data = this.initRule
				}
			} else {
				this.isSaveing = false;
				this.data = this.initRule
			}
		},

		async saveRule() {
			// 表单提交方法
			this.isSaveing = true;
			// 检查 nodeConfig 和 nodeDeviceList 是否存在
			// 深拷贝原始数据
			if (this.saveData !== undefined) {
				this.saveData = JSON.parse(JSON.stringify(this.data));
			}
			console.log(this.data);
			if(this.saveData.nodeConfig !== undefined) {
				console.error('nodeConfig or nodeDeviceList is undefined:', this.saveData.nodeConfig);
				this.saveData.nodeConfig = JSON.stringify(this.saveData.nodeConfig);
			}else {
				this.saveData.nodeConfig = JSON.stringify([]); // 默认值
			}

			try {
				var res = await this.$API.rules.rules.rulesSeting.post(this.saveData);
				this.isSaveing = false;
				if (res.code == 200) {
					//this.visible = false;
					this.$message.success("操作成功")
				} else {
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			} catch (error) {
				console.error('请求失败:', error);
				this.isSaveing = false;
				this.$alert('请求失败，请稍后再试', "提示", {type: 'error'})
			}
		},
		open(){
			this.visible = true;
			return this
		},
		//表单注入数据
		setData(data){
			//rulesId =data.id
			Object.assign(this.form, data)
		}
	}
}
</script>

<style>

</style>
