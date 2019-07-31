package com.indusfo.spc.sdk.common.exception.impl;


import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class SysTipsException extends SpcRuntimeException {

	public SysTipsException(String msg) {

		super(ExceptionCode.SYS_TIP, msg);
	}

	/**描述*/  
	private static final long serialVersionUID = 1L;
}
