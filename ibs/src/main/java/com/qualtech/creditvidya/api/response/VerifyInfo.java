package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_CREDIT_VRFY_VERIFY_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long eid;
	
	private String emailBounced;
	private String connectionType;
	private String deviceType;
	private String deviceBrand;
	private String deviceModel;
	private String operatingSystem;
	private String screenSize;
	private String browserName;
	private String deviceOnCharge;
	private String percentageBatteryLeft;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	private String isp;
	private String subscriberName;
	private String locationAccepted;
	private String addressSource;
	private String uniqueid;
	
	@JsonIgnore
	private String sid;
	
	
	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_VRFY_INFO_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_VRFY_INFO_SQC",sequenceName="IB_CREDIT_VRFY_VRFY_INFO_SQC",allocationSize=1)
	@Column(name="EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}

	@Column
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name="EMAIL_BOUNCED")
	public String getEmailBounced() {
		return emailBounced;
	}
	public void setEmailBounced(String emailBounced) {
		this.emailBounced = emailBounced;
	}
	@Column(name="CONNECTION_TYPE")
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	@Column(name="DEVICE_TYPE")
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name="DEVICE_BRAND")
	public String getDeviceBrand() {
		return deviceBrand;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	@Column(name="DEVICE_MODEL")
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	@Column(name="OS")
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	@Column(name="SCREEN_SIZE")
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	@Column(name="BROWSER_NAME")
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	@Column(name="DEVICE_ONCHARGE")
	public String getDeviceOnCharge() {
		return deviceOnCharge;
	}
	public void setDeviceOnCharge(String deviceOnCharge) {
		this.deviceOnCharge = deviceOnCharge;
	}
	@Column(name="PERCENT_BATTERY_LEFT")
	public String getPercentageBatteryLeft() {
		return percentageBatteryLeft;
	}
	public void setPercentageBatteryLeft(String percentageBatteryLeft) {
		this.percentageBatteryLeft = percentageBatteryLeft;
	}
	@Column(name="ADDRESS_LINE1")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	@Column(name="ADDRESS_LINE2")
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="PINCODE")
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Column(name="ISP")
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	@Column(name="SUBSCRIBER_NAME")
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	@Column(name="LOCATION_ACCEPTED")
	public String getLocationAccepted() {
		return locationAccepted;
	}
	public void setLocationAccepted(String locationAccepted) {
		this.locationAccepted = locationAccepted;
	}
	@Column(name="ADDRESS_SOURCE")
	public String getAddressSource() {
		return addressSource;
	}
	public void setAddressSource(String addressSource) {
		this.addressSource = addressSource;
	}
   
	@Override
	public String toString() {
		return "VerifyInfo [emailBounced=" + emailBounced + ", connectionType=" + connectionType + ", deviceType="
				+ deviceType + ", deviceBrand=" + deviceBrand + ", deviceModel=" + deviceModel + ", operatingSystem="
				+ operatingSystem + ", screenSize=" + screenSize + ", browserName=" + browserName + ", deviceOnCharge="
				+ deviceOnCharge + ", percentageBatteryLeft=" + percentageBatteryLeft + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", isp=" + isp + ", subscriberName=" + subscriberName + ", locationAccepted=" + locationAccepted
				+ ", addressSource=" + addressSource + "]";
	}
	
}
