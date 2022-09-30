import { Component, OnInit,Input , EventEmitter, Output } from '@angular/core';
import { Product } from '../../../model/Product';
import { HttpClientService } from '../../../service/http-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {

  @Input()
  product!: Product;

  @Output()
  productAddedEvent = new EventEmitter();

  constructor(private httpClientService: HttpClientService,
                                private router: Router) { }

  ngOnInit(): void {
  }

  addProduct() {

  if (this.product.id == null) {
        this.httpClientService.addProduct(this.product).subscribe(
          (product) => {
            this.productAddedEvent.emit();
            this.router.navigate(['admin', 'products']);
          }
        );
      } else {
      this.httpClientService.updateProduct(this.product).subscribe(
              (product) => {
                this.productAddedEvent.emit();
                this.router.navigate(['admin', 'products']);
              }
            );
          }
         }

}
