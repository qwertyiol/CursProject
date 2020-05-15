import { Pipe, PipeTransform } from '@angular/core';
import {Post} from "../models/Post";
import {UserService} from "../services/user.service";

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  constructor(private userService: UserService) {

  }

  transform(array: Post[], args?: any): Post[] {
    let result = array;
    return this.userService.enteredText && this.userService.enteredText.length != 0 ? result.filter((p)=> p.text.includes(args)):result;
  }
}
