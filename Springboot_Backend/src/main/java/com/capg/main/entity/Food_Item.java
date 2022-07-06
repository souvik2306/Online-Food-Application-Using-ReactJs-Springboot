package com.capg.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Food_Item {

	@Id
	@Column(name = "id")
	private int f_id;
	@Column(name = "name")
	private String f_name;
	@Column(name = "price")
	private double f_price;

	public Food_Item() {
		// TODO Auto-generated constructor stub
	}

	public Food_Item(int f_id, String f_name, double f_price, int qnt) {
		super();
		this.f_id = f_id;
		this.f_name = f_name;
		this.f_price = f_price;
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public double getF_price() {
		return f_price;
	}

	public void setF_price(double f_price) {
		this.f_price = f_price;
	}

	@Override
	public String toString() {
		return "Food_Item [f_id=" + f_id + ", f_name=" + f_name + ", f_price=" + f_price + "]";
	}

	

}
