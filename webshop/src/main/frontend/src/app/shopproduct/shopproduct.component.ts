import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import { HttpClientService } from '../service/http-client.service';
import { Product } from '../model/Product';

@Component({
  selector: 'app-shopproduct',
  templateUrl: './shopproduct.component.html',
  styleUrls: ['./shopproduct.component.css']
})
export class ShopproductComponent implements OnInit {

products: Array<Product> = [];
productsReceived: Array<Product> = [];
selectedProduct!: Product;
  action!: string;
cartProducts: any;

  constructor(private router: Router, private activedRoute: ActivatedRoute,private httpClientService: HttpClientService){
   }

  ngOnInit(): void {
  this.refreshData();
  //from localstorage retrieve the cart item
      let data = localStorage.getItem('cart');
      //if this is not null convert it to JSON else initialize it as empty
      if (data !== null) {
        this.cartProducts = JSON.parse(data);
      } else {
        this.cartProducts = [];
      }
  }

  refreshData() {
    this.httpClientService.getProducts().subscribe(
          response => this.handleSuccessfulResponse(response)
        );
    this.activedRoute.queryParams.subscribe(
          (params: any) => {
            this.action = params['action'];
            const selectedProductId = params['id'];
                              if (selectedProductId) {
                                this.selectedProduct = this.products.find(product => product.id === +selectedProductId) as Product;
                              }
          }
        );
    }

  handleSuccessfulResponse(response: any) {
  this.products = new Array<Product>();
      //get products returned by the api call
      this.productsReceived = response;
      for (const product of this.productsReceived) {

        const productwithRetrievedImageField = new Product();
        productwithRetrievedImageField.id = product.id;
        productwithRetrievedImageField.name = product.name;
        //populate retrieved image field so that product image can be displayed
        productwithRetrievedImageField.description = product.description;
        productwithRetrievedImageField.price = product.price;
        productwithRetrievedImageField.picByte = product.picByte;
        this.products.push(productwithRetrievedImageField);
      }
    }




addToCart(productId: any) {
    //retrieve product from products array using the product id
    let product = this.products.find(product => {
      return product.id === +productId;
    });
    let cartData = [];
    //retrieve cart data from localstorage
    let data = localStorage.getItem('cart');
    //parse it to json
    if (data !== null) {
      cartData = JSON.parse(data);
    }
    // add the selected product to cart data
    cartData.push(product);
    //updated the cartProducts
    this.updateCartData(cartData);
    //save the updated cart data in localstorage
    localStorage.setItem('cart', JSON.stringify(cartData));
    console. log(cartData); // Logs output to dev tools console.
    alert(cartData); // Displays output using window.alert()
  }

  updateCartData(cartData: any) {
    this.cartProducts = cartData;
  }

  buyCart() {
    this.httpClientService.sendCart(this.cartProducts);
    console. log("works!"); // Logs output to dev tools console.
    this.emptyCart();
  }

  emptyCart() {
    this.cartProducts = [];
    localStorage.clear();
  }

}
