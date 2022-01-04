import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { LoginDTO } from "./dto/login-dto.component";


const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
};

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(private http: HttpClient, private router : Router) {}

  loginLink = "/api/usuarios/auth";
  loginCriar = "/api/usuarios";

  jwt : any;

  login(dto : LoginDTO): Observable<LoginDTO> {
    return this.http.post<LoginDTO>(
      environment.apiUrl + this.loginLink,
      { login: dto.login, senha: dto.senha },
      httpOptions
    );

  }
  criarLogin(dto : LoginDTO): Observable<LoginDTO> {
    return this.http.post<LoginDTO>(
      environment.apiUrl + this.loginCriar,
      { login: dto.login, senha: dto.senha },
    );

  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public pegarToken(token : string, sub : LoginDTO){
     localStorage.setItem('token', token);
     localStorage.setItem('idLogin', sub.idLogin.toString());
     this.getToken();

  }

  public logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("idLogin");
    this.router.navigate(["login"]);
  }

}
