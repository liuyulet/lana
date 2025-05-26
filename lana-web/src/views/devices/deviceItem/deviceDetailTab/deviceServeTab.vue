<template>
	<el-card shadow="never">
<!--	<el-alert title="设备的功能页面" type="warning" style="margin-bottom: 15px;"></el-alert>-->
		<div class="mb-2 ml-4" >
			<el-radio-group v-model="serveData.actionType">
				<el-radio :label="1">控制功能</el-radio>
				<el-radio :label="2">采集功能</el-radio>
			</el-radio-group>
		</div>
		<el-card shadow="never" class="custom-form"  v-loading="buttonLoading">
			<!-- 展示 controlType === 1 的按钮 -->

			<div v-show="serveData.actionType==1" >
				<el-button
					v-for="(item, index) in serveData.serveForm.controllist"
					:key="index"
					@click="controlFunctions(item)"
					class="custom-success-button">
					{{ item.controlName }}
				</el-button>
			</div>

			<div v-show="serveData.actionType==2">
				<el-button
					v-for="(item, index) in serveData.serveForm.gatherList"
					:key="index"
					@click="controlFunctions(item)"
					class="custom-success-button">
					{{ item.controlName }}
				</el-button>
			</div>
		</el-card>
		<div v-loading="loading">
			<el-row :gutter="15" class="custom-form" >
				<el-col :lg="12">发送数据：
					<el-card shadow="never"  class="message-form">
						<sc-code-editor ref="editor" v-model="serveData.dataTypeDic.reqData" mode="javascript" :height="300"></sc-code-editor>
					</el-card>
				</el-col>
				<el-col :lg="12">操作结果：
					<el-card shadow="never"  class="message-form">
						<sc-code-editor ref="editor" v-model="serveData.dataTypeDic.resData" mode="javascript" :height="300"></sc-code-editor>
					</el-card>
				</el-col>
			</el-row>
		</div>

	</el-card>

</template>


<script>
import { defineAsyncComponent } from 'vue';
const scCodeEditor = defineAsyncComponent(() => import('@/components/scCodeEditor'));
export default {
	components: {
		scCodeEditor
	},
	name: 'deviceServeTab',
	props: {
		deviceItemId: Number,
	},
	data() {
		return {
			loading: false,
			buttonLoading: false,
			serveData: {
				serveForm: {
					controllist: [],
					gatherList:[]
				},
				actionType:1,
				dataTypeDic: {
					reqData:'',
					resData: ''
				},
			}
		}
	},

	mounted() {
		this.getDeviceAbility(this.deviceItemId)
	},


	methods: {
		//获取功能列表
		async getDeviceAbility(deviceItemId){
			this.buttonLoading = true;
			var res = await this.$API.device.deviceAbility.list.get({"deviceItemId":deviceItemId});
			if (res.code === 200) {
				this.serveData.serveForm.controllist =res.data.filter(item => item.controlType === 1);
				this.serveData.serveForm.gatherList = res.data.filter(item => item.controlType === 2);
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.buttonLoading = false;
		},

		//指令发送
		async controlFunctions(item){
			this.loading = true;
/*			//展示数据
			let showReqData = {
				topic: "/SB" + item.deviceItemId,
				pushData: {
					[item.controlMode]: item.controlDefultValue
				}
			};*/
			//请求数据
			let reqData = {
				topic: "/SB" + item.deviceItemId,
				pushData: JSON.stringify({ [item.controlMode]: item.controlDefultValue }) // 转为字符串
			}
			this.serveData.dataTypeDic.reqData =  JSON.stringify(reqData, null, 2);

			var res = await this.$API.abutment.directives.pushData.post(reqData);
			if (res.code === 200) {
				this.serveData.dataTypeDic.resData = JSON.stringify(res, null, 2);
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},

	}
}
</script>

<style scoped>.custom-success-button {
	background: linear-gradient(135deg, #409eff, #83befc); /* 渐变背景 */
	border: none; /* 移除边框 */
	color: white; /* 文字颜色 */
	padding: 12px 24px; /* 内边距 */
	text-align: center; /* 文字水平居中 */
	display: inline-flex; /* 使用 flexbox 布局 */
	align-items: center; /* 文字垂直居中 */
	justify-content: center; /* 文字水平居中 */
	font-size: 16px; /* 字体大小 */
	border-radius: 8px; /* 圆角 */
	transition: background 0.3s, transform 0.3s, box-shadow 0.3s; /* 平滑过渡效果 */
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15); /* 阴影效果 */
	cursor: pointer; /* 鼠标指针样式 */
	margin: 10px;
}

.custom-success-button:hover {
	background: linear-gradient(135deg, #83befc, #409eff); /* 鼠标悬停时的渐变背景 */
	transform: translateY(-2px); /* 鼠标悬停时轻微上移 */
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2); /* 鼠标悬停时更大的阴影 */
}

.custom-success-button:active {
	background: linear-gradient(135deg, #83befc, #409eff); /* 鼠标按下时的渐变背景 */
	transform: translateY(2px); /* 鼠标按下时轻微下移 */
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 鼠标按下时较小的阴影 */
}


.custom-form {
	margin-top: 10px;
}
.message-form {
	margin-top: 10px;
	height: 250px;
}
</style>
