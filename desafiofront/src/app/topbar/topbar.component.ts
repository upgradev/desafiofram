import { Component, OnInit } from "@angular/core";
import { AuthService } from "../seguranca/auth.service";

@Component({
  selector: "app-topbar",
  template: `
    <div class="toolbar" role="banner">
      <a routerLink="listagempost" class="link-home">Posts</a>
      <a routerLink="login" class="link-home">Login</a>
      <a routerLink="signup" class="link-home">Criar Login</a>
      <button
        (click)="logout()"
        style="background-color: transparent; border: 0; font-weight: bold;"
        class="link-home"
      >
        Logout
      </button>
    </div>
  `,
  styleUrls: ["./topbar.component.css"],
})
export class TopbarComponent implements OnInit {
  constructor(private serviceAuth: AuthService) {}
  usuario: number;
  existeLogin: boolean = true;
  estilo: string;
  ngOnInit() {
    this.usuario = parseInt(localStorage.getItem("idLogin"));
    console.log("this.usuario: ", this.usuario);
  }

  logout() {
    this.serviceAuth.logout();
  }
}
