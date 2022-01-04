package br.com.desafioframework.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByStatus(StatusObjeto status);

	Optional<Post> findByIdAndStatus(Integer id, StatusObjeto status);

	
	
}
