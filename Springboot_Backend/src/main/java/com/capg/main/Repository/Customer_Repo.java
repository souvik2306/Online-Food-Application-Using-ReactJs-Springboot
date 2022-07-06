package com.capg.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.main.entity.Customer;

@Repository
public interface Customer_Repo extends JpaRepository<Customer, Integer>{
	
	@Query(value="select * from customer y order by y.mobile",nativeQuery = true)
	List<Customer> sortbynumber();
	
	@Query(value="select * from customer y where y.email=:email and y.password=:password",nativeQuery = true)
	Customer findByMail(String email, String password);
	
	@Query(value="select * from customer y where y.email=:email",nativeQuery = true)
	Customer findemail(String email);


}
