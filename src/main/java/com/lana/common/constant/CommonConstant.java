package com.lana.common.constant;

public interface CommonConstant {
    /**
     * 微信、支付宝、短信，后面有精力了在搞
     */

	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    
    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;



    /**
     * 微信、
     */
    String WECHATAPPID="";
    String WECHATAPPSECRET="";



    /**
     * 微信公众号密钥配置
     * 正式环境
     *     String SERVICENUMBERAPPID="";
     *     String SERVICENUMBERSECRET="";
     */
    /** 测试环境
    String SERVICENUMBERAPPID="";
    String SERVICENUMBERSECRET="";*/


    String SERVICENUMBERAPPID="";
    String SERVICENUMBERSECRET="";

    /**回调地址*/
    String WECHATAPPREBACK="";

    /**
     * 支付宝配置
     */
    String ZHIFUBAOAPPID="";
    /**支付宝公钥*/
    String ZHIFUBAOPUNLICKEYP="";
    /**支付宝私钥*/
    String ZHIFUBAOPRIVATEKEY="";
    /**回调地址*/
    String ZHIFUBAOAPPREBACK="";

}
