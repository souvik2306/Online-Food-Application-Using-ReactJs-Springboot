package com.capg.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.main.entity.Food_Item;

@Repository
public interface Item_Repo extends JpaRepository<Food_Item, Integer>{
	
	@Query(value="select * from item i where i.name=:name", nativeQuery=true)
	List<Food_Item> getbyname(@Param("name") String nm);

}
