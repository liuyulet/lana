<template>
	<div class="branch-wrap">
		<div class="branch-box-wrap">
			<div class="branch-box">
				<el-button class="add-branch" type="success" plain round @click="addTerm">添加条件</el-button>
				<div class="col-box" v-for="(item,index) in nodeConfig.conditionNodes" :key="index">
					<div class="condition-node">
						<div class="condition-node-box">
							<div class="auto-judge" @click="show(index)">
								<div class="sort-left" v-if="index!=0" @click.stop="arrTransfer(index,-1)">
									<el-icon><el-icon-arrow-left /></el-icon>
								</div>
								<div class="title">
									<span class="node-title">{{ item.nodeName }}</span>
									<span class="priority-title">优先级{{item.priorityLevel}}</span>
									<el-icon class="close" @click.stop="delTerm(index)"><el-icon-close /></el-icon>
								</div>
								<div class="content">
									<span v-if="toText(nodeConfig, index)">{{ toText(nodeConfig, index) }}</span>
									<span v-else class="placeholder">请设置条件</span>
								</div>
								<div class="sort-right" v-if="index!=nodeConfig.conditionNodes.length-1" @click.stop="arrTransfer(index)">
									<el-icon><el-icon-arrow-right /></el-icon>
								</div>
							</div>
							<add-node v-model="item.childNode"></add-node>
						</div>
					</div>
					<slot v-if="item.childNode" :node="item"></slot>
					<div class="top-left-cover-line" v-if="index==0"></div>
					<div class="bottom-left-cover-line" v-if="index==0"></div>
					<div class="top-right-cover-line" v-if="index==nodeConfig.conditionNodes.length-1"></div>
					<div class="bottom-right-cover-line" v-if="index==nodeConfig.conditionNodes.length-1"></div>
				</div>
			</div>
			<div class="add-node-btn-box">
				<div class="add-node-btn">

				</div>
			</div>
		</div>
		<el-drawer title="条件设置" v-model="drawer" destroy-on-close append-to-body :size="700">
			<template #header>
				<div class="node-wrap-drawer__title">
					<label @click="editTitle" v-if="!isEditTitle">{{form.nodeName}}<el-icon class="node-wrap-drawer__title-edit"><el-icon-edit /></el-icon></label>
					<el-input v-if="isEditTitle" ref="nodeTitle" v-model="form.nodeName" clearable @blur="saveTitle" @keyup.enter="saveTitle"></el-input>
				</div>
			</template>
			<el-container>
				<el-main style="padding:0 20px 20px 20px">
					<el-form label-position="top">
						<el-form-item label="条件通过关系">
							<el-radio-group v-model="form.conditionMode">
								<el-radio :label="1">与(仅适用于同一个设备情况下，多个条件均满足才会通过的情况)</el-radio>
								<el-radio :label="2">或(适用于单个/多个设备情况下，多个条件中只要有一个满足就会通过)</el-radio>
