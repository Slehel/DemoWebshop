import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './admin/products/products.component';
import { ShopproductComponent } from './shopproduct/shopproduct.component';

const routes: Routes = [
{ path: 'admin/products', component: ProductsComponent },
{ path: 'shop', component: ShopproductComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
