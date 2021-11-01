package com.fresco.beverage.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "beverage")
public class Beverage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beverage_id")
	private Integer beverageId;

	private String name;

	private double price;
	
	@Column(name = "estimated_time")
	private double estimatedTime;

	@OneToMany(mappedBy = "beverage")
	@JsonIgnore
	private List<Order> orders;

	public Beverage() {
	}

	public Beverage(String name, double price, double estimatedTime) {
		this.name = name;
		this.price = price;
		this.estimatedTime = estimatedTime;
	}

	public Integer getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Integer beverageId) {
		this.beverageId = beverageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	@Override
	public String toString() {
		return "Beverage [beverageId=" + beverageId + ", estimatedTime=" + estimatedTime + ", name=" + name + ", price="
				+ price + "]";
	}

}
