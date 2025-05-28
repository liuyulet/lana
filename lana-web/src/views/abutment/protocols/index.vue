
<template>
	<el-main>
		<el-row :gutter="15">
			<el-col :xl="6" :lg="6" :md="8" :sm="12" :xs="24" v-for="item in list" :key="item.id">
				<el-card class="task task-item" shadow="hover"  @click="show(item)">
					<h2>{{item.title}}</h2>
					<ul>
						<li>
							<h3>服务名称</h3>
							<p>{{item.mqttName}}</p>
						</li>
						<li>
							<h3>协议来源</h3>
							<p v-if="item.mqttType==1">系统 mqtt</p>
							<p v-if="item.mqttType==2">其他代理 mqtt</p>
						</li>
					</ul>
					<div class="bottom" @click.stop>
						<div class="state">
							<el-tag v-if="item.mqttEnabled=='1'" size="small">运行中</el-tag>
							<el-tag v-if="item.mqttEnabled=='0'" size="small" type="info">未启用</el-tag>
						</div>
						<div class="handler">
<!--							<el-popconfirm title="确定立即执行吗？" @confirm="run(item)">
								<template #reference>
									<el-button type="primary" icon="el-icon-caret-right" circle></el-button>
								</template>
							</el-popconfirm>-->
							<el-dropdown trigger="click" >
								<el-button type="primary" icon="el-icon-more" circle plain></el-button>
								<template #dropdown>
									<el-dropdown-menu>
										<el-dropdown-item  v-if="item.mqttType !=1" @click="edit(item)">编辑</el-dropdown-item>
										<div v-auth="'abutment:protocols:logs'">
											<el-dropdown-item  @click="logs(item)">日志</el-dropdown-item>
										</div>
										<div v-auth="'abutment:protocols:del'">
											<el-dropdown-item  @click="del(item)" divided>删除</el-dropdown-item>
										</div>

									</el-dropdown-menu>
								</template>
							</el-dropdown>
						</div>
					</div>
				</el-card>
			</el-col>
			<el-col :xl="6" :lg="6" :md="8" :sm="12" :xs="24">
				<el-card class="task task-add" shadow="never" @click="add">
					<el-icon><el-icon-plus /></el-icon>
					<p>添加其他代理接入协议</p>
				</el-card>
			</el-col>
		</el-row>
	</el-main>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSuccess" @closed="dialog.save=false"></save-dialog>

	<el-drawer title="日志" v-model="dialog.logsVisible" :size="600" direction="rtl" destroy-on-close>
		<logs></logs>
	</el-drawer>
</template>

<script>
	import saveDialog from './save'
	import logs from './logs'

	export default {
		name: 'task',
		components: {
			saveDialog,
			logs
		},
/*		provide() {
			return {
				list: this.list
			}
		},*/
		data() {
			return {
				dialog: {
					save: false,
					logsVisible: false
				},
				list: [
					{
						id: "",
						ip: "",
						mqttName: "",
						mqttType: "",
						tcpPort: "",
						websocketPort: "",
						authEnable: "",
						username: "",
						password: "",
						httpEnable: "",
						httpBasicAuth: "",
						httpBasicUsername: "",
						httpBasicPassword: "",
						sslEnabled: "",
						keystorePath: "",
						keystorePass: "",
						clientAuth: "",
						truststorePath: "",
						truststorePass: "",
						mqttEnabled: "",
						createTime: "",
						updateTime: "",
						creatorName: "",
						updaterName: "",
						linkType: ""
					}
				]
			}
		},
		mounted() {
			this.getProtocols()
		},
		methods: {
			//获取协议列表
			async getProtocols(){
				this.showGrouploading = true;
				var res = await this.$API.abutment.protocols.list.get();
				if(res.code == 200){
					this.showGrouploading = false;
					this.list = res.data;
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})

				}

			},

			add(){
		/*		this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open()
				})*/
				/*this.$alert("初版暂不支持添加代理接入协议", "提示", {type: 'warning'})*/
				this.$message.error("初版暂不支持添加代理接入协议");
			},
			show(item){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('show').setData(item)
				})
			},
			edit(item){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(item)
				})
			},
			del(item){
				this.$confirm(`确认删除代理协议： ${item.mqttName} 吗？`,'提示', {
					type: 'warning',
					confirmButtonText: '删除',
					confirmButtonClass: 'el-button--danger'
				}).then(async () => {
					this.isSaveing = true;
					var res = await this.$API.abutment.protocols.delete.get({"id": item.id});
					if(res.code == 200){
						this.$message.success("操作成功")
						this.getProtocols()
					}else{
						this.$alert(res.msg, "提示", {type: 'error'})
					}
					this.isSaveing = false;
					//this.list.splice(this.list.findIndex(item => item.id === task.id), 1)
				}).catch(() => {
					//取消
				})
			},


			logs(){
				this.dialog.logsVisible = true
			},
			run(task){
				this.$message.success(`已成功执行计划任务：${task.title}`)
			},
			//本地更新数据
			handleSuccess(){
				this.getProtocols()
			}
		}
	}
</script>

<style scoped>
	.task {height: 210px;}
	.task-item h2 {font-size: 15px;color: #3c4a54;padding-bottom:15px;}
	.task-item li {list-style-type:none;margin-bottom: 10px;}
	.task-item li h4 {font-size: 12px;font-weight: normal;color: #999;}
	.task-item li p {margin-top: 5px;}
	.task-item .bottom {border-top: 1px solid #EBEEF5;text-align: right;padding-top:10px;display: flex;justify-content: space-between;align-items: center;}

	.task-add {display: flex;flex-direction: column;align-items: center;justify-content: center;text-align: center;cursor: pointer;color: #999;}
	.task-add:hover {color: #409EFF;}
	.task-add i {font-size: 30px;}
	.task-add p {font-size: 12px;margin-top: 20px;}

	.dark .task-item .bottom {border-color: var(--el-border-color-light);}
</style>
