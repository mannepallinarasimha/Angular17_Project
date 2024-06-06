import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { AuthService } from '../../services/Auth/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatRadioModule, ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

  constructor(public authService: AuthService){}
  isRegister = true;
  registrationForm= new FormGroup({
    fullName: new FormControl("", [Validators.required]),
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.minLength(6)])
  })
  loginForm= new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required])
  })
  onSubmit(){

  }

  handleRegister(){
    console.log("register", this.registrationForm.value);
    this.authService.registerAction(this.registrationForm.value).subscribe({
      next: (response) =>{
        localStorage.setItem("jwt", response.jwtToken);
        this.authService.getUserProfile().subscribe();
        console.log("SignUp Success...", response);
      }
    }

    )
  }
  handleLogin(){
    console.log("login", this.loginForm.value);
    this.authService.loginAction(this.loginForm.value).subscribe({
      next: (response) =>{
        localStorage.setItem("jwt", response.jwtToken);
        this.authService.getUserProfile().subscribe();
        console.log("Login Success...", response);
      }
    }

    )
  }

  togglePanel(){
    this.isRegister=!this.isRegister;
  }
}
