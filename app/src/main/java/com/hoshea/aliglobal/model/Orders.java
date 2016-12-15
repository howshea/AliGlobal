package com.hoshea.aliglobal.model;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private String createTime;
	private String state;
	private Float total;
	private String receivePersonName;
	private String receivePhone;
	private String receiveAddress;

	// Constructors

	/** default constructor */
	public Orders() {
	}


	/** full constructor */
	public Orders(String createTime, String state, Float total,
			String receivePersonName, String receivePhone,
			String receiveAddress) {
		this.createTime = createTime;
		this.state = state;
		this.total = total;
		this.receivePersonName = receivePersonName;
		this.receivePhone = receivePhone;
		this.receiveAddress = receiveAddress;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getReceivePersonName() {
		return this.receivePersonName;
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	public String getReceivePhone() {
		return this.receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getReceiveAddress() {
		return this.receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}



}