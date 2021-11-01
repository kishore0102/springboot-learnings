package com.fresco.beverage.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordertable")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "order_for")
	private String orderFor;

	@Column(name = "beverage_id", insertable = false, updatable = false)
	private Integer beverageId;

	@Column(name = "batch_id", insertable = false, updatable = false)
	private Integer batchId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "beverage_id", referencedColumnName = "beverage_id")
	private Beverage beverage;

	public Order() {
	}

	public Order(String orderFor, Beverage beverage) {
		this.orderFor = orderFor;
		this.beverage = beverage;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderFor() {
		return orderFor;
	}

	public void setOrderFor(String orderFor) {
		this.orderFor = orderFor;
	}

	public Integer getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Integer beverageId) {
		this.beverageId = beverageId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String toString() {
		return "Order [batchId=" + batchId + ", beverage=" + beverage + ", beverageId=" + beverageId + ", orderFor="
				+ orderFor + ", orderId=" + orderId + "]";
	}

}
