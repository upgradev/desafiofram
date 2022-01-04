package br.com.desafioframework.config;

public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Senha Invalida");
	}
}
