import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { router } from './routes/index.router';

import { AppComponent } from './app.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { WhyDonateBloodComponent } from './components/why-donate-blood/why-donate-blood.component';
import { RegisterDcpmemberComponent } from './components/register-dcpmember/register-dcpmember.component';
import { RegisterDonorComponent } from './components/register-donor/register-donor.component';
import { RegisterDoctorComponent } from './components/register-doctor/register-doctor.component';


@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    WhyDonateBloodComponent,
    RegisterDcpmemberComponent,
    RegisterDonorComponent,
    RegisterDoctorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(router)
  ],
  providers: [
    {
      provide: APP_BASE_HREF,
      useValue: '/'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
