package br.com.desafioframework.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafioframework.config.SenhaInvalidaException;
import br.com.desafioframework.dto.CredenciaisDTO;
import br.com.desafioframework.dto.TokenDTO;
import br.com.desafioframework.jwt.JwtService;
import br.com.desafioframework.model.Usuario;
import br.com.desafioframework.repository.UsuarioRepository;
import br.com.desafioframework.service.UsuarioServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		Optional<Usuario> loginUsuario = usuarioRepository.findByLogin(usuario.getLogin());
		if(loginUsuario.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"usuário ja existe");
		}
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);

	}

	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(credenciais.getLogin());
			usuario.setSenha(credenciais.getSenha());
			UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			
			Optional<Usuario> usuario2 = usuarioRepository.findByLogin(usuario.getLogin());
			
			return new TokenDTO(usuario.getLogin(), token, usuario2.get().getId());
			
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		} 
	}
}
