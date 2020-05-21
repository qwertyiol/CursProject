import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { PostService } from "../../services/post.service";
import { Subscription } from "rxjs";
import { Post } from "../../models/Post";
import { Router } from "@angular/router";
import { flatMap, skip, switchMapTo, take } from "rxjs/operators";
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  selector: 'app-newpost',
  templateUrl: './newpost.component.html',
  styleUrls: ['./newpost.component.css']
})
export class NewpostComponent implements OnInit {

  selectedFile: File = null;

  public post: Post[];
  private subscriptions: Subscription[] = [];
  public editablePost: Post = new Post();
  constructor(private postService: PostService,
    private AuthenticationService: AuthenticationService,
    private http: HttpClient,
    private router: Router) {
  }

  ngOnInit() {
    this.editablePost.datePost = Date.now();
    this.editablePost.idUser = this.AuthenticationService.currUser.id;
  }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  public _updatePost(): void {
    this.loadPost();
    this.editablePost.idUser = this.AuthenticationService.currUser.id;
  }

  public _addPost(): void {
    this.postService.savePost(this.editablePost).subscribe((post: Post) => {
      this.postService.putFileToPostByPostId(post.id, this.selectedFile).subscribe((status) => {
        if (status == 1) {
          alert("This picture contains other stegonagraphy message!")
        } else if (status == 2) {
          alert("This picture picture from other user!!")
        } else if (status == 0) {
          this.router.navigate(['/']);
        }
      })
    });
  }

  private refreshPost(): void {
    this.editablePost = new Post();
  }

  private loadPost(): void {
    this.subscriptions.push(this.postService.getPost().subscribe(posts => {
      this.post = posts as Post[];
      console.log(this.post);
    }));
  }

}
