<template>
	<div class="node-wrap">
		<div class="node-wrap-box start-node" @click="show">
			<div class="title" style="background: #576a95;">
				<el-icon class="icon"><el-icon-User /></el-icon>
				<span>{{ nodeConfig.nodeName }}</span>
			</div>
			<div class="content">
				<span>{{ toText(nodeConfig) }}</span>
			</div>
		</div>
		<add-node v-model="nodeConfig.childNode"></add-node>
		<el-drawer title="开始" v-model="drawer" destroy-on-close append-to-body :size="700">
			<template #header>
				<div class="node-wrap-drawer__title">
					<label @click="editTitle" v-if="!isEditTitle">{{form.nodeName}}<el-icon class="node-wrap-drawer__title-edit"><el-icon-edit /></el-icon></label>
					<el-input v-if="isEditTitle" ref="nodeTitle" v-model="form.nodeName" clearable @blur="saveTitle" @keyup.enter="saveTitle"></el-input>
				</div>
			</template>
			<el-container>
				<el-main style="padding:0 20px 20px 20px">
					<el-form label-position="top">

						<el-form-item label="规则触发类型">
							<el-select v-model="form.setType" @change="changNodeDeviceList">
								<el-option :value=1 label="设备监听"></el-option>
								<el-option :value=2 label="定时任务"></el-option>
							</el-select>
						</el-form-item>

						<el-form-item label="请选择要监听的设备" v-if="form.setType==1">

							<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(1, form.nodeDeviceList)">选择设备</el-button>
							<div class="tags-list">
								<el-tag v-for="(role, index) in form.nodeDeviceList" :key="role.id" type="info" closable @close="delRole(index)">{{role.name}}</el-tag>
							</div>
							<el-form-item label="（注意，监听哪些设备，则条件中也只能选择监听的设备。需要人为的甄别，不然动作无效）"></el-form-item>
							<el-alert v-if="form.nodeDeviceList.length==0" title="不选择则默认为匹配所有的设备，这样可能会导致无法精准的处理规则。非必要情况请不要这么做，因为这样会非常影响服务性能，设备超级多的情况下可能会直接尥蹶子哦！！！" type="info" :closable="false"/>
						</el-form-item>

						<el-card shadow="never" header="请设置定时信息" v-if="form.setType==2">
							<sc-cron v-model="form.cron" placeholder="请输入Cron定时规则" clearable :shortcuts="shortcuts"></sc-cron>
							<el-alert v-if="form.cron == undefined" title="定时情况中，定时规则不填则不会触发" type="info" style="margin-top: 10px" :closable="false"/>
						</el-card>
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
	import addNode from './addNode'
	import scCron from "@/components/scCron/index.vue";
	import Space from "@/views/userCenter/user/space.vue";

	export default {
		inject: ['select'],
		props: {
			modelValue: { type: Object, default: () => {} }
		},
		components: {
			Space,
			scCron,
			addNode
		},
		data() {
			return {
				nodeConfig: {},
				drawer: false,
				isEditTitle: false,
				form: {}
			}
		},
		watch:{
			modelValue(){
				this.nodeConfig = this.modelValue
			}
		},
		mounted() {
			this.nodeConfig = this.modelValue
		},
		methods: {
			changNodeDeviceList(){
				if(this.form.setType == 2){
					this.form.nodeDeviceList = []
				}else {
					this.form.cron = ''
				}

			},
			show(){
				this.form = {}
				this.form = JSON.parse(JSON.stringify(this.nodeConfig))
				this.isEditTitle = false
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
			selectHandle(type, data){
				this.select(type, data)
			},
			delRole(index){
				this.form.nodeDeviceList.splice(index, 1)
			},
			save(){
				this.$emit("update:modelValue", this.form)
				this.drawer = false
			},
			toText(nodeConfig){
				if(nodeConfig.setType==2){
					return nodeConfig.cron
				}else {
					if(nodeConfig.nodeDeviceList && nodeConfig.nodeDeviceList.length > 0){
						return nodeConfig.nodeDeviceList.map(item=>item.name).join("、")
					}else{
						return "所有设备"
					}
				}
			}
		}
	}
</script>

<style>
</style>
