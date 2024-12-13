import { Routes } from "@angular/router";

import {RegisterDcpmemberComponent} from '../components/register-dcpmember/register-dcpmember.component';
import {RegisterDonorComponent} from '../components/register-donor/register-donor.component';
import {RegisterDoctorComponent} from '../components/register-doctor/register-doctor.component';
import {WhyDonateBloodComponent} from '../components/why-donate-blood/why-donate-blood.component';
import {LogInComponent} from '../components/log-in/log-in.component';
import {RegisterComponent} from '../components/register/register.component';
import {DonorComponent} from '../../../../donor/src/app/components/donors/donor.component';

export const router: Routes = [
  {
    path: '',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LogInComponent
  },
  {
    path: 'why-donate',
    component: WhyDonateBloodComponent
  },
  {
    path: 'register-donor',
    component: RegisterDonorComponent
  },
  {
    path: 'register-dcp',
    component: RegisterDcpmemberComponent
  }, {
    path: 'register-doctor',
    component: RegisterDoctorComponent
  }
  // {
  //   path: 'donor',
  //   component: DonorComponent
  // }
];
