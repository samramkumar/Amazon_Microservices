package com.example.springmysql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.springmysql.model.products;


public interface ProductDao extends CrudRepository<products, Integer>  {
	@Query(value="SELECT * FROM product WHERE productname=?1", nativeQuery=true)
	List<products> findbyproductname(String productname) ;
		// TODO Auto-generated method stub
		
	
}
