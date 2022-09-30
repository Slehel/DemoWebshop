import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/Product';
import { HttpClientService } from '../../service/http-client.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

products: Array<Product> = [];
selectedProduct!: Product;
  action!: string;

  constructor(private httpClientService: HttpClientService,private activedRoute: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {this.refreshData();}

  refreshData() {
  this.httpClientService.getProducts().subscribe(
        response => this.handleSuccessfulResponse(response)
      );
  this.activedRoute.queryParams.subscribe(
        (params) => {
          this.action = params['action'];
          const selectedProductId = params['id'];
                            if (selectedProductId) {
                              this.selectedProduct = this.products.find(product => product.id === +selectedProductId) as Product;
                            }
        }
      );
  }

handleSuccessfulResponse(response: any) {
    this.products = response;
  }
addProduct() {
    this.selectedProduct = new Product();
    this.router.navigate(['admin', 'products'], { queryParams: { action: 'add' } });
  }

viewProduct(id: number) {
    this.router.navigate(['admin', 'products'], { queryParams: { id, action: 'view' } });
  }
}
