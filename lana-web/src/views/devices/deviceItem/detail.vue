<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1500" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-alert title="如果要关闭该页面，请点击右上角的 X ，该页面的保存操作不会关闭页面" type="warning" style="margin-bottom: 15px;"></el-alert>
		<el-tabs tab-position="top">
			<el-tab-pane label="设备物模型">
				<DeviceModelTab :form="form" :rules="rules" :deviceItemModel="deviceItemModel" />
			</el-tab-pane>
		</el-tabs>
<!--		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>-->
	</sc-dialog>
</template>

<script>
import DeviceModelTab from './deviceDetailTab/DeviceModelTab.vue';
import { defineAsyncComponent } from 'vue';
const scCodeEditor = defineAsyncComponent(() => import('@/components/scCodeEditor'));

export default {
	components: {
		scCodeEditor,
		DeviceModelTab
	},
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			titleMap: {
				add: '新增设备详情',
				edit: '设备详情',
				show: '查看设备详情'
			},

			deviceItemId: '',
			visible: false,
			isSaveing: false,

			msg: {
				open: true,
				appKey: "",
				secretKey: ""
			},


		}
	},
	methods: {

		//显示
		open(mode='add'){
			this.mode = mode;
			this.visible = true;
			return this
		},

		setData(data){
			this.deviceItemId = data.id
			this.deviceItemId = data.id
			this.deviceItemId = data.id
		}
	}
}
</script>

<style>
.custom-margin-top{
	margin-top: 20px;
	justify-content: flex-end;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
