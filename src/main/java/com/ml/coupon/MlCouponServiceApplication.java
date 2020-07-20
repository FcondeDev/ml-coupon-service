package com.ml.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MlCouponServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlCouponServiceApplication.class, args);
	}

}
