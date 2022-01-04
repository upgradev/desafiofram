package br.com.desafioframework.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.desafioframework.enums.StatusObjeto;

@Entity
@Table(name = "imagem_post")
public class ImagemPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@Column(name = "caminho", length = 500)
	private String caminho;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusObjeto status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public StatusObjeto getStatus() {
		return status;
	}

	public void setStatus(StatusObjeto status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, post, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemPost other = (ImagemPost) obj;
		return Objects.equals(id, other.id) && Objects.equals(post, other.post) && status == other.status;
	}

}
