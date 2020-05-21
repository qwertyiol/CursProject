import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Post} from "../../models/Post";
import {User} from "../../models/User";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private  authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
  }

  logoutUser(){
    this.authService.logout();
    this.router.navigate(['/entry'])
  }
}
