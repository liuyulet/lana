<template>
	<el-card shadow="hover" header="欢迎">
		<div class="welcome">
			<div class="logo">
				<img src="img/logo.png">
				<h2>欢迎体验 lana</h2>
			</div>
			<div class="tips">
				<div class="tips-item">
					<div class="tips-item-icon"><el-icon><el-icon-menu/></el-icon></div>
					<div class="tips-item-message">这里是项目控制台，你可以点击右上方的“自定义”按钮来添加移除或者移动部件。</div>
				</div>
				<div class="tips-item">
					<div class="tips-item-icon"><el-icon><el-icon-promotion/></el-icon></div>
					<div class="tips-item-message">本项目前端基于SCUI进行修改开发，后端权限设计有一部分是按照前端要求的数据结构进行设计</div>
				</div>
				<div class="tips-item">
					<div class="tips-item-icon"><el-icon><el-icon-milk-tea/></el-icon></div>
					<div class="tips-item-message">项目目的：第一版本是提供一个基础的前后端分离的项目，后期会将其改造为物联网平台与办公平台</div>
				</div>
			</div>
			<div class="actions">
				<el-button type="primary" icon="el-icon-check" size="large" @click="godoc">文档编写中...</el-button>
			</div>
		</div>
	</el-card>
</template>

<script>
	export default {
		title: "欢迎",
		icon: "el-icon-present",
		description: "项目特色以及文档链接",
		data() {
			return {

			}
		},
		methods: {
			godoc(){
				// todo 项目完成第一版之后要编写项目文档
				/*window.open("https://lolicode.gitee.io/scui-doc/")*/
			}
		}
	}
</script>

<style scoped>
	.welcome {}
	.welcome .logo {text-align: center;}
	.welcome .logo img {vertical-align: bottom;width: 100px;height: 100px;margin-bottom: 20px;}
	.welcome .logo h2 {font-size: 30px;font-weight: normal;display: flex;align-items: center;justify-content: center;}

	.tips {margin-top: 20px;padding:0 40px;}
	.tips-item {display: flex;align-items: center;justify-content: center;padding:7.5px 0;}
	.tips-item-icon {width: 40px;height:40px;display: flex;align-items: center;justify-content: center;border-radius: 50%;font-size: 18px;margin-right: 20px;color: var(--el-color-primary);background: rgba(180,180,180,0.1);}
	.tips-item-message {flex: 1;font-size: 14px;}

	.actions {text-align: center;margin: 40px 0 20px 0;}
</style>
