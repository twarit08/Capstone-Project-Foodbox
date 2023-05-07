import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Services/login.service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {
  ngOnInit(): void {
    this.username = this.loginService.getUserDetails().username;
    this.name = this.loginService.getUserDetails().firstName + ' ' + this.loginService.getUserDetails().lastName;
  }
  username!: string;
  name!: string;
  constructor(private loginService: LoginService, private router: Router) { }

  getOrders() {
    let url = '/get/orders/' + this.username;
    this.router.navigateByUrl(url);
  }

}
