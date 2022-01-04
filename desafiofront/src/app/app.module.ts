import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { PostComponent } from "./post/post.component";
import { TelaerrorComponent } from "./telaerror/telaerror.component";
import { TopbarComponent } from "./topbar/topbar.component";
import { InicioComponent } from "./inicio/inicio.component";
import { LoginComponent } from "./seguranca/login/login.component";
import { SignupComponent } from "./seguranca/signup/signup.component";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TokenInterceptor } from "./seguranca/tokeninterceptor.component";
import { CadastroPostComponent } from './post/cadastro-post/cadastro-post.component';
import { ComentarPostComponent } from './post/comentar-post/comentar-post.component';



@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    TelaerrorComponent,
    TopbarComponent,
    InicioComponent,
    LoginComponent,
    SignupComponent,
    CadastroPostComponent,
    ComentarPostComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
