package com.capg.main.entity;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="order_date")
	private LocalDate date;
	
	@ManyToOne
	private Food_Item item;
	@ManyToOne
	private Customer cust;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String date, Food_Item item, Customer cust) throws ParseException {
		super();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = LocalDate.parse(date, dtf);
		this.item = item;
		this.cust = cust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date1 = date.format(dtf);
		return date1;
	}

	public void setDate(String date) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = LocalDate.parse(date, dtf);
		System.out.println(this.date);
	}

	public Food_Item getItem() {
		return item;
	}

	public void setItem(Food_Item item) {
		this.item = item;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", item_id=" + item + ", cust_id=" + cust + "]";
	}

}
