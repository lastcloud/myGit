package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class TokenIllegalException extends SpcRuntimeException {

	private static final long serialVersionUID = 1774283263830169522L;

	public TokenIllegalException() {
		super(ExceptionCode.TOKEN_ILLEGAL_EXCEPTION, "token验证非法.");
	}

	public TokenIllegalException(String detail) {
		super(ExceptionCode.TOKEN_ILLEGAL_EXCEPTION, "token验证非法.", detail);
	}

}
