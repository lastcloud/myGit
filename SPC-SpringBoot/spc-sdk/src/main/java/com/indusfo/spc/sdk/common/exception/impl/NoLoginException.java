package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class NoLoginException extends SpcRuntimeException {

	private static final long serialVersionUID = 7930795304461255493L;

	public NoLoginException() {
		super(999, "请先登录");
	}

}
