package com.ml.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ml.coupon.dto.CouponDTO;
import com.ml.coupon.dto.CouponResponseDTO;
import com.ml.coupon.dto.JsonDTO;
import com.ml.coupon.service.CouponService;

@RestController
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@PostMapping("coupon")
	public ResponseEntity<JsonDTO<CouponResponseDTO>> getTheBestValue(@RequestBody CouponDTO couponDTO){
		return new ResponseEntity<>(new JsonDTO<>(couponService.getTheBestValue(couponDTO)),HttpStatus.OK);
	}

}
