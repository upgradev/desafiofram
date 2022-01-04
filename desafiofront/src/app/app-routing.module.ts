import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AppComponent } from "./app.component";
import { InicioComponent } from "./inicio/inicio.component";
import { CadastroPostComponent } from "./post/cadastro-post/cadastro-post.component";
import { ComentarPostComponent } from "./post/comentar-post/comentar-post.component";
import { PostComponent } from "./post/post.component";
import { LoginComponent } from "./seguranca/login/login.component";
import { SignupComponent } from "./seguranca/signup/signup.component";
import { TelaerrorComponent } from "./telaerror/telaerror.component";

const routes: Routes = [
  { path: "listagempost", component: PostComponent },
  { path: "post", component: CadastroPostComponent },
  { path: "post/:id", component: CadastroPostComponent },
  { path: "postComentar/:id", component: ComentarPostComponent },
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "", redirectTo: "/", pathMatch: "full" },
  {
    path: "**",
    component: InicioComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
