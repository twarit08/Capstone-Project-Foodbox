import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './Components/admin-login/admin-login.component';
import { AdminHomeComponent } from './Components/Admin/admin-home/admin-home.component';
import { CustomerHomeComponent } from './Components/Customer/customer-home/customer-home.component';
import { HomeComponent } from './Components/home/home.component';
import { SignupComponent } from './Components/signup/signup.component';
import { UserLoginComponent } from './Components/user-login/user-login.component';
import { AdminGuard } from './Services/admin.guard';
import { CustomerGuard } from './Services/customer.guard';
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

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full', title: 'Foodbox' },
  { path: 'admin/dashboard', component: AdminHomeComponent, canActivate: [AdminGuard], pathMatch: 'full', title: 'Admin Dashboard' },
  { path: 'customer/home', component: CustomerHomeComponent, canActivate: [CustomerGuard], pathMatch: 'full', title: 'Home' },
  { path: 'customer/login', component: UserLoginComponent, pathMatch: 'full', title: 'Customer Login' },
  { path: 'admin/login', component: AdminLoginComponent, pathMatch: 'full', title: 'Admin Login' },
  { path: 'user/signup', component: SignupComponent, pathMatch: 'full', title: 'User Signup' },
  { path: 'admin/add/new-product', component: AddProductComponent, canActivate: [AdminGuard], pathMatch: 'full', title: 'Add a Food Item' },
  { path: 'admin/get/all-products', component: AllProductsComponent, canActivate: [AdminGuard], pathMatch: 'full', title: 'All Food Items' },
  { path: 'admin/update/product/:pid', component: UpdateProductComponent, canActivate: [AdminGuard], pathMatch: 'full', title: "Update Food Item" },
  { path: 'foodbox/search/product/:name', component: SearchProductComponent, pathMatch: 'full', title: 'Search Result' },
  { path: 'get/cuisine/:name', component: GetProductComponent, pathMatch: 'full', title: 'Cuisine Result' },
  { path: 'get/cart/details', component: CartDetailsComponent, pathMatch: 'full', title: 'Cart Details' },
  { path: 'customer/create/order', component: CreateOrderComponent, canActivate: [CustomerGuard], pathMatch: 'full', title: 'Create Order' },
  { path: 'order-confirmation/invoice/:oid', component: OrderSummaryComponent, canActivate: [CustomerGuard], pathMatch: 'full', title: 'Order Confirmation' },
  { path: 'order/invoice/:oid', component: OrderSummaryComponent, canActivate: [AdminGuard], pathMatch: 'full', title: 'Order Invoice' },
  { path: 'get/all-orders', component: AllOrdersComponent, canActivate: [AdminGuard], pathMatch: 'full', title: 'All Orders' },
  { path: 'get/orders/:username', component: CustomerOrdersComponent, canActivate: [CustomerGuard], pathMatch: 'full', title: 'Orders' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
