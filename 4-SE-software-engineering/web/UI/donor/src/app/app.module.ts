import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import {RouterModule} from "@angular/router";
import { FormsModule }    from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';


import { AppComponent } from './app.component';
import { DonationHistoryComponent } from './components/donation-history/donation-history.component';
import { NextDonationComponent } from './components/next-donation/next-donation.component';
import { DonorComponent} from "./components/donors/donor.component";
import { CurrentAppointmentComponent } from './components/current-appointment/current-appointment.component';
import { DonateComponent } from './components/donate/donate.component';
import {routes} from "./routes/router.module";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(routes, {enableTracing: true})
    ],
    declarations: [
        AppComponent,
        DonationHistoryComponent,
        NextDonationComponent,
        DonorComponent,
        CurrentAppointmentComponent,
        DonateComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
