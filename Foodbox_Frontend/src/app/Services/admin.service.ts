import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../Models/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080';

  public addFoodItem(product: Product, image: Blob): Observable<any> {
    let formData = new FormData();
    formData.append('product', JSON.stringify(product));
    formData.append('image', image);
    return this.http.post<any>(`${this.baseUrl}/add/new-product`, formData);
  }

  public getAllFoodItems(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all-products`);
  }

  public getFoodItemByName(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/product/${name}`);
  }

  public getFoodByCuisineType(cuisineType: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/cuisine-type/${cuisineType}`);
  }

  public getFoodItemById(pid: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get/product/id/${pid}`);
  }

  public updateFoodItem(pid: number, product: Product): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/update/product/${pid}`, product);
  }

  public deleteFoodItem(pid: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/product/id/${pid}`);
  }

  public setAvailability(pid: number, product: Product): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/set-availability/product/${pid}`, product);
  }
}
