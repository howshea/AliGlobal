package com.hoshea.aliglobal.model;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods {

	// Fields

	private Integer goodsId;
	private Integer stock;
	private String goodsName;
	private Float price;
	private String category;
	private String titlePicture;
	private String detailPicture;

	@Override
	public String toString() {
		return "Goods{" +
				"category='" + category + '\'' +
				", goodsId=" + goodsId +
				", stock=" + stock +
				", goodsName='" + goodsName + '\'' +
				", price=" + price +
				", titlePicture='" + titlePicture + '\'' +
				", detailPicture='" + detailPicture + '\'' +
				'}';
	}
// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(Integer stock, String goodsName, Float price, String category,
			String titlePicture, String detailPicture) {
		this.stock = stock;
		this.goodsName = goodsName;
		this.price = price;
		this.category = category;
		this.titlePicture = titlePicture;
		this.detailPicture = detailPicture;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitlePicture() {
		return this.titlePicture;
	}

	public void setTitlePicture(String titlePicture) {
		this.titlePicture = titlePicture;
	}

	public String getDetailPicture() {
		return this.detailPicture;
	}

	public void setDetailPicture(String detailPicture) {
		this.detailPicture = detailPicture;
	}



}