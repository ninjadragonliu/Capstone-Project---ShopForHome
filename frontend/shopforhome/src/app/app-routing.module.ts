import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OutdoorsComponent } from './outdoors/outdoors.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { AdministratorInterfaceComponent } from './administrator-interface/administrator-interface.component';
import { LoginComponent } from './login/login.component';
import { CartComponent } from './components/cart/cart.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { UserComponent } from './components/user/user.component';
import { ProductComponent } from './components/product/product.component';
import { UserProductComponent } from './components/user-product/user-product.component';


const routes = [
  { path: 'admin', component: AdministratorInterfaceComponent },
  { path: '', component: HomeComponent },
  { path: 'outdoors', component: OutdoorsComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cart', component: CartComponent },
  { path: 'wishlist', component: WishlistComponent },
  { path: 'usermanagement', component: UserComponent },
  { path: 'productmanagement', component: ProductComponent },
  { path: 'products', component: UserProductComponent },
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
