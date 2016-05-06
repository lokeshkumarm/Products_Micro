package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableHystrixDashboard
@EnableHystrix
@EnableSwagger2
public class ExampleApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ExampleApplication.class, args);
        ProductDetailRepository repo = ctx.getBean(ProductDetailRepository.class);

		ProductDetail detail = new ProductDetail();
		detail.setProductId("1");
        detail.setProductName("Passenger Car");
        detail.setShortDescription("utility vehicle.");
        detail.setLongDescription("This is designed for carrying eight or fewer persons.");
        detail.setInventoryId("78461");
        repo.save(detail);
        
        detail = new ProductDetail();
        detail.setProductId("2");
        detail.setProductName("Sports Car");
        detail.setShortDescription("utility vehicle.");
        detail.setLongDescription("This transport device is designed for carrying persons.");
        detail.setInventoryId("78445");
        repo.save(detail);
        
        detail = new ProductDetail();
        detail.setProductId("3");
        detail.setProductName("Low Speed Car");
        detail.setShortDescription("utility vehicle.");
        detail.setLongDescription("This is a vehicle having a top speed of 20 to 25 mph.");
        detail.setInventoryId("78498");
        repo.save(detail);
       
	}
}
