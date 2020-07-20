package com.ml.coupon.error;

public enum ErrorEnum {

	NOT_FOUND_EXCEPTION(1, "No se encontro ningun item",
			"Ninguno de los items se puede adquirir con el cupon suministrado"),
	DEFAULT_EXCEPTION(3, "Error", "Se presento un error, intentelo mas tarde"),
	FEIGN_EXCEPTION(4, "Error mercado libre API", "Hubo un error contactando el api de mercado libre"),
	ITEM_NOT_FOUND_ML_API(4, "Error mercado libre API", "No fue posible encontrar informacion en el API de mercado libre para alguno de los items asociados");

	public final int code;
	public final String title;
	public final String description;

	ErrorEnum(int code, String title, String description) {
		this.code = code;
		this.title = title;
		this.description = description;
	}

}