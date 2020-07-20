package com.ml.coupon.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonDTO<T> implements Serializable {

	private static final long serialVersionUID = 9212895439118420961L;

	@JsonProperty("data")
	private transient T data;

	public JsonDTO(T data) {
		super();
		this.data = data;
	}

}
