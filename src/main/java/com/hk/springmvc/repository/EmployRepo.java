package com.hk.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hk.springmvc.models.Employ;
@Repository
public interface EmployRepo extends JpaRepository<Employ, Integer>{

	List<Employ> findByspec(String spec);

	@Query("from Employ where name = ?1")  //1st parameter is mean ?1 and search within Model class 
	List<Employ> findE(String name);

}
