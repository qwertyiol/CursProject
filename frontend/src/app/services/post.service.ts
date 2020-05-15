import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/Post";

@Injectable()
export class PostService {
  private _currPost: Post;

  get currPost(): Post {
    return this._currPost;
  }

  set currPost(value: Post) {
    this._currPost = value;
  }

  constructor(private http: HttpClient) {
  }

  getPost(): Observable<Post[]> {
    return this.http.get<Post[]>('/api/post/');
  }

  savePost(post: Post): Observable<Post> {
    return this.http.post<Post>('/api/post/', post);
  }

  deletePost(postId: number): Observable<void> {
    return this.http.delete<void>('/api/post/' + postId);
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>('/api/post/' + id);
  }

  putFileToPostByPostId(postId: number, file: File): Observable<any> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', '/api/post/{id}/image'.replace('{id}', postId.toString()), formdata, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }
}

