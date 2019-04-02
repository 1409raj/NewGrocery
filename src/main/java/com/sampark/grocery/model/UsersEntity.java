package com.sampark.grocery.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="users")
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="phone1")
	private String phone1;
	@Column(name="phone2")
	private String phone2;
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="store_name")
	private String storename;
	
	@Column(name="passwd")
	private String passwd;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="address_line_1")
	private String addressLine1;
	@Column(name="address_line_2")
	private String addressLine2;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="pincode")
	private String pincode;
	@Column(name="lat")
	private Double lat;
	@Column(name="lng")
	private Double lng;
	
	@Column(name="sponsorby")
	private String sponsorby;
	
	@Column(name="startshoptime")
	private String startshoptime;
	
	@Column(name="endshoptime")
	private String endshoptime;
	
	@Column(name="image_name")
	private String imagename;
	
	@Column(name="address_proof_type")
	private String addressProofType;
	@Column(name="address_proof_location")
	private String addressProofLocation;
	@Column(name="id_proof_type")
	private String idProofType;
	@Column(name="id_proof_location")
	private String idProofLocation;
	@Column(name="gst_no")
	private String gstNo;
	
	@Column(name="token")
	private String token;
	@Column(name="created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	@Column(name="updated_at")
	@CreationTimestamp
	private Timestamp updatedAt;
	
	@Transient
	private String AuthToken;
	
	@Transient
	private String imagepath;
	
	@Transient
	private String authToken;
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name="role_id",referencedColumnName="role_id",insertable=false,updatable=false)
	private RolesEntity roleEntity;
	
	@Transient
	private ProductPriceEntity priceEntity;
	
	
	public ProductPriceEntity getPriceEntity() {
		return priceEntity;
	}

	public void setPriceEntity(ProductPriceEntity priceEntity) {
		this.priceEntity = priceEntity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getSponsorby() {
		return sponsorby;
	}

	public void setSponsorby(String sponsorby) {
		this.sponsorby = sponsorby;
	}

	public String getStartshoptime() {
		return startshoptime;
	}

	public void setStartshoptime(String startshoptime) {
		this.startshoptime = startshoptime;
	}

	public String getEndshoptime() {
		return endshoptime;
	}

	public void setEndshoptime(String endshoptime) {
		this.endshoptime = endshoptime;
	}

	public String getAddressProofType() {
		return addressProofType;
	}

	public void setAddressProofType(String addressProofType) {
		this.addressProofType = addressProofType;
	}

	public String getAddressProofLocation() {
		return addressProofLocation;
	}

	public void setAddressProofLocation(String addressProofLocation) {
		this.addressProofLocation = addressProofLocation;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProofLocation() {
		return idProofLocation;
	}

	public void setIdProofLocation(String idProofLocation) {
		this.idProofLocation = idProofLocation;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public RolesEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RolesEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	

	@Override
	public String toString() {
		return "UsersEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone1="
				+ phone1 + ", phone2=" + phone2 + ", emailId=" + emailId + ", storename=" + storename + ", passwd="
				+ passwd + ", roleId=" + roleId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", lat=" + lat + ", lng=" + lng
				+ ", sponsorby=" + sponsorby + ", startshoptime=" + startshoptime + ", endshoptime=" + endshoptime
				+ ", imagename=" + imagename + ", addressProofType=" + addressProofType + ", addressProofLocation="
				+ addressProofLocation + ", idProofType=" + idProofType + ", idProofLocation=" + idProofLocation
				+ ", gstNo=" + gstNo + ", token=" + token + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", imagepath=" + imagepath + ", authToken=" + authToken + ", roleEntity=" + roleEntity
				+ ", priceEntity=" + priceEntity + "]";
	}

	
}