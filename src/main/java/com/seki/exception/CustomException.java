package com.seki.exception;

public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ϵͳ�Զ����쳣�࣬���Ԥ���쳣����Ҫ�ڳ������׳�������쳣
	 */

	// �쳣��Ϣ
	public String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
