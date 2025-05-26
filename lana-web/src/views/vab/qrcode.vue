<template>
	<el-main>
		<el-row :gutter="15">
			<el-col :lg="8">
				<el-card shadow="never" header="常用">
					<sc-qr-code text="lana"></sc-qr-code>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never" header="带Logo">
					<sc-qr-code text="lana" logo="img/logo.png"></sc-qr-code>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never" header="自定义颜色大小">
					<sc-qr-code text="lana" :size="100" colorDark="#088200" colorLight="#fff"></sc-qr-code>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never" header="动态">
					<sc-qr-code :text="qrcode"></sc-qr-code>
					<el-input v-model="qrcode" placeholder="Please input"  style="margin-top: 20px;"/>
				</el-card>
			</el-col>
		</el-row>
	</el-main>
</template>

<script>
	export default {
		name: 'qrcode',
		data() {
			return {
				qrcode: "lana"
			}
		},
		mounted() {

		},
		methods: {

		}
	}
</script>

<style>
</style>
