<template>
	 <div class="add-node-btn-box">
	 	<div class="add-node-btn">
			<el-popover placement="right-start" :width="270" trigger="click" :hide-after="0" :show-after="0">
				<template #reference>
					<el-button type="primary" icon="el-icon-plus" circle></el-button>
				</template>
				<div class="add-node-popover-body">
					<ul>
						<li>
							<el-icon style="color: #ff943e;" @click="addType(1)"><el-icon-Setting /></el-icon>
							<p>设备控制</p>
						</li>
						<li>
							<el-icon style="color: #3296fa;" @click="addType(2)"><el-icon-Expand /></el-icon>
							<p>数据流转</p>
						</li>
					</ul>
				</div>
			</el-popover>
	 	</div>
	 </div>
	<div></div>
</template>

<script>
	export default {
		props: {
			modelValue: { type: Object, default: () => {} }
		},
		data() {
			return {

			}
		},
		mounted() {

		},
		methods: {
			addType(type){
				var node = {}
				if (type == 1) {
					node = {
						nodeName: "设备控制",
						type: 1,			//节点类型（1，设备控制节点；2数据流转节点；3：条件路由）
						setType: 1,			//审核人类型
						nodeGroupList: [],	//分组
						nodeDeviceList: [],	//设备
						selectMode: 1,		//多设备时控制执行方式(1:按顺序依次执行;2:不按顺序同步执行)
						termAuto: false,	//期限超时自动审批(true启用，fasle不启用)
						term: 0,			//执行期限（秒）
						termMode: 1,		//期限超时后执行（0：自动通过，1：自动停止）
						childNode: this.modelValue
					}
				}else if(type == 2){
					node = {
						nodeName: "数据流转",
						type: 2,
						pushDataFormat: true,
						pushPlatformInformation: [],
						pushTopic: Date.now().toString(),
						childNode: this.modelValue
					}

				}else if(type == 4){
					node = {
						nodeName: "条件路由",
						type: 4,
						conditionNodes: [
							{
								nodeName: "条件1",
								type: 3,
								priorityLevel: 1,//优先级
								conditionMode: 1,//条件关系，1与，2或
								nodeDeviceList: [],//设备列表，回显使用
								conditionList: []	//条件
							},
							{
								nodeName: "条件2",
								type: 3,
								priorityLevel: 2,//优先级
								conditionMode: 1,//条件关系，1与，2或
								nodeDeviceList: [],//设备列表，回显使用
								conditionList: []	//条件
							}
						],
						childNode: this.modelValue
					}

				}


				this.$emit("update:modelValue", node)
			}
		}
	}
</script>

<style>
</style>
