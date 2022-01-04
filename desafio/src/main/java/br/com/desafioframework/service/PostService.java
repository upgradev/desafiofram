package br.com.desafioframework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafioframework.dto.PostDTO;
import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Post;
import br.com.desafioframework.model.Usuario;
import br.com.desafioframework.repository.PostRepository;
import br.com.desafioframework.repository.UsuarioRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Post salvarPost(PostDTO dto) {
		Post novoPost = new Post();
		Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuario());
		novoPost.setUsuario(usuario.get());
		novoPost.setLink(dto.getLink());
		novoPost.setTexto(dto.getTexto());
		novoPost.setStatus(StatusObjeto.ATIVO);
		return postRepository.save(novoPost);
	}

	public Post buscarPorId(Integer id) {
		return postRepository.findByIdAndStatus(id, StatusObjeto.ATIVO).orElseThrow(() -> new Error("Post não encontrado"));
	}
	
	public List<PostDTO> lista() {
		List<PostDTO> dto = new ArrayList<>();
		List<Post> list = postRepository.findByStatus(StatusObjeto.ATIVO);
		for (Post post : list) {
			PostDTO postDto = new PostDTO();
			postDto.setId(post.getId());
			postDto.setLink(post.getLink());
			postDto.setTexto(post.getTexto());
			postDto.setUsuario(post.getUsuario().getId());
			dto.add(postDto);
		}
		return dto;
	}
	
	public PostDTO buscarPorIdDto(Integer id) {
		PostDTO dto = new PostDTO();
		Optional<Post> findByIdAndStatus = postRepository.findByIdAndStatus(id, StatusObjeto.ATIVO);
		findByIdAndStatus.map(ps -> {
			dto.setId(ps.getId());
			dto.setLink(ps.getLink());
			dto.setTexto(ps.getTexto());
			dto.setUsuario(ps.getUsuario().getId());
			return dto;
		});
		return dto;
	}

	public Post atualizar(Integer id, PostDTO dto) {
		Post post = buscarPorId(id);
		post.setLink(dto.getLink());
		post.setTexto(dto.getTexto());
		postRepository.save(post);
		return post;
	}

	public void deletar(Integer id) {
		Post post = buscarPorId(id);
		post.setStatus(StatusObjeto.INATIVO);
		postRepository.save(post);
	}
	
}
