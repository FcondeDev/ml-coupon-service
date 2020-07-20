package com.ml.coupon.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MercadoLibreBodyItemsDTO implements Serializable {
	private static final long serialVersionUID = 7066410159001416191L;
	private String id;
	private String title;
	private Float price;

}
