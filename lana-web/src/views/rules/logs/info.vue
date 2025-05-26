<template>
	<el-main style="padding:0 20px;">
		<el-descriptions :column="1" border size="small">
			<el-descriptions-item label="执行机器IP">{{data.ip}}</el-descriptions-item>
			<el-descriptions-item label="执行方式">{{data.reqUri}}</el-descriptions-item>
			<el-descriptions-item label="模块名称">{{data.module}}</el-descriptions-item>
			<el-descriptions-item label="日志类型">{{data.name}}</el-descriptions-item>
			<el-descriptions-item label="请求类型">{{data.reqMethod}}</el-descriptions-item>
			<el-descriptions-item label="记录日志时间">{{data.ts}}</el-descriptions-item>
			<el-descriptions-item label="操作结果">
				<span v-if="data.status === 1">成功</span>
				<span v-else-if="data.status === 2">失败</span>
				<span v-else>未知</span>
			</el-descriptions-item>
			<el-descriptions-item label="情景模式创建人员">{{data.realName}}</el-descriptions-item>
		</el-descriptions>
		<el-collapse v-model="activeNames" style="margin-top: 20px;">
			<el-collapse-item title="执行参数（内容）" name="1">
				<el-alert  :type="typeMap[data.level]" :closable="false" >
					{{data.reqParams}}
				</el-alert>
			</el-collapse-item>
			<el-collapse-item title="代理/平台信息" name="2">
				<div class="code">
					{{data.userAgent}}
				</div>
			</el-collapse-item>
		</el-collapse>
	</el-main>
</template>

<script>
	export default {
		data() {
			return {
				data: {},
				activeNames: ['1','2'],
				typeMap: {
					'info': "info",
					'warn': "warning",
					'error': "error"
				}
			}
		},
		methods: {
			setData(data){
				this.data = data
			}
		}
	}
</script>

<style scoped>
	.code {background: #848484;padding:15px;color: #fff;font-size: 12px;border-radius: 4px;}
</style>
