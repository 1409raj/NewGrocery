package com.sampark.grocery.model;

import java.util.List;

public class Domain<K>{
	private K object;
	private Boolean hasError;
	private String message;
	private Integer id;
	private List<String> value;
	
	public K getObject() {
		return object;
	}

	public void setObject(K object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	
}
