package com.sampark.grocery.util;

public class BeanModel {
	private String name;
	private String dataType;
	public BeanModel(String name, String dataType) {
		super();
		this.name = name;
		this.dataType = dataType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
}
