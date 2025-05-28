<template>
	<el-container>
		<el-aside width="60%">
			<el-container>
				<el-header>
					<div class="left-panel">
						<el-button type="primary" icon="el-icon-plus" v-auth="'devices:deviceGroup:save'" @click="add"></el-button>
						<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0"  @click="batch_del"></el-button>
					</div>
					<div class="center-panel">
						<H3>分组管理</H3>
					</div>
					<div class="right-panel">
						<div class="right-panel-search">
							<el-input v-model="search.name"  placeholder="分组名称" clearable></el-input>
							<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
						</div>
					</div>
				</el-header>
				<el-main class="nopadding">
					<scTable ref="table" :apiObj="apiObj" row-key="id" stripe highlightCurrentRow @row-click="rowClick">
						<el-table-column type="selection" width="30"></el-table-column>
						<el-table-column label="分组名称" prop="name" width="150"></el-table-column>
						<el-table-column label="排序" prop="sort" width="50"></el-table-column>
						<el-table-column label="状态" prop="status" width="70">
							<template #default="scope">
								<el-tag v-if="scope.row.status==1" type="success">启用</el-tag>
								<el-tag v-if="scope.row.status==0" type="danger">未启用</el-tag>
							</template>
						</el-table-column>
						<el-table-column label="备注" prop="remark" min-width="150"></el-table-column>
						<el-table-column label="创建时间" prop="createTime" min-width="150"></el-table-column>
						<el-table-column label="创建人" prop="creatorName" min-width="150"></el-table-column>
						<el-table-column label="操作" fixed="right" align="right" width="150">
							<template #default="scope">
								<el-button-group>
									<el-button text type="primary" size="small"  @click.stop="table_show(scope.row, scope.$index)">查看</el-button>
									<el-button text type="primary" size="small" v-auth="'devices:deviceGroup:update'" @click.stop="table_edit(scope.row, scope.$index)">编辑</el-button>
<!--									这里不做处理，如果要删除分组，右侧要展示出来对应的设备，以便于先解绑设备，再进行删除-->
									<el-popconfirm title="确定删除吗？" v-auth="'devices:deviceGroup:delete'" @confirm="table_del(scope.row, scope.$index)">
										<template #reference>
											<el-button text type="primary" v-auth="'devices:deviceGroup:delete'" size="small">删除</el-button>
										</template>
									</el-popconfirm>
									<el-button text type="primary" size="small" v-auth="'devices:deviceGroup:binding'" @click.stop="device_binding(scope.row, scope.$index)">绑定设备</el-button>
								</el-button-group>
							</template>
						</el-table-column>
					</scTable>
				</el-main>
			</el-container>
		</el-aside>
		<el-container>
			<el-header>
				<div class="left-panel">
				</div>
				<div class="center-panel">
					<H3>已绑定的设备列表</H3>
				</div>
				<div class="right-panel">
				</div>
			</el-header>
			<el-main class="nopadding">
				<scTable ref="sontable" :apiObj="sontableApiObj" row-key="id" stripe>
					<el-table-column label="图片" width="80" column-key="filterAvatar" :filters="[{text: '已上传', value: '1'}, {text: '未上传', value: '0'}]">
						<template #default="scope">
							<el-avatar :src="scope.row.imgs" size="small"></el-avatar>
						</template>
					</el-table-column>
					<el-table-column label="设备名称" prop="name" width="120" ></el-table-column>
					<el-table-column label="设备编码" prop="deviceNumber" width="80" ></el-table-column>
					<el-table-column label="设备状态" prop="status" width="80">
						<template #default="scope">
							<el-tag v-if="scope.row.status==1" type="success">正常</el-tag>
							<el-tag v-if="scope.row.status==0" type="danger">停用</el-tag>
						</template>
					</el-table-column>
					<el-table-column label="所属产品" prop="productTypeName" width="100" ></el-table-column>
					<el-table-column label="操作"  width="100">
						<template #default="scope">
							<el-button-group>
								<el-button text type="primary" size="small" v-show="this.groupId!=null" v-auth="'devices:deviceGroup:unbind'" @click.stop="device_un_binding(scope.row, scope.$index)">取消绑定</el-button>
							</el-button-group>
						</template>
					</el-table-column>
				</scTable>
			</el-main>
		</el-container>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>
	<binding-dialog v-if="dialog.binDingSta" ref="bindingDialog" @success="handleBindingSaveSuccess" @closed="dialog.binDingSta=false"></binding-dialog>
</template>

<script>
import saveDialog from './save'
import bindingDialog from "./binding.vue";

export default {
	name: 'deviceGroup',
	components: {
		bindingDialog,
		saveDialog,
	},
	data() {
		return {
			dialog: {
				save: false,
				binDingSta: false
			},
			apiObj: this.$API.device.deviceGroup.page,
			sontableApiObj: this.$API.device.deviceGroup.groupDeviceItemPage,
			selection: [],
			search: {
				name: null
			},
			groupId: null
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
		/* eslint-disable */
		//删除
		async table_del(){
			if(this.sontableApiObj.get.length>0){
				this.$message.warning("请先解除该分组已经绑定的设备")
			}else {
				var reqData = {id: row.id}
				var res = await await this.$API.device.deviceGroup.delete.get(reqData);
				if(res.code == 200){
					this.$refs.table.refresh()
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			}
		},
		//批量删除
		async batch_del(){
			this.$confirm(`确定删除选中的 ${this.selection.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
				type: 'warning'
			}).then(async () => {
				//获取选中id集合列表
				const id = this.selection.map(item => item.id).join(',');
				const loading = this.$loading();
				var res = await await this.$API.system.role.delete.get({"id":id});
				if (res.code == 200) {
					this.$refs.table.refresh()
					this.$message.success("删除成功")
					this.$refs.table.refresh()
					loading.close();
				} else {
					loading.close();
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			}).catch(() => {

			})
		},
		async device_binding(row){
			//绑定设备
			this.dialog.binDingSta = true
			this.$nextTick(() => {
				this.$refs.bindingDialog.open('edit').setData(row)
			})
		},
		//取消绑定
		async device_un_binding(row){

			const loading = this.$loading();
			this.pamras = {
				groupId: this.groupId,
				deviceId: row.id
			}
			var res = await this.$API.device.deviceGroup.deleteBindingDevice.post(this.pamras);
			if (res.code == 200) {
				this.$refs.sontable.refresh()
				this.$message.success("解绑成功")
				loading.close();
			} else {
				loading.close();
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
		rowClick(row){

			this.groupId = row.id
			var params = {
				groupId: row.id
			}
			this.$refs.sontable.reload(params)
		},
		//产品详情
		handleBindingSaveSuccess(data, mode){
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
