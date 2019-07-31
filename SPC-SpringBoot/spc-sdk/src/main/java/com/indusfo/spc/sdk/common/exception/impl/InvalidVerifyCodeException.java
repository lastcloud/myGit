package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class InvalidVerifyCodeException extends SpcRuntimeException {

	private static final long serialVersionUID = -2071032196508491318L;

	public InvalidVerifyCodeException() {
		super(ExceptionCode.INVALID_VERIFY_CODE, "验证码不正确");
	}

}
