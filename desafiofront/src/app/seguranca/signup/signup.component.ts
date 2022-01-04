import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

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
    this.service.criarLogin(this.formulario.value).subscribe((sub) => {
      this.router.navigate(["login"]);
    }, (err) => {
      alert(err.error.message)

    });
  }

}
