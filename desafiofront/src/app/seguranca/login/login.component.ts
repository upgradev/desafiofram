import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthService } from "../auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  public formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      login: [""],
      senha: [""],
    });
  }

  logar() {
    this.service.login(this.formulario.value).subscribe((sub) => {
      this.router.navigate(["listagempost"]);
      this.service.pegarToken(sub.token, sub);
    });
  }
}
