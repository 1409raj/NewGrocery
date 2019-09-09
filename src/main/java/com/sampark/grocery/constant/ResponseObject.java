package com.sampark.grocery.constant;

public class ResponseObject {

	private Object object;
	private Boolean hasError;
	private String message;
	private Integer quantity;
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Boolean getHasError() {
		return hasError;
	}
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ResponseObject [object=" + object + ", hasError=" + hasError + ", message=" + message + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
