package com.indusfo.spc.sdk.common.exception.impl;

import com.indusfo.spc.sdk.common.exception.ExceptionCode;
import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;

public class EntityNotFoundException extends SpcRuntimeException {

	private static final long serialVersionUID = 386049259703755188L;

	final static String TIPS = "找不到实体";

	public EntityNotFoundException() {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, TIPS);
	}
	
	public EntityNotFoundException(Integer id) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, TIPS, "根据主键" + id + "找不到实体");
	}
	
	public EntityNotFoundException(Long id) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, TIPS,"根据主键" + id + "找不到实体");
	}
	
	public EntityNotFoundException(String id) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, TIPS,"根据主键" + id + "找不到实体");
	}
	
	
	public EntityNotFoundException(String msg, Long obj) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, msg, "根据主键" + obj + "找不到实体");
	}
	
	public EntityNotFoundException(String msg, Integer obj) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, msg, "根据主键" + obj + "找不到实体");
	}
	
	public EntityNotFoundException(String msg, String obj) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, msg, "根据主键" + obj + "找不到实体");
	}

	public EntityNotFoundException(Object obj) {
		super(ExceptionCode.ENTITY_NOTFOUND_EXCEPTION, "找不到实体", "根据主键" + obj + "找不到实体");
	}

}