<!--								<el-radio :label="3">非</el-radio>-->
							</el-radio-group>

							<div class="tags-list">
								<el-tag v-for="(user, index) in form.nodeDeviceList" :key="user.id" closable @close="delUser(index)">{{user.name}}</el-tag>
							</div>
						</el-form-item>
						<el-divider></el-divider>
						<el-form-item>
							<el-table :data="form.conditionList">

								<el-table-column prop="label" label="条件唯一编码"  v-if="false" width="100">
									<template #default="scope">
										<el-input v-model="scope.row.unicode" placeholder="条件唯一编码"></el-input>
									</template>
								</el-table-column>

								<el-table-column prop="label" label="设备id"  v-if="false" width="100">
									<template #default="scope">
										<el-input v-model="scope.row.id" placeholder="设备id"></el-input>
									</template>
								</el-table-column>
								<el-table-column prop="label" label="设备名称" width="180">
									<template #default="scope">
										<el-input v-model="scope.row.label" placeholder="点击选择设备" @click="selectHandle(1, form.nodeDeviceList,scope.row)"></el-input>
									</template>
								</el-table-column>
								<el-table-column prop="modeSigns" v-if="false" label="设备属性标志位" width="150">
									<template #default="scope">
										<el-input v-model="scope.row.modeSigns" placeholder="点击选择设备属性标志位"  @click="selectModel(scope.row)"></el-input>
									</template>
								</el-table-column>
								<el-table-column prop="modeSignsName" label="设备属性" width="150">
									<template #default="scope">
										<el-input v-model="scope.row.modeSignsName" placeholder="点击选择属性"  @click="selectModel(scope.row)"></el-input>
									</template>
								</el-table-column>

								<el-table-column prop="operator" label="运算符" width="130">
									<template #default="scope">
										<el-select v-model="scope.row.operator" placeholder="Select">
											<el-option label="等于" value="0"></el-option>
											<el-option label="大于" value="1"></el-option>
											<el-option label="小于" value="2"></el-option>
										</el-select>
									</template>
								</el-table-column>
								<el-table-column prop="value" label="值" width="140">
									<template #default="scope">
										<el-input v-model="scope.row.value" placeholder="值"></el-input>
									</template>
								</el-table-column>
								<el-table-column prop="value" label="移除" width="55">
									<template #default="scope">
										<el-link type="danger" :underline="false" @click="deleteConditionList(scope.$index)">移除</el-link>
									</template>
								</el-table-column>
							</el-table>
						</el-form-item>
						<p><!--<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(1, form.nodeDeviceList)">选择设备</el-button>--> <el-button type="primary" icon="el-icon-plus" round @click="addConditionList">增加空白条件</el-button></p>
					</el-form>
				</el-main>
				<el-footer>
					<el-button type="primary" @click="save">保存</el-button>
					<el-button @click="drawer=false">取消</el-button>
				</el-footer>
			</el-container>
			<!--	物模型数据	-->
			<div>
				<sc-dialog :title="选择产品物模型" v-model="visible" :width="700" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
					<el-container>
						<el-main class="nopadding">
							<scTable ref="table" :data="deviceItemModel" @selection-change="selectionChange"  stripe height="500" paginationLayout="" remoteSort remoteFilter>
								<el-table-column type="selection" width="50"></el-table-column>
								<el-table-column label="ID" prop="id" width="100" sortable='custom'></el-table-column>
								<el-table-column label="属性名称" prop="modeName" width="180" sortable='custom' ></el-table-column>
								<el-table-column label="属性标志" prop="modeSigns" width="160" sortable='custom'></el-table-column>
								<el-table-column label="单位" prop="modeRemark" width="100" sortable='custom'></el-table-column>
							</scTable>
						</el-main>
					</el-container>
					<template #footer>
						<el-button @click="visible=false" >取 消</el-button>
						<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
					</template>
				</sc-dialog>
			</div>
		</el-drawer>
	</div>
</template>

