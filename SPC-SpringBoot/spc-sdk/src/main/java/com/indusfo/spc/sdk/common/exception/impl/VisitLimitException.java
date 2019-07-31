package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class VisitLimitException extends SpcRuntimeException {

	private static final long serialVersionUID = -6250671812198424173L;

	public VisitLimitException() {
		super(ExceptionCode.INVAILD_PARAM_EXCEPTION, "访问受限制");
	}
	
	public VisitLimitException(String msg) {
		super(ExceptionCode.INVAILD_PARAM_EXCEPTION, msg);
	}

}
