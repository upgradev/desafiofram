import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { PostComentarioDTO } from "../post-comentario-dto.component";
import { PostDTO } from "../post-dto.component";
import { PostService } from "../post.service";

@Component({
  selector: "app-comentar-post",
  templateUrl: "./comentar-post.component.html",
  styleUrls: ["./comentar-post.component.css"],
})
export class ComentarPostComponent implements OnInit {
  public formulario: FormGroup;

  postAtual: PostDTO;

  listComentarios: PostComentarioDTO[];

  usuario: number;

  idItem: number;

  constructor(
    private service: PostService,
    private routes: ActivatedRoute,
    private formBulder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    let idUrl = this.routes.snapshot.params["id"];
    this.listComentarios = [];
    this.postAtual = new PostDTO();
    this.usuario = parseInt(localStorage.getItem("idLogin"));

    this.service.buscarPorId(idUrl).subscribe((sub) => {
      this.postAtual = sub;
    });

    this.service.buscarListaComentarioPorPost(idUrl).subscribe(
      (sub) => {
        console.log(sub);
        if (sub.length > 0) {
          sub.map((valor) => {
            this.listComentarios.push(valor);
          });
        }
      },
      (err) => {
        if (err.status === 403) {
          alert("Sessão expirou");
          this.router.navigate(["login"]);
        }
      }
    );

    this.formulario = this.formBulder.group({
      id: [""],
      texto: [""],
      usuario: [""],
      post: [""],
    });
  }

  alterar(item: PostComentarioDTO) {
    this.idItem = item.id;
    this.formulario.setValue(item);
  }

  salvar() {
    let idUrl = this.routes.snapshot.params["id"];
    this.formulario.get("usuario").setValue(this.usuario);
    this.formulario.get("post").setValue(idUrl);
    if (this.formulario.get("texto").value !== "") {
      if (this.idItem) {
        this.service
          .alterarComentario(this.idItem, this.formulario.value)
          .subscribe(
            (sub) => {
              alert("Comentário alterado com sucesso!");
              this.ngOnInit();
            },
            (err) => {
              alert("Erro ao alterar: ".concat(err));
            }
          );
      } else {
        this.service.salvarComentario(this.formulario.value).subscribe(
          (sub) => {
            alert("Comentário salvo com sucesso!");
            this.ngOnInit();
          },
          (err) => {
            if (err.status === 403) {
              alert("Sessão expirou");
              this.router.navigate(["login"]);
            }
            alert("Erro ao salvar: ".concat(err));
          }
        );
      }
    }
    else{
      alert("preencha o comentário")
    }
  }

  deletar(id: number) {
    this.service.deletarComentario(id).subscribe(
      (sub) => {
        alert("Comentário deletado com sucesso!");
        this.ngOnInit();
      },
      (err) => {
        if (err.status === 403) {
          alert("Sessão expirou");
          this.router.navigate(["login"]);
        }
        alert("Erro ao deletar: ".concat(err));
      }
    );
  }
}