<script>
	import addNode from './branchAddNode'

	export default {
		inject: ['select'],
		props: {
			modelValue: { type: Object, default: () => {} }
		},
		components: {
			addNode
		},
		data() {
			return {
				visible: false,
				deviceItemModel: [],
				selection: '',
				selectionModelName: '',
				unicode:'',
				nodeConfig: {},
				drawer: false,
				isEditTitle: false,
				index: 0,
				nodeDevices: [],
				form: {}
			}
		},
		watch:{
			modelValue(){
				this.nodeConfig = this.modelValue
			},
			'form.nodeDeviceList': {
				handler(newVal) {

					if(newVal.length > 0 && newVal.length <= 1 ){
						this.form.conditionList.forEach(condition => {
							// 找到与当前 condition 的 unicode 匹配的设备
							if (this.unicode === condition.unicode) {
								// 将该条的 label 修改为 newVal[0].name, 将 id 修改为 newVal[0].id
								condition.label = newVal[0].name;
								condition.id = newVal[0].id;
								//赋值完成之后清空集合，
								this.form.nodeDeviceList = [];
							}
						});
					} else if(newVal.length > 1){
						this.$message.error("只能选一个，不能多选或少选哦");
					}
				},
				deep: true // 如果 nodeDeviceList 是一个对象或数组，需要设置 deep: true
			}
		},


		mounted() {
			this.nodeConfig = this.modelValue;
		},
		methods: {

			//保存数据
			submit(){
				// eslint-disable-next-line no-debugger
				//遍历，根据unicode判断，如果unicode相同，则将数据设置进去
				this.form.conditionList.forEach(condition => {

					// 找到与当前 condition 的 unicode 匹配的设备
					if (this.unicode === condition.unicode) {
						condition.modeSigns = this.selection;
						condition.modeSignsName = this.selectionModelName;
						this.visible=false
					}
				});
			},



			delUser(index){
				this.form.nodeDeviceList.splice(index, 1)
			},

			show(index){
				this.index = index
				this.form = {}
				this.form = JSON.parse(JSON.stringify(this.nodeConfig.conditionNodes[index]))
				this.drawer = true
			},
			editTitle(){
				this.isEditTitle = true
				this.$nextTick(()=>{
					this.$refs.nodeTitle.focus()
				})
			},
			saveTitle(){
				this.isEditTitle = false
			},
			save(){
				this.nodeConfig.conditionNodes[this.index] = this.form
				this.$emit("update:modelValue", this.nodeConfig)
				this.drawer = false
			},
			addTerm(){

				let len = this.nodeConfig.conditionNodes.length + 1
				this.nodeConfig.conditionNodes.push({
					nodeName: "条件" + len,
					type: 3,
					priorityLevel: len,
					conditionMode: 1,
					conditionList: []
				})
			},
			delTerm(index){
				this.nodeConfig.conditionNodes.splice(index, 1)
				if (this.nodeConfig.conditionNodes.length == 1) {
					if (this.nodeConfig.childNode) {
						if (this.nodeConfig.conditionNodes[0].childNode) {
							this.reData(this.nodeConfig.conditionNodes[0].childNode, this.nodeConfig.childNode)
						}else{
							this.nodeConfig.conditionNodes[0].childNode = this.nodeConfig.childNode
						}
					}
					this.$emit("update:modelValue", this.nodeConfig.conditionNodes[0].childNode);
				}
			},
			reData(data, addData) {
				if (!data.childNode) {
					data.childNode = addData
				} else {
					this.reData(data.childNode, addData)
				}
			},
			arrTransfer(index, type = 1){
				this.nodeConfig.conditionNodes[index] = this.nodeConfig.conditionNodes.splice(index + type, 1, this.nodeConfig.conditionNodes[index])[0]
				this.nodeConfig.conditionNodes.map((item, index) => {
					item.priorityLevel = index + 1
				})
				this.$emit("update:modelValue", this.nodeConfig)
			},

			// todo 通过弹窗或者是下拉框，选择设备的属性信息。
			async selectModel(row) {
				this.loading = true;
				//this.deviceItemId = deviceItemId
				var res = await this.$API.device.deviceItem.getDeviceMode.get({"id": row.id});
				if (res.code === 200) {
					//设置唯一编码
					this.unicode = row.unicode
					this.deviceItemModel = res.data;
					this.visible = true;

				} else {
					this.$alert(res.msg, "提示", {type: 'error'});
				}
				this.loading = false;

			},

			selectHandle(type, data, row){
				//每次选择设备的时候，都需要记录本次点击的条件是哪一个
				this.unicode = row.unicode
				//
				//this.form.nodeDeviceList = [];
				this.select(type,data)
			},
			addConditionList(){
				this.form.conditionList.push({
					unicode:Date.now().toString(),
					id: '',
					label: '',
					modeSigns: '',
					modeSignsName: '',
					operator: '=',
					value: ''
				})
			},
			deleteConditionList(index){
				this.form.conditionList.splice(index, 1)
			},
			toText(nodeConfig, index){
/*				if(nodeConfig.nodeDeviceList){

				}*/
				var { conditionList } = nodeConfig.conditionNodes[index]
				if (conditionList && conditionList.length == 1) {
					const text = conditionList.map(item => `${item.label}${item.operator}${item.value}`).join(" 和 ")
					return text
				}else if(conditionList && conditionList.length > 1){
					const conditionModeText = nodeConfig.conditionNodes[index].conditionMode==1?'与行':'或行'
					return conditionList.length + "个条件，" + conditionModeText
				}else{
					if(index == nodeConfig.conditionNodes.length - 1){
						return "其他条件进入此流程"
					}else{
						return false
					}
				}
			},
			//表格选择后回调事件
			selectionChange(selection){
				console.log(selection)
				if(selection.length>1){
					this.$message.error("单次只能选择一个物模型哦！")
				}else {
					//设置参数
					this.selection = '';
					this.selection = selection[0].modeSigns;
					this.selectionModelName = selection[0].modeName;

				}
			}
		}
	}
</script>

<style>
</style>
