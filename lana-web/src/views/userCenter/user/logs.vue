<template>
	<el-card shadow="never" header="我的所有的操作记录">
		<scTable ref="table" :apiObj="apiObj" height="auto" paginationLayout="total, prev, pager, next" hideDo>
			<sc-table-column label="序号" type="index"></sc-table-column>
			<sc-table-column label="操作人" prop="realName" width="100"></sc-table-column>
			<sc-table-column label="模块名" prop="module" width="100"></sc-table-column>
			<sc-table-column label="操作名" prop="name" width="100"></sc-table-column>
			<sc-table-column label="触发方式" prop="reqUri" width="150"></sc-table-column>
			<sc-table-column label="请求方法" prop="reqMethod" width="100"></sc-table-column>
			<sc-table-column label="请求参数" prop="reqParams" width="300"></sc-table-column>
			<sc-table-column label="操作IP" prop="ip" width="100"></sc-table-column>
			<sc-table-column label="操作结果" prop="status" width="150">
				<template #default="scope">
					<el-tag v-if="scope.row.status==1" type="success">成功</el-tag>
					<el-tag v-if="scope.row.status==0" type="danger">失败</el-tag>
				</template>
			</sc-table-column>
			<sc-table-column label="操作时间" prop="ts" width="150"></sc-table-column>
		</scTable>
	</el-card>
</template>

<script>
	export default {
		data() {
			return {
				apiObj: this.$API.system.log.list
			}
		}
	}
</script>

<style>
</style>
