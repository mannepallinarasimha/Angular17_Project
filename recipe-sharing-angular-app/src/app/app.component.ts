import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthService } from './services/Auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  imports: [
    RouterOutlet,
    MatToolbarModule,
    MatIconModule,
    NavbarComponent,
    FooterComponent,
    HomePageComponent,
    AuthComponent,
  ],
})
export class AppComponent {
  title = 'recipe-sharing-angular-app';
  user: any = null;

  constructor(public authService: AuthService) {}

  ngOnInit() {
    console.log('ngOnInIt() methos inside...');
    this.authService.getUserProfile().subscribe({
      next: (data) => console.log('user data', data),
      error: (error) => console.log('error', error),
    });
    this.authService.authSubject.subscribe((auth: any) => {
      console.log('auth state::', auth);
      this.user = auth.user;
    });
  }
}
