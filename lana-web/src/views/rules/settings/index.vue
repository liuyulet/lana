<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" v-auth="'rules:rules:save'" @click="add">新增规则</el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.name" placeholder="规则名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<scTable ref="table" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="40"></el-table-column>
				<el-table-column label="规则名称" prop="asName" width="150"></el-table-column>
				<el-table-column label="规则类型" prop="asType" width="120">
					<template #default="scope">
						<el-tag v-if="scope.row.asType==1" >规则脚本</el-tag>
						<el-tag v-if="scope.row.asType==2" >表达式</el-tag>
						<el-tag v-if="scope.row.asType==3" >图形化配置</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="是否启用" prop="asEnable" width="120">
					<template #default="scope">
						<el-tag v-if="scope.row.asEnable==1" type="success">启用</el-tag>
						<el-tag v-if="scope.row.asEnable==0" type="danger">未启用</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="创建人员" prop="creatorName" width="150"></el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>
				<el-table-column label="规则说明" prop="asRemark" min-width="220"></el-table-column>
				<el-table-column label="操作" fixed="right" align="right" width="220">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" v-auth="'rules:rules:setRule'" @click="rulesSeting(scope.row, scope.$index)">规则设置</el-button>
							<el-button text type="primary" size="small" v-auth="'rules:rules:update'" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
							<el-popconfirm title="确定删除吗？"  @confirm="table_del(scope.row, scope.$index)">
								<template #reference>
									<el-button text type="primary" v-auth="'rules:rules:delete'" size="small">删除</el-button>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>
			</scTable>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>
	<rules-detail v-if="dialog.rule" ref="rulesDetail" @success="handleSaveSuccess" :rules-id="rulesId" @closed="dialog.rule=false"></rules-detail>

</template>

<script>
	import saveDialog from './save'
	import rulesDetail from './rules.vue'
	export default {
		name: 'role',
		components: {
			saveDialog,
			rulesDetail
		},
		data() {
			return {
				rulesId: Number,
				dialog: {
					save: false,
					rule: false,
					permission: false
				},
				apiObj: this.$API.rules.rules.getPage,
				selection: [],
				search: {
					keyword: null
				}
			}
		},
		methods: {
			//添加
			add(){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('add')
				})
			},
			//编辑
			table_edit(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(row)
				})
			},
			//规则设置
			rulesSeting(row){
				this.rulesId = row.id
				this.dialog.rule = true
				this.$nextTick(() => {
					this.$refs.rulesDetail.open('rulesSeting').setData(row)
				})
			},

			//删除
			async table_del(row){
				var reqData = {id: row.id}
				var res = await await this.$API.rules.rules.delete.get(reqData);
				if(res.code == 200){
					this.$refs.table.refresh()
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},

			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//表格内开关
			changeSwitch(val, row){
				row.status = row.status == '1'?'0':'1'
				row.$switch_status = true;
				setTimeout(()=>{
					delete row.$switch_status;
					row.status = val;
					this.$message.success("操作成功")
				}, 500)
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},

			//本地更新数据
			handleSaveSuccess(data, mode){
				if(mode=='add'){
					this.$refs.table.refresh()
				}else if(mode=='edit'){
					this.$refs.table.refresh()
				}
			}
		}
	}
</script>

<style>
</style>
