import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Product } from 'src/app/Models/product';
import { AdminService } from 'src/app/Services/admin.service';
import { CartService } from 'src/app/Services/cart.service';

@Component({
  selector: 'app-get-product',
  templateUrl: './get-product.component.html',
  styleUrls: ['./get-product.component.css']
})
export class GetProductComponent implements OnInit {
  cuisineName!: string;
  product!: Product[];
  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  constructor(private route: ActivatedRoute, private userService: AdminService, private cartService: CartService) { }
  ngOnInit(): void {
    this.cuisineName = this.route.snapshot.params['name'];
    this.getCuisine();
  }

  onTableDataChange(event: any) {
    this.page = event;
  }

  getCuisine() {
    this.userService.getFoodByCuisineType(this.cuisineName).subscribe({
      next: (data) => {
        this.product = data;
        this.product.forEach((p) => {
          p.img = 'data:image/jpeg;base64,' + p.productImage.imageData;
        })
      }, error: (error) => {
        console.log(error);
        alert('No dish found!');
      }
    })
  }

  sortByPriceLowToHigh() {
    this.product.sort((a, b) => a.price - b.price);
  }
  sortByPriceHighToLow() {
    this.product.sort((a, b) => b.price - a.price);
  }
  sortByNameAscending() {
    this.product.sort((a, b) => a.name.localeCompare(b.name));
  }
  sortByNameDescending() {
    this.product.sort((a, b) => b.name.localeCompare(a.name));
  }

  addToCart(product: Product) {
    const cartItem = new CartItem(product);
    this.cartService.addToCart(cartItem);
  }

}
