import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { PostDTO } from "./post-dto.component";
import { PostComentarioDTO } from "./post-comentario-dto.component";

@Injectable({
  providedIn: "root",
})
export class PostService {
  urlPost = "/api/post";
  urlComentario = "/api/comentario";

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(environment.apiUrl + this.urlPost);
  }

  salvar(dto: PostDTO): Observable<PostDTO> {
    let usuario = localStorage.getItem("idLogin");
    console.log("usuario: ", usuario);
    dto.usuario = parseInt(usuario);
    return this.http.post<PostDTO>(environment.apiUrl + this.urlPost, dto);
  }

  buscarPorId(id: number): Observable<PostDTO> {
    return this.http.get<PostDTO>(environment.apiUrl + this.urlPost + `/${id}`);
  }

  alterar(id: number, dto : PostDTO): Observable<PostDTO> {
    return this.http.put<PostDTO>(environment.apiUrl + this.urlPost + `/${id}`, dto);
  }

  deletar(id: number): Observable<PostDTO> {
    return this.http.delete<PostDTO>(environment.apiUrl + this.urlPost + `/${id}`);
  }

  buscarListaComentarioPorPost(id: number): Observable<PostComentarioDTO[]> {
    return this.http.get<PostComentarioDTO[]>(environment.apiUrl + this.urlComentario + `/comentarioPost/${id}`);
  }

  salvarComentario(dto: PostComentarioDTO): Observable<PostComentarioDTO> {
    return this.http.post<PostComentarioDTO>(environment.apiUrl + this.urlComentario, dto);
  }

  alterarComentario(id: number, dto: PostComentarioDTO): Observable<PostComentarioDTO> {
    return this.http.put<PostComentarioDTO>(environment.apiUrl + this.urlComentario+`/${id}`, dto);
  }

  deletarComentario(id: number): Observable<PostComentarioDTO> {
    return this.http.delete<PostComentarioDTO>(environment.apiUrl + this.urlComentario+`/${id}`);
  }

}
