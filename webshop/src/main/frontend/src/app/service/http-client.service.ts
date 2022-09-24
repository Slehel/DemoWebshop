import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) {
     }

     getUsers()
  {
    return this.httpClient.get<User[]>('http://localhost:8080/users/get');
  }
}