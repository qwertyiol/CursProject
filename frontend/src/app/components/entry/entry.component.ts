import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {User} from "../../models/User";

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {

  errorMassage:string;
  user : User;
  sub: any;
  signInForm: FormGroup;

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.signInForm = new FormGroup({
      username: new FormControl("", [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(25)
        ]
      ),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(25)
      ]),
      isRemember: new FormControl()
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signInForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  signInClick() {
    this.sub = this.authService.authenticate(this.signInForm.controls['username'].value,this.signInForm.controls['password'].value).subscribe(value => {

      if(this.authService.currUser !== null){
        this.router.navigate(['/']);
      }else{
        this.errorMassage='Incorrect data. Recheck entered data'
      }

      console.log(this.authService.currUser);

    });
  }

}
