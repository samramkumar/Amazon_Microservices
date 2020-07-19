package com.example.springmysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="completedorders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class products {
	@Id
	@GeneratedValue
	private int id;
	private double totalprice;
	private String totalproducts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getTotalproducts() {
		return totalproducts;
	}
	public void setTotalproducts(String totalproducts) {
		this.totalproducts = totalproducts;
	}
}
