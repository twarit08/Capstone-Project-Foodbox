import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { HomeComponent } from './Components/home/home.component';
import { SignupComponent } from './Components/signup/signup.component';
import { UserLoginComponent } from './Components/user-login/user-login.component';
import { CustomerHomeComponent } from './Components/Customer/customer-home/customer-home.component';
import { AdminHomeComponent } from './Components/Admin/admin-home/admin-home.component';
import { authInterceptorProviders } from './Services/auth.interceptor';
import { AdminLoginComponent } from './Components/admin-login/admin-login.component';
import { AddProductComponent } from './Components/Admin/add-product/add-product.component';
import { AllProductsComponent } from './Components/Admin/all-products/all-products.component';
import { UpdateProductComponent } from './Components/Admin/update-product/update-product.component';
import { SearchProductComponent } from './Components/search-product/search-product.component';
import { GetProductComponent } from './Components/get-product/get-product.component';
import { CartDetailsComponent } from './Components/cart-details/cart-details.component';
import { CreateOrderComponent } from './Components/Customer/create-order/create-order.component';
import { OrderSummaryComponent } from './Components/Customer/order-summary/order-summary.component';
import { AllOrdersComponent } from './Components/Admin/all-orders/all-orders.component';
import { CustomerOrdersComponent } from './Components/Customer/customer-orders/customer-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SignupComponent,
    UserLoginComponent,
    CustomerHomeComponent,
    AdminHomeComponent,
    AdminLoginComponent,
    AddProductComponent,
    AllProductsComponent,
    UpdateProductComponent,
    SearchProductComponent,
    GetProductComponent,
    CartDetailsComponent,
    CreateOrderComponent,
    OrderSummaryComponent,
    AllOrdersComponent,
    CustomerOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
