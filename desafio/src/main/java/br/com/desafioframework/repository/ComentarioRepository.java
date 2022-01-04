package br.com.desafioframework.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Comentario;
import br.com.desafioframework.model.Post;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
	
	List<Comentario> findByStatus(StatusObjeto status);
	
	List<Comentario> findByPostAndStatus(Post post, StatusObjeto status);
	
	Optional<Comentario> findByIdAndStatus(Integer id, StatusObjeto status);
	
}
