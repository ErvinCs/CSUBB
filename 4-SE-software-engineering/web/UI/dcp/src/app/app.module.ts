import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { DcpMemberComponent } from './components/dcp-member/dcp-member.component';
import { BloodRequestListComponent } from './components/blood-request-list/blood-request-list.component';
import { BloodStocksComponent } from './components/blood-stocks/blood-stocks.component';
import { IdleDonorsComponent } from './components/idle-donors/idle-donors.component';
import { DonorDataComponent } from './components/donor-data/donor-data.component';
import {RouterModule} from "@angular/router";
import {routes} from "./router/router.module";


@NgModule({
  declarations: [
    AppComponent,
    DcpMemberComponent,
    BloodRequestListComponent,
    BloodStocksComponent,
    IdleDonorsComponent,
    DonorDataComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
