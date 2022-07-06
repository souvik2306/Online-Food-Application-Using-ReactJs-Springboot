package com.capg.main.Controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.main.Service.Food_Service;
import com.capg.main.entity.Customer;
import com.capg.main.entity.Food_Item;
import com.capg.main.entity.Login;
import com.capg.main.entity.Order;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class Food_Controller {

	@Autowired
	private Food_Service fservice;

	public Food_Service getFS() {
		return this.fservice;
	}

	@GetMapping("itemname/{name}")
	public ResponseEntity<Object> getbynm(@PathVariable("name") String name) {
		try {
			List<Food_Item> lt = fservice.getIR().getbyname(name);
			return new ResponseEntity<Object>(lt, HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<Object>("Invalid credentials", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Object> getOrder(@PathVariable("id") int id) {
		try {
			Optional<Order> order = fservice.getOrder().findById(id);
			return new ResponseEntity<Object>(order.get(), HttpStatus.FOUND);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<Object>("Element Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("findAll")
	public ResponseEntity<Object> getAllorders() {
		ArrayList<Order> list = new ArrayList<Order>();
		Iterable<Order> it = fservice.getOrder().findAll();
		if (it != null) {
			it.forEach(list::add);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("No Records Available", HttpStatus.NO_CONTENT);

	}

	@GetMapping("sortedbyfield/{field}")
	public ResponseEntity<Object> getsortedbyfield(@PathVariable("field") String field) {
		List<Order> list = new ArrayList<>();
		try {
			list = fservice.getOrder().findAll(Sort.by(Sort.Direction.ASC, field));
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<Object>("Invalid field or url", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("insert")
	public ResponseEntity<String> saveOrder(@RequestBody Order ord) {
		System.out.println("Hello");
		fservice.saveCustomer(ord.getCust());
		fservice.saveItem(ord.getItem());
		Order odd = fservice.saveOrder(ord);
		System.out.println(odd);
		return new ResponseEntity<String>("Successfully Stored", HttpStatus.CREATED);

	}

	@PostMapping("saveitem")
	public ResponseEntity<Object> saveOrder(@RequestBody Food_Item item) {
		fservice.getIR().save(item);
		return new ResponseEntity<Object>(item, HttpStatus.CREATED);
	}

	@PutMapping("updt/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Order od) throws ParseException {
		Optional<Order> op = fservice.getOrder().findById(id);
		if (op.get() != null) {
			Order ord = op.get();
			// fservice.saveItem(od.getItem());
			// fservice.saveCustomer(od.getCust());
			ord.setDate(od.getDate());
			// ord.getCust().setCust_id(od.getCust().getCust_id());
			ord.getCust().setCust_name(od.getCust().getCust_name());
			ord.getCust().setPassword(od.getCust().getPassword());
			ord.getCust().setCust_email(od.getCust().getCust_email());
			// ord.getItem().setF_id(od.getItem().getF_id());
			ord.getItem().setF_name(od.getItem().getF_name());
			ord.getItem().setF_price(od.getItem().getF_price());
			//ord.getItem().setQnt(od.getItem().getQnt());
			fservice.saveOrder(ord);
			return new ResponseEntity<Object>(ord, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not Found or Error", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("deletebyid/{id}")
	public ResponseEntity<Object> deletebyid(@PathVariable("id") int id) {
		try {
			fservice.getOrder().deleteById(id);
			return new ResponseEntity<Object>("Deleted if found", HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Not present", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("deleteAll")
	public ResponseEntity<Object> deleteAll() {
		fservice.getOrder().deleteAll();
		return new ResponseEntity<Object>("All deleted", HttpStatus.NO_CONTENT);
	}

	@GetMapping("specific/{cid}/{iid}")
	public ResponseEntity<Object> getSpecific(@PathVariable("cid") int cid, @PathVariable("iid") int iid) {
		List<Order> list = new ArrayList<Order>();
		try {
			list = fservice.getonly(cid, iid);
			System.out.println(list);
			return new ResponseEntity<Object>(list, HttpStatus.FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("getbydate/{date}")
	public ResponseEntity<Object> findbydt(@PathVariable("date") String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date1 = LocalDate.parse(date, dtf);
		try {
			List<Order> lst = fservice.getOrder().findbydate(date1);
			if (lst.size() == 0) {
				return new ResponseEntity<Object>("No such element", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(lst, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ---------------CUSTOMER CONTROLLER-----------------
	@PostMapping("savecust")
	public Customer savecust(@RequestBody Customer ord) {
			Customer odd = fservice.getCR().save(ord);
			System.out.println(odd);
			return odd;

	}
	
	@GetMapping("getcustbylogin/{email}/{pswd}")
	public Customer getcsm(@PathVariable("email") String email, @PathVariable("pswd") String pass)
	{
		Customer csm = fservice.getCR().findByMail(email, pass);
		System.out.println(csm);
		return csm;
	}
	@GetMapping("getcustbyid/{id}")
	public Customer getbyid(@PathVariable int id)
	{
		return fservice.getCR().findById(id).get();
	}
	@PutMapping("custupdate/{id}")
	public void updatecust(@PathVariable("id") int id, @RequestBody Customer cus)throws ParseException
	{
		Customer cst = fservice.getCR().findById(id).get();
		cst.setCust_name(cus.getCust_name());
		cst.setCust_email(cus.getCust_email());
		cst.setPassword(cus.getPassword());
		fservice.getCR().save(cst);
	}
	
	@GetMapping("getcustbymail/{email}")
	public Customer getbymail(@PathVariable("email") String email)
	{
		Customer cs = fservice.getCR().findemail(email);
		System.out.println(cs);
		return cs;
	}
	
	
	//------------------LOGIN CONTROLLER---------------------
	@PostMapping("/loggedin")
	public Login login(@RequestBody Login lg)
	{
		Login log = fservice.getLR().save(lg);
		return log;
	}
	
	@DeleteMapping("/logout")
	public void logout()
	{
		fservice.getLR().deleteAll();
	}
	
	@GetMapping("/isloggedin")
	public Login isloggedin()
	{
		try{Login lgs = fservice.getLR().findAll().get(0);
		return lgs;}
		catch(IndexOutOfBoundsException ie)
		{
			return null;
		}
	}
}
