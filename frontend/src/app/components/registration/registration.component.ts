import { Component, OnInit } from '@angular/core';
import {User} from "../../models/User";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user = new User();
  registrationForm: FormGroup;

  constructor(private  userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      flName: new FormControl("", [
        Validators.required,
        //Validators.pattern("[A-za-z]"),
        Validators.minLength(2),
        Validators.maxLength(40)
      ]),
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)
      ])
    })
  }

  createUser() {
    this.user={
      id: null,
      username:this.registrationForm.controls['username'].value,
      flName:this.registrationForm.controls['flName'].value,
      password:this.registrationForm.controls['password'].value,
      idRole:2,
      idStatus:2,
    }
    console.log(this.user);
    this.userService.saveUser(this.user).subscribe(user => {
        this.user = user as User;
        if(this.user!==null){
          console.log(user);
          this.router.navigate(['/']);
        }
      }
    )
    console.log(this.registrationForm);
  }

}
