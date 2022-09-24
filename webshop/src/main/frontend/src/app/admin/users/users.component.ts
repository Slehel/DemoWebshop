import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { HttpClientService } from 'src/app/service/http-client.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: Array<User> = [];

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit() {
      this.httpClientService.getUsers().subscribe(
        response => this.handleSuccessfulResponse(response),
      );
    }

    handleSuccessfulResponse(response: any) {
      this.users = response;
    }

}
