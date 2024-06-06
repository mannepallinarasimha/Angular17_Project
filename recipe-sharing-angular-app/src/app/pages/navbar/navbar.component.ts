import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { AuthService } from '../../services/Auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  user: any = null;

  constructor(public authService: AuthService, private route: Router) {}

  ngOnInit() {
    console.log('NavbarComponent - ngOnInIt() methos inside...');

    this.authService.authSubject.subscribe((auth: any) => {
      console.log('auth state::', auth);
      this.user = auth.user;
    });
  }
  handleLogout() {
    this.authService.logout();
    // this.route.navigate
  }
}
