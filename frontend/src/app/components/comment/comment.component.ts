import {Component, Input, OnInit} from '@angular/core';
import {Comment} from "../../models/Comment";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {AuthenticationService} from "../../services/authentication.service";
import {CommentService} from "../../services/comment.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() selectedPost: Post;
  public comments: Comment[];
  public posts: Post[];
  private subscriptions: Subscription[] = [];

  constructor(private postService: PostService,
              private AuthenticationService: AuthenticationService,
              private commentService: CommentService) { }

  ngOnInit() {
    this.loadComment();
  }

  public _deleteComment(CommentId: number): void {
    this.subscriptions.push(this.commentService.deleteComment(CommentId).subscribe(() => {
      this._updateComment();
    }));
  }

  public _updateComment(): void {
    this.loadComment();
  }

  private loadComment(): void {
    this.subscriptions.push(this.commentService.getAllComment().subscribe(comments => {
      if(this.selectedPost){
        this.comments = comments.filter((comment: Comment) => comment.idPost === this.selectedPost.id);
      } else{
        this.comments = comments;
      }
     }));
  }
}
