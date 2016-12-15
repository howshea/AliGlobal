package com.hoshea.aliglobal.model;

/**
 * Sold entity. @author MyEclipse Persistence Tools
 */

public class Sold {

    // Fields

    private Integer ordersgoodsId;
    private Integer number;

    // Constructors

    /**
     * default constructor
     */
    public Sold() {
    }

    /**
     * full constructor
     */
    public Sold(Integer number) {
        this.number = number;
    }

    // Property accessors

    public Integer getOrdersgoodsId() {
        return this.ordersgoodsId;
    }

    public void setOrdersgoodsId(Integer ordersgoodsId) {
        this.ordersgoodsId = ordersgoodsId;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}