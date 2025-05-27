<template>
	<el-container>
		<el-aside width="250px" v-loading="showGrouploading">
			<el-container>
				<el-header>
					<el-input placeholder="输入关键字进行过滤" v-model="groupFilterText" clearable></el-input>
				</el-header>
				<el-main class="nopadding">
					<el-tree ref="group" class="menu" node-key="id" :data="group" :current-node-key="''" :highlight-current="true" :expand-on-click-node="false" :filter-node-method="groupFilterNode" @node-click="groupClick"></el-tree>
				</el-main>
			</el-container>
		</el-aside>
		<el-container>
				<el-header>
					<div class="left-panel">
						<el-button type="primary"  icon="el-icon-plus" v-auth="'devices:deviceItem:save'" @click="add"></el-button>
					</div>
					<div class="right-panel">
						<div class="right-panel-search">
							<el-input v-model="search.name" placeholder="设备名称" clearable></el-input>
							<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
						</div>
					</div>
				</el-header>
				<el-main class="nopadding">
					<scTable ref="table" :apiObj="apiObj" @selection-change="selectionChange" stripe remoteSort remoteFilter>
						<el-table-column type="selection" width="30"></el-table-column>
<!--						<el-table-column label="ID" prop="id" width="100" sortable='custom'></el-table-column>-->
						<el-table-column label="图片" width="70" column-key="filterAvatar" :filters="[{text: '已上传', value: '1'}, {text: '未上传', value: '0'}]">
							<template #default="scope">
								<el-avatar :src="scope.row.imgs" size="small"></el-avatar>
							</template>
						</el-table-column>
						<el-table-column label="设备名称" prop="name" width="150" ></el-table-column>
						<el-table-column label="设备主题"  width="150" >
							<template #default="scope">
								<el-text >SB{{scope.row.id}}</el-text>
							</template>
						</el-table-column>
						<el-table-column label="设备编码" prop="deviceNumber" width="110" ></el-table-column>
						<el-table-column label="设备状态" prop="status" width="100">
							<template #default="scope">
								<el-tag v-if="scope.row.status==1" type="success">正常</el-tag>
								<el-tag v-if="scope.row.status==0" type="danger">停用</el-tag>
							</template>
						</el-table-column>
						<el-table-column label="设备描述" prop="remark" width="140"  ></el-table-column>
						<el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>
						<el-table-column label="创建人员" prop="creatorName" width="150"></el-table-column>
						<el-table-column label="操作" fixed="right" align="right" width="230">
							<template #default="scope">
								<el-button-group>
									<el-button text type="primary" size="small"  @click="table_show(scope.row, scope.$index)">查看</el-button>
									<el-button text type="primary" size="small" v-auth="'devices:deviceItem:update'" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
									<el-popconfirm title="确定删除吗？"  @confirm="table_del(scope.row, scope.$index)">
										<template #reference>
											<el-button text type="primary" v-auth="'devices:deviceItem:delete'" size="small">删除</el-button>
										</template>
									</el-popconfirm>
									<el-button text type="primary" size="small" v-auth="'devices:deviceDetail:detail'" @click="device_detil(scope.row, scope.$index)">设备详情</el-button>
								</el-button-group>
							</template>
						</el-table-column>

					</scTable>
				</el-main>
		</el-container>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSuccess" @closed="dialog.save=false"></save-dialog>
	<detail-dialog v-if="dialog.detail" ref="detailDialog" @success="deviceDetailSaveSuccess" @closed="dialog.detail=false"></detail-dialog>

</template>

<script>
	import saveDialog from './save.vue'
	import detailDialog from './detail.vue'

	export default {
		name: 'user',
		components: {
			detailDialog,
			saveDialog
		},
		data() {
			return {
				dialog: {
					save: false,
					detail: false
				},
				showGrouploading: false,
				groupFilterText: '',
				group: [],
				apiObj: this.$API.device.deviceItem.page,
				selection: [],
				search: {
					name: null
				}
			}
		},
		watch: {
			groupFilterText(val) {
				this.$refs.group.filter(val);
			}
		},
		mounted() {
			//加载产品列表
			this.getGroup()
			//加载列表
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
			//删除
			async table_del(row, index){
				var reqDataArray = [row.id];
				var res = await this.$API.device.deviceItem.delete.post(reqDataArray);

				if(res.code == 200){
					//这里选择刷新整个表格 OR 插入/编辑现有表格数据
					this.$refs.table.tableData.splice(index, 1);
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			async device_detil(row){
				this.dialog.detail = true
				this.$nextTick(() => {
					this.$refs.detailDialog.open('edit',row)
				})
			},

			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//加载树数据
			async getGroup(){
				this.showGrouploading = true;
				var res = await this.$API.device.productType.list.get();
				this.showGrouploading = false;
				var allNode ={id: '', label: '所有'}
				res.data.unshift(allNode);
				this.group = res.data;
			},
			//树过滤
			groupFilterNode(value, data){
				if (!value) return true;
				return data.label.indexOf(value) !== -1;
			},
			//树点击事件
			groupClick(data){
				var params = {
					groupId: data.id
				}
				this.$refs.table.reload(params)
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},
			//本地更新数据
			handleSuccess(data, mode){

				if(mode=='add'){
					this.$refs.table.refresh()
				}else if(mode=='edit'){
					this.$refs.table.refresh()
				}

				//要刷新的数据太多，这里只更新部分，
/*				if(mode=='add'){
					data.id = new Date().getTime()
					this.$refs.table.tableData.unshift(data)
				}else if(mode=='edit'){
					this.$refs.table.tableData.filter(item => item.id===data.id ).forEach(item => {
						Object.assign(item, data)
					})
				}*/
			},
			deviceDetailSaveSuccess(data, mode) {
				if (mode == 'add') {
					this.$refs.table.refresh()
				} else if (mode == 'edit') {
					this.$refs.table.refresh()
				}
			}
		}
	}
</script>

<style>
</style>
