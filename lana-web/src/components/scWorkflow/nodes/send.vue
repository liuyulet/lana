<template>
	<div class="node-wrap">
		<div class="node-wrap-box" @click="show">
			<div class="title" style="background: #3296fa;">
				<el-icon class="icon"><el-icon-Expand /></el-icon>
				<span>{{ nodeConfig.nodeName }}</span>
				<el-icon class="close" @click.stop="delNode()"><el-icon-close /></el-icon>
			</div>
			<div class="content">
				<span v-if="toText(nodeConfig)">{{ toText(nodeConfig) }}</span>
				<span v-else class="placeholder">请选择</span>
			</div>
		</div>
		<add-node v-model="nodeConfig.childNode"></add-node>
		<el-drawer title="推送设置" v-model="drawer" destroy-on-close append-to-body :size="700">
			<template #header>
				<div class="node-wrap-drawer__title">
					<label @click="editTitle" v-if="!isEditTitle">{{form.nodeName}}<el-icon class="node-wrap-drawer__title-edit"><el-icon-edit /></el-icon></label>
					<el-input v-if="isEditTitle" ref="nodeTitle" v-model="form.nodeName" clearable @blur="saveTitle" @keyup.enter="saveTitle"></el-input>
				</div>
			</template>
			<el-container>
				<el-main style="padding:0 20px 20px 20px">
					<el-form label-position="top">
<!--						<el-form-item label="控制设备类型">
							<el-select v-model="form.setType">
								<el-option :value="1" label="MQTT"></el-option>
								<el-option :value="2" label="HTTP"></el-option>
								<el-option :value="3" label="kafka(初版不支持)"></el-option>
							</el-select>
							<el-button  icon="el-icon-plus"></el-button>
						</el-form-item>-->
						<el-form-item label="选择要推送的平台信息：">


							<el-button type="primary" icon="el-icon-plus" round @click="selectHandle(3, form.pushPlatformInformation)">选择推送平台信息</el-button>
							<div class="tags-list">
								<el-tag v-for="(user, index) in form.pushPlatformInformation" :key="user.id" closable @close="delUser(index)">{{user.name}}</el-tag>
							</div>
						<el-divider></el-divider>

							<el-form-item label="推送数据类型">
								<el-select v-model="form.sendType">
<!--									<el-option :value=1 label="设备在线状态推送"></el-option>-->
									<el-option :value=1 label="设备预警消息推送"></el-option>
									<el-option :value=2 label="解除预警数据推送"></el-option>
								</el-select>
							</el-form-item>
						<el-divider></el-divider>
						</el-form-item>
						<el-form-item label="查看默认推送数据格式：">
							<el-checkbox v-model="form.pushDataFormat" label="查看默认推送的数据格式"></el-checkbox>
						</el-form-item>

						<el-divider v-if="form.pushDataFormat !=''"></el-divider>
						<el-form-item label="推送的topic主题：" v-if="form.pushDataFormat !=''">
							<el-input  v-model="form.pushTopic" placeholder="设备id"></el-input>
						</el-form-item>
						<el-form-item label="推送的数据内容（初版这里写什么就推送什么，不做任何相关的数据绑定）：" v-if="form.pushDataFormat !=''" >
							<textarea style="color: #e8824e;width: 480px;height: 350px" v-model="form.pushData">
							</textarea>

						</el-form-item>
						<el-divider></el-divider>
					</el-form>
				</el-main>
				<el-footer>
					<el-button type="primary" @click="save">保存</el-button>
					<el-button @click="drawer=false">取消</el-button>
				</el-footer>
			</el-container>
		</el-drawer>
		<div class="add-node-btn-box">
			<div class="add-node-btn">

			</div>
		</div>
	</div>

</template>

<script>

	export default {
		inject: ['select'],
		props: {
			modelValue: { type: Object, default: () => {} }
		},
		components: {
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
				this.form.pushPlatformInformation.splice(index, 1)
			},
			selectHandle(type, data){
				this.select(type, data)
			},
			toText(nodeConfig){
				if (nodeConfig.pushPlatformInformation && nodeConfig.pushPlatformInformation.length>0) {
					const users = nodeConfig.pushPlatformInformation.map(item=>item.name).join("、")
					return users
				}else{
					if(nodeConfig.userSelectFlag){
						return "请选择数据流转方式"
					}else{
						return false
					}

				}
			}
		}
	}
</script>

<style>
</style>
