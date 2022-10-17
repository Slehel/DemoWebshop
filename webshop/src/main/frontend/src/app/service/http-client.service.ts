import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../model/Product';


@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient:HttpClient) {}
    getProducts() {
      return this.httpClient.get<Product[]>('http://localhost:8080/products/get');
      }
    addProduct(newProduct: Product) {
        return this.httpClient.post<Product>('http://localhost:8080/products/add', newProduct);
      }
    deleteProduct(id: number) {
        return this.httpClient.delete<Product>('http://localhost:8080/products/' + id);
      }
    updateProduct(updatedProduct: Product) {
        return this.httpClient.put<Product>('http://localhost:8080/products/update', updatedProduct);
      }
    sendCart(cart : Product[]) {
          return this.httpClient.post<Product[]>('http://localhost:8080/produce/message', cart);
          }
}
