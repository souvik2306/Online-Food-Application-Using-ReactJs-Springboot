package com.capg.main.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.main.entity.Order;

@Repository
public interface Order_Repo extends JpaRepository<Order, Integer>{

	@Query(value="select * from orders p where p.cust_id=:cid and p.item_id=:iid",nativeQuery = true)
	List<Order> findspec(@Param("cid") int cid,@Param("iid") int iid);
	
	@Query(value="select * from orders p where p.order_date=:odt",nativeQuery = true)
	List<Order> findbydate(@Param("odt") LocalDate dt);
}
