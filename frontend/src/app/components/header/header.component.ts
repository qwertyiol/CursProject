import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {Post} from "../../models/Post";
import {User} from "../../models/User";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private  userService: UserService,
              private router: Router) { }

  ngOnInit() {
  }

  logoutUser(){
    localStorage.removeItem('token');
    this.router.navigate(['/entry'])
  }
}
