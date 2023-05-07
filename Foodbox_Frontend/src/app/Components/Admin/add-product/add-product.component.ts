import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { AdminService } from 'src/app/Services/admin.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {

  constructor(private adminService: AdminService, private router: Router) { }

  product: Product = new Product();
  file!: Blob;
  isValid!: boolean;
  message!: string;

  onSubmit() {
    this.adminService.addFoodItem(this.product, this.file).subscribe({
      next: (response) => {
        this.isValid = true;
        this.message = 'Food item added successfully!';
      }, error: (error) => {
        this.isValid = false;
        this.message = 'Something went wrong!';
      }
    })
  }

  onChangeFileField(event: any) {
    this.file = event.target.files[0];
  }

  onClick() {
    this.router.navigate(['/admin/dashboard']);
  }

}
