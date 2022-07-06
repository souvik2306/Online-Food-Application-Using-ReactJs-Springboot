package com.capg.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.main.entity.Login;

@Repository
public interface Login_Repo extends JpaRepository<Login, Integer>{

}
