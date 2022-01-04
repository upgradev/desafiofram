package br.com.desafioframework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafioframework.dto.ComentarioDTO;
import br.com.desafioframework.enums.StatusObjeto;
import br.com.desafioframework.model.Comentario;
import br.com.desafioframework.repository.ComentarioRepository;
import br.com.desafioframework.service.ComentarioService;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private ComentarioService comentarioService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comentario salvar(@RequestBody ComentarioDTO dto) {
		return comentarioService.salvar(dto);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Comentario> listar() {
		return comentarioRepository.findByStatus(StatusObjeto.ATIVO);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ComentarioDTO buscarPorId(@PathVariable("id") Integer id) {
		return comentarioService.buscarPorIdDto(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Comentario atualizar(@PathVariable("id") Integer id, @RequestBody ComentarioDTO dto) {
		return comentarioService.atualizar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") Integer id) {
		comentarioService.deletar(id);
	}
	
	@GetMapping("/comentarioPost/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ComentarioDTO> listarPorPost(@PathVariable("id") Integer id) {
		return comentarioService.buscarPorPost(id, StatusObjeto.ATIVO);
	}
	
	
	
}
