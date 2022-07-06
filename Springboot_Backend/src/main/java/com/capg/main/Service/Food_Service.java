package com.capg.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.main.Repository.Customer_Repo;
import com.capg.main.Repository.Item_Repo;
import com.capg.main.Repository.Login_Repo;
import com.capg.main.Repository.Order_Repo;
import com.capg.main.entity.Customer;
import com.capg.main.entity.Food_Item;
import com.capg.main.entity.Order;

@Service
public class Food_Service {

	@Autowired
	private Order_Repo frepo;
	@Autowired
	private Customer_Repo crepo;
	@Autowired
	private Item_Repo irepo;
	
	@Autowired
	private Login_Repo lrepo;
	
	public Food_Service() {
		// TODO Auto-generated constructor stub
	}
	
	public Login_Repo getLR()
	{
		return lrepo;
	}
	public Order_Repo getOrder()
	{
		return this.frepo;
	}
	public Customer_Repo getCR()
	{
		return this.crepo;
	}
	
	public Item_Repo getIR()
	{
		return this.irepo;
	}
	public List<Order> getonly(int c_id, int i_id)
	{
		return frepo.findspec(c_id, i_id);
	}

	public Order saveOrder(Order ord) {
		// TODO Auto-generated method stub
		return frepo.save(ord);
	}
	public Customer saveCustomer(Customer cus)
	{
		return crepo.save(cus);
	}
	public Food_Item saveItem(Food_Item fit)
	{
		return irepo.save(fit);
	}
}
