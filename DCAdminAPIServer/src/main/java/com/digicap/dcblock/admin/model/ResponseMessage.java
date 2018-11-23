package com.digicap.dcblock.admin.model;

public class ResponseMessage {

	private int code;
	private String reason;

	public ResponseMessage() {
		
	}

	public ResponseMessage(int code, String reason) {
		super();
		this.code = code;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", reason=" + reason + "]";
	}

}
