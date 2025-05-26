<template>

	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-alert title="如果要关闭该页面，请点击右上角的 X ，该页面的保存操作不会关闭页面" type="warning" style="margin-bottom: 15px;"></el-alert>
		<el-tabs tab-position="top" v-model="activeTab">
			<el-tab-pane label="设备物模型"  name="deviceModel">
				<DeviceModelTab  v-if="activeTab === 'deviceModel' && deviceItemId !== null" :device-item-id="deviceItemId" />
			</el-tab-pane>

			<el-tab-pane label="设备功能" name="deviceAbility">
				<DeviceAbilityTab  v-if="activeTab === 'deviceAbility' && deviceItemId !== null" :device-item-id="deviceItemId" />
			</el-tab-pane>

			<el-tab-pane label="设备服务" name="deviceService">
				<DeviceServeTab  v-if="activeTab === 'deviceService' && deviceItemId !== null" :device-item-id="deviceItemId" />
			</el-tab-pane>

			<el-tab-pane label="设备数据" name="deviceData">
				<DeviceDataTab  v-if="activeTab === 'deviceData' && deviceItemId !== null" :device-item-id="deviceItemId" />
			</el-tab-pane>
		</el-tabs>
	</sc-dialog>
</template>

<script>
import DeviceModelTab from './deviceDetailTab/deviceModelTab.vue';
import DeviceAbilityTab from './deviceDetailTab/deviceAbilityTab.vue';
import DeviceServeTab from './deviceDetailTab/deviceServeTab.vue';
import DeviceDataTab from './deviceDetailTab/deviceDataTab.vue';
export default {
	components: {
		DeviceModelTab,
		DeviceAbilityTab,
		DeviceServeTab,
		DeviceDataTab
	},
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			activeTab: 'deviceModel',
			titleMap: {
				add: '设备详情',
				edit: '设备详情',
				show: '设备详情'
			},
			deviceItemId: Number,
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
		open(mode,data){
			if (data) {
				this.deviceItemId = data.id
			}
			this.$nextTick(() => {
				console.log('deviceItemId after $nextTick:', this.deviceItemId); // 确认 deviceItemId 被正确设置
			});

			this.mode = mode;
			this.visible = true;
			return this
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
