package br.com.desafioframework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafioframework.dto.ComentarioDTO;
import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Comentario;
import br.com.desafioframework.model.Post;
import br.com.desafioframework.model.Usuario;
import br.com.desafioframework.repository.ComentarioRepository;
import br.com.desafioframework.repository.PostRepository;
import br.com.desafioframework.repository.UsuarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostRepository postRepository;

	public Comentario salvar(ComentarioDTO dto) {
		Comentario comentario = new Comentario();
		Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuario());
		Optional<Post> post = postRepository.findByIdAndStatus(dto.getPost(), StatusObjeto.ATIVO);
		comentario.setTexto(dto.getTexto());
		comentario.setStatus(StatusObjeto.ATIVO);
		comentario.setUsuario(usuario.get());
		comentario.setPost(post.get());
		return comentarioRepository.save(comentario);
	}

	public Comentario buscarPorId(Integer id) {

		return comentarioRepository.findByIdAndStatus(id, StatusObjeto.ATIVO)
				.orElseThrow(() -> new Error("Comentario não encontrado"));
	}

	public ComentarioDTO buscarPorIdDto(Integer id) {

		Optional<Comentario> findByIdAndStatus = comentarioRepository.findByIdAndStatus(id, StatusObjeto.ATIVO);
		ComentarioDTO dto = new ComentarioDTO();
		findByIdAndStatus.map(com -> {
			dto.setId(com.getId());
			dto.setPost(com.getPost().getId());
			dto.setTexto(com.getTexto());
			dto.setUsuario(com.getUsuario().getId());
			return dto;
		});
		return dto;

	}

	public Comentario atualizar(Integer id, ComentarioDTO dto) {
		Comentario comentario = buscarPorId(id);
		comentario.setTexto(dto.getTexto());
		return comentarioRepository.save(comentario);
	}

	public Object deletar(Integer id) {
		Comentario comentario = buscarPorId(id);
		comentario.setStatus(StatusObjeto.INATIVO);
		return comentarioRepository.save(comentario);
	}

	public List<ComentarioDTO> buscarPorPost(Integer id, StatusObjeto ativo) {
		Optional<Post> post = postRepository.findByIdAndStatus(id, StatusObjeto.ATIVO);
		List<ComentarioDTO> list = new ArrayList<>();
		List<Comentario> listaCom = comentarioRepository.findByPostAndStatus(post.get(), ativo);
		for (Comentario comentario : listaCom) {
			ComentarioDTO comentarioDTO = new ComentarioDTO();
			comentarioDTO.setId(comentario.getId());
			comentarioDTO.setPost(comentario.getPost().getId());
			comentarioDTO.setTexto(comentario.getTexto());
			comentarioDTO.setUsuario(comentario.getUsuario().getId());
			list.add(comentarioDTO);
		}
		return list;
	}

}
