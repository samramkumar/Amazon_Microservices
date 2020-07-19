package com.example.springmysql.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.springmysql.dao.ProductDao;
import com.example.springmysql.model.products;



@RestController
@RequestMapping("/product")
public class ProductController<dao> {
	@Autowired
	private ProductDao dao;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/highestpurchaseorder")
	public String getProductList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      //From the completed orders DB take product ids based on highest purchase amount
	      String response = restTemplate.exchange("http://localhost:9193/product/getOrders/highestprice", HttpMethod.GET, entity, String.class).getBody();
	      System.out.println(response);
	      response = response.substring(response.indexOf("totalproducts")+16,response.indexOf("}")-1);
	      
	      String[] collectproductid = response.split("]");
	      String productids = "";
	      String getresponses = "";
	      for (int i = 0; i < collectproductid.length; i++){
          	String productid = collectproductid[i].substring(1);
          	productids = productids +" "+ productid;
          	System.out.println(productids);
          	//send the collected product ids into the below API and collect the product names
          	String response2 = restTemplate.exchange("http://localhost:9190/product/getProducts/productid/"+productid, HttpMethod.GET, entity, String.class).getBody();
          	response2 = response2.substring(response2.indexOf("productname")+14,response2.indexOf("totalprice")-3);
          	getresponses = getresponses+", "+response2;
          	getresponses = getresponses.substring(1);
          	System.out.println(getresponses);
	      }
	      getresponses = "Products purchased for the highest price : "+getresponses;
	      return getresponses;
	}
	
}
