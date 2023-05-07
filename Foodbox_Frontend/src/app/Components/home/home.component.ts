import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router: Router) { }

  foodItemName!: string;

  searchFoodItem(name: string) {
    if (name != undefined) {
      let url = '/foodbox/search/product/' + name;
      this.router.navigateByUrl(url);
    } else {
      alert('Please enter a food item name!');
    }

  }

}
