package com.ml.coupon.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ml.coupon.dto.MercadoLibreItemsDTO;

@FeignClient(name = "mercado-libre-items-service", url = "${url.ml.items.service}")
public interface ItemsClient {

	@GetMapping("items")
	public ResponseEntity<List<MercadoLibreItemsDTO>> getItemsValue(@RequestParam Object[] ids);

}
