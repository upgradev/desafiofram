import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { PostService } from "../post.service";

@Component({
  selector: "app-cadastro-post",
  templateUrl: "./cadastro-post.component.html",
  styleUrls: ["./cadastro-post.component.css"],
})
export class CadastroPostComponent implements OnInit {
  public formulario: FormGroup;

  habilitado: boolean = false;

  constructor(
    private service: PostService,
    private routes: ActivatedRoute,
    private formBulder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    let idUrl = this.routes.snapshot.params["id"];
    let usuario = localStorage.getItem("idLogin");
    if (idUrl) {
      this.service.buscarPorId(idUrl).subscribe((sub) => {
        let idUsuario = parseInt(usuario);
        if (idUsuario !== sub.usuario) {

          this.habilitado = true;
        }
        this.formulario.setValue(sub);
      });
    }
    this.habilitado = false;
    this.formulario = this.formBulder.group({
      id: [""],
      texto: [""],
      link: [""],
      usuario: [""],
    });

  }

  salvar() {
    let idUrl = this.routes.snapshot.params["id"];
    if(this.formulario.get("texto").value !== "" && this.formulario.get("link").value !== ''){
      if (idUrl) {
        this.service.alterar(idUrl, this.formulario.value).subscribe(
          (sub) => {
            alert("Post alterado com sucesso!");
            this.router.navigate(["listagempost"]);
          },
          (err) => {
            if (err.status === 403) {
              alert("Sessão expirou");
              this.router.navigate(["login"]);
            }
            alert("Erro ao alterar: ".concat(err));
          }
        );
      } else {
        this.service.salvar(this.formulario.value).subscribe(
          (sub) => {
            alert("Post salvo com sucesso!");
            this.router.navigate(["listagempost"]);
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
      alert("preencha todos os campos")
    }

  }
}
