package br.com.desafioframework.dto;

public class TokenDTO {

	private String login;
	private String token;
	private Integer idLogin;

	public TokenDTO() {
		super();
	}

	public TokenDTO(String login, String token) {
		super();
		this.login = login;
		this.token = token;
	}

	public TokenDTO(String login, String token, Integer idLogin) {
		super();
		this.login = login;
		this.token = token;
		this.idLogin = idLogin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

}
