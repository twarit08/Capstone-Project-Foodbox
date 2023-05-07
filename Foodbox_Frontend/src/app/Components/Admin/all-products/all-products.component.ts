import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { AdminService } from 'src/app/Services/admin.service';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  ngOnInit(): void {
    this.getAllProducts();
  }
  constructor(private adminService: AdminService, private router: Router) { }

  product!: Product[];
  foodItemName!: string;
  updateProduct: Product = new Product();
  page: number = 1;
  count: number = 0;
  tableSize: number = 7;
  event: any;

  onTableDataChange(event: any) {
    this.page = event;
  }

  getAllProducts() {
    this.adminService.getAllFoodItems().subscribe({
      next: (data) => {
        this.product = data;
        this.product.forEach((p) => {
          p.img = 'data:image/jpeg;base64,' + p.productImage.imageData;
        })
      }, error: (error) => {
        console.log(error);
        alert('No Food item found!');
      }
    })
  }

  updateProductDetails(pid: number) {
    let url = '/admin/update/product/' + pid;
    this.router.navigateByUrl(url);
  }

  deleteProduct(pid: number) {
    this.adminService.deleteFoodItem(pid).subscribe({
      next: (data) => {
        this.getAllProducts();
      }, error: (error) => {
        console.log(error);
      }
    })
  }

  onActivate(pid: number, product: Product) {
    this.updateProduct = product;
    this.adminService.setAvailability(pid, this.updateProduct).subscribe({
      next: (data) => {

      }, error: (error) => {
        console.log(error);
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

  getProductByName() {
    this.onTableDataChange(this.event);
    this.adminService.getFoodItemByName(this.foodItemName).subscribe({
      next: (data) => {
        this.product = data;
        this.product.forEach((p) => {
          p.img = 'data:image/jpeg;base64,' + p.productImage.imageData;
        })
      }, error: (error) => {
        console.log(error);
        alert('No Food Item Found!');
      }
    })
  }

}
