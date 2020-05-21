import { Pipe, PipeTransform } from '@angular/core';
import {Post} from "../models/Post";
import {AuthenticationService} from "../services/authentication.service";

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  constructor(private authService: AuthenticationService) {

  }

  transform(array: Post[], args?: any): Post[] {
    let result = array;
    return this.authService.enteredText && this.authService.enteredText.length != 0 ? result.filter((p)=> p.text.includes(args)):result;
  }
}
