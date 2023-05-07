import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
})
export class AllOrdersComponent implements OnInit {

  orders: any[] = [];
  constructor(private customerService: CustomerService, private router: Router) { }
  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders() {
    this.customerService.getAllOrders().subscribe({
      next: (data) => {
        this.orders = data;
      }, error: (error) => {
        console.log(error);
        alert('No Orders Found!');
      }
    })
  }

  getOrderDetails(oid: number) {
    let url = '/order/invoice/' + oid;
    this.router.navigateByUrl(url);
  }

  deleteOrder(oid: number) {
    this.customerService.deleteOrder(oid).subscribe({
      next: (data) => {
        this.getAllOrders();
      }, error: (error) => {
        console.log(error);
      }
    })
  }



}
