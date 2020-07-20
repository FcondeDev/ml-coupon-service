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
public class MercadoLibreItemsDTO implements Serializable {

	private static final long serialVersionUID = -4214414833879784488L;
	private Integer code;
	private MercadoLibreBodyItemsDTO body;

}
