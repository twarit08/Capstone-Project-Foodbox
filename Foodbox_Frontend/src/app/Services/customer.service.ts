import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateOrder } from '../Models/create-order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080';

  public createOrder(createOrder: CreateOrder): Observable<CreateOrder> {
    return this.http.post<CreateOrder>(`${this.baseUrl}/user/create/order`, createOrder);
  }

  public getOrderById(oid: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get/order-invoice/${oid}`);
  }

  public getOrderByUsername(username: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/orders/${username}`);
  }

  public getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all/orders`);
  }

  public deleteOrder(oid: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/order/${oid}`);
  }
}
