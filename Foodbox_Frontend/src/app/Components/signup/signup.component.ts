import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user-details';
import { LoginService } from 'src/app/Services/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  user: User = new User();
  isValid!: boolean;
  message!: string;

  constructor(private loginService: LoginService, private router: Router) { }

  onSubmit() {
    this.loginService.createUser(this.user).subscribe({
      next: (response) => {
        this.isValid = true;
        this.message = 'Successfully Registered! Please Sign In to continue.';
      }, error: (error) => {
        console.log(error);
        this.isValid = false;
        this.message = 'E-mail address is already registered! Please try again with different email address.';
      }
    })
  }

  onClick() {
    this.router.navigate(['/customer/login']);
  }

}
