<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" v-auth="'devices:deviceProductType:save'" @click="add"></el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.label" placeholder="产品名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<scTable ref="table" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" hidePagination>
				<el-table-column type="selection" width="30"></el-table-column>
				<el-table-column label="产品名称" prop="label" width="200"></el-table-column>
				<el-table-column label="接入方式" prop="deviceAbutmentType" width="180">
					<template #default="scope">
						<el-tag v-if="scope.row.deviceAbutmentType==1" type="success">直连设备</el-tag>
						<el-tag v-if="scope.row.deviceAbutmentType==2" type="success">边缘计算设备</el-tag>
						<el-tag v-if="scope.row.deviceAbutmentType==3" type="success">边缘子设备</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="状态" prop="status" width="130">
					<template #default="scope">
						<el-tag v-if="scope.row.status==1" type="success">启用</el-tag>
						<el-tag v-if="scope.row.status==0" type="danger">停用</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="备注" prop="remark" min-width="150"></el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>
				<el-table-column label="创建人" prop="creatorName" min-width="150"></el-table-column>
				<el-table-column label="操作" fixed="right" align="right" width="250">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" @click="table_show(scope.row, scope.$index)">查看</el-button>
							<el-button text type="primary" size="small" v-auth="'devices:deviceProductType:update'" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
							<el-popconfirm title="确定删除吗？" @confirm="table_del(scope.row, scope.$index)">
								<template #reference>
									<el-button text type="primary" v-auth="'devices:deviceProductType:delete'" size="small">删除</el-button>
								</template>
							</el-popconfirm>
							<el-button text type="primary" size="small" v-auth="'devices:deviceProductType:detail'" @click="product_detil(scope.row, scope.$index)">产品详情</el-button>
						</el-button-group>
					</template>
				</el-table-column>
			</scTable>
		</el-main>
	</el-container>
	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>
	<detail-dialog v-if="dialog.detailDialogSta" ref="detailDialog" @success="handleDetailSaveSuccess" @closed="dialog.detailDialogSta=false"></detail-dialog>

</template>

<script>
	import saveDialog from './save'
	import detailDialog from './detail.vue'

	export default {
		name: 'productType',
		components: {
			saveDialog,
			detailDialog
		},
		data() {
			return {
				dialog: {
					save: false,
					detailDialogSta: false
				},
				apiObj: this.$API.device.productType.list,
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
					this.$refs.saveDialog.open()
				})
			},
			//编辑
			table_edit(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(row)
				})
			},
			//查看
			table_show(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('show').setData(row)
				})
			},
			//设备详情
			product_detil(row){
				this.dialog.detailDialogSta = true
				this.$nextTick(() => {
					this.$refs.detailDialog.open('edit').setData(row)
				})
			},
			//删除
			async table_del(row){
				var reqData = {id: row.id}
				var res = await this.$API.device.productType.delete.get(reqData);
				if(res.code == 200){
					this.$refs.table.refresh()
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			//批量删除
/*			async batch_del(){
				this.$confirm(`确定删除选中的 ${this.selection.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
					type: 'warning'
				}).then(() => {
					const loading = this.$loading();
					this.$refs.table.refresh()
					loading.close();
					this.$message.success("操作成功")
				}).catch(() => {

				})
			},*/
			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},
			//根据ID获取树结构
			filterTree(id){
				var target = null;
				function filter(tree){
					tree.forEach(item => {
						if(item.id == id){
							target = item
						}
						if(item.children){
							filter(item.children)
						}
					})
				}
				filter(this.$refs.table.tableData)
				return target
			},
			//本地更新数据
			handleSaveSuccess(data, mode){
				if(mode=='add'){
					this.$refs.table.refresh()
				}else if(mode=='edit'){
					this.$refs.table.refresh()
				}
			},
			//产品详情
			handleDetailSaveSuccess(data, mode){
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
