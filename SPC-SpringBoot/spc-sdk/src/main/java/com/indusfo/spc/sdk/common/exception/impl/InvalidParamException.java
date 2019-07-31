package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class InvalidParamException extends SpcRuntimeException {

	private static final long serialVersionUID = 4741470773171550547L;

	public InvalidParamException() {
		super(ExceptionCode.INVAILD_PARAM_EXCEPTION, "非法参数");
	}
	
	public InvalidParamException(String msg) {
		super(ExceptionCode.INVAILD_PARAM_EXCEPTION, msg);
	}
	
	public InvalidParamException(String msg, String detail) {
		super(ExceptionCode.INVAILD_PARAM_EXCEPTION, msg, detail);
	}

}
