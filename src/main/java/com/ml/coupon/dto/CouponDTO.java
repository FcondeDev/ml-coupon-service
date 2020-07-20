package com.ml.coupon.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDTO implements Serializable {

	private static final long serialVersionUID = -8917676528775094803L;
	@JsonProperty("item_ids")
	private List<String> itemIds;
	private Float amount;

}
