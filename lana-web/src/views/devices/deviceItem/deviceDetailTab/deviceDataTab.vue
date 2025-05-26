<template>
	<div class="mb-2 ml-4" >
		<el-radio-group v-model="deviceData.actionType" >
			<el-radio :label="1">实时数据</el-radio>
			<el-radio :label="2">历史数据</el-radio>
		</el-radio-group>
	</div>
	<el-table :data="deviceData.deviceForm" stripe  v-loading="loading" v-show="deviceData.actionType == 1">
		<el-table-column label="#" type="index" width="150"></el-table-column>
		<el-table-column label="属性名称"  width="250">
			<template #default="scope">
				<span>{{scope.row.modeName}}</span>
			</template>
		</el-table-column>

		<el-table-column label="属性标志"  width="250">
			<template #default="scope">
				<span>{{ scope.row.modeSigns }}</span>
			</template>
		</el-table-column>

		<el-table-column label="实时数据"  width="250">
			<template #default="scope">
				<span><text style="color: #0b94ef">{{ scope.row.id }}</text></span>
			</template>
		</el-table-column>
		<el-table-column label="属性单位"  width="250">
			<template #default="scope">
				<span>{{ scope.row.modeRemark }}</span>
			</template>
		</el-table-column>
		<el-table-column min-width="1"></el-table-column>
	</el-table>
</template>

<script>export default {
	name: 'deviceDataTab',
	props: {
		deviceItemId: Number,
	},
	data() {
		return {
			loading: false,
			testDatas: 123,
			deviceData: {
				actionType:1,
				deviceForm:[]
			}
		}
	},

	mounted() {
		this.getDeviceMode(this.deviceItemId);
	},

	// todo mqtt监听数据，实时显示在实时数据中

	methods: {
		async getDeviceMode(deviceItemId){
			this.loading = true;
			var res = await this.$API.device.deviceItem.getDeviceMode.get({"id":deviceItemId});
			if (res.code === 200) {
				this.deviceData.deviceForm = res.data;
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},
	}
}
</script>

<style scoped>
.custom-margin-top {
	margin-top: 20px;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
