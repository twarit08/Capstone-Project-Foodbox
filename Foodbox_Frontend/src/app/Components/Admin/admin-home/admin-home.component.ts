import { Component } from '@angular/core';
import { LoginService } from 'src/app/Services/login.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
  username!: string;
  name!: string;
  constructor(private loginService: LoginService) {
    this.username = this.loginService.getUserDetails().username;
    this.name = this.loginService.getUserDetails().firstName + ' ' + this.loginService.getUserDetails().lastName;
  }



}
