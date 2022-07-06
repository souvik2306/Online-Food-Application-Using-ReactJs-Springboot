package com.capg.main;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.capg.main.Controller.Food_Controller;
import com.capg.main.entity.Customer;
import com.capg.main.entity.Order;

@SpringBootTest
class Sprint1ApplicationTests {

	@Autowired
	private Food_Controller orep;

	@Test
	void contextLoads() {

		Optional<Order> od = orep.getFS().getOrder().findById(2);
		assertThat(od.get().getCust().getCust_name().equals("Karthik"));
		System.out.println("Test Case 0 Passed");
	}

	@Test
	void testcase1() {
		List<Customer> lst;
		lst = orep.getFS().getCR().findAll();
		assertThat(lst.size() >= 2);
		System.out.println("Test Case 1 Passed");
	}

	@Test
	void testcase2() {
		List<Order> list = orep.getFS().getOrder().findAll(Sort.by(Sort.Direction.ASC, "date"));
		LocalDate date1, date2, date3;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date1 = LocalDate.parse(list.get(0).getDate(), dtf);
		date2 = LocalDate.parse(list.get(1).getDate(), dtf);
		date3 = LocalDate.parse(list.get(2).getDate(), dtf);
		assertThat((date2.isAfter(date1) || date2.isEqual(date1)) && (date3.isAfter(date2) || date3.isEqual(date2)));
		System.out.println("Test Case 2 passed");
	}

}
