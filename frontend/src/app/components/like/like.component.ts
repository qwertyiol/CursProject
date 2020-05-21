import { Component, OnInit } from '@angular/core';
import {Post} from "../../models/Post";
import {Subscription} from "rxjs";
import {Like} from "../../models/Like";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {LikeService} from "../../services/like.service";
import {Comment} from "../../models/Comment";

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  public likes: Like[];
  private subscriptions: Subscription[] = [];

  constructor(private AuthenticationService: AuthenticationService,
              private likeService: LikeService) { }

  ngOnInit() {
    this.loadLike();
  }

  private loadLike(): void {
    this.subscriptions.push(this.likeService.getAllLike().subscribe(likes => {
      this.likes = likes as Comment[];
      console.log(this.likes);
    }));
  }
}
