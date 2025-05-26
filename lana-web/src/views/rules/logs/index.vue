<template>
	<el-container>
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
							<el-table-column label="序号" type="index" width="100"></el-table-column>
							<el-table-column label="模块名称" prop="module" width="120"></el-table-column>
							<el-table-column label="日志类型" prop="name" width="120"></el-table-column>
							<el-table-column label="执行方式" prop="reqUri" width="150"></el-table-column>
							<el-table-column label="记录日志时间" prop="ts" width="200"></el-table-column>
							<el-table-column label="执行结果" prop="status" width="120">
									<template #default="scope">
									<el-tag v-if="scope.row.status==1" type="success">成功</el-tag>
									<el-tag v-if="scope.row.status==0" type="danger">失败</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="情景模式创建人员" prop="realName" width="180"></el-table-column>
							<el-table-column label="执行机器IP" prop="ip" width="150"></el-table-column>
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

			apiObj: this.$API.system.log.sysRulesPage,
			search: {
				operateType: 9,
				date: []
			}
		}
	},
	methods: {

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
