import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/Models/cart-item';
import { CreateOrder } from 'src/app/Models/create-order';
import { OrderItem } from 'src/app/Models/order-item';
import { CartService } from 'src/app/Services/cart.service';
import { CustomerService } from 'src/app/Services/customer.service';
import { LoginService } from 'src/app/Services/login.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  createOrder: CreateOrder = new CreateOrder();
  cartItem: CartItem[] = [];
  orderItem: OrderItem[] = [];
  paidAmount: number = 0;
  username!: string;

  constructor(private cartService: CartService, private customerService: CustomerService, private loginService: LoginService) { }
  ngOnInit(): void {
    this.cartItem = this.cartService.cartItems;
    for (let items of this.cartItem) {
      let item: OrderItem = new OrderItem();
      item.pid = items.pid;
      item.quantity = items.quantity;
      this.orderItem.push(item);
    }
    this.cartService.totalPrice.subscribe(data => { this.paidAmount = data });
    this.cartService.calculateTotalPrice();
    this.username = this.loginService.getUserDetails().username;
    this.createOrder.username = this.username;
    this.createOrder.paidAmount = this.paidAmount;
    this.createOrder.paymentMode = 'CARD-PAYMENT';
    this.createOrder.cartItem = this.orderItem;

  }

  onSubmit() {
    this.customerService.createOrder(this.createOrder).subscribe({
      next: (data) => {
        window.location.href = "/order-confirmation/invoice/" + data.oid;
      }, error: (error) => {
        console.log(error);
        alert("Something went wrong!");
      }
    })
  }





}
