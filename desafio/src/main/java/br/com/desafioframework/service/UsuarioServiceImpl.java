package br.com.desafioframework.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.desafioframework.config.SenhaInvalidaException;
import br.com.desafioframework.model.Usuario;
import br.com.desafioframework.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

//	@Autowired
//    private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository repository;

	private PasswordEncoder encoder;

	@Autowired
	public UsuarioServiceImpl(@Lazy PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		if(senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base de dados"));

		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles("USER").build();

//		if (!username.equals("fulano")) {
//			throw new UsernameNotFoundException("Usuário não encontrado na base.");
//		}
//
//		return User.builder().username("fulano").password(passwordEncoder.encode("123")).roles("USER").build();
	}

}
