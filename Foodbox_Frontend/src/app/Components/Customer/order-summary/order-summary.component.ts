import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderSummary } from 'src/app/Models/order-summary';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {
  oid!: number;
  orderInvoice: OrderSummary = new OrderSummary();
  constructor(private route: ActivatedRoute, private customerService: CustomerService) { }
  ngOnInit(): void {
    this.oid = this.route.snapshot.params['oid'];
    this.getOrderSummary();
  }

  getOrderSummary() {
    this.customerService.getOrderById(this.oid).subscribe({
      next: (data) => {
        this.orderInvoice = data;
        this.orderInvoice.products.forEach((p) => {
          p.product.img = 'data:image/jpeg;base64,' + p.product.productImage.imageData;
        })
      }, error: (error) => {
        console.log(error);
        alert('Something went wrong!');
      }
    })
  }




}
