package br.com.desafioframework.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.desafioframework.config.ApiErrors;


@RestControllerAdvice
public class ApplicationControllerAdvice {

	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErrors(errors);
	}

}
