import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrls: ['./customer-orders.component.css']
})
export class CustomerOrdersComponent implements OnInit {

  constructor(private customerService: CustomerService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.getAllOrders();
  }
  orders: any[] = [];
  username!: string;

  getAllOrders() {
    this.customerService.getOrderByUsername(this.username).subscribe({
      next: (data) => {
        this.orders = data;
      }, error: (error) => {
        console.log(error);
        alert('No Orders Found!');
      }
    })
  }

  getOrderDetails(oid: number) {
    let url = '/order-confirmation/invoice/' + oid;
    this.router.navigateByUrl(url);
  }

}
