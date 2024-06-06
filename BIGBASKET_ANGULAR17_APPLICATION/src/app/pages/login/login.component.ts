import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginObject: any = {
    userName: '',
    password: '',
  };
  constructor(private router: Router){}

  onLogin(){
    if(this.loginObject.userName == 'admin' && this.loginObject.password == '334455'){
      this.router.navigateByUrl('/products')
    }else{
      alert('Wrong Credentials...')
    }
  }
}
