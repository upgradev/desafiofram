package br.com.desafioframework.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafioframework.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	Optional<Usuario> findByLogin(String login);
	
}
