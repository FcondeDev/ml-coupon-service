package com.ml.coupon.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ml.coupon.dto.ApiErrorDTO;
import com.ml.coupon.error.ErrorEnum;
import com.ml.coupon.exception.CustomException;
import com.ml.coupon.exception.NotFoundException;

import feign.FeignException;
import lombok.extern.java.Log;

@RestControllerAdvice
@Log
public class Handler {

	@ResponseBody
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ApiErrorDTO> handleException(CustomException exception) {
		log.info(String.format("--- OCURRIO ALGO INESPERADO  : %s ---", exception.getMessage()));
		return new ResponseEntity<>(new ApiErrorDTO(exception.getErrorEnum().code, exception.getErrorEnum().title,
				exception.getErrorEnum().description), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<ApiErrorDTO> handleException(NotFoundException exception) {
		log.info("--- EL CUPON NO PUEDE SER APLICADO A NINGUN PRODUCTO PORQUE TODOS SOBREPASAN SU VALOR ---");
		return new ResponseEntity<>(
				new ApiErrorDTO(ErrorEnum.NOT_FOUND_EXCEPTION.code, ErrorEnum.NOT_FOUND_EXCEPTION.title,ErrorEnum.NOT_FOUND_EXCEPTION.description),
				HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiErrorDTO> handleException(Exception exception) {
		log.info(String.format("--- ERROR NO CONTROLADO : %s ---", exception.getMessage()));
		return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.DEFAULT_EXCEPTION.code, ErrorEnum.DEFAULT_EXCEPTION.title,
				ErrorEnum.DEFAULT_EXCEPTION.description), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	@ExceptionHandler(value = FeignException.class)
	public ResponseEntity<ApiErrorDTO> handleException(FeignException exception) {
		log.info(String.format("--- HUBO UN ERROR EN LA PETICION A TRAVES DEL FEIGN : %s ---", exception.getMessage()));
		return new ResponseEntity<>(new ApiErrorDTO(ErrorEnum.FEIGN_EXCEPTION.code, ErrorEnum.FEIGN_EXCEPTION.title,
				ErrorEnum.FEIGN_EXCEPTION.description), HttpStatus.SERVICE_UNAVAILABLE);
	}

}