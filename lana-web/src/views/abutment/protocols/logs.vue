

<template>
	<el-container>
		<el-main  style="padding:0 20px;">
			<scTable ref="table" :data="data"  stripe>
				<el-table-column label="执行时间" prop="time" width="200"></el-table-column>
				<el-table-column label="执行结果" prop="state" width="100">
					<template #default="scope">
						<span v-if="scope.row.state==200" style="color: #67C23A;"><el-icon><el-icon-success-filled /></el-icon></span>
						<span v-else style="color: #F56C6C;"><el-icon><el-icon-circle-close-filled /></el-icon></span>
					</template>
				</el-table-column>
				<el-table-column label="执行日志" prop="logs" width="100" fixed="right">
					<template #default="scope">
						<el-button size="small" @click="show(scope.row)" type="text">日志</el-button>
					</template>
				</el-table-column>
			</scTable>
		</el-main>
	</el-container>
	<el-drawer title="日志" v-model="logsVisible" :size="800" direction="rtl" destroy-on-close style="width: 800px;">
		<el-main>
			<pre style="font-size: 12px; color: #999; padding: 20px; background: #333; font-family: consolas; line-height: 1.5; overflow: auto; width: 100%;">
				{{ demoLog }}
			</pre>
		</el-main>
	</el-drawer>

</template>

<script>
	export default {
		data() {
			return {
				logsVisible: false,
				demoLog: `2024-09-03 19:32:29.322  INFO 49103 --- [on(5)-127.0.0.1] o.a.c.c.C.[Tomcat].[localhost].[/lana]   :
				Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-09-03 19:32:29.322  INFO 49103 --- [on(5)-127.0.0.1] o.s.web.servlet.DispatcherServlet        :
Initializing Servlet 'dispatcherServlet'
2024-09-03 19:32:29.323  INFO 49103 --- [on(5)-127.0.0.1] o.s.web.servlet.DispatcherServlet        :
 Completed initialization in 1 ms
2024-09-03 19:33:17.496  INFO 49103 --- [nio-8888-exec-1] o.apache.tomcat.util.http.parser.Cookie  :
A cookie header was received [Hm_lvt_b7fd400b2e66412997802961d8148bce=1718327496,1718343807,1718774250,1719367092;] that contained an invalid cookie. That cookie will be ignored.
 Note: further occurrences of this error will be logged at DEBUG level.
2024-09-03 19:34:05.953 DEBUG 49103 --- [nio-8888-exec-4] c.lana.system.dao.SysUserDao.selectList  : ==>
 Preparing: SELECT id,username,password,real_name,avatar,gender,email,mobile,signature,super_admin,status,creator,create_time,updater,update_time,deleted,creator_name,updater_name FROM sys_user WHERE deleted=0 AND (username = ?)
2024-09-03 19:34:05.965 DEBUG 49103 --- [nio-8888-exec-4] c.lana.system.dao.SysUserDao.selectList  : ==>
Parameters: admin(String)
2024-09-03 19:34:05.996 DEBUG 49103 --- [nio-8888-exec-4] c.lana.system.dao.SysUserDao.selectList  : <==
     Total: 1
2024-09-03 19:34:05.999 DEBUG 49103 --- [nio-8888-exec-4] c.l.s.dao.SysMenusDao.getAuthorityList   : ==>
  Preparing: select code from sys_menus where deleted = 0
2024-09-03 19:34:05.999 DEBUG 49103 --- [nio-8888-exec-4] c.l.s.dao.SysMenusDao.getAuthorityList   : ==>
Parameters:
2024-09-03 19:34:06.025 DEBUG 49103 --- [nio-8888-exec-4] c.l.s.dao.SysMenusDao.getAuthorityList   : <==
     Total: 65
2024-09-03 19:34:06.025 DEBUG 49103 --- [nio-8888-exec-4] c.l.s.d.SysRoleDao.getDataScopeByUserId  : ==>
 Preparing: SELECT MIN( role.data_scope ) AS dataScope FROM sys_role role, sys_user_role userrole WHERE role.id = userrole.role_id AND userrole.user_id = ? and role.deleted = 0
2024-09-03 19:34:06.025 DEBUG 49103 --- [nio-8888-exec-4] c.l.s.d.SysRoleDao.getDataScopeByUserId  : ==>
Parameters: 1(Long)`,
				data: [
					{
						time: "2021-07-07 00:00:00",
						state: "500",
						logs: ""
					},
					{
						time: "2021-07-06 00:00:00",
						state: "200",
						logs: ""
					},
					{
						time: "2021-07-05 00:00:00",
						state: "200",
						logs: ""
					},
					{
						time: "2021-07-04 00:00:00",
						state: "200",
						logs: ""
					},
					{
						time: "2021-07-03 00:00:00",
						state: "200",
						logs: ""
					},
					{
						time: "2021-07-02 00:00:00",
						state: "200",
						logs: ""
					},
					{
						time: "2021-07-01 00:00:00",
						state: "200",
						logs: ""
					}
				]
			}
		},
		mounted() {

		},
		methods: {
			show(){
				this.logsVisible = true;
			}
		}
	}
</script>

<style>
</style>
