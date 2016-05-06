package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
public class ProductDetailController {
    private final ProductDetailRepository repository;
    @Autowired
    public ProductDetailController(ProductDetailRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all Products")
    public Iterable findAllProducts(){
    	return repository.findAll();
    }
    
    
    @ApiOperation(value = "Get all Cars - api call")
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallback")
    public Object findAll() {
    	return getapi();
    }
    
    public List fallback(){
    	return new ArrayList();
    }
   
    public Iterable getProducts(){
    	System.out.println("fall back method");
    	 return null;
    }
    @ApiOperation(value = "Get the product using id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDetail find(@PathVariable String id) {
           return repository.findOne(id);        
    }
    
    @ApiOperation(value = "post a product")
    @RequestMapping(method = RequestMethod.POST)
    public ProductDetail create(@RequestBody ProductDetail detail) {
        return repository.save(detail);
    }
    
    public HttpEntity<String> prepareGet() throws  IOException {
    	  HttpEntity<String> entity = new HttpEntity<String>(prepareHeader());
    	  return entity;
    	 }
    
    public HttpHeaders prepareHeader() throws  IOException {
    	  HttpHeaders headers = new HttpHeaders();
    	  headers.add("Content-Type", "application/json");
    	  return headers;
    	 }
    
   
    public RestTemplate restTemplate = new RestTemplate();
    
    public Object getapi(){
    	ResponseEntity<Object> response = null;

    	HttpEntity<String> requestEntity;
    	try {
    		requestEntity = prepareGet();
    		response = restTemplate.exchange( "http://newt-pk037.newtglobal.chn.local:8759/cars", HttpMethod.GET, requestEntity, Object.class);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		return response.getBody();
    }
}