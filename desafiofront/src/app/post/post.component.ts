import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { PostDTO } from "./post-dto.component";
import { PostService } from "./post.service";

@Component({
  selector: "app-post",
  templateUrl: "./post.component.html",
  styleUrls: ["./post.component.css"],
})
export class PostComponent implements OnInit {
  constructor(private service: PostService, private router : Router) {}

  listaPost: PostDTO[];

  idusuario : number

  ngOnInit() {
    this.listaPost = [];
    this.idusuario =  parseInt(localStorage.getItem("idLogin"));

    this.service.listarTodos().subscribe(
      (list) => {
        list.map((l) => {
          this.listaPost.push(l);
        });
      },
      (err) => {
        if (err.status === 403) {
          alert("Sessão expirou")
          this.router.navigate(["login"]);
        }
      }
    );
  }

  deletar(id: number) {

    this.service.deletar(id).subscribe(
      (sub) => {
        alert("Post deletado com sucesso!");
        this.ngOnInit();
      },
      (err) => {
        alert("Erro ao deletar: ".concat(err));
      }
    );
  }
}
