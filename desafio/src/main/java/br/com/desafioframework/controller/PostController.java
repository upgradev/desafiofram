package br.com.desafioframework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafioframework.dto.PostDTO;
import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Post;
import br.com.desafioframework.repository.PostRepository;
import br.com.desafioframework.service.PostService;


@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Post salvarPost(@RequestBody PostDTO post) {
		
		return service.salvarPost(post);

	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PostDTO> listarPost() {
		return service.lista();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDTO buscarPost(@PathVariable("id") Integer id) {
		return service.buscarPorIdDto(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Post atualizarPost(@PathVariable("id") Integer id, @RequestBody PostDTO dto) {
		return service.atualizar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPost(@PathVariable("id") Integer id) {
		service.deletar(id);
	}

}
