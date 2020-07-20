package com.ml.coupon.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponResponseDTO implements Serializable {
	private static final long serialVersionUID = 7939724376011915330L;
	@JsonProperty("item_ids")
	private List<String> itemIds;
	@JsonProperty("total")
	private Float bestValue;

}
