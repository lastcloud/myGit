package com.indusfo.spc.sdk.common.exception;

public class ExceptionCode {
	
	/**未登录*/
	public static final int NO_LOGIN = 999;
	/**访问频率限制*/
	public static final int REQUEST_FREQUENCY_LIMIT = 998;
	/**数据库操作失败*/
	public static final int SQL_EXCEPTION = 997;
	/**签名验证不通过*/
	public static final int API_SIGN_EXCEPTION = 994;
	/**数据未找到*/
	public static final int ENTITY_NOTFOUND_EXCEPTION = 993;
	/**无效非法的参数*/
	public static final int INVAILD_PARAM_EXCEPTION = 992;
	/**token验证错误*/
	public static final int TOKEN_ILLEGAL_EXCEPTION = 990;
	/**验证码不正确*/
	public static final int INVALID_VERIFY_CODE = 989;
	/**系统提示*/
	public static final int SYS_TIP = 988;
	/**访问受限制*/
	public static final int VISIT_LIMIT = 987;
	/**系统异常*/
	public static final int SYS = 986;


	/**登陆设备码不正确**/
	public static final int DPC_PRIVATE  = 600;
}
