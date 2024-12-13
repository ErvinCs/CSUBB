import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { BloodRequestComponent } from './components/blood-request/blood-request.component';
import { CheckRequestStatusComponent } from './components/check-request-status/check-request-status.component';
import {RouterModule} from "@angular/router";
import {routes} from "./router/router.module";


@NgModule({
  declarations: [
    AppComponent,
    DoctorComponent,
    BloodRequestComponent,
    CheckRequestStatusComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
