package com.sampark.grocery.model;

public class CustomerViewPage {
	
	private Object daily;
	private Object recent;
	private Object nearestMerchants;
	public Object getNearestMerchants() {
		return nearestMerchants;
	}
	public void setNearestMerchants(Object nearestMerchants) {
		this.nearestMerchants = nearestMerchants;
	}
	public Object getDaily() {
		return daily;
	}
	public void setDaily(Object daily) {
		this.daily = daily;
	}
	public Object getRecent() {
		return recent;
	}
	public void setRecent(Object recent) {
		this.recent = recent;
	}
	
	

}
