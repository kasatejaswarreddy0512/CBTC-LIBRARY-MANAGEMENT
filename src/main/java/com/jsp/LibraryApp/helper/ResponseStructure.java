package com.jsp.LibraryApp.helper;

import java.util.List;

public class ResponseStructure<T> {
	
	private int statusCode;
	private T data;
	
	private String message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public void setData(List<T> datas) {
		for(T d:datas) {
			setData(d);	
		}
	}
	

}
