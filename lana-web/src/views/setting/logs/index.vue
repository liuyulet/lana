<template>
	<el-container>
			<el-aside width="220px">
				<el-main class="nopadding">
					<el-tree
						ref="category"
						class="menu"
						node-key="label"
						:data="category"
						:default-expanded-keys="['系统日志']"
						current-node-key="系统日志"
						:highlight-current="true"
						:expand-on-click-node="false"
						@node-click="handleNodeClick"
					></el-tree>
				</el-main>
			</el-aside>
		<el-container>
			<el-main class="nopadding">
				<el-container>
					<el-header>
						<div class="left-panel">
							<el-date-picker v-model="search.date" @change="upsearch" type="datetimerange" range-separator="至"
							start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
						</div>
						<div class="right-panel">
						</div>
					</el-header>
					<el-main class="nopadding">
						<scTable ref="table" :apiObj="apiObj" stripe highlightCurrentRow @row-click="rowClick">
							<el-table-column  width="30"></el-table-column>
							<el-table-column label="序号" type="index" width="80"></el-table-column>
							<el-table-column label="模块名" prop="module" width="140"></el-table-column>
							<el-table-column label="操作名" prop="name" width="160"></el-table-column>
							<el-table-column label="触发方式" prop="reqUri" width="300"></el-table-column>
							<el-table-column label="日志时间" prop="ts" width="170"></el-table-column>
							<el-table-column label="操作结果" prop="status" width="100">
									<template #default="scope">
									<el-tag v-if="scope.row.status==1" type="success">成功</el-tag>
									<el-tag v-if="scope.row.status==0" type="danger">失败</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="操作人员" prop="realName" width="110"></el-table-column>
							<el-table-column label="操作IP" prop="ip" width="150"></el-table-column>
						</scTable>
					</el-main>
				</el-container>
			</el-main>
		</el-container>
	</el-container>

	<el-drawer v-model="infoDrawer" title="日志详情" :size="600" destroy-on-close>
		<info ref="info"></info>
	</el-drawer>
</template>

<script>
import info from './info.vue'
import scEcharts from '@/components/scEcharts/index.vue'

export default {
	name: 'log',
	components: {
		info,
		scEcharts
	},
	data() {
		return {
			infoDrawer: false,

			apiObj: this.$API.system.log.syslist,
			search: {
				operateType: 1,
				date: []
			},

			category: [
				{
					label: '系统日志',
					value: '11',
					children: [
						{label: '新增日志', value: 2},
						{label: '删除日志', value: 4},
						{label: '修改日志', value: 3},
						{label: '查询日志', value: 1}
					]
				},
				{
					label: '设备日志',
					value: '12',
					children: [
						{label: '设备操作日志', value: 0},
						{label: '设备告警日志', value: 5},
						{label: '解除告警日志', value: 6}
					]
				},
				{
					label: '通讯日志',
					value: '13',
					children: [
						/*{label: '平台推送日志', value: 7},*/
						{label: '接入协议日志', value: 8}
					]
				}
			]
		}
	},
	methods: {

		handleNodeClick(data) {
			const selectedValue = data.value;
			var params = {
				operateType: selectedValue,
				startTime: this.search.date[0],
				endTime: this.search.date[1]
			}
			this.$refs.table.reload(params);
		},

		upsearch() {
			var params = {
				operateType: this.search.operateType,
				startTime: this.search.date[0],
				endTime: this.search.date[1]
			}
			this.$refs.table.upData(params)
		},
		rowClick(row) {
			this.infoDrawer = true;
			this.$nextTick(() => {
				this.$refs.info.setData(row);
			});
		}

	}
}
</script>

<style>
</style>
