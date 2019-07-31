package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class SysException extends SpcRuntimeException {

	private static final long serialVersionUID = -6939930889768719567L;

	public SysException(String msg) {
		super(ExceptionCode.SYS_TIP, "系统异常", msg);
	}

}
