package com.indusfo.spc.sdk.common.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SpcRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 7285694384926860231L;

    private int code;
    private String msg;
    private String detailMsg;

    public SpcRuntimeException(int code, String msg) {
	    this(code, msg, msg);
    }

    public SpcRuntimeException(int code, String msg, String detailMsg) {
	super(msg);
	this.code = code;
	this.msg = msg;
	this.detailMsg = detailMsg;
    }

    public int getCode() {
	return code;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public String getDetailMsg() {
	return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
	this.detailMsg = detailMsg;
    }

    @Override
    public String toString() {

        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);

        builder.append("[code: ", this.code);
        builder.append(", msg: ", this.msg);
        builder.append(", detail: ", this.detailMsg);
        builder.append("]");

        return builder.toString();
    }
}
