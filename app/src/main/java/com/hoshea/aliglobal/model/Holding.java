package com.hoshea.aliglobal.model;

/**
 * Holding entity. @author MyEclipse Persistence Tools
 */

public class Holding{

	// Fields

	private Integer cartsgoodsId;
	private Integer number;

	// Constructors

	/** default constructor */
	public Holding() {
	}

	/** full constructor */
	public Holding(Integer number) {

		this.number = number;
	}

	// Property accessors

	public Integer getCartsgoodsId() {
		return this.cartsgoodsId;
	}

	public void setCartsgoodsId(Integer cartsgoodsId) {
		this.cartsgoodsId = cartsgoodsId;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}