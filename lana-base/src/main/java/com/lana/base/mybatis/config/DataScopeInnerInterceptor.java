package com.lana.base.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.security.token.user.UserDetail;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 数据范围内部拦截器
 * @auther liuyulet
 * @date 2024/3/16 14:20
 */
public class DataScopeInnerInterceptor implements InnerInterceptor {

    /**
     * 在查询之前执行操作，根据数据权限范围动态修改SQL。
     *
     * @param executor 执行器，用于执行SQL。
     * @param ms 映射语句，描述了如何从数据库中获取数据。
     * @param parameter 查询参数。
     * @param rowBounds 行限制，用于指定查询的起始行和行数。
     * @param resultHandler 结果处理器，用于处理查询结果。
     * @param boundSql 原始SQL对象，包含查询SQL和参数信息。
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        //boolean ss = judgementDataScopeIgnore(ms);
        if (!(parameter instanceof Map)) {
            return;
        }
        Map<?, ?> paramMap = (Map<?, ?>) parameter;
        if (!paramMap.containsKey("DataScopeIgnore")) {
            return;
        }

        Object dataScopeIgnore = paramMap.get("DataScopeIgnore");
        if (!(dataScopeIgnore instanceof Boolean) || !(Boolean) dataScopeIgnore) {
            return;
        }

        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == 1) {
            return;
        }

        List<Long> dataScopeList = user.getDataScopeList();
        if (dataScopeList == null) {
            return;
        }

        String scope = getDataScope(paramMap);
        if (scope == null || StrUtil.isBlank(scope)) {
            return;
        }

        String buildSql = getSelectSql(boundSql.getSql(), scope);
        PluginUtils.mpBoundSql(boundSql).sql(buildSql);

    }


    /**
     * 根据参数获取数据范围。
     * 这个方法用于根据当前登录用户的权限设置，构造SQL查询时的数据范围条件。
     * 数据范围由用户的角色决定，角色不同，能够查询的数据也不同。
     *
     * @param parameter 方法参数，当前未使用，保留以待未来可能的需求变化。
     * @return DataScope对象，包含构造的SQL过滤条件。如果用户没有数据权限，则返回null。
     */
    private String getDataScope(Map<?, ?> parameter) {
        // 获取当前登录的用户详情
        UserDetail user = SecurityUser.getUser();
        // 数据权限范围（这里面都是用户id）
        List<Long> dataScopeList = user.getDataScopeList();

        // 全部数据权限
        if (dataScopeList == null || dataScopeList.isEmpty()) {
            return null;
        }

        // 初始化SQL过滤条件的StringBuilder
        StringBuilder sqlFilter = new StringBuilder(" creator in (");
        sqlFilter.append(StrUtil.join(",", dataScopeList));
        sqlFilter.append(")");

        // 返回构建好的数据范围对象
        return sqlFilter.toString();
    }


    /**
     *  处理业务相关的sql
     * @param buildSql
     * @param scope
     * @return
     */
    private String getSelectSql(String buildSql, String scope) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(buildSql);
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            // 获取别名
            String tableName = getTableName(plainSelect);
            return appendConditionToSql(plainSelect, scope, tableName);
        } catch (ValidationException | JSQLParserException e) {
            System.out.println("Failed to parse or modify SQL: " + buildSql + "；报错信息：" + e.getMessage());
            return buildSql; // 保持原方法的异常处理逻辑
        }
    }

    /**
     * 获取表名/别名
     * @param plainSelect plainSelect
     * @return 表名/别名
     */
    public String getTableName(PlainSelect plainSelect) {
        // 获取别名(如果是关联查询是取第一个join左侧的表名/别名)
        Table table = (Table) plainSelect.getFromItem();
        Alias alias = table.getAlias();
        return alias != null ? alias.getName() : table.getName();
    }


    private String appendConditionToSql(PlainSelect plainSelect, String filter,String tableName) {
        Expression expression = plainSelect.getWhere();
        filter = tableName+"."+filter;
        if (expression == null) {
            plainSelect.setWhere(new StringValue(validateAndSanitize(filter)));
        } else {
            Expression sanitizedFilter = new StringValue(validateAndSanitize(filter));
            AndExpression andExpression = new AndExpression(expression, sanitizedFilter);
            plainSelect.setWhere(andExpression);
        }
        return plainSelect.toString().replaceAll("'", ""); // 更安全的处理单引号
    }

    private String validateAndSanitize(String input) {
        // 实现具体的验证和清洗逻辑
        // 例如，可以移除或转义危险的SQL操作符和特殊字符
        // 注意：这里的实现应根据实际的业务需求和安全策略来定
        return input;
    }

}
