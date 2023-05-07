import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Product } from 'src/app/Models/product';
import { AdminService } from 'src/app/Services/admin.service';
import { CartService } from 'src/app/Services/cart.service';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {
  name!: string;
  product!: Product[];
  page: number = 1;
  count: number = 0;
  tableSize: number = 7;

  constructor(private route: ActivatedRoute, private userService: AdminService, private cartService: CartService) { }
  ngOnInit(): void {
    this.name = this.route.snapshot.params['name'];
    this.getProductByName();
  }

  onTableDataChange(event: any) {
    this.page = event;
  }

  getProductByName() {
    this.userService.getFoodItemByName(this.name).subscribe({
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
