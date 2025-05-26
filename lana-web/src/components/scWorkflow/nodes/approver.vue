<template>
	<div class="node-wrap">
		<div class="node-wrap-box" @click="show">
			<div class="title" style="background: #ff943e;">
				<el-icon class="icon"><el-icon-Setting /></el-icon>
				<span>{{ nodeConfig.nodeName }}</span>
				<el-icon class="close" @click.stop="delNode()"><el-icon-close /></el-icon>
			</div>
			<div class="content">
				<span v-if="toText(nodeConfig)">{{ toText(nodeConfig) }}</span>
				<span v-else class="placeholder">请选择</span>
			</div>
		</div>
		<add-node v-model="nodeConfig.childNode"></add-node>
		<el-drawer title="设置" v-model="drawer" destroy-on-close append-to-body :size="700">
			<template #header>
				<div class="node-wrap-drawer__title">
					<label @click="editTitle" v-if="!isEditTitle">{{form.nodeName}}<el-icon class="node-wrap-drawer__title-edit"><el-icon-edit /></el-icon></label>
					<el-input v-if="isEditTitle" ref="nodeTitle" v-model="form.nodeName" clearable @blur="saveTitle" @keyup.enter="saveTitle"></el-input>
				</div>
			</template>
			<el-container>
				<el-main style="padding:0 20px 20px 20px">
					<el-form label-position="top">

						<el-form-item label="控制设备类型">
							<el-select v-model="form.setType" @change="changNodeList">
								<el-option :value="1" label="指定设备"></el-option>
<!--								<el-option :value="2" label="指定分组"></el-option>-->
							</el-select>
						</el-form-item>
						<el-form-item v-if="form.setType==1" label="选择设备">
							<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(1, form.nodeDeviceList)">选择设备</el-button>
							<div class="tags-list">
								<el-tag v-for="(user, index) in form.nodeDeviceList" :key="user.id" closable @close="delUser(index)">{{user.name}}</el-tag>
							</div>
						</el-form-item>

						<el-form-item v-if="form.setType==2" label="选择分组">
							<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(2, form.nodeGroupList)">选择分组</el-button>
							<div class="tags-list">
								<el-tag v-for="(role, index) in form.nodeGroupList" :key="role.id" type="info" closable @close="delRole(index)">{{role.name}}</el-tag>
							</div>
						</el-form-item>

						<el-divider></el-divider>
						<el-form-item label="控制执行动作">
							<el-select v-model="form.actionType">
<!--								<el-option :value=1 label="控制已选设备开"></el-option>
								<el-option :value=2 label="控制已选设备关"></el-option>-->
								<el-option :value=3  v-if="form.setType!=2" label="自定义控制设备"></el-option>
							</el-select>
						</el-form-item>
						<el-table :data="deviceListMode" v-if="form.actionType == 3" stripe  v-loading="loading">
							<el-table-column label="#" type="index" width="50"></el-table-column>
							<el-table-column label="设备主键" v-if="false" width="170">
								<template #default="scope">
									<el-input  v-model="scope.row.id" placeholder="设备id"></el-input>
								</template>
							</el-table-column>
							<el-table-column label="设备名称"  width="150">
								<template #default="scope">
									<span >{{scope.row.name}}</span>
								</template>
							</el-table-column>
							<el-table-column label="功能类型"  width="140">
								<template #default="scope">
									<el-select v-if="scope.row.isSet"  v-model="scope.row.controlType" @change="getDeviceAbility(scope.row)" placeholder="请选择" >
										<el-option v-for="item in controlTypeDic" :key="item.value" :label="item.label" :value="item.value"></el-option>
									</el-select>
									<span v-else>{{scope.row.controlDataTypeLabel}}</span>
								</template>
							</el-table-column>
							<el-table-column label="操作功能"  width="140"  @change="setControlAbilityLabel(scope.row)">

								<template #default="scope">
									<el-select v-if="scope.row.isSet" v-model="scope.row.controlAbility" @change="getDeviceAbilityLabel(scope.row)" placeholder="请选择" >
										<el-option v-for="item in DeviceAbility"  :key="item.value" :label="item.label" :value="item.value"></el-option>
									</el-select>
									<span v-else>{{scope.row.controlDeviceAbilityLabel}}</span>
								</template>
							</el-table-column>

							<el-table-column min-width="1"></el-table-column>

							<el-table-column label="操作" fixed="right" width="150">
								<template #default="scope">
									<el-button-group>
										<el-button @click="table_edit(scope.row)" text type="primary" size="small">{{scope.row.isSet?'保存':"修改"}}</el-button>
										<el-button v-if="scope.row.isSet" @click=cancels(scope.row) text type="primary" size="small">取消</el-button>
									</el-button-group>
								</template>
							</el-table-column>
						</el-table>

						<el-divider></el-divider>
						<el-form-item label="多设备时控制执行方式">
							<el-radio-group v-model="form.examineMode">
