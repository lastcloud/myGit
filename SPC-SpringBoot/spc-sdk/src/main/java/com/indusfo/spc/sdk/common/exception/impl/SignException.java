package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class SignException extends SpcRuntimeException {

	private static final long serialVersionUID = -5742063523171517693L;

	public SignException() {
		super(ExceptionCode.API_SIGN_EXCEPTION, "验签失败.");
	}
	public SignException(String msg) {
		super(ExceptionCode.API_SIGN_EXCEPTION, msg);
	}

}
