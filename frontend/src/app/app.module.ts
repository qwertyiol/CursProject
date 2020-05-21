import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { AppComponent } from "./app.component";
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import { EntryComponent } from './components/entry/entry.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FeedComponent } from './components/feed/feed.component';
import { UserComponent } from './components/user/user.component';
import { PostComponent } from './components/post/post.component';
import { EditComponent } from './components/edit/edit.component';
import { ActivityComponent } from './components/activity/activity.component';
import { HeaderComponent } from './components/header/header.component';
import { CommentComponent } from './components/comment/comment.component';
import { LikeComponent } from './components/like/like.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { NewpostComponent } from './components/newpost/newpost.component';
import {PostService} from "./services/post.service";
import { ComplaintsforadminComponent } from './components/complaintsforadmin/complaintsforadmin.component';
import { AdminComponent } from './components/admin/admin.component';
import {ComplaintService} from "./services/complaint.service";
import {LikeService} from "./services/like.service";
import {CommentService} from "./services/comment.service";
import {SearchPipe} from "./pipes/search.pipe";
import { AuthenticationService } from "./services/authentication.service";
import { BasicAuthHtppInterceptorService } from "./interseptors/interceptor.service";

const appRoutes: Routes = [
  {path: "", component: FeedComponent},
  {path: "entry", component: EntryComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "user", component: UserComponent},
  {path: "edit", component: EditComponent},
  {path: "activity", component: ActivityComponent},
  {path: "post", component: PostComponent},
  {path: "comment", component: CommentComponent},
  {path: "like", component: LikeComponent},
  {path: "complaint", component: ComplaintComponent},
  {path: "newpost", component: NewpostComponent},
  {path: "complaintsforadmin", component: ComplaintsforadminComponent},
  {path: "admin", component: AdminComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    EntryComponent,
    RegistrationComponent,
    FeedComponent,
    UserComponent,
    PostComponent,
    EditComponent,
    ActivityComponent,
    HeaderComponent,
    CommentComponent,
    LikeComponent,
    ComplaintComponent,
    NewpostComponent,
    ComplaintsforadminComponent,
    AdminComponent,
    SearchPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule
  ],
  providers: [
    PostService,
    ComplaintService,
    LikeService,
    CommentService,
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHtppInterceptorService,
      multi: true
    } 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
