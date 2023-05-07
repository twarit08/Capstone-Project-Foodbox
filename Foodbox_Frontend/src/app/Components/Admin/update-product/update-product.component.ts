import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { AdminService } from 'src/app/Services/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  product: Product = new Product();
  pid!: number;
  isValid!: boolean;
  message!: string;

  constructor(private route: ActivatedRoute, private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.pid = this.route.snapshot.params['pid'];
    this.getProductDetails();
  }

  getProductDetails() {
    this.adminService.getFoodItemById(this.pid).subscribe({
      next: (data) => {
        this.product = data;
      }, error: (error) => {
        console.log(error);
      }
    })
  }

  updateProduct() {
    this.adminService.updateFoodItem(this.pid, this.product).subscribe({
      next: (data) => {
        this.isValid = true;
        this.message = 'Food item details updated successfully!';
      }, error: (error) => {
        this.isValid = false;
        this.message = 'Something went wrong!';
      }
    })
  }

  onClick() {
    this.router.navigate(['/admin/get/all-products']);
  }

}