<!--								<p style="width: 100%;"><el-radio :label="1">按顺序依次执行</el-radio></p>-->
								<p style="width: 100%;"><el-radio :label="2">默认网络分发</el-radio></p>
							</el-radio-group>
						</el-form-item>

						<el-divider></el-divider>
						<el-form-item label="" v-if="form.examineMode==1">
							<el-checkbox v-model="form.termAuto" label="超时自动处理（A设备异常，则跳过A,自动处理B设备）"></el-checkbox>
						</el-form-item>
						<template v-if="form.termAuto && form.examineMode==1" >
							<el-form-item label="执行期限（为0则不生效）">
								<el-input-number v-model="form.term" :min="0"/> 秒
							</el-form-item>
							<el-form-item label="期限超时后执行">
								<el-radio-group v-model="form.termMode">
									<el-radio :label="0">自动通过</el-radio>
									<el-radio :label="1">自动停止</el-radio>
								</el-radio-group>
							</el-form-item>
						</template>

					</el-form>
				</el-main>
				<el-footer>
					<el-button type="primary" @click="save">保存</el-button>
					<el-button @click="drawer=false">取消</el-button>
				</el-footer>
			</el-container>
		</el-drawer>
	</div>
</template>

<script>
	import addNode from './approverAddNode'

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
				nodeConfig: {},
				drawer: false,
				isEditTitle: false,
				form: {},
				deviceListMode: [],
				controlTypeDic: [
					{
						label: "控制功能",
						value: 1
					},
					{
						label: "采集功能",
						value: 2
					}
				],
				DeviceAbility: []
			}
		},
		watch:{
			modelValue(){
				this.nodeConfig = this.modelValue
			},
			'form.nodeDeviceList': {
				handler(newVal) {
					//如果设备列表为空，则直接赋值，否则过滤掉已存在的设备
					if(this.deviceListMode.length<1){
						this.deviceListMode = newVal;
					}else {
						if(newVal.length<this.deviceListMode.length){
							const filteredDeviceList = this.deviceListMode.filter(device => newVal.some(val => val.id === device.id));
							this.deviceListMode = filteredDeviceList;
						}else if(newVal.length>this.deviceListMode.length){
							const addDeviceList = this.deviceListMode.filter(device => newVal.some(val => val.id != device.id));
							this.deviceListMode.push(addDeviceList);
						}

					}

				},
				deep: true // 如果 nodeDeviceList 是一个对象或数组，需要设置 deep: true
			}
		},
		mounted() {
			this.nodeConfig = this.modelValue;
		},
		methods: {
			changNodeList(){
				if(this.form.setType == 2){
					this.form.nodeDeviceList = []
				}else {
					this.form.nodeGroupList = []
				}
			},
			show(){
				this.form = {}
				this.form = JSON.parse(JSON.stringify(this.nodeConfig))
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

				this.$emit("update:modelValue", this.form)
				this.drawer = false
			},
			delNode(){
				this.$emit("update:modelValue", this.nodeConfig.childNode)
			},
			delUser(index){
				this.form.nodeDeviceList.splice(index, 1)
			},
			delRole(index){
				this.form.nodeGroupList.splice(index, 1)
			},
			selectHandle(type, data){
				this.select(type, data)
			},
			toText(nodeConfig){
				if(nodeConfig.setType == 1){
					if (nodeConfig.nodeDeviceList && nodeConfig.nodeDeviceList.length>0) {
						const users = nodeConfig.nodeDeviceList.map(item=>item.name).join("、")
						return users
					}else{
						return false
					}
				}else if (nodeConfig.setType == 2) {
					if (nodeConfig.nodeGroupList && nodeConfig.nodeGroupList.length>0) {
						const roles = nodeConfig.nodeGroupList.map(item=>item.name).join("、")
						return '分组-' + roles
					}else{
						return false
					}
				}
			},
			table_edit(row){
				if(row.isSet){
					row.isSet = false
				}else{
					row.isSet = true
				}
			},
			cancels(row){
					row.isSet = false
			},
			//获取功能列表
			async getDeviceAbility(row) {
				row.controlDataTypeLabel = this.controlTypeDic.filter(item => item.value === row.controlType).map(item => item.label)
				var res = await this.$API.device.deviceAbility.list.get({ "deviceItemId": row.id });
				if (res.code === 200) {
					this.DeviceAbility = res.data
						.filter(item => item.controlType === row.controlType) // 过滤出 controlType 等于 row.controlType 的项
						.map(item => ({
							label: item.controlName,
							value: item.id
						}));
				} else {
					this.$alert(res.msg, "提示", { type: 'error' });
				}
			},
			getDeviceAbilityLabel(row){
				console.log(row)
				row.controlDeviceAbilityLabel = this.DeviceAbility.filter(item => item.value === row.controlAbility).map(item => item.label)


			}

		}
	}
</script>

<style>

</style>
